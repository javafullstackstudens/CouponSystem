package DB.DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import Main.*;
import DB.DAO.CompanyDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;


public class CompanyDBDAO implements CompanyDAO {

	
	// Attributes
	
	Connection conn;

	// Methods that DBDAO Must use from DAO
	
	@Override
	public void createCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insertCompany(Company company) throws Exception {

	    //Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		//Define the Execute query
		String sql = "INSERT INTO COMPANY (COMP_NAME,PASSWORD,EMAIL)  VALUES(?,?,?)";
		PreparedStatement pstmt = null;
		try {
		    pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//Handle errors for JDBC
			throw new Exception("Company creation failed");
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}

		}
		System.out.println("Company " + company.getCompName() + " inserted successfully");
	}
	
	

	@Override
	//**This method remove an company by ID key  **//
	public void removeCompany(Company company) throws Exception {
		

	    //Open a connection
		conn = DriverManager.getConnection(Utils.getDBUrl());
		//Define the Execute query
		String sql = "DELETE FROM COMPANY WHERE id=?";
		PreparedStatement pstmt = null;
		try {
		    pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);//turn off auto-commit
			pstmt.setLong(1, company.getId()); //Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();
			conn.commit();//Commit the changes,If there is no error.
			System.out.println(company.getCompName()+" successfully Removed from the DB");
		} catch (SQLException e) {
			try {
				conn.rollback();//roll back updates to the database , If there is error 
			} catch (SQLException e1) {
				throw new Exception(e1.getMessage());
			}
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new Exception("The close connection action faild"); 
			}

		}
		
	}

	@Override
	public void updateCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coupon getCompany(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Company> getAllCompanies() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Set<Coupon> getCoupons() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean login(String compName, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
