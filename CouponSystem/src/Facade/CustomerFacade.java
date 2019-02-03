package Facade;

import java.util.Set;

import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class CustomerFacade implements CouponClientFacade {

	
	
	
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CustomerFacade() 
	{
	//TODO//	
	}
	
	public void purchaseCoupon(Coupon coupon) throws Exception{
		
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
