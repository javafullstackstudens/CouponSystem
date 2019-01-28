package DB.DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public void insertCompany(Company company) throws Exception {
		
		conn = DriverManager.getConnection(Utils.getDBUrl());
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
	
	

	@Override
	public void removeCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		
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
	public Set<Coupon> getAllCompanies() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
