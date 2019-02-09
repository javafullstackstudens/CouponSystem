package Facade;

import java.util.Set;

import DB.DBDAO.CompanyDBDAO;
import DB.DBDAO.CustomerDBDAO;
import JavaBeans.Company;
import JavaBeans.Customer;

public class AdminFacade implements CouponClientFacade {
	
	/**
	 * This class implements the client level of the system. 
	 * The user login to the system and the instance will be according to the type of the client. 
	 * This level should uses the DAO level( CompanyDBDAO, CustomerDBDAO ) 
	 * In this level we will implement the logic of the program. 
	 * It Contains : 
	 * Login
	 * createCompany
	 * removeCompany
	 * updateCoupon
	 * getCoupon
	 * getAllCoupons
	 */
	/**************************************Attributes*****************************************/ 
	private CompanyDBDAO compDAO = new CompanyDBDAO();
	private CustomerDBDAO custDAO = new CustomerDBDAO(); 
	private Company company;
	/****************************************CTRO*********************************************/ 
	public AdminFacade() {
		// TODO Auto-generated constructor stub
	}
	/***************************************Methods*******************************************/ 
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createCompany(Company company)throws Exception { 
		compDAO.createCompany(company);
	}
	
	public void removeCompany(Company company) throws Exception {
		compDAO.removeCompany(company);
	}

	public void updateCompany(Company company, String newName,String newPassword, String newEmail) throws Exception {
		company.setCompName(newName);
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		compDAO.updateCompany(company);
	}

	public Company getCompany(long id) throws Exception {
			
		return compDAO.getCompany(id);

	}

	public Set<Company> getAllCompanies() throws Exception {
	
		return compDAO.getAllCompanies();
	}
	
	public void createCustomer(Customer customer) throws Exception{ 

		custDAO.createCustomer(customer);
	}
	
	public void removeCustomer(Customer customer) throws Exception {
		
		custDAO.removeCustomer(customer);
	}
	
	public void updateCustomer(Customer customer) throws Exception {
		
		custDAO.updateCustomer(customer);
	}
	
	public Customer getCustomer(long id ) throws Exception { 
		
		return custDAO.getCustomer(id);
		
	}
	
	public Set<Customer> getAllCustomers() throws Exception{
		
		return custDAO.getAllCustomer(); 
	}

}
