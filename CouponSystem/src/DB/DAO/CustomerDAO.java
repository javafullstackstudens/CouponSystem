package DB.DAO;
import java.util.ArrayList;
import java.util.Set;

import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public interface CustomerDAO {

	void createCustomer(Customer customer) throws Exception; 
	
	void insertCustomer(Customer customer) throws Exception;

	void removeCustomer(Customer customer) throws Exception;

	void updateCustomer(Customer customer) throws Exception;

	Customer getCustomer(int id) throws Exception;
	
	Set<Company> getAllCustomer() throws Exception;	
	
	Set<Coupon> getCoupons() throws Exception; 
	
	Boolean login(String compName, String password) throws Exception; 
	
	
	
	
}
