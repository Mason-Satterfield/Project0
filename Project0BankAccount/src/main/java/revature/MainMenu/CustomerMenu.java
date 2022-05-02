package revature.MainMenu;

import java.util.Scanner;

import revature.utilities.AccountUtil;
import revature.utilities.CustomerUtil;
import revature.utilities.JointAccountUtil;
import revature.utilities.LogInUtil;

public class CustomerMenu {
	static String tempUsername;
	static int option;
	static Scanner input = new Scanner(System.in);
	
	public static void registrationMenu() {
		LogInUtil.Registertest();
		tempUsername = LogInUtil.getUsername();
		CustomerUtil.addCustomer(tempUsername);
		CustomerUtil.getCustomerID(tempUsername);
	}
	
	
	public static void customerMenu() {
		LogInUtil.LogIntest();
		do {
        	System.out.println("\nPlease enter the number for which part of application you wish to use \n"
        			+ "1. Apply to open a new Account \n"
        			+ "2. Apply to open a new Joint account \n"
        			+ "3. View an Account \n"
        			+ "4. View a Joint Account \n"
        			+ "5. Withdraw \n"
        			+ "6. Deposit \n"
        			+ "7. Transfer between Accounts \n"
        			+ "8. Log out");
            option = input.nextInt();
	        switch(option) {
	        	case 1: 
	        		AccountUtil.applyAccount();
	        		break;
	        	case 2:
	        		JointAccountUtil.applyJointAccount();
	        		break;
	        	case 3:
	        		AccountUtil.getInfoAcct();
	        		break;
	        	case 4:
	        		JointAccountUtil.getInfojAcct();
	        		break;
	        	case 5:
	        		System.out.println("Please enter 1 to withdraw from an Account or 2 to withdraw from a Joint Account");
	        		int selection1 = input.nextInt();
	        		if(selection1 == 1) {
	        			AccountUtil.withdrawAccount();
	        		}
	        		else {
	        			JointAccountUtil.withdrawjAccount();
	        		}
	        		break;
	        	case 6:
	        		System.out.println("Please enter 1 to deposit into an Account or 2 to deposit into a Joint Account");
	        		int selection = input.nextInt();
	        		if(selection == 1) {
	        			AccountUtil.depositAccount();
	        		}
	        		else {
	        			JointAccountUtil.depositjAccount();
	        		}
	        		break;
				case 7:
					System.out.println("Please enter 1 to withdraw from an Account or 2 to withdraw from a Joint account");
	        		int selection4 = input.nextInt();
	        		if(selection4 == 1) {
	        			AccountUtil.withdrawAccount();
	        			System.out.println("Please enter 1 to deposit to an Account or 2 to deposit to a Joint account");
		        		int selection3 = input.nextInt();
		        		if(selection3 == 1) {
		        			float wit2 = AccountUtil.getWit1();
		        			AccountUtil.transfer(wit2);
		        		}
		        		else {
		        			float wit2 = AccountUtil.getWit1();
		        			JointAccountUtil.transfer(wit2);
		        		}
	        		}
	        		else {
	        			JointAccountUtil.withdrawjAccount();
	        			System.out.println("Please enter 1 to deposit to an Account or 2 to deposit to a Joint account");
		        		int selection3 = input.nextInt();
		        		if(selection3 == 1) {
		        			float wit2 = JointAccountUtil.getWit1();
		        			AccountUtil.transfer(wit2);
		        		}
		        		else {
		        			float wit2 = JointAccountUtil.getWit1();
		        			JointAccountUtil.transfer(wit2);
		        		}
	        		}
	        		break;
	        	case 8:
	        		System.out.println("You are now logged out!");
	        		break;
	        	default: 
	        		System.out.println("The number you entered was not between 1-5, please try again");
	        		break;
	        }
        }
        while (option != 8);
		
	}

}
