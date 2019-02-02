package Main;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

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


		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Database.getDatabase(); 
		Company company = new Company(4, "mPrest", "12345", "oriel@test.com");
		Coupon coupon = new Coupon(1, "The eucalyptus", Utils.getDate(), Utils.endDate(20), 1245, CouponType.HEALTH,"wtf", 1251, "image");
		Customer customer = new Customer(6, "Oriel", "1234");

		CouponDBDAO Co = new CouponDBDAO(); 
		Co.insertCoupon(coupon);
		Company c = new Company(); 
		CompanyDBDAO companyDBDAO = new CompanyDBDAO(); 
		CustomerDBDAO customerDBDAO = new CustomerDBDAO(); 

		customerDBDAO.removeCustomer(customer);
		Co.removeCoupon(coupon);

		
		}

		

	}

