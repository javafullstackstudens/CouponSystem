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
import DB.DBException;
import DB.DAO.CompanyDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class CompanyDBDAO implements CompanyDAO {

	/**
	 * This class implement basic methods between the application level to the DB
	 * such as C.R.U.D. the logic of the program dosen't implement in this level.
	 * this level is the only connection to the SQL database,this level uses a
	 * connection pool as a data access pattern It Contains : createCompany
	 * removeComapny updateComapny getCompany getAllCompanies getCoupons
	 * printAllCompmies getCompany
	 */

	/**************************************
	 * Attributes
	 *******************************************/
	static Connection conn;
	private Company company;

	/******************************************
	 * CTOR
	 *********************************************/

	public CompanyDBDAO() {
		// TODO Auto-generated constructor stub
	}

	/*****************************************
	 * Methods
	 * 
	 * @throws SQLException
	 *******************************************/
	@Override

	public void createCompany(Company company) throws DBException {
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		// Define the Execute query
		String sql = "INSERT INTO COMPANY (COMP_NAME,PASSWORD,EMAIL)  VALUES(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// Handle errors for JDBC
			throw new DBException("Company creation failed");
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException e) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DBException("The close connection action faild");
			}

		}
		System.out.println("Company " + company.getCompName()); 
	}


	public void removeCompany(Company company) throws DBException {

		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e2) {
			throw new DBException("The Connection is faild");
		}
		// Define the Execute query
		String sql = "DELETE FROM COMPANY WHERE id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);// turn off auto-commit
			pstmt.setLong(1, company.getId()); // Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();
			conn.commit();// Commit the changes,If there is no error.
			System.out.println(company.getCompName() + " successfully Removed from the DB");
		} catch (SQLException e) {
			try {
				conn.rollback();// roll back updates to the database , If there is error
			} catch (SQLException e1) {
				throw new DBException(e1.getMessage());
			}
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}

	}

	@Override
	public void updateCompany(Company company) throws DBException {
		// create statment
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			String sql = "UPDATE COMPANY " + "SET COMP_NAME='" + company.getCompName() + "', PASSWORD = '"
					+ company.getPassword() + "', EMAIL = '" + company.getEmail() + "' WHERE ID=" + company.getId();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new DBException("update customer failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}
		System.out.println(company.getCompName() + " successfully Updated from the DB");

	}

	@Override
	public Company getCompany(long id) throws DBException {

		Company company = new Company();
		// open a Connection to the db
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
		// Define the Execute query
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COMPANY WHERE ID=" + id;
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			resultSet.next();
			company.setId(resultSet.getLong(1));
			;
			company.setCompName(resultSet.getString(2));
			company.setPassword(resultSet.getString(3));
			company.setEmail(resultSet.getString(4));

			// TODO - Add the coupons list from the ArrayCollection

		} catch (SQLException e) {
			throw new DBException("get company failed with id=" + id);
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}

		return company;
	}

	@Override
	public synchronized Set<Company> getAllCompanies() throws DBException {

		Set<Company> companies = new HashSet<Company>();

		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
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
				company.setId(resultSet.getLong(1));
				;
				company.setCompName(resultSet.getString(2));
				company.setPassword(resultSet.getString(3));
				company.setEmail(resultSet.getString(4));

				companies.add(company);
			}

		} catch (SQLException e) {
			throw new DBException("Retriev all the coupons failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}

		return null;
	}

	@Override
	public synchronized Set<Coupon> getCoupons() throws DBException {

		Coupon coupon = new Coupon();
		Set<Coupon> coupons = new HashSet<Coupon>();

		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
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
			throw new DBException("Retriev all the coupons failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}
		return coupons;
	}

	public void printAllCompmies() throws DBException {

		Company company = new Company();

		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
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

				company.setId(resultSet.getLong(1));
				;
				company.setCompName(resultSet.getString(2));
				company.setPassword(resultSet.getString(3));
				company.setEmail(resultSet.getString(4));

				System.out.println(company);
			}

		} catch (SQLException e) {
			throw new DBException("Retriev all the coupons failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}

	}

	@Override
	public Boolean login(String compName, String password) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/***************************
	 * Return company object by company name
	 *********************/
	public Company getCompany(String comp_name) throws DBException {
		// Open a connection
		Company company = new Company();
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
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
				if (resultSet.getString(2).equals(comp_name)) {

					company.setId(resultSet.getLong(1));
					company.setCompName(resultSet.getString(2));
					company.setPassword(resultSet.getString(3));
					company.setEmail(resultSet.getString(4));
				}

			}
		} catch (SQLException e) {
			// Handle errors for JDBC
			throw new DBException("get Company from Database failed");
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new DBException("The close connection action faild");
			}

		}
		return company;

	}

}
