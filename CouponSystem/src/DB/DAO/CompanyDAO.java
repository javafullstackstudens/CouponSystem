package DB.DAO;
import java.util.ArrayList;
import java.util.Set;
import JavaBeans.*;

public interface CompanyDAO {
	
	/**
	 * This interface defines all the methods are should implement in the CustomerDBDAO. 
	 * It Contains : 
	 * createCompany
	 * removeComapny 
	 * updateComapny 
	 * getCompany by name and by ID
	 * getAllCompanies
	 * getCoupons
	 * login 
	 * @throws Exception
	 */
      
		void createCompany(Company company ) throws Exception; 

		void removeCompany(Company company) throws Exception;

		void updateCompany(Company company) throws Exception;

		Company getCompany(long id) throws Exception;
		
		Set<Company> getAllCompanies() throws Exception;	
		
		Set<Coupon> getCoupons() throws Exception; 
		
		Boolean login(String compName, String password) throws Exception; 
	
		
	}