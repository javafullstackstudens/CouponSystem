package JavaBeans;
import java.util.ArrayList;

/*
@Author - Oriel
*/

public class Customer {

	
	// Attributes
	private long id;
	private String CustomerName;
	private String password;

	ArrayList<Coupon> coupons = new ArrayList<Coupon>();
	
	String name = "";
	
	
	// Ctors
	
	// Empty Ctor
	public Customer() {
	}
	
	// Full Ctor
	public Customer(long id, String name, String pass) {
		this.id = id;
		this.CustomerName = name;
		this.password = pass;

	}
	
	
	
	
	// Getters & Setters

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	// Other methods
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", CustomerName=" + CustomerName + ", password=" + password + ", coupons=" + coupons + "]";
	}

	
}