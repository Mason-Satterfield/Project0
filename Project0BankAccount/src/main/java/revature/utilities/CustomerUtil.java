package revature.utilities;

import java.util.Scanner;

import revature.DAO.CustomerDAO;
import revature.DAO.CustomerDAOImpl;
import revature.Model.Customer;

public class CustomerUtil {
	
	static Scanner input = new Scanner(System.in);
	
	public static CustomerDAO customer = new CustomerDAOImpl();
	
	public static void getCustomer() {
		System.out.println("Please enter the customer id");
		int cust = input.nextInt();
		Customer custy = customer.getInfo(cust);
		System.out.println(custy.toString());
	}
	
	public static void addCustomer(String username) {
		System.out.println("Please enter your first name: ");
		String first = input.nextLine();
		System.out.println("Please enter your last name: ");
		String last = input.nextLine();
		customer.create(new Customer(first, last, username));
	}
	
	public static void getCustomerID(String username) {
		Customer custy = customer.getID(username);
		System.out.println("Your customer id is: " +custy.customerID);
		
		System.out.println("\nYou have successfully registered!");
	}
}
