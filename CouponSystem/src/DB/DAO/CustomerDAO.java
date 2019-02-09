package DB.DAO;
import java.util.ArrayList;
import java.util.Set;

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

	void createCustomer(Customer customer) throws Exception; 
	
	void removeCustomer(Customer customer) throws Exception;

	void updateCustomer(Customer customer) throws Exception;

	Customer getCustomer(String CUST_NAME) throws Exception;
	
	Set<Customer> getAllCustomer() throws Exception;	
	
	Set<Coupon> getCoupons() throws Exception; 
	
	Boolean login(String custName, String password) throws Exception; 
	
	
	
	
}
