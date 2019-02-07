package DB.DBDAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import Main.*;
import DB.ConnPool;
import DB.DAO.CompanyDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;


public class CompanyDBDAO implements CompanyDAO {

	Company company = new Company(); 
	
	// Attributes
	
	static Connection conn;
	
	ConnPool Pool = ConnPool.getInstance(); 
	
	// Methods that DBDAO Must use from DAO
	
		@Override
		
	public void createCompany(Company company) throws Exception {
		 //Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		//Define the Execute query
		String sql = "INSERT INTO COMPANY (COMP_NAME,PASSWORD,EMAIL)  VALUES(?,?,?)";
		PreparedStatement pstmt = null;
		try {
		    pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//Handle errors for JDBC
			throw new Exception("Company creation failed");
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}

		}
		System.out.println("Company " + company.getCompName() + " inserted successfully");
		
	}
	
	@Override
	//**This method remove an company by ID key  **//
	public void removeCompany(Company company) throws Exception {
		

	    //Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		//Define the Execute query
		String sql = "DELETE FROM COMPANY WHERE id=?";
		PreparedStatement pstmt = null;
		try {
		    pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);//turn off auto-commit
			pstmt.setLong(1, company.getId()); //Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();
			conn.commit();//Commit the changes,If there is no error.
			System.out.println(company.getCompName()+" successfully Removed from the DB");
		} catch (SQLException e) {
			try {
				conn.rollback();//roll back updates to the database , If there is error 
			} catch (SQLException e1) {
				throw new Exception(e1.getMessage());
			}
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}

		}
		
	}

	@Override
	public void updateCompany(Company company) throws Exception {
		// create statment 
		conn = DriverManager.getConnection(Utils.getDBUrl()); 
		java.sql.Statement stmt = null ; 
		
		try {
			stmt = conn.createStatement(); 
			String sql = "UPDATE COMPANY " + "SET COMP_NAME='" + company.getCompName() + "', PASSWORD = '" + company.getPassword() +"', EMAIL = '" + company.getEmail() + "' WHERE ID=" + company.getId();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			throw new Exception("update customer failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}

		}
		System.out.println(company.getCompName() + " successfully Updated from the DB");
		
		
	}
	
	@Override
	public Company getCompany(long id) throws Exception {
		
		Company company = new Company(); 
		//open a Connection to the db 
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;
		
		try {
			stmt=conn.createStatement(); 
			//build The SQL query 
			String sql = "SELECT * FROM COMPANY WHERE ID=" +id; 
			//Set the results from the database 
			ResultSet resultSet = stmt.executeQuery(sql); 
			//constructor the object, retrieve the attributes from the results
			resultSet.next(); 
			company.setId(resultSet.getLong(1));;
			company.setCompName(resultSet.getString(2));
			company.setPassword(resultSet.getString(3));
			company.setEmail(resultSet.getString(4));
			
			//TODO - Add the coupons list from the ArrayCollection 
			
		} catch (SQLException e) {
			throw new Exception("get company failed with id=" + id);
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}

		}
		
		return company;
	}

	@Override
	public Set<Company> getAllCompanies() throws Exception {
		
		Set<Company> companies = new HashSet<Company>(); 
		
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;
		
		try {
			stmt = conn.createStatement(); 
			// build The SQL query
			String sql = "SELECT * FROM COMPANY"; 
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			while (resultSet.next()) {
				Company company = new Company(); 
				company.setId(resultSet.getLong(1));;
				company.setCompName(resultSet.getString(2));
				company.setPassword(resultSet.getString(3));
				company.setEmail(resultSet.getString(4));

				companies.add(company);
			}	
			
		} catch (SQLException e) {
			throw new Exception("Retriev all the coupons failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				// do nothing
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		
		return null;
	}


	@Override
	public Set<Coupon> getCoupons() throws Exception {

		Coupon coupon = new Coupon();
		Set<Coupon> coupons = new HashSet<Coupon>();

		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COMPANY";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			while (resultSet.next()) {

				coupon.setId(resultSet.getLong(1));
				coupon.setTitle(resultSet.getString(2));
				coupon.setStartDate((Date) resultSet.getDate(3));
				coupon.setEndDate((Date) resultSet.getDate(4));
				coupon.setAmount(resultSet.getInt(5));
				CouponType type = CouponType.valueOf(resultSet.getString(6)); // Convert String to Enum
				coupon.setType(type);
				coupon.setMessage(resultSet.getString(7));
				coupon.setPrice(resultSet.getDouble(8));
				coupon.setImage(resultSet.getString(9));

				coupons.add(coupon);

			}

		} catch (SQLException e) {
			throw new Exception("Retriev all the coupons failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				// do nothing
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return coupons;
	}

	
	public void printAllCompmies() throws Exception{ 
		
		
		Company company = new Company(); 
		
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;
		
		try {
			stmt = conn.createStatement(); 
			// build The SQL query
			String sql = "SELECT * FROM COMPANY"; 
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			while (resultSet.next()) {
				
				company.setId(resultSet.getLong(1));;
				company.setCompName(resultSet.getString(2));
				company.setPassword(resultSet.getString(3));
				company.setEmail(resultSet.getString(4));

				System.out.println(company);
			}	
			
		} catch (SQLException e) {
			throw new Exception("Retriev all the coupons failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				// do nothing
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		
		
	}
	@Override
	public Boolean login(String compName, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	

	

}
