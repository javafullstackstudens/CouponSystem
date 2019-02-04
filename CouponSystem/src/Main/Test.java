package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import DB.Database;
import DB.DBDAO.CompanyDBDAO;
import DB.DBDAO.CouponDBDAO;
import DB.DBDAO.CustomerDBDAO;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;

public class Test {

	public static void main(String[] args) throws Exception {
		
		
		/**************Coupon HashSet Collection TEST****************/
		Set<Coupon> coupons = new HashSet<Coupon>();
		Class.forName("org.apache.derby.jdbc.ClientDriver");

		CouponDBDAO couponDBDAO = new CouponDBDAO(); 

		coupons =couponDBDAO.getAllCoupouns();  
		
		Iterator<Coupon> iterator = coupons.iterator(); 
		
		while(iterator.hasNext()) { 
			
			System.out.println(iterator.next());
		}
		
		/**************Customer HashSet Collection TEST****************/
		Set<Customer> customers = new HashSet<Customer>(); 
		CustomerDBDAO customerDBDAO = new CustomerDBDAO(); 
		
		customers = customerDBDAO.getAllCustomer(); 
		
		Iterator<Customer> iterator2 = customers.iterator(); 
		
		while (iterator2.hasNext()) {
			
			System.out.println(iterator2.next());
			
		}
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Database.getDatabase(); 
//		Company company = new Company(2, "Phoebus", "12345", "oriel@test.com");
//		Coupon coupon = new Coupon(4, "Evi The King", Utils.getDate(), Utils.endDate(20),7000, CouponType.HEALTH,"wtf", 1251, "image");
//		Customer customer = new Customer(3, "Oriel", "1234");
//		Customer customer2 = new Customer(5,"Evi", "221284");
//	
//		CustomerDBDAO customerDBDAO = new CustomerDBDAO(); 
////		
//		System.out.println(customerDBDAO.getCustomer(5)); 
//		
//		CompanyDBDAO companyDBDAO = new CompanyDBDAO(); 
//
//		
//		companyDBDAO.updateCompany(company);
//		couponDBDAO.printAllCoupons();
		
		



		
		}

		

	}

