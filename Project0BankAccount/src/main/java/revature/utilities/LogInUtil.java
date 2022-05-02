package revature.utilities;

import java.util.Scanner;

import revature.DAO.LogInDAO;
import revature.DAO.LogInDAOImpl;
import revature.Model.LogInCreds;

public class LogInUtil {
	static String username1;
	static Scanner input = new Scanner(System.in);
	
	public static LogInDAO logIn = new LogInDAOImpl();
	
	private static boolean usernameExists(String username) {

		return logIn.usernameExists(username);
	}
	
	public static void LogIntest() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Username: ");
		String username = sc.nextLine();
		while (!usernameExists(username)) {
			System.out.println("That username does not exist");
			username = sc.next();
		}
		LogInCreds user = logIn.getUser(username);
		System.out.println("Password: ");
		String password = sc.nextLine();
		while (!user.password.equals(password)) {
			System.out.println("Incorrect password.\n");
			System.out.println("Please try again.");
			System.out.println("Password:");
			password = sc.nextLine();

		}
		System.out.println("\nYou have successfully logged in!");
	}
	
	public static void Registertest() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the username you would like for your registration ");
		String username = sc.nextLine();
		username1 = username;
		while (usernameExists(username)) {
			System.out.println("That username already exists");
			username = sc.next();
			username1 = username;
		}
		LogInCreds user = logIn.getUser(username);
		System.out.println("Password: ");
		String password = sc.nextLine();
		logIn.register(new LogInCreds(username,password));	
	}
	
	public static String getUsername() {
		return username1;
	}
	
}
