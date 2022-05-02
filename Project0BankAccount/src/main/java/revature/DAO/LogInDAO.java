package revature.DAO;

import revature.Model.LogInCreds;

public interface LogInDAO {

	
	public boolean usernameExists(String username);
	
	public LogInCreds getUser(String username);
	
	public void register(LogInCreds user);
}
