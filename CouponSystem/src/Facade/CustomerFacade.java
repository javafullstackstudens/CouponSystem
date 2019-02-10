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
import Main.CouponSystem.clientType;

public class CustomerFacade implements CouponClientFacade {
	
	
	/**
	 * This class implements the client level of the system. 
	 * The user login to the system and the instance will be according to the type of the client. 
	 * This level should uses the DAO level( CompanyDBDAO, CustomerDBDAO ) 
	 * In this level we will implement the logic of the program. 
	 * It Contains : 
	 * Login
	 * purchaseCoupon
	 * getAllPurchasedCoupons
	 * getAllPurchasedCouponsByType
	 * getAllPurchasedCouponsByPrice
	 * getAllCoupons
	 */

/**************************************Attributes*****************************************/ 	
    private Customer customer = new Customer(); 
    private String CUST_NAME = null; 
    private String PASS = null;
    private Main.CouponSystem.clientType clientType = null; 
    private Connection conn; 
    private CustomerDBDAO  customerDBDAO = new CustomerDBDAO();
	
	/*************************************CTOR***********************************************/
	public CustomerFacade() 
	{
	//TODO//
	
	}
	
	/*************************************Methods********************************************/	
	@Override
	public CouponClientFacade login(String name, String password, clientType cType) {
		this.CUST_NAME = name; 
		this.PASS = password; 
		this.clientType = cType; 
		
		return null;

	}

	public void purchaseCoupon(Coupon coupon) throws Exception{
		
		customer = customerDBDAO.getCustomer(CUST_NAME); 
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
