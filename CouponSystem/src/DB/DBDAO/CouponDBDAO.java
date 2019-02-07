package DB.DBDAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.sql.ResultSet;
import javax.management.Query;

import org.apache.derby.client.net.Typdef;

import DB.DAO.CouponDAO;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import Main.Utils;
import jdk.nashorn.internal.ir.WhileNode;
import sun.net.ftp.FtpDirEntry.Type;

public class CouponDBDAO implements CouponDAO {

	// Attributes

	Connection conn;
	private Coupon coupon;
	private long id;

	// Methods that DBDAO Must use from DAO

	// **This method remove an company by ID key **//

	@Override
	// **This method remove an company by ID key **//
	public void removeCoupon(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		String sql = "DELETE FROM COUPON WHERE id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);// turn off auto-commit
			pstmt.setLong(1, coupon.getId()); // Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();// Execute the query and update
			conn.commit();// Commit the changes,If there is no error.

		} catch (SQLException e) {
			try {
				conn.rollback();
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
		System.out.println(coupon.getTitle() + " successfully Removed from the DB");
	}

	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		// create statment
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// create the Execute query
		java.sql.Statement stmt = null;

		try {
			// create statement and build the SQL query
			stmt = conn.createStatement();
			String sql = "UPDATE COUPON " + "SET TITLE ='" + coupon.getTitle() + "', START_DATE= '"
					+ (Date) coupon.getStartDate() + "', END_DATE= '" + (Date) coupon.getEndDate() + "', AMOUNT= "
					+ coupon.getAmount() + ", TYPE= '" + coupon.getType() + "', MESSAGE= '" + coupon.getMessage()
					+ "', PRICE = " + coupon.getPrice() + ", IMAGE = '" + coupon.getImage() + "'WHERE ID= "
					+ coupon.getId();

			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new Exception("update coupon failed with id =" + coupon.getId());
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
		System.out.println(coupon.getTitle() + " successfully Updated from the DB");

	}

	@Override
	public Coupon getCoupon(long id) throws Exception {
		Coupon coupon = new Coupon();

		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COUPON WHERE ID=" + id;
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results
			resultSet.next();
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

			// TODO - Add the coupons list from the ArrayCollection
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
		return coupon;
	}

	@Override
	public Set<Coupon> getAllCoupouns() throws Exception {

		Coupon coupon = new Coupon();
		Set<Coupon> coupons = new HashSet<Coupon>();

		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COUPON";
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

	public void printAllCoupons() throws Exception {

		Coupon coupon = new Coupon();
		Set<Coupon> coupons = new HashSet<Coupon>();

		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COUPON";
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

				System.out.println(coupon);

			}

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
	}

	@Override
	public Set<Coupon> getCouponByType(Coupon coupon) throws Exception {

		Coupon coupon1 = new Coupon();
		Set<Coupon> coupons = new HashSet<Coupon>();
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		java.sql.Statement stmt = null;

		try {
			stmt = conn.createStatement();
			// build The SQL query
			String sql = "SELECT * FROM COUPON";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {
				CouponType type = CouponType.valueOf(resultSet.getString(6));
				// Check the type of the Coupon
				if (coupon.getType().equals(type)) {

					coupon1.setId(resultSet.getLong(1));
					coupon1.setTitle(resultSet.getString(2));
					coupon1.setStartDate((Date) resultSet.getDate(3));
					coupon1.setEndDate((Date) resultSet.getDate(4));
					coupon1.setAmount(resultSet.getInt(5));
					CouponType type2 = CouponType.valueOf(resultSet.getString(6)); // Convert String to Enum
					coupon1.setType(type2);
					coupon1.setMessage(resultSet.getString(7));
					coupon1.setPrice(resultSet.getDouble(8));
					coupon1.setImage(resultSet.getString(9));
					System.out.println(coupon);
					coupons.add(coupon);

				}

			}

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

		return coupons;
	}
	
	public void createCoupon(Coupon coupon, long comp_id) throws Exception {
		
		this.id = comp_id;
		long id_inc = 0; 
		// TODO Auto-generated method stub
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		
		// Define the Execute query
		String sql = "INSERT INTO COUPON (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE)  VALUES(?,?,?,?,?,?,?,?)";
	    String sql2 = " INSERT INTO COMPANY_COUPON (COMP_ID,COUPON_ID) VALUES(?,?)"; 
		String sql3 = "SELECT * FROM COUPON";
		// Set the results from the database
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null; 
		java.sql.Statement stmt= null; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, (Date) coupon.getStartDate());
			pstmt.setDate(3, (Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name()); // **.name() casting the ENUM to String
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			// Execute the query and update
			pstmt.executeUpdate();
			 //Insert the new coupon to join table COMPANY_COUPON 
			stmt = conn.createStatement();  
			ResultSet resultSet = stmt.executeQuery(sql3); 
			while (resultSet.next()) {
				id_inc = resultSet.getLong(1); 	
			}
			// constructor the object, retrieve the attributes from the results
			pstmt2 = conn.prepareStatement(sql2); 
			pstmt2.setLong(1,comp_id );
            pstmt2.setLong(2,id_inc);
            pstmt2.executeUpdate(); 
            
		} catch (SQLException e) {
			// Handle errors for JDBC
			throw new Exception("Coupon creation failed");
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
		System.out.println("Coupon " + coupon.getTitle() + " inserted successfully");

		
	}

	@Override
	public void createCoupon(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub
		// Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		// Define the Execute query
		String sql = "INSERT INTO COUPON (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE)  VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, (Date) coupon.getStartDate());
			pstmt.setDate(3, (Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name()); // **.name() casting the ENUM to String
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			// Execute the query and update
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// Handle errors for JDBC
			throw new Exception("Coupon creation failed");
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
		System.out.println("Coupon " + coupon.getTitle() + " inserted successfully");

	
		
	}

}
