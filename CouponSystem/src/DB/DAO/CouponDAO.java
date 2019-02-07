package DB.DAO;

import java.util.ArrayList;
import java.util.Set;

import JavaBeans.Coupon;

public interface CouponDAO {


	void createCoupon(Coupon coupon) throws Exception ;
	
	void removeCoupon(Coupon coupon) throws Exception;

	void updateCoupon(Coupon coupon) throws Exception;

	Coupon getCoupon(long id) throws Exception;
	
	Set<Coupon> getAllCoupouns() throws Exception;
	
	Set<Coupon> getCouponByType(Coupon coupon) throws Exception; 
	
	public void createCoupon(Coupon coupon, long id) throws Exception;
	
	
	
	
}
