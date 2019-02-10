package Main;

import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CouponClientFacade;
import Facade.CustomerFacade;

public class CouponSystem {

	private static CouponSystem instance = new CouponSystem();

	/************************************** GET-DAO ******************************/

	public enum clientType {
		Admin, Customer, Company
	};

	private CouponSystem() {

	}

	public static CouponSystem getCouponSystem() {
		return instance;
	}

	public CouponClientFacade login(String name, String password, clientType cType) throws Exception {
		// TODO Auto-generated method stub

		switch (cType) {
		case Admin:
			AdminFacade adminFacade = new AdminFacade();
            adminFacade.login(name, password, cType); 
            return adminFacade; 
            //TODO - DailyCouponExpirationTask

		case Customer:
			CustomerFacade customerFacade = new CustomerFacade();
			customerFacade.login(name, password, cType);
			//TODO - DailyCouponExpirationTask
			return customerFacade; 

		case Company:
			CompanyFacade companyFacade = new CompanyFacade();
			companyFacade.login(name, password, cType); 
			//TODO - DailyCouponExpirationTask
			return companyFacade; 
		}

		return null;
	}
	
	public void ShutDown() { 
		
		//TODO - Close all the connectionPool
		//TODO - Stop the DailyTask 
	}

}

