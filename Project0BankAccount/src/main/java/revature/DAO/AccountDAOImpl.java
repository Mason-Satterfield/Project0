package revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import revature.Driver.Driver;
import revature.Model.Account;
import revature.Model.LogInCreds;

public class AccountDAOImpl implements AccountDAO {
	
	private static Logger log = LogManager.getLogger(Driver.class);
	
	private Connection conn = ConnectionManager.getConnection();
	
	public Account getAccount(int accountNumber) {
		try {

			String query = "SELECT * FROM Accounts WHERE account_number = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Account acct = new Account();
				acct.accountNumber = rs.getInt("account_number");
				acct.balance = rs.getFloat("balance");
				acct.type = rs.getString("type");
				acct.valid = rs.getBoolean("valid");
				acct.fkCustomerId = rs.getInt("fk_customer_id");
				log.info("Account successfully returned from the Accounts table");
				return acct;
			} else {
				return null;
			}
		} catch (SQLException E) {
			log.error("Error while returning an Account from the Accounts table", E);
			return null;
		}
	}

	@Override
	public Account getBalance(int accountNumber) {
		try {

			String query = "SELECT * FROM Accounts WHERE account_number = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Account acct = new Account();
				acct.accountNumber = rs.getInt("account_number");
				acct.balance = rs.getFloat("balance");
				acct.type = rs.getString("type");
				acct.valid = rs.getBoolean("valid");
				acct.fkCustomerId = rs.getInt("fk_customer_id");
				log.info("Balance successfully returned from the Accounts table");
				return acct;
			} else {
				return null;
			}
		} catch (SQLException E) {
			log.error("Error while returning balance from the Accounts table", E);
			return null;
		}
	}

	@Override
	public void withdraw(Account account) {
		try {

			String query = "UPDATE Accounts SET balance = ? WHERE account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setFloat(1, account.balance);
			pstmt.setInt(2, account.accountNumber);
			pstmt.execute();

			log.info("Withdrawn balance successfully updated in the Accounts table");
		} catch (SQLException E) {
			log.error("Error while updating the withdrawn balance in the Accounts table", E);
		}
		
	}

	@Override
	public void deposit(Account account) {
		try {

			String query = "UPDATE Accounts SET balance = ? WHERE account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setFloat(1, account.balance);
			pstmt.setInt(2, account.accountNumber);
			pstmt.execute();

			log.info("Deposited balance successfully updated in the Accounts table");
		} catch (SQLException E) {
			log.error("Error while updating the deposited balance in the Accounts table", E);
		}
	}
	
	@Override
	public void delete(Account account) {
		try {

			String query = "DELETE FROM Accounts WHERE account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, account.accountNumber);
			pstmt.execute();

			log.info("Record successfully deleted from the Accounts table in database");
		} catch (SQLException E) {
			log.error("Error while removing from the Accounts table in database", E);
		}
		
	}

	@Override
	public void apply(Account account) {
		try {

			String query = "INSERT INTO Accounts (balance, type, valid, fk_customer_id) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setFloat(1, account.balance);
			pstmt.setString(2, account.type);
			pstmt.setBoolean(3, account.valid);
			pstmt.setInt(4, account.fkCustomerId);
			pstmt.execute();
			log.info("Single record successfully inserted into the Accounts table.");
			
		} catch (SQLException E) {
			log.error("Error while inserting into the Accounts table in database", E);
		}
		
	}

	@Override
	public ArrayList<Account> getFalseAccts() {
		try {
			//this creates a new statement called statement that uses a connection to the
			Statement statement = conn.createStatement();
			
			//this is our query to be executed
			ResultSet rs = statement.executeQuery("SELECT * FROM Accounts WHERE valid = false;");
			
			//this is our arraylist for storing our results
			ArrayList<Account> acc = new ArrayList<Account>();
			
			//this loop runs so long as there is another row in rs
			while(rs.next()) {
				//getting each column in the row and setting it to our variable
				//the rs.getInt("id") come from the Student model not SQL
				int accountNumber = rs.getInt("account_number");
				float balance = rs.getFloat("balance");
				String type = rs.getString("type");
				boolean valid = rs.getBoolean("valid");
				int fkCustomerId = rs.getInt("fk_customer_id");
				
				//this adds each new student to our student list(array) in line 20
				acc.add(new Account(accountNumber, balance, type, valid, fkCustomerId));
			}
			log.info("Record(s) successfully returned from the Accounts table.");
			return acc;
		} catch (SQLException e) {
			log.error("Error while accessing Accounts table in database", e);
		}
		return null;
	}

	@Override
	public void approve(Account account) {
		try {

			String query = "UPDATE Accounts SET valid = ? WHERE account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setBoolean(1, account.valid);
			pstmt.setInt(2, account.accountNumber);
			pstmt.execute();

			log.info("Record successfully approved in the Accounts table in database");
		} catch (SQLException E) {
			log.error("Error while approving a record from the Joint Accounts table in database", E);
		}
		
	}

	@Override
	public boolean overdraw(float withdr, int accountNumber) {
		try {

			String query = "SELECT * FROM Accounts WHERE account_number = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				float balance = rs.getFloat("balance");
				balance -= withdr;
				if (balance < 0) {
					log.info("Do not allow withdraw from the Accounts table");
					return false;
				}
				else {
					log.info("Allowed withdraw from the Accounts table");
					return true;
				}
				
			} else {
				return false;
			}
		} catch (SQLException E) {
			log.error("Error while returning a User from the Login Creds table", E);
			return false;
		}
	}
}
