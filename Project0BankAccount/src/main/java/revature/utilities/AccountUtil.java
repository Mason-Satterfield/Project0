package revature.utilities;

import java.util.ArrayList;
import java.util.Scanner;

import revature.DAO.AccountDAO;
import revature.DAO.AccountDAOImpl;
import revature.Model.Account;

public class AccountUtil {
	static float wit1;
	static Scanner input = new Scanner(System.in);
	
	public static AccountDAO account = new AccountDAOImpl();
	
	public static void getInfoAcct() {
		System.out.println("Please enter your Account Number: ");
		int acctNumber = input.nextInt();
		Account acctInfo = account.getAccount(acctNumber);
		System.out.println(acctInfo.toString());
	}
	
	public static void getBalanceAcct() {
		System.out.println("Please enter the Account Number: ");
		int acctNumber1 = input.nextInt();
		Account balance = account.getAccount(acctNumber1);
		System.out.println("$" +balance.balance);
	}
	
	public static void applyAccount() {
		System.out.println("Please enter your customer id: ");
		int custer1 = input.nextInt();
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
		account.apply(new Account(initbalance, atype, valid, custer1));
		Account acc = account.getAccount(custer1);
		System.out.println("You have successfully applied to create an account");
		
	}
	
	public static void withdrawAccount() {
		System.out.println("Please enter the account number you wish to withdraw from:");
		int acctNumber = input.nextInt();
		Account acctInfo = account.getAccount(acctNumber);
		System.out.println("Please enter the amount you wish to withdraw: ");
		float wit = input.nextFloat();
		boolean holder = overdraw(wit, acctNumber);
		while(wit <= 0 || holder == false) {
			System.out.println("The number you entered was equal to 0 or less than 0, or the amount you are withdrawing is too much\n");
			System.out.println("Please try again.");
			wit = input.nextFloat();
			holder = overdraw(wit, acctNumber);
		}
		
		wit1 = wit;
		acctInfo.balance -= wit;
		account.deposit(acctInfo);
		System.out.println("You have successfully withdrawn $" + wit);
	}
	
	public static void depositAccount() {
		System.out.println("Please enter the account number you wish to deposit to:");
		int acctNumber = input.nextInt();
		Account acctInfo = account.getAccount(acctNumber);
		System.out.println("Please enter the amount you wish to deposit: ");
		float dep = input.nextFloat();
		while(dep <= 0) {
			System.out.println("The number you entered was equal to 0 or less than 0 \n");
			System.out.println("Please try again.");
			dep = input.nextFloat();
		}
		acctInfo.balance += dep;
		account.deposit(acctInfo);
		System.out.println("You have successfully deposited $" + dep);
	}
	
	public static void approve() {
		ArrayList<Account> accts = account.getFalseAccts();
		for(Account i: accts) {
			System.out.println(i);
		}
		System.out.println("Please enter the account number you wish to approve: ");
		int acctNumber = input.nextInt();
		Account acctInfo = account.getAccount(acctNumber);
		acctInfo.valid = true;
		account.approve(acctInfo);
		System.out.println("You have successfully approved an account!");
	}
	
	public static void deny() {
		ArrayList<Account> accts = account.getFalseAccts();
		for(Account i: accts) {
			System.out.println(i);
		}
		System.out.println("Please enter the account number you wish to deny: ");
		int acctNumber = input.nextInt();
		Account acctInfo = account.getAccount(acctNumber);
		account.delete(acctInfo);
		System.out.println("You have successfully denied an account!");
	}
	
	public static void delete() {
		System.out.println("Please enter the account number:");
		int acct = input.nextInt();
		Account acctInfo = account.getAccount(acct);
		account.delete(acctInfo);
		System.out.println("You have successfully deleted the "+acct+ " id account.");
	}
	
	public static void transfer(float wit2) {
		System.out.println("Please enter the account number you wish to deposit to:");
		int acctNumber = input.nextInt();
		Account acctInfo = account.getAccount(acctNumber);
		acctInfo.balance += wit2;
		account.deposit(acctInfo);
		System.out.println("You have successfully deposited $" + wit2+ " into " +acctNumber);
	}
	
	public static boolean overdraw(float withdr, int accountNumber) {
		 
		return account.overdraw(withdr, accountNumber);
	}
	
	public static float getWit1() {
		return wit1;
	}
	
}
