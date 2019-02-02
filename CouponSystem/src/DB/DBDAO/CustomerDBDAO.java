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
		//Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		//Define the Execute query
        String sql = "INSERT INTO CUSTOMER (CUST_NAME,PASSWORD) VALUES (?,?)";
        PreparedStatement pstmt = null;
        try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getPassword());
		pstmt.executeUpdate();

        } catch (SQLException e) {
        	//Handle errors for JDBC
			throw new Exception("Customer creation faild");
        } finally {
            //finally block used to close resources
            try {
                if(pstmt!=null)
                    conn.close();	
            } catch (SQLException se) {
                // do nothing
            }
            try {
                if(conn!=null)
                    conn.close();	
            } catch (SQLException se) {
                se.printStackTrace();
            }
            
        }
		System.out.println("Customer " + customer.getCustomerName() + " inserted successfully");
	}

	@Override
	public void removeCustomer(Customer customer) throws Exception {
	    //Open a connection
	    conn = DriverManager.getConnection(Utils.getDBUrl());
	    //Define the Execute query
	    String sql = "DELETE FROM CUSTOMER WHERE id=?";
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = conn.prepareStatement(sql);
	        conn.setAutoCommit(false);//turn off auto-commit
	        pstmt.setLong(1, customer.getId()); //Sets the designated parameter to the given Java long value
	        pstmt.executeUpdate();
	        conn.commit();//Commit the changes,If there is no error.
	        System.out.println(customer.getCustomerName()+" successfully Removed from the DB");
	    } catch (SQLException e) {
	        try {
	            conn.rollback();//roll back updates to the database , If there is error 
	        } catch (SQLException e1) {
	            throw new Exception(e1.getMessage());
	        }
	    } finally {
	        //finally block used to close resources
	        try {
	            if(pstmt!=null)
	                conn.close();	
	        } catch (SQLException se) {
	            // do nothing
	        }
	        try {
	            if(conn!=null)
	                conn.close();	
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	        
	    }
	    System.out.println(customer.getCustomerName() + " successfully Removed from the DB");
	    
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
