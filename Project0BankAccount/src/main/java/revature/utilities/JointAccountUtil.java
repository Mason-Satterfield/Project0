package revature.utilities;

import java.util.ArrayList;
import java.util.Scanner;

import revature.DAO.JointAccountDAO;
import revature.DAO.JointAccountDAOImpl;
import revature.Model.JointAccount;

public class JointAccountUtil {
	static float wit1;
	static Scanner input = new Scanner(System.in);
	
	public static JointAccountDAO jointaccount = new JointAccountDAOImpl();
	
	public static void getInfojAcct() {
		System.out.println("Please enter your Joint Account Number: ");
		int jacctNumber = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacctNumber);
		System.out.println(jacctInfo.toString());
	}
	
	
	public static void getBalancejAcct() {
		System.out.println("Please enter the Joint Account Number: ");
		int jacctNumber1 = input.nextInt();
		JointAccount balance = jointaccount.getAccount(jacctNumber1);
		System.out.println("$" +balance.balance);
	}
	
	public static void applyJointAccount() {
		System.out.println("Please enter the customer id for the first person: ");
		int custer1 = input.nextInt();
		System.out.println("Please enter the customer id for the second person: ");
		int custer2 = input.nextInt();
		System.out.println("Select 1 for Checking and 2 for Savings");
		int acctType = input.nextInt();
		String atype;
		if(acctType == 1) {
			atype = "Checking";
		}
		else {
			atype = "Savings";
		}
		boolean valid = false;
		float initbalance = 0;
		jointaccount.apply(new JointAccount(initbalance, atype, valid, custer1, custer2));
		System.out.println("You have successfully applied to create a joint account");
		
	}
	
	public static void withdrawjAccount() {
		System.out.println("Please enter the joint account number you wish to withdraw from:");
		int jacctNumber = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacctNumber);
		System.out.println("Please enter the amount you wish to withdraw: ");
		float wit = input.nextFloat();
		boolean holder = overdraw(wit, jacctNumber);
		while(wit <= 0 || holder == false) {
			System.out.println("The number you entered was equal to 0 or less than 0, or the amount you are withdrawing is too much\n");
			System.out.println("Please try again.");
			wit = input.nextFloat();
			holder = overdraw(wit, jacctNumber);
		}
		wit1 = wit;
		jacctInfo.balance -= wit;
		jointaccount.deposit(jacctInfo);
		System.out.println("You have successfully withdrawn $" + wit);
	}
	
	public static void depositjAccount() {
		System.out.println("Please enter the joint account number you wish to deposit to:");
		int jacctNumber = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacctNumber);
		System.out.println("Please enter the amount you wish to deposit: ");
		float dep = input.nextFloat();
		while(dep <= 0) {
			System.out.println("The number you entered was equal to 0 or less than 0 \n");
			System.out.println("Please try again.");
			dep = input.nextFloat();
		}
		jacctInfo.balance += dep;
		jointaccount.deposit(jacctInfo);
		System.out.println("You have successfully deposited $" + dep);
	}
	
	public static void approve() {
		ArrayList<JointAccount> jaccts = jointaccount.getFalseJAccts();
		for(JointAccount i: jaccts) {
			System.out.println(i);
		}
		System.out.println("Please enter the joint account number you wish to approve: ");
		int jacctNumber = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacctNumber);
		jacctInfo.valid = true;
		jointaccount.approve(jacctInfo);
		System.out.println("You have successfully approved a joint account!");
	}
	
	public static void deny() {
		ArrayList<JointAccount> jaccts = jointaccount.getFalseJAccts();
		for(JointAccount i: jaccts) {
			System.out.println(i);
		}
		System.out.println("Please enter the joint account number you wish to deny: ");
		int jacctNumber = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacctNumber);
		jointaccount.delete(jacctInfo);
		System.out.println("You have successfully denied an account!");
	}
	
	public static void delete() {
		System.out.println("Please enter the joint account number:");
		int jacct = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacct);
		jointaccount.delete(jacctInfo);
		System.out.println("You have successfully deleted the "+jacct+ "id account.");
	}
	
	public static void transfer(float wit2) {
		System.out.println("Please enter the joint account number you wish to deposit to:");
		int jacctNumber = input.nextInt();
		JointAccount jacctInfo = jointaccount.getAccount(jacctNumber);
		jacctInfo.balance += wit2;
		jointaccount.deposit(jacctInfo);
		System.out.println("You have successfully transfered $" + wit2+ " into " +jacctNumber);
	}
	
	public static boolean overdraw(float withdr, int accountNumber) {
		 
		return jointaccount.overdraw(withdr, accountNumber);
	}
	
	public static float getWit1() {
		return wit1;
	}
	
}
