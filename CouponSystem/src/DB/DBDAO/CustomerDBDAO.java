package DB.DBDAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.org.apache.bcel.internal.generic.I2D;
import com.sun.webkit.ThemeClient;
import com.sun.xml.internal.bind.v2.TODO;

import DB.DBException;
import DB.DAO.CustomerDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;
import Main.Utils;
import sun.security.action.GetBooleanAction;

public class CustomerDBDAO implements CustomerDAO {

	/**
	 * This class implements basic methods between the application level to the DB such as C.R.U.D.
	 * the logic of the program doesn't implement in this level. 
	 * this level is the only connection to the SQL database,this level uses a connection pool as a data access pattern 
	 * createCustomer
	 * removeCustomer 
	 * updateCustomer
	 * getCustomer
	 * getAllCustomer
	 * getCouponByType
	 * getCoupons
	 * @throws DBException
	 * login 
	 */
	
	/*****************************************Attributes********************************************/ 

	Connection conn;

	/********************************************CTOR***********************************************/ 
	
	public CustomerDBDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/*******************************************Methods*********************************************/ 
	@Override
	public void createCustomer(Customer customer) throws DBException{
		// TODO Auto-generated method stub
		// Open a connection
				try {
					conn = DriverManager.getConnection(Utils.getDBUrl());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					throw new DBException("The Connection is faild");
				}
				// Define the Execute query
				String sql = "INSERT INTO CUSTOMER (CUST_NAME,PASSWORD) VALUES (?,?)";
				PreparedStatement pstmt = null;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, customer.getCustomerName());
					pstmt.setString(2, customer.getPassword());
					pstmt.executeUpdate();

				} catch (SQLException e) {
					// Handle errors for JDBC
					throw new DBException("Customer creation faild");
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
				
				System.out.println("Customer " + customer.getCustomerName() + " inserted successfully");
		
	}
	
	@Override
	public void removeCustomer(Customer customer) throws DBException {
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		String sql = "DELETE FROM CUSTOMER WHERE id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);// turn off auto-commit
			pstmt.setLong(1, customer.getId()); // Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();
			conn.commit();// Commit the changes,If there is no error.
		} catch (SQLException e) {
			try {
				conn.rollback();// roll back updates to the database , If there is error
			} catch (SQLException e1) {
				throw new DBException("The Rollback connection failed");
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
		System.out.println(customer.getCustomerName() + " successfully Removed from the DB");
	}

	@Override
	public void updateCustomer(Customer customer) throws DBException {
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		// create the Execute query
				PreparedStatement pstms = null; 
				String sqlString = "UPDATE CUSTOMER SET CUST_NAME= ?, PASSWORD = ? WHERE ID = ? "; 
		try {
			// create PreparedStatement and build the SQL query
			pstms = conn.prepareStatement(sqlString);
			pstms.setString(1, customer.getCustomerName());
			pstms.setString(2, customer.getPassword());
			pstms.setLong(3, customer.getId());
			

			pstms.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("update customer failed");
		} finally {
			// finally block used to close resources
			try {
				if (pstms != null)
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
		System.out.println(customer.getCustomerName() + " successfully Updated");
	}

	public Customer getCustomer(long id) throws DBException {
		
		Customer customer = new Customer(); 
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		java.sql.Statement stmt = null;
		
		try {
			stmt =conn.createStatement(); 
			//build The SQL query
			String sql = "SELECT * FROM CUSTOMER WHERE ID=" + id;
			//Set the results from the database 
			ResultSet resultSet = stmt.executeQuery(sql); 
			//constructor the object, retrieve the attributes from the results 
			resultSet.next(); 
			customer.setId(resultSet.getLong(1));
			customer.setCustomerName(resultSet.getString(2)); 
			customer.setPassword(resultSet.getString(3));
			//TODO - Add the coupons list from the ArrayCollection 
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
		return customer ;
	}

	@Override
	public Set<Coupon> getCoupons() throws DBException {
		Set<Coupon> coupons = new HashSet<Coupon>();

		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COUPON";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			while (resultSet.next()) {
				Coupon coupon = new Coupon();
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

	@Override
	public Boolean login(String ccustName, String password) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> getAllCustomer() throws DBException {
		// TODO Auto-generated method stub
		
		Set<Customer> customers = new HashSet<Customer>(); 
		
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		java.sql.Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM CUSTOMER";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			while (resultSet.next()) {
				Customer customer = new  Customer(); 
				customer.setId(resultSet.getLong(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setPassword(resultSet.getString(3));
				
				customers.add(customer); 
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
		return customers;
		
	}
	
	public void printAllCustmers() throws DBException { 

		// TODO Auto-generated method stub
		Customer customer = new  Customer(); 
				
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		java.sql.Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM CUSTOMER";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			while (resultSet.next()) {
				
				customer.setId(resultSet.getLong(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setPassword(resultSet.getString(3));
				
				System.out.println(customer); 
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
	
	public Customer getCustomer(String CUST_NAME) throws DBException 
	{ 

		Customer customer = new Customer(); 
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		java.sql.Statement stmt = null;
		
		try {
			
			stmt =conn.createStatement(); 
			String sql = "SELECT * FROM CUSTOMER";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);

			while( resultSet.next()) { 
				if(resultSet.getString(2).equals(CUST_NAME))
				{
					customer.setId(resultSet.getLong(1));
					customer.setCustomerName(resultSet.getString(2));
					customer.setPassword(resultSet.getString(3));
					break; 
				}

			}

		} catch (SQLException e) {
			  System.out.println(e.getMessage());
			throw new DBException("get customer failed");
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
	return customer; 
	}
	
	public void purchaseCoupon(Coupon coupon, Customer customer ) throws DBException {
		
		long id_inc = 0; 
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBException("The Connection is faild");
		}
		System.out.println(coupon);
		System.out.println(customer);
		String sql1 = "SELECT * FROM COUPON";
		String sql2 = " INSERT INTO CUSTOMER_COUPON(CUST_ID,COUPON_ID) VALUES(?,?)"; 
		// Set the results from the database
		PreparedStatement pstmt = null;
		java.sql.Statement stmt= null;
		try {

			 //Insert the new coupon to join table COMPANY_COUPON 
			stmt = conn.createStatement();  
			ResultSet resultSet = stmt.executeQuery(sql1); 
			while (resultSet.next()) {
				if(coupon.getTitle().equals(resultSet.getString(2)))
				{
					id_inc = resultSet.getLong(1); 
				}		
			}
			
			// constructor the object, retrieve the attributes from the results
			pstmt = conn.prepareStatement(sql2); 
			pstmt.setLong(1,customer.getId());
            pstmt.setLong(2,id_inc);
            pstmt.executeUpdate(); 
            
		} catch (SQLException e) {
			// Handle errors for JDBC
			throw new DBException("Purchased Coupon failed");
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
		System.out.println("Coupon " + coupon.getTitle() + " inserted successfully");

		
	}

}
