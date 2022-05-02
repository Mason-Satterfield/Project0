package revature.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	//lowercase connection is the Name, and Connection is the data type
	private static Connection connection;
	//connection string format is httpprotocol:sqltype://serverURL:portNumber/databaseName
	//for postgresql we use port 5432
	//this is our connection data
	private static String connectionString = "jdbc:postgresql://rajje.db.elephantsql.com:5432/pbfurmat",
			username = "pbfurmat",
			password = "WGE2S_MzvubIhi16ILlPBeepH9lrkIKG";
	
	public static Connection getConnection() {
		//this will not compile without being in a try catch block
		try {
			if(connection == null || connection.isClosed()) {
				//this ensures that the driver class is loaded before we try to use it
				//it will fail without. it needs the maven dependency added in the pom.xml file
				Class.forName("org.postgresql.Driver");
				
				//this is saying to use the driver manager to make sure there is a suitable driver
				//to use to make the connection string work
				//it will call the postgres driver to set up the output/input io stream to pass data between the application and database
				connection = DriverManager.getConnection(connectionString, username, password);
			}
			return connection;
		} catch (Exception e) {
			
		} 
		return null;
	}
}
