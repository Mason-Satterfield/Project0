package revature.MainMenu;


import java.util.Scanner;

import revature.utilities.AccountUtil;
import revature.utilities.JointAccountUtil;
import revature.utilities.LogInUtil;

public class BankAdminMainMenu {

	static Scanner input = new Scanner(System.in);
	
	public static void bankAdminMenu(){
		LogInUtil.LogIntest();
		
		int option;
		do {
        	System.out.println("\nPlease enter the number for which part of application you wish to use \n1. Approve/Deny Accounts or Joint Accounts \n2. Withdraw \n3. Deposit \n4. Delete \n5. View Account \n6. View Joint Account \n7. Transfer between Accounts\n8. Log out");
            option = input.nextInt();
	        switch(option) {
	        	case 1: 
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
	        	case 2:
	        		System.out.println("Please enter 1 to withdraw from an Account or 2 to withdraw from a Joint Account");
	        		int selection1 = input.nextInt();
	        		if(selection1 == 1) {
	        			AccountUtil.withdrawAccount();
	        		}
	        		else {
	        			JointAccountUtil.withdrawjAccount();
	        		}
	        		break;
	        	case 3:
	        		System.out.println("Please enter 1 to deposit into an Account or 2 to deposit into a Joint Account");
	        		int selection = input.nextInt();
	        		if(selection == 1) {
	        			AccountUtil.depositAccount();
	        		}
	        		else {
	        			JointAccountUtil.depositjAccount();
	        		}
	        		break;
	        	case 4:
	        		System.out.println("Please enter 1 to delete an Account or 2 to delete a Joint Account");
	        		int selection2 = input.nextInt();
	        		if(selection2 == 1) {
	        			AccountUtil.delete();
	        		}
	        		else {
	        			JointAccountUtil.delete();
	        		}
	        		break;
	        	case 5:
	        		AccountUtil.getInfoAcct();
	        		break;
	        	case 6:
	        		JointAccountUtil.getInfojAcct();
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
