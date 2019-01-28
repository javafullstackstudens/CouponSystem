
import java.sql.Connection;
import java.sql.DriverManager;

import DB.Database;
import JavaBeans.*;


public class Test {

	public static void main(String[] args) throws Exception {
		
		Connection con = DriverManager.getConnection(Database.getDBUrl());
		
		Class.forName("org.apache.derby.jdbc.ClientDriver");
	
		
	    Company company = new Company(1,"Amdocs","1234", "oriel@test.com");
		Coupon coupon = new Coupon(1,"The eucalyptus", Utils.getDate(), Utils.getDate() , 1245, CouponType.RESTURANTS , "wtf" , 1251, "image" );
		Customer customer = new Customer(123,"Oriel","1234");
		

		Database.getDatabase().createTables(con);
		
		Database.getDatabase().selectTableCompany();
		
		
	}

}