import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;



public class Database {

	/*
	 * Singleton class
	 */
	
	private static Database instance = new Database();
	static Connection conn;

	private Database() {
	}

	public static Database getDatabase() {
		return instance;
	}

	public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}

	// the local Port 3301
	// Name of database - JBDB
	
	public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/MyDB;create=true";
	}

	public static void createTables(Connection con) throws SQLException {
		
	    //Connection
	
		conn = DriverManager.getConnection(getDBUrl());
		String sql;

		// Table 1 creation (Company)
		try {
	    java.sql.Statement stmt = conn.createStatement();
		sql = "create table Company("
				+ "ID integer not null primary key generated always as identity(start with 1, increment by 1), "
				+ "COMP_NAME varchar(30) not null, "
				+ "PASSWORD varchar(30) not null," 
				+ "EMAIL varchar(30) not null)";
		stmt.executeUpdate(sql);
		System.out.println("success:" + sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		// Table 2 creation (Customer)
		try {
		java.sql.Statement stmt2 = conn.createStatement();
		sql = "create table Customer("
				+ "ID integer not null primary key generated always as identity(start with 1, increment by 1), "
				+ "CUST_NAME varchar(30) not null, " 
				+ "PASSWORD varchar(30) not null)" ;
		stmt2.executeUpdate(sql);
		System.out.println("success:" + sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Table 3 creation (Coupon)
		try {
		java.sql.Statement stmt3 = conn.createStatement();
		sql = "create table Coupon("
				+ "ID integer not null primary key generated always as identity(start with 1, increment by 1), "
				+ "TITLE varchar(30) not null, " 
				+ "START_DATE DATE not null, " 
				+ "END_DATE DATE not null,"
				+ "AMOUNT INTEGER not null," 
				+ "TYPE varchar(10) not null," 
				+ "MESSAGE varchar(30) not null,"
				+ "PRICE double not null,"
				+ "IMAGE varchar(200) not null)";
		stmt3.executeUpdate(sql);
		System.out.println("success:" + sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		// Table 4 creation (Customer_Coupon - Join table)
		try {
		java.sql.Statement stmt4 = conn.createStatement();
		sql = "create table Customer_Coupon("
				+ "CUST_ID integer not null REFERENCES Customer(ID),"
				+ "COUPON_ID integer not null REFERENCES Coupon(ID))";
		stmt4.executeUpdate(sql);
		System.out.println("success:" + sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


		// Table 5 creation (Company_Coupon - Join table)
		try {
		java.sql.Statement stmt5 = conn.createStatement();
		sql = "create table Company_Coupon("
				+ "COMP_ID integer not null REFERENCES Company(ID),"
				+ "COUPON_ID integer not null REFERENCES Coupon(ID))";
		stmt5.executeUpdate(sql);
		System.out.println("success:" + sql);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			conn.close();
		}
}

	
	
	public void insertCompany(Company company) throws Exception {
		conn = DriverManager.getConnection(getDBUrl());
		String sql = "INSERT INTO COMPANY (COMP_NAME,PASSWORD,EMAIL)  VALUES(?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.executeUpdate();

			System.out.println("Company created" + company.toString());

		} catch (SQLException e) {
			throw new Exception("Company creation failed");
		} finally {
			conn.close();
		}
	}

	
	public void insertCoupon(Coupon coupon) throws Exception{
		
		conn = DriverManager.getConnection(getDBUrl());

		
		String sql = "INSERT INTO COUPON (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE) VALUES(?,?,?,?,?,?,?,?)";
        try{
        	
        	
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	LocalDate localDate = LocalDate.now();
        	Date date = java.sql.Date.valueOf(localDate);
        
        	pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, date);
			pstmt.setDate(3, date);
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name());	
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());	
			pstmt.executeUpdate();
			
        }catch(SQLException e) {
        	throw new Exception("Coupon creation faild");
        } finally {
        	conn.close();
        }
	}
	
	
	
	public void insertCustomer(Customer customer) throws Exception{
		
		conn = DriverManager.getConnection(getDBUrl());
        String sql = "INSERT INTO CUSTOMER (CUST_NAME,PASSWORD) VALUES (?,?)";
        try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getPassword());
		pstmt.executeUpdate();
        } catch (SQLException e) {
			throw new Exception("Customer creation faild");
		} finally {
			conn.close();
		}

	}
	
	public static void selectTableCompany() throws SQLException {

		conn = DriverManager.getConnection(getDBUrl());
		java.sql.Statement stmt = conn.createStatement();
		java.sql.ResultSet results = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM COMPANY");
		ResultSetMetaData rsmd = results.getMetaData();
		int numberCols = rsmd.getColumnCount();
		for (int i = 1; i <= numberCols; i++)
			System.out.print(rsmd.getColumnLabel(i) + "\t\t");
		System.out.println("\n-----------------------------------------------------------------------------------");
		while (results.next()) {
			int id = results.getInt(1);
			String restName = results.getString(2);
			String cityName = results.getString(3);
			String email = results.getString(4);
			System.out.println(id + "\t\t" + restName + "\t\t\t" + cityName + "\t\t\t" + email);
		}
		results.close();
		conn.close();
	}
	
	
	

}