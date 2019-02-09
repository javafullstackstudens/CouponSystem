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
	
	
	/**
	 * This class implements the client level of the system. 
	 * The user login to the system and the instance will be according to the type of the client. 
	 * This level should uses the DAO level ( couponDBDAO, CompanyDBDAO ) 
	 * In this level we will implement the logic of the program. 
	 * It Contains : 
	 * Login
	 * createCoupon
	 * removeCoupon
	 * updateCoupon
	 * getCoupon
	 * getAllCoupons
	 */

	/**************************************Attributes*****************************************/ 
	private Company c = new Company(); 
	private Connection conn; 
	private long ID_comp ; 
	private String COMP_NAME = null; 
	private String pass = null;     
	private CouponDBDAO couponDBDAO = new CouponDBDAO(); 
	private CompanyDBDAO CompanyDBDAO = new CompanyDBDAO(); 
   
	/***************************************CTRO*********************************************/
	public CompanyFacade() {
		// TODO Auto-generated constructor stub
	}
	
	/**************************************Methods*******************************************/
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		// TODO Auto-generated method stub
		this.COMP_NAME = name; 
		this.pass = password; 
		return null;
	}
	
	public void createCoupon(Coupon coupon) throws Exception { 
		
		c = CompanyDBDAO.getCompany(COMP_NAME); 
		couponDBDAO.createCoupon(coupon, c.getId());
				
	}
	
	public void removeCoupon(Coupon coupon) {
		
	}
	
	public void updateCoupon(Coupon coupon) {
		
	}

	public Coupon getCoupon(long id ) {
		return null; 
		
	}
	
	public Set<Coupon> getAllCoupons() 

	{
		return null; 
		
	}
	
	
	


}
