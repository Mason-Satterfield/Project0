package revature.Model;

public class Account {
	
	public int accountNumber, fkCustomerId;
	public float balance;
	public String type;
	public boolean valid;
	
	public Account() {
		
	}
	
	public Account(float balance, String type, boolean valid, int fk_customer_id ){
		this.balance = balance;
		this.type = type;
		this.valid = valid;
		fkCustomerId = fk_customer_id;
	}
	
	public Account(int account_number, float balance, String type, boolean valid, int fk_customer_id ){
		accountNumber = account_number;
		this.balance = balance;
		this.type = type;
		this.valid = valid;
		fkCustomerId = fk_customer_id;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", fkCustomerId=" + fkCustomerId + ", balance=" + balance
				+ ", type=" + type + ", valid=" + valid + "]";
	}	
	
}
