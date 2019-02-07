package Facade;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import DB.ConnPool;
import DB.DBDAO.CompanyDBDAO;
import DB.DBDAO.CouponDBDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import Main.Utils;

public class CompanyFacade implements CouponClientFacade {
	
	Company c = new Company(); 
    Connection conn; 
    long ID_comp ; 
    String COMP_NAME = null; 
    String pass = null; 
    
	CouponDBDAO couponDBDAO = new CouponDBDAO(); 
    
	
	public void  getCompany () throws Exception { 
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
					while(resultSet.next()) { 
						if(resultSet.getString(2).equals(COMP_NAME)) { 
						
							c.setId(resultSet.getLong(1)); 
							c.setCompName(resultSet.getString(2));
							c.setPassword(resultSet.getString(3));
							c.setEmail(resultSet.getString(4));
						}
						
					}
				} catch (SQLException e) {
					//Handle errors for JDBC
					throw new Exception("get Company from Database failed");
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
	public CouponClientFacade login(String name, String password, String clientType) {
		// TODO Auto-generated method stub
		this.COMP_NAME = name; 
		this.pass = password; 
		return null;
	}
	
	/*****************Coupon Methods 
	 * @throws Exception ********************/
	public void createCoupon(Coupon coupon) throws Exception { 
		
		getCompany();
		System.out.println(c);
		couponDBDAO.createCoupon(coupon, c.getId());
				
	}
	
	public void removeCoupon(Coupon coupon) {
		
	}
	
	public void updateCoupon(Coupon coupon) {
		
	}

	public Coupon getCopon(long id ) {
		return null; 
		
	}
	
	public Set<Coupon> getAllCoupons() 

	{
		return null; 
		
	}
	
	
	


}
