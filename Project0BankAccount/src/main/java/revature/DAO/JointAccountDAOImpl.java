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
import revature.Model.JointAccount;

public class JointAccountDAOImpl implements JointAccountDAO{

	public Connection conn = ConnectionManager.getConnection();
	
	private static Logger log = LogManager.getLogger(Driver.class);
	
	public JointAccount getAccount(int jAccountNumber) {
		try {	
			String query = "SELECT * FROM Joint_Accounts WHERE joint_account_number = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, jAccountNumber);
			ResultSet rs = pstmt.executeQuery();
	
			if (rs.next()) {
				JointAccount acct = new JointAccount();
				acct.jAccountNumber = rs.getInt("joint_account_number");
				acct.balance = rs.getFloat("balance");
				acct.type = rs.getString("type");
				acct.valid = rs.getBoolean("valid");
				acct.fkCustomerId = rs.getInt("fk_customer_id");
				acct.fkCustomerId2 = rs.getInt("fk_customer_id2");
				log.info("Joint Account successfully returned from the Joint Accounts table");
				return acct;
			} else {
				return null;
			}
		} catch(SQLException E) {
			log.error("Error while returning a Joint Account from the Joint Accounts table", E);
			return null;
		}
	}

	@Override
	public JointAccount getBalance(int jAccountNumber) {
		try {

			String query = "SELECT * FROM Joint_Accounts WHERE joint_account_number = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, jAccountNumber);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				JointAccount jacct = new JointAccount();
				jacct.jAccountNumber = rs.getInt("joint_account_number");
				jacct.balance = rs.getFloat("balance");
				jacct.type = rs.getString("type");
				jacct.valid = rs.getBoolean("valid");
				jacct.fkCustomerId = rs.getInt("fk_customer_id");
				jacct.fkCustomerId2 = rs.getInt("fk_customer_id2");
				log.info("Balance successfully returned from the Joint Accounts table");
				return jacct;
			} else {
				return null;
			}
		} catch (SQLException E) {
			log.error("Error while returning balance from the Joint Accounts table", E);
			return null;
		}
	}

	@Override
	public void withdraw(JointAccount jointAccount) {
		try {

			String query = "UPDATE Joint_Accounts SET balance = ? WHERE joint_account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setFloat(1, jointAccount.balance);
			pstmt.setInt(2, jointAccount.jAccountNumber);
			pstmt.execute();

			log.info("Withdrawn balance successfully updated in the Joint Accounts table");
		} catch (SQLException E) {
			log.error("Error while updating the withdrawn balance in the Joint Accounts table", E);
		}
		
	}

	@Override
	public void deposit(JointAccount jointAccount) {
		try {

			String query = "UPDATE Joint_Accounts SET balance = ? WHERE joint_account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setFloat(1, jointAccount.balance);
			pstmt.setInt(2, jointAccount.jAccountNumber);
			pstmt.execute();

			log.info("Deposited balance successfully updated in the Joint Accounts table");
		} catch (SQLException E) {
			log.error("Error while updating the deposited balance in the Joint Accounts table", E);
		}
		
	}

	@Override
	public void delete(JointAccount jointAccount) {
		try {

			String query = "DELETE FROM Joint_Accounts WHERE joint_account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, jointAccount.jAccountNumber);
			pstmt.execute();

			log.info("Record successfully deleted from the Joint Accounts table in database");
		} catch (SQLException E) {
			log.error("Error while removing from the Joint Accounts table in database", E);
		}
		
	}

	@Override
	public void apply(JointAccount jointAccount) {
		try {

			String query = "INSERT INTO Joint_Accounts (balance, type, valid, fk_customer_id, fk_customer_id2) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setFloat(1, jointAccount.balance);
			pstmt.setString(2, jointAccount.type);
			pstmt.setBoolean(3, jointAccount.valid);
			pstmt.setInt(4, jointAccount.fkCustomerId);
			pstmt.setInt(5, jointAccount.fkCustomerId2);
			pstmt.execute();

			log.info("Single record successfully inserted into the Joint Accounts table.");
		} catch (SQLException E) {
			log.error("Error while inserting into the Joint Accounts table in database", E);
		}
		
	}

	@Override
	public ArrayList<JointAccount> getFalseJAccts() {
		try {

			Statement statement = conn.createStatement();
		
			ResultSet rs = statement.executeQuery("SELECT * FROM Joint_Accounts WHERE valid = false;");
			
	
			ArrayList<JointAccount> jacc = new ArrayList<JointAccount>();
			
		
			while(rs.next()) {
			
				int jAccountNumber = rs.getInt("joint_account_number");
				float balance = rs.getFloat("balance");
				String type = rs.getString("type");
				boolean valid = rs.getBoolean("valid");
				int fkCustomerId = rs.getInt("fk_customer_id");
				int fkCustomerId2 = rs.getInt("fk_customer_id2");

				jacc.add(new JointAccount(jAccountNumber, balance, type, valid, fkCustomerId, fkCustomerId2));
			}
			log.info("Record(s) successfully returned from the Joint Accounts table.");
			return jacc;
		} catch (SQLException e) {
			log.error("Error while accessing Joint Accounts table in database", e);
		}
		return null;
	}

	@Override
	public void approve(JointAccount jointAccount) {
		try {

			String query = "UPDATE Joint_Accounts SET valid = ? WHERE joint_account_number = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
		
			pstmt.setBoolean(1, jointAccount.valid);
			pstmt.setInt(2, jointAccount.jAccountNumber);
			pstmt.execute();

			log.info("Record successfully approved in the Joint Accounts table in database");
		} catch (SQLException E) {
			log.error("Error while approving a record from the Joint Accounts table in database", E);
		}
		
	}
	
	public boolean overdraw(float withdr, int accountNumber) {
		try {

			String query = "SELECT * FROM Joint_Accounts WHERE joint_account_number = ?";
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
