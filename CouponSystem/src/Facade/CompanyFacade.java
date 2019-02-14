package Facade;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DB.ConnPool;
import DB.DBException;
import DB.DBDAO.CompanyDBDAO;
import DB.DBDAO.CouponDBDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import Main.Utils;
import Main.CouponSystem.clientType;

public class CompanyFacade implements CouponClientFacade {
	
	
	/**
	 * This class implements the client level of the system. 
	 * The user login to the system and the instance will be according to the type of the client. 
	 * This level should uses the DAO level ( couponDBDAO, CompanyDBDAO ) 
	 * In this level we will implement the logic of the program. 
	 * It Contains : 
	 * Login
	 * createCoupon
	 * removeCoupon
	 * updateCoupon
	 * getCoupon
	 * getAllCoupons
	 */

	/**************************************Attributes*****************************************/ 
	private Company company = new Company(); 
	private Connection conn; 
	private long ID_comp ; 
	private String compName = null; 
	private String pass = null;     
	private CouponDBDAO couponDBDAO = new CouponDBDAO(); 
	private CompanyDBDAO companyDBDAO = new CompanyDBDAO(); 
   
	/***************************************CTRO*********************************************/
	public CompanyFacade() {
		// TODO Auto-generated constructor stub
	}
	
	/**************************************Methods
	 * @throws DBException *******************************************/
	@Override
	public Boolean login(String name, String password, clientType cType) throws DBException {
		// TODO Auto-generated method stub
		this.compName = name; 
		this.pass = password;
		//Create instance locally of company 		
		company = getCompany(compName);
		//Authentication of the password and company name  
		if (company.getCompName().equals(this.compName) && company.getPassword().equals(this.pass) && company != null) { 
			return true; 
		}
		else return false;
		
		
	}
	
	public void createCoupon(Coupon coupon) throws DBException { 
		
		Set<Coupon> allCoupons = new HashSet<Coupon>() ; 
		allCoupons = getAllCoupons(); 
		Iterator<Coupon> itr = allCoupons.iterator(); 
		 
		while(itr.hasNext())
		{ 
			
			Coupon coupon2 = new Coupon(); 
			coupon2 = (Coupon) itr.next(); 
			if (coupon2 instanceof Coupon && coupon2.getTitle().equals(coupon.getTitle())) { 
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "Coupon " + coupon.getTitle() + " Already Exist");
				return;		
			}
			
		}

		couponDBDAO.createCoupon(coupon, company.getId());	
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		JOptionPane.showMessageDialog(frame, "Created coupon " + coupon.getTitle() + " successfully");
	}
	
	public void removeCoupon(Coupon coupon) throws DBException {
		
		//Remove and update Customer_Coupon Table
		couponDBDAO.removeCustomerCoupon(coupon);
		//Remove and update Company_Coupon table 
		couponDBDAO.removeCompanyCoupon(coupon);
		//Remove coupon from Coupon table 
		couponDBDAO.removeCoupon(coupon);
		
	}
	
	public void updateCoupon(Coupon coupon) throws DBException {
		
		couponDBDAO.updateCoupon(coupon); 
		
	}

	public Coupon getCoupon(long id ) {
		return null; 
		
	}
	
	public Set<Coupon> getAllCoupons() throws DBException 

	{
		Set<Coupon> allCoupons = new HashSet<Coupon>(); 
		allCoupons = couponDBDAO.getAllCoupouns(); 
		
		return allCoupons; 
		
	}
	
	public Company getCompany(String compName) throws DBException {
		
		Company companyLocaly = new Company(); 
		Set<Company> allCompanies = new HashSet<Company>() ; 
		allCompanies = companyDBDAO.getAllCompanies(); 
		Iterator<Company> itr = allCompanies.iterator(); 
		
		while (itr.hasNext() ) { 
			companyLocaly = itr.next(); 
			if (companyLocaly.getCompName().equals(compName))
			{
				return companyLocaly;
			}
			 
		}
		 return null ;
		
 
		
	}

	public Set<Coupon> getCompanyCoupons(Company company) throws DBException { 
		
		Set<Coupon> allCoupons = new HashSet<Coupon>() ; 
		allCoupons =companyDBDAO.getCompanyCoupons(company);
		
				if(!(allCoupons.isEmpty())) {
					
					return allCoupons;
				}
				else 
				{ 
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");
					JOptionPane.showMessageDialog(frame, "To a comapny, " + company.getCompName() + " hasn't coupons"); 
					return null;
				}
		
	}


	public synchronized Set<Coupon> getCouponByType(CouponType type) throws DBException {
		
		Set<Coupon> coupons2 = new HashSet<Coupon>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		coupons = getCompanyCoupons(company);
		Iterator<Coupon> itr = coupons.iterator(); 

			while (itr.hasNext()) {
				Coupon coupon2 = new  Coupon(); 
				coupon2 = itr.next();
				// Check the type of the Coupon
				if (type.equals(coupon2.getType())) {

					coupons2.add(coupon2);
                
				}

			}
			if(coupons2.isEmpty()) { 
			
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");
					JOptionPane.showMessageDialog(frame, "To comapny, " + company.getCompName() + " hasn't coupons in type" +type.name()); 
					return null;
			}

		return coupons2;
	}
	
	public synchronized Set<Coupon> getCouponsByPrice(Double priceLimt) throws DBException {
		
		Set<Coupon> coupons2 = new HashSet<Coupon>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		coupons = getCompanyCoupons(company);
		Iterator<Coupon> itr = coupons.iterator(); 

			while (itr.hasNext()) {
				Coupon coupon2 = new  Coupon(); 
				coupon2 = itr.next();
				// Check the type of the Coupon
				if (priceLimt >= coupon2.getPrice()) {

					coupons2.add(coupon2);
                
				}

			}
			if(coupons2.isEmpty()) { 
				
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "To comapny , " + company.getCompName() + " hasn't coupons that cost " + priceLimt ); 
				return null;
		}

		return coupons2;
		
	}	
	
	public synchronized Set<Coupon> getCouponsByExpiredDate(java.util.Date date) throws DBException {
		
		Set<Coupon> coupons2 = new HashSet<Coupon>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		coupons = getCompanyCoupons(company);
		Iterator<Coupon> itr = coupons.iterator(); 

			while (itr.hasNext()) {
				Coupon coupon2 = new  Coupon(); 
				coupon2 = itr.next();
				// Check the type of the Coupon
				if (date.compareTo(coupon2.getEndDate())>0) {

					coupons2.add(coupon2);
                
				}

			}
			if(coupons2.isEmpty()) { 
				
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "To comapny , " + company.getCompName() + " hasn't coupons before date" + date ); 
				return null;
		}

		return coupons2;
	}
	
	


}
