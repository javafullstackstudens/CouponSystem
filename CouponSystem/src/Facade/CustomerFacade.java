package Facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.derby.iapi.error.PassThroughException;

import DB.DBDAO.CustomerDBDAO;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;
import Main.Utils;

public class CustomerFacade implements CouponClientFacade {

	Customer customer = new Customer(); 
	String CUST_NAME = null; 
	String PASS = null; 
	Connection conn; 
	CustomerDBDAO  customerDBDAO = new CustomerDBDAO(); 
	
	
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		// TODO Auto-generated method stub
		this.CUST_NAME = name; 
		this.PASS = password;
		return null; 
	}

	
	public CustomerFacade() 
	{
	//TODO//	
	}
	
	public void getCustomer() throws Exception 
	{ 

		conn = DriverManager.getConnection(Utils.getDBUrl());

		// Define the Execute query
		java.sql.Statement stmt = null;
		
		try {
			stmt =conn.createStatement(); 
			String sql = "SELECT * FROM CUSTOMER"; 
			ResultSet resultSet = stmt.executeQuery(sql); 
			while( resultSet.next()) { 
				if(resultSet.getString(2).equals(CUST_NAME))
				{
					customer.setId(resultSet.getLong(1));
					customer.setCustomerName(resultSet.getString(2));
					customer.setPassword(resultSet.getString(3));
				}
				
				
			}

		} catch (SQLException e) {
			throw new Exception("get customer failed");
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
	
	public void purchaseCoupon(Coupon coupon) throws Exception{
		
		getCustomer();
		customerDBDAO.purchaseCoupon(coupon, customer);
	} 
	
	public Set<Coupon> getAllPurchasedCoupons()throws Exception {
		return null;
		
	}
	
	public Set<Coupon> getAllPurchasedCouponsByType(CouponType cType)throws Exception{
		
		return null; 
	}
	
	public Set<Coupon> getAllPurchasedCouponsByPrice(double price)throws Exception{ 
		
		return null; 
	}

}
