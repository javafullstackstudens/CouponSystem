package DB.DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import javax.management.Query;

import DB.DAO.CouponDAO;
import JavaBeans.Coupon;
import Main.Utils;

public class CouponDBDAO implements CouponDAO {

	// Attributes

	Connection conn;

	// Methods that DBDAO Must use from DAO

	@Override
	public void createCoupon(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	// **This method remove an company by ID key **//
	public void insertCoupon(Coupon coupon) throws Exception {

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
		// TODO Auto-generated method stub

	}

	@Override
	public Coupon getCoupon(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Coupon> getAllCoupouns() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<Coupon> getCouponByType(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
