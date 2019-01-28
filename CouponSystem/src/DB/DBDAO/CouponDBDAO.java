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
public void insertCoupon(Coupon coupon) throws Exception{
		conn = DriverManager.getConnection(Utils.getDBUrl());
		String sql = "INSERT INTO COUPON (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE) VALUES(?,?,?,?,?,?,?,?)";
        try{
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	LocalDate localDate = LocalDate.now();
        	Date date = java.sql.Date.valueOf(localDate);
        
        	pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, date);
			pstmt.setDate(3, date);
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name());	
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());	
		    pstmt.executeUpdate();
			
        }catch(SQLException e) {
        	throw new Exception("Coupon creation faild");
        } finally {
        	conn.close();
        }
	}

	
	
	@Override
	public void removeCoupon(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub
		
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
