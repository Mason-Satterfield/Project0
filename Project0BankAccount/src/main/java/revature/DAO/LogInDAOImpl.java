package revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import revature.Driver.Driver;
import revature.Model.LogInCreds;

public class LogInDAOImpl implements LogInDAO{
	
	private static Logger log = LogManager.getLogger(Driver.class);
	
	public Connection conn = ConnectionManager.getConnection();
	
	public boolean usernameExists(String username) {
		try {

			String query = "SELECT * FROM Login_Creds WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				log.info("User successfully returned from the Login Creds table");
				return true;
			} else {
				return false;
			}
		} catch (SQLException E) {
			log.error("Error while returning a User from the Login Creds table", E);
			return false;
		}
	}


	public LogInCreds getUser(String username) {
		try {

			String query = "SELECT * FROM Login_Creds WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LogInCreds user = new LogInCreds();
				user.username = rs.getString("username");
				user.password = rs.getString("password");
				log.info("User successfully returned from the Login Creds table");
				return user;
			} else {
				return null;
			}
		} catch (SQLException E) {
			log.error("Error while returning a User from the Login Creds table", E);
			return null;
		}
	}
	public void register(LogInCreds user) {
		try {

			String query = "INSERT INTO Login_Creds (username, password) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.username);
			pstmt.setString(2, user.password);
			pstmt.execute();
			
			log.info("Single record successfully inserted into the Login Creds table.");
	
		} catch (SQLException E) {
			log.error("Error while inserting into the Login Creds table in database", E);
		}
	}




}
