package revature.Model;

public class LogInCreds {
	
	public String username, password;
	
	public LogInCreds(){
		
	}
	
	public LogInCreds(String username, String password){
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LogInCreds [username=" + username + ", password=" + password + "]";
	}

	
	
	
}
