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

import DB.ConnPool;
import DB.Database;
import DB.DBDAO.CompanyDBDAO;
import DB.DBDAO.CouponDBDAO;
import DB.DBDAO.CustomerDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CouponClientFacade;
import Facade.CustomerFacade;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;
import Main.CouponSystem.clientType;

public class Test {

	public static void main(String[] args) throws Exception {
		
//		Database.getDatabase(); 
		
		Company company = new Company(2, "Shoshana", "12345", "oriel@test.com");
		Coupon coupon = new Coupon(4, "ConnPool", Utils.getDate(), Utils.endDate(20),7000, CouponType.CAMPING,"wtf", 1251, "image");
		Customer customer = new Customer(3, "Snir", "1234");
		Customer customer2 = new Customer(4,"Evi", "221284");
		Coupon coupon1 = new Coupon(3, "Omer", Utils.getDate(), Utils.endDate(60),5555, CouponType.HEALTH,"wtf", 1251, "image");
		Company company2 = new Company(3, "mPrest", "12345", "oriel@test.com"); 
		
	/************************************Admin-Facade-Test************************/	
//        AdminFacade adminFacade = new AdminFacade(); 
//        adminFacade.removeCompany(company);
//        CouponDBDAO couponDBDAO = new CouponDBDAO(); 
//        couponDBDAO.removeCoupon(coupon1);
//        adminFacade.createCustomer(customer);
//        adminFacade.createCustomer(customer2);
//        adminFacade.createCompany(company);
//        adminFacade.login("Mashav", "12345", clientType.Admin); 
//        adminFacade.updateCompany(company,"Ee123456","Evi.cohen@gmail.com");
//       Set<Company> companies = new HashSet<Company>(); 
//       companies = adminFacade.getAllCompanies(); 
//       Iterator iterator = companies.iterator(); 
//       
//       while(iterator.hasNext()) { 
//    	   System.out.println(iterator.next());
//       }
       
//        adminFacade.removeCustomer(customer2); 
//        adminFacade.updateCompany(company2, newPassword, newEmail);
//        adminFacade.removeCompany(company);
	/****************************Test-CreateCoupon*********************************/
//		CompanyFacade companyFacade = new CompanyFacade();
//		companyFacade.login(company.getCompName(), company.getPassword(),clientType.Company);
//        companyFacade.createCoupon(coupon1);
//        companyFacade.createCoupon(coupon);
//        System.out.println(companyFacade.getAllCoupons()); 
//		companyFacade.removeCoupon(coupon1);
//		companyFacade.createCoupon(coupon);

/*********************************************Test-Main****************************/ 
		/*********Admin**************/
		AdminFacade adminFacade2 = new AdminFacade();
		adminFacade2 = (AdminFacade) CouponSystem.getCouponSystem().login("admin", "12346", clientType.Admin);
        if ( adminFacade2 != null ) { 
        	adminFacade2.createCompany(company);	
        }
		/*********Customer************/
        CustomerFacade customerFacade = new CustomerFacade(); 
        customerFacade = (CustomerFacade) CouponSystem.getCouponSystem().login("Evi", "2212846", clientType.Customer); 
        if(customerFacade!=null) { 
        	System.out.println(customerFacade.getAllPurchasedCoupons());
        }
        
        /*********Company************/
        CompanyFacade companyFacade = new CompanyFacade(); 
        companyFacade = (CompanyFacade) CouponSystem.getCouponSystem().login("Phoebus", "123456",clientType.Company);
        if(companyFacade!=null) { 
        	System.out.println(companyFacade.getCompanyCoupons(companyFacade.getCompany("Phoebus")));
        }
    
    /****************************Test-PurchasedCoupon*****************************/

//        CustomerFacade customerFacade = new CustomerFacade(); 
//        customerFacade.login(customer2.getCustomerName(),customer2.getPassword(),clientType.Customer); 
//        System.out.println(customerFacade.getAllPurchasedCoupons());  
//        System.out.println(customerFacade.getAllPurchasedCouponsByPrice(1251));

        

		
//		/**************Coupon HashSet Collection TEST****************/
//		Set<Coupon> coupons = new HashSet<Coupon>();
//		Class.forName("org.apache.derby.jdbc.ClientDriver");
//
//		CouponDBDAO couponDBDAO = new CouponDBDAO(); 
//
//		coupons =couponDBDAO.getAllCoupouns();  
//		Iterator iterator = coupons.iterator(); 
//		
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
	


		
//		/**************Customer HashSet Collection TEST****************/
//		Set<Customer> customers = new HashSet<Customer>(); 
//		CustomerDBDAO customerDBDAO = new CustomerDBDAO(); 
//		
//		customers = customerDBDAO.getAllCustomer(); 
//		
//		Iterator iterator2 = customers.iterator(); 
//		
//		while (iterator2.hasNext()) {
//			
//			System.out.println(iterator2.next());
//			
//		}
//		

		}

		

	}

