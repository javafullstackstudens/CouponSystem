package DB.DAO;
import java.util.ArrayList;
import java.util.Set;

import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public interface CustomerDAO {

	void createCustomer(Customer customer) throws Exception; 
	
	void removeCustomer(Customer customer) throws Exception;

	void updateCustomer(Customer customer) throws Exception;

	Customer getCustomer(long id) throws Exception;
	
	Set<Customer> getAllCustomer() throws Exception;	
	
	Set<Coupon> getCoupons() throws Exception; 
	
	Boolean login(String custName, String password) throws Exception; 
	
	
	
	
}
