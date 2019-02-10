package DB.DAO;
import java.util.ArrayList;
import java.util.Set;

import DB.DBException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public interface CustomerDAO {
	
	/**
	 * This interface defines all the methods are should implement in the CustomerDBDAO. 
	 * It Contains : 
	 * createCustomer
	 * removeCustomer 
	 * updateCustomer
	 * getCustomer
	 * getAllCustomer
	 * getCouponByType
	 * getCoupons
	 * @throws Exception
	 * login 
	 */

	void createCustomer(Customer customer) throws DBException; 
	
	void removeCustomer(Customer customer) throws DBException;

	void updateCustomer(Customer customer) throws DBException;

	Customer getCustomer(String CUST_NAME) throws DBException;
	
	Set<Customer> getAllCustomer() throws DBException;	
	
	Set<Coupon> getCoupons() throws DBException; 
	
	Boolean login(String custName, String password) throws DBException; 
	
	
	
	
}
