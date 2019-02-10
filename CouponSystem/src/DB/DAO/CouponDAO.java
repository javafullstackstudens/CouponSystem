package DB.DAO;


import java.util.Set;

import DB.DBException;
import JavaBeans.Coupon;

public interface CouponDAO {
	
	/**
	 * This interface defines all the methods are should implement in the CustomerDBDAO. 
	 * It Contains : 
	 * createCoupon
	 * removeCoupon 
	 * updateCoupon
	 * getCoupon
	 * getAllCoupouns
	 * getCouponByType
	 * createCoupon
	 * @throws Exception
	 */


	void createCoupon(Coupon coupon) throws DBException ;
	
	void removeCoupon(Coupon coupon) throws DBException;

	void updateCoupon(Coupon coupon) throws DBException;

	Coupon getCoupon(long id) throws DBException;
	
	Set<Coupon> getAllCoupouns() throws DBException;
	
	Set<Coupon> getCouponByType(Coupon coupon) throws DBException; 
	
	public void createCoupon(Coupon coupon, long id) throws DBException;
	
	
	
	
}
