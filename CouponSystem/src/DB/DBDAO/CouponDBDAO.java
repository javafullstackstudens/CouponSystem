package DB.DBDAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import javax.management.Query;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.derby.client.net.Typdef;
import org.apache.derby.impl.sql.execute.UpdateConstantAction;

import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import DB.DBException;
import DB.DAO.CouponDAO;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import Main.Utils;
import jdk.nashorn.internal.ir.WhileNode;
import sun.net.ftp.FtpDirEntry.Type;
import java.util.*;

public class CouponDBDAO implements CouponDAO {

	/**
	 * This class implements basic methods between the application level to the DB
	 * such as C.R.U.D. the logic of the program doesn't implement in this level.
	 * this level is the only connection to the SQL database,this level uses a
	 * connection pool as a data access pattern Used prepared statements to prevent
	 * SQL injection createCoupon removeCoupon updateCoupon getCoupon getAllCoupouns
	 * getCouponByType createCoupon login
	 * 
	 * @throws DBException
	 */

	/*****************************************
	 * Attributes
	 *********************************************/

	private Connection conn;
	private Coupon couponLocal;
	private long id;

	/********************************************
	 * CTOR
	 ************************************************/
	public CouponDBDAO() {
		// TODO Auto-generated constructor stub
	}

	/********************************************
	 * Methods
	 *********************************************/
	@Override
	// **This method remove an company by ID key **//
	public void removeCoupon(Coupon coupon) throws DBException {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = getAllCoupouns();
		Iterator<Coupon> itr = allCoupons.iterator();

		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e2) {
			throw new DBException("The Connection is faild");
		}
		// Define the Execute query
		String sql = "DELETE FROM COUPON WHERE id=?";
		PreparedStatement pstmt = null;

		try {
			while (itr.hasNext()) {
				Coupon coupon2 = new Coupon();
				coupon2 = itr.next();
				if (coupon2.getTitle().equals(coupon.getTitle())) {
					pstmt = conn.prepareStatement(sql);
					conn.setAutoCommit(false);// turn off auto-commit
					pstmt.setLong(1, coupon2.getId()); // Sets the designated parameter to the given Java long value
					pstmt.executeUpdate();// Execute the query and update
					conn.commit();// Commit the changes,If there is no error.
				}
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
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
		System.out.println(coupon.getTitle() + " successfully Removed from the DB");
	}

	public void removeCustomerCoupon(Coupon coupon) throws DBException {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = getAllCoupouns();
		Iterator<Coupon> itr = allCoupons.iterator();

		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e2) {
			throw new DBException("The Connection is faild");
		}

		String sql = "DELETE FROM CUSTOMER_COUPON WHERE COUPON_ID=?";
		PreparedStatement pstmt = null;

		try {
			while (itr.hasNext()) {

				Coupon coupon2 = new Coupon();
				coupon2 = itr.next();
				if (coupon2.getTitle().equals(coupon.getTitle())) {
					pstmt = conn.prepareStatement(sql);
					conn.setAutoCommit(false);// turn off auto-commit
					id = coupon2.getId();
					pstmt.setLong(1, id);
					pstmt.executeUpdate();
					conn.commit();
					System.out.println(coupon2);
					break;
				}
			}

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

	public void removeCompanyCoupon(Coupon coupon) throws DBException {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = getAllCoupouns();
		Iterator<Coupon> itr = allCoupons.iterator();

		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e2) {
			throw new DBException("The Connection is faild");
		}

		String sql = "DELETE FROM COMPANY_COUPON WHERE COUPON_ID=?";
		PreparedStatement pstmt = null;

		try {
			// Remove all the company coupons from the join table
			while (itr.hasNext()) {

				Coupon coupon2 = new Coupon();
				coupon2 = itr.next();
				if (coupon2.getTitle().equals(coupon.getTitle())) {
					pstmt = conn.prepareStatement(sql);
					conn.setAutoCommit(false);// turn off auto-commit
					pstmt.setLong(1, coupon2.getId());
					pstmt.executeUpdate();
					conn.commit();
				}
			}

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
	public void updateCoupon(Coupon coupon) throws DBException {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = getAllCoupouns();
		Iterator<Coupon> itr = allCoupons.iterator();

		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
		// create the Execute query
		PreparedStatement pstms = null;
		String sqlString = "UPDATE COUPON SET START_DATE = ?, END_DATE = ? WHERE ID = ? ";

		try {
			// create PreparedStatement and build the SQL query
			while (itr.hasNext()) {

				Coupon coupon2 = new Coupon();
				coupon2 = itr.next();
				
				if (coupon2.getTitle().equals(coupon.getTitle())) {
					pstms = conn.prepareStatement(sqlString);
					pstms.setDate(1, (Date) coupon.getStartDate());
					pstms.setDate(2, (Date) coupon.getEndDate());
					pstms.setLong(3, coupon2.getId());
					pstms.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new DBException("update coupon failed with id =" + coupon.getId());
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
		System.out.println(coupon.getTitle() + " successfully Updated");

	}

	@Override
	public Coupon getCoupon(long id) throws DBException {
		Coupon coupon = new Coupon();

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
		return coupon;
	}

	@Override
	public synchronized Set<Coupon> getAllCoupouns() throws DBException {

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

	public void printAllCoupons() throws DBException {

		Coupon coupon = new Coupon();

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
	}

	@Override
	public synchronized Set<Coupon> getCouponByType(Coupon coupon) throws DBException {

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
			String sql = "SELECT * FROM COUPON";
			// Set the results from the database
			ResultSet resultSet = stmt.executeQuery(sql);
			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {
				CouponType type = CouponType.valueOf(resultSet.getString(6));
				// Check the type of the Coupon
				if (coupon.getType().equals(type)) {

					Coupon coupon1 = new Coupon();
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
					coupons.add(coupon1);

				}

			}

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

		return coupons;
	}

	public void createCoupon(Coupon coupon, long comp_id) throws DBException {

		this.id = comp_id;
		long id_inc = 0;
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}

		// Define the Execute query
		String sql = "INSERT INTO COUPON (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE)  VALUES(?,?,?,?,?,?,?,?)";
		String sql2 = " INSERT INTO COMPANY_COUPON (COMP_ID,COUPON_ID) VALUES(?,?)";
		String sql3 = "SELECT * FROM COUPON";
		// Set the results from the database
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		java.sql.Statement stmt = null;
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
			// Insert the new coupon to join table COMPANY_COUPON
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql3);
			while (resultSet.next()) {
				id_inc = resultSet.getLong(1);
			}
			// constructor the object, retrieve the attributes from the results
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setLong(1, comp_id);
			pstmt2.setLong(2, id_inc);
			pstmt2.executeUpdate();

		} catch (SQLException e) {
			// Handle errors for JDBC
			throw new DBException("Coupon creation failed");
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
	public void createCoupon(Coupon coupon) throws DBException {
		// TODO Auto-generated method stub
		// Open a connection
		try {
			conn = DriverManager.getConnection(Utils.getDBUrl());
		} catch (SQLException e1) {
			throw new DBException("The Connection is faild");
		}
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
			throw new DBException("Coupon creation failed");
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
