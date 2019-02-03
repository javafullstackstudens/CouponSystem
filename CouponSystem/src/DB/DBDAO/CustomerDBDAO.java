package DB.DBDAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.I2D;
import com.sun.xml.internal.bind.v2.TODO;

import DB.DAO.CustomerDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import Main.Utils;
import sun.security.action.GetBooleanAction;

public class CustomerDBDAO implements CustomerDAO {

	// Attributes

	Connection conn;

	// Methods that DBDAO Must use from DAO
	
	@Override
	public void createCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertCustomer(Customer customer) throws Exception {
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
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
			throw new Exception("Customer creation faild");
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
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
		System.out.println("Customer " + customer.getCustomerName() + " inserted successfully");
	}

	@Override
	public void removeCustomer(Customer customer) throws Exception {
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
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
				throw new Exception("The Rollback connection failed");
			}
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
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
		System.out.println(customer.getCustomerName() + " successfully Removed from the DB");
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;
		try {
			//create statement
			stmt = conn.createStatement();
			//build The SQL query
			String sql = "UPDATE CUSTOMER " + "SET CUST_NAME='" + customer.getCustomerName() + "', PASSWORD = '" + customer.getPassword() + "' WHERE ID=" + customer.getId();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new Exception("update customer failed");
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
		System.out.println(customer.getCustomerName() + " successfully Updated from the DB");
	}

	@Override
	public Customer getCustomer(long id) throws Exception {
		
		Customer customer = new Customer(); 
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
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
			throw new Exception("update customer failed");
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
		return customer ;
	}

	@Override
	public Set<Company> getAllCustomer() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Coupon> getCoupons() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(String compName, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
