package revature.Model;

public class JointAccount {
	
	public int jAccountNumber, fkCustomerId, fkCustomerId2;
	public float balance;
	public String type;
	public boolean valid;
	
	public JointAccount() {
		
	}
	public JointAccount(int joint_account_number, float balance, String type, boolean valid, int fk_customer_id, int fk_customer_id2 ){
		jAccountNumber = joint_account_number;
		this.balance = balance;
		this.type = type;
		this.valid = valid;
		fkCustomerId = fk_customer_id;
		fkCustomerId2 = fk_customer_id2;
	}
	
	public JointAccount(float balance, String type, boolean valid, int fk_customer_id, int fk_customer_id2 ){
		this.balance = balance;
		this.type = type;
		this.valid = valid;
		fkCustomerId = fk_customer_id;
		fkCustomerId2 = fk_customer_id2;
	}
	@Override
	public String toString() {
		return "JointAccount [jAccountNumber=" + jAccountNumber + ", fkCustomerId=" + fkCustomerId + ", fkCustomerId2="
				+ fkCustomerId2 + ", balance=" + balance + ", type=" + type + ", valid=" + valid + "]";
	}
	
	
}
