package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import DB.Database;
import Facade.CompanyFacade;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;

public class Test {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");

		Company company = new Company(1, "Amdocs", "12345", "oriel@test.com");
		Coupon coupon = new Coupon(1, "The eucalyptus", Utils.getDate(), Utils.getDate(), 1245, CouponType.HEALTH,"wtf", 1251, "image");
		Customer customer = new Customer(123, "Oriel", "1234");

		Database.getDatabase();
		CompanyFacade companyFacade = new CompanyFacade();
		
		Company company_remove = new Company(5, "Amdocs", "12345", "oriel@test.com");
		companyFacade.removeCompany(company_remove);

	}

}