package revature.DAO;

import revature.Model.Customer;

public interface CustomerDAO {
	
	public Customer getInfo(int customerID);
	
	public void create(Customer customer);
	
	public Customer getID(String username);
	
}
