package revature.MainMenu;


import java.util.Scanner;

import revature.utilities.AccountUtil;
import revature.utilities.CustomerUtil;
import revature.utilities.JointAccountUtil;
import revature.utilities.LogInUtil;


public class EmployeeMainMenu {
	
	static Scanner input = new Scanner(System.in);
	
	public static void employeeMenu(){
		LogInUtil.LogIntest();
		int option;
		do {
        	System.out.println("\nPlease enter the number for which part of application you wish to use \n1. View Account Info \n2. View Joint Account Info \n3. View Account Balance \n4. View Joint Account Balance \n5. View Personal Information \n6. Approve/Deny an Account or Joint Account \n7. Log out");
            option = input.nextInt();
	        switch(option) {
	        	case 1: 
	        		AccountUtil.getInfoAcct();
	        		break;
	        	case 2:
	        		JointAccountUtil.getInfojAcct();
	        		break;
	        	case 3:
	        		AccountUtil.getBalanceAcct();
	        		break;
	        	case 4:
	        		JointAccountUtil.getBalancejAcct();
	        		break;
	        	case 5:
	        		CustomerUtil.getCustomer();
	        		break;
	        	case 6:
	        		System.out.println("Please enter 1 if you wish to approve or 2 if you wish to deny an Account/Joint Account");
	        		int option1 = input.nextInt();
	        		if(option1 == 1) {
	        			System.out.println("Please enter 1 to approve an account or 2 to approve a joint account");
	        			int whichAcct = input.nextInt();
	        			if(whichAcct ==1) {
	        				AccountUtil.approve();
	        			}
	        			else {
	        				JointAccountUtil.approve();
	        			}
	        		}
	        		else {
	        			System.out.println("Please enter 1 to deny an account or 2 to deny a joint account");
	        			int whichAcct = input.nextInt();
	        			if(whichAcct ==1) {
	        				AccountUtil.deny();
	        			}
	        			else {
	        				JointAccountUtil.deny();
	        			}
	        		}
	        		break;
	        	case 7:
	        		System.out.println("You are now logged out!");
	        		break;
	        	default: 
	        		System.out.println("The number you entered was not between 1-5, please try again");
	        		break;
	        }
        }
        while (option != 7);
	}
	
}
