package DB.DAO;
import java.util.Set;
import JavaBeans.*;

public interface CompanyDAO {
      
		
	    void insertCompany(Company company) throws Exception;

		void removeCompany(Company company) throws Exception;

		void updateCompany(Company company) throws Exception;

		Coupon getCompany(int id) throws Exception;
		
		Set<Coupon> getAllCompanies() throws Exception;

		
	}