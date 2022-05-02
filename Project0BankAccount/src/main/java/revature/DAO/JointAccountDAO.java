package revature.DAO;

import java.util.ArrayList;

import revature.Model.JointAccount;

public interface JointAccountDAO {
	
	public JointAccount getAccount(int jAccountNumber);
	
	public JointAccount getBalance(int jAccountNumber);
	
	public void withdraw(JointAccount jointAccount);
	
	public void deposit(JointAccount jointAccount);
	
	public void delete(JointAccount jointAccount);
	
	public void apply(JointAccount jointAccount);
	
	public ArrayList<JointAccount> getFalseJAccts();
	
	public void approve(JointAccount jointAccount);
	
	public boolean overdraw(float withdr, int accountNumber);
}
