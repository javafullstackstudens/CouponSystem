package DB.DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;
import DB.DAO.CouponDAO;
import JavaBeans.Coupon;
import Main.Utils;

public class CouponDBDAO implements CouponDAO {
	
	
	//Attributes
	
	Connection conn;
	
	
	
	// Methods that DBDAO Must use from DAO

	@Override
	public void insertCoupon(Coupon coupon) throws Exception {
		
		conn = DriverManager.getConnection(Utils.getDBUrl());
		String sql = "INSERT INTO COUPON (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE)  VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, (Date) coupon.getStartDate());
			pstmt.setDate(3, (Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5,coupon.getType().name()); //**.name() casting the ENUM to String 
			pstmt.setString(6,coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			pstmt.executeUpdate();
			System.out.println("Coupon " + coupon.getTitle() + " inserted successfully");
		} catch (SQLException e) {
			throw new Exception("Coupon creation failed");
		} finally {
			conn.close();
		}
	}

	
	
	@Override
	public void removeCoupon(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub
		conn = DriverManager.getConnection(Utils.getDBUrl());
		String sql = "DELETE FROM COMPANY WHERE id=?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			pstmt.setLong(1, coupon.getId()); //Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();
			conn.commit();
			System.out.println(coupon.getTitle()+" successfully Removed from the DB");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new Exception(e1.getMessage());
			}
		} finally {
			conn.close();
		}
		
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
	
	
	

}
