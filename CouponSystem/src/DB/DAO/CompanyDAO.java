package DB.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import DB.DBException;

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
      
		void createCompany(Company company ) throws DBException; 

		void removeCompany(Company company) throws DBException;

		void updateCompany(Company company) throws DBException;

		Company getCompany(long id) throws DBException;
		
		Set<Company> getAllCompanies() throws DBException;	
		
		public Set<Coupon> getCompanyCoupons(Company company) throws DBException; 
		
		Boolean login(String compName, String password) throws DBException; 
	
		
	}