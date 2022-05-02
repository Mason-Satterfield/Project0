package revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import revature.Driver.Driver;
import revature.Model.Customer;
import revature.Model.LogInCreds;

public class CustomerDAOImpl implements CustomerDAO{
	
	private static Logger log = LogManager.getLogger(Driver.class);
	
	private Connection conn = ConnectionManager.getConnection();
	@Override
	public Customer getInfo(int customerID) {
		try {

			String query = "SELECT * FROM Customers WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, customerID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Customer user = new Customer();
				user.customerID = rs.getInt("customer_id");
				user.firstName = rs.getString("first_name");
				user.lastName = rs.getString("last_name");
				user.fk_username = rs.getString("fk_username");
				log.info("Customer successfully returned from the Customers table");
				return user;
			} else {
				return null;
			}
		} catch (SQLException E) {
			log.error("Error while returning a Customer from the Customers table", E);
			return null;
		}
	}
	@Override
	public void create(Customer customer) {
		try {

			String query = "INSERT INTO Customers (first_name, last_name, fk_username) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer.firstName);
			pstmt.setString(2, customer.lastName);
			pstmt.setString(3, customer.fk_username);
			pstmt.execute();
			
			log.info("Single record successfully inserted into the Customers table.");
	
		} catch (SQLException E) {
			log.error("Error while inserting into the Customers table in database", E);
		}
	}
	@Override
	public Customer getID(String username) {
		try {

			String query = "SELECT * FROM Customers WHERE fk_username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Customer user = new Customer();
				user.customerID = rs.getInt("customer_id");
				user.firstName = rs.getString("first_name");
				user.lastName = rs.getString("last_name");
				user.fk_username = rs.getString("fk_username");
				log.info("Customer ID successfully returned from the Customers table");
				return user;
			} else {
				return null;
			}
		} catch (SQLException E) {
			log.error("Error while returning customer ID from the Customers table", E);
			return null;
		}
	}
	

}
