package revature.DAO;

import java.util.ArrayList;

import revature.Model.Account;

public interface AccountDAO {

	public Account getAccount(int accountNumber);
	
	public Account getBalance(int accountNumber);
	
	public void withdraw(Account account);
	
	public void deposit(Account account);
	
	public void delete(Account account);
	
	public void apply(Account account);
	
	public ArrayList<Account> getFalseAccts();
	
	public void approve(Account account);
	
	public boolean overdraw(float withdr, int accountNumber);

}
