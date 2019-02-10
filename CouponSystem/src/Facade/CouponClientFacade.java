package Facade;

import Main.CouponSystem.clientType;

public interface CouponClientFacade {
	

	public CouponClientFacade login(String name, String password,clientType cType) throws Exception; 

}
