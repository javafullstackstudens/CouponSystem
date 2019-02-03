package Facade;

import java.util.Set;
import DB.DBDAO.CompanyDBDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;

public class CompanyFacade implements CouponClientFacade {

	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*****************Coupon Methods ********************/
	public void createCoupon(Coupon coupon) { 
		
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
