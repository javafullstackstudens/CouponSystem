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
	
	    Company company = new Company(1,"Amdocs","1234", "oriel@test.com");
		Coupon coupon = new Coupon(1,"The eucalyptus", Utils.getDate(), Utils.getDate() , 1245, CouponType.RESTURANTS , "wtf" , 1251, "image" );
		Customer customer = new Customer(123,"Oriel","1234");
		

		CompanyFacade companyFacade = new CompanyFacade();
		
        companyFacade.insertCompany(company);
		companyFacade.removeCompany(company);  
		
		
	}

}