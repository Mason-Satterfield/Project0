package revature.Model;

public class Customer {
	
	public int customerID;
	public String firstName, lastName, fk_username;
	
	public Customer() {
		
	}
	
	public Customer(String first_name, String last_name, String fk_username) {
		firstName = first_name;
		lastName = last_name;
		this.fk_username = fk_username;
		
	}
	
	public Customer(int customer_id, String first_name, String last_name, String fk_username) {
		customerID = customer_id;
		firstName = first_name;
		lastName = last_name;
		this.fk_username = fk_username;
		
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fk_username=" + fk_username + "]";
	}
	
	
}
