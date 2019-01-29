package Facade;

import java.util.Set;
import DB.DBDAO.CompanyDBDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;

public class CompanyFacade {

	private CompanyDBDAO compDAO = new CompanyDBDAO();
	private Company company;

	public CompanyFacade(Company c) {
		this.company = c;
	}

	public CompanyFacade() {
	}

	public void insertCompany(Company company) throws Exception {
		compDAO.insertCompany(company);
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

	public Company getCompany() {
		return company;
	}

	public Set<Coupon> getAllCompanies() throws Exception {
	
		return compDAO.getAllCompanies();
	}
	
}
