package DB.DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import DB.DAO.CustomerDAO;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import Main.Utils;



public class CustomerDBDAO implements CustomerDAO {
	
	
	//Attributes
	
	Connection conn;

	
	
	
	// Methods that DBDAO Must use from DAO
	
	@Override
public void insertCustomer(Customer customer) throws Exception{
		
		conn = DriverManager.getConnection(Utils.getDBUrl());
        String sql = "INSERT INTO CUSTOMER (CUST_NAME,PASSWORD) VALUES (?,?)";
        try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getPassword());
		pstmt.executeUpdate();
		System.out.println("Customer " + customer.getCustomerName() + " inserted successfully");
        } catch (SQLException e) {
			throw new Exception("Customer creation faild");
		} finally {
			conn.close();
		}
	}

	@Override
	public void removeCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coupon getCustomer(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Coupon> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
