package revature.Project0BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import revature.DAO.AccountDAO;
import revature.DAO.AccountDAOImpl;
import revature.DAO.CustomerDAO;
import revature.DAO.CustomerDAOImpl;
import revature.DAO.JointAccountDAO;
import revature.DAO.JointAccountDAOImpl;
import revature.DAO.LogInDAO;
import revature.DAO.LogInDAOImpl;
import revature.Model.Account;
import revature.Model.Customer;
import revature.Model.JointAccount;
import revature.Model.LogInCreds;

public class DriverTest 
{
	LogInDAO login = new LogInDAOImpl();
	CustomerDAO cust = new CustomerDAOImpl();
	AccountDAO acc = new AccountDAOImpl();
	JointAccountDAO jacc = new JointAccountDAOImpl();
    @Test
    public void balanceAccTest1()
    {
        Account balance = acc.getBalance(1);
    	assertTrue(1 == balance.balance );
    }
    
    @Test
    public void accountnotvalidTest2() {
    	Account valid = acc.getAccount(4);
    	assertEquals(false, valid.valid);
    }
    
    @Test
    public void balanceJAccTest3() {
    	JointAccount balance1 = jacc.getBalance(4);
    	assertTrue(5 == balance1.balance);
    }
    
    @Test
    public void accountnotvalidTest4() {
    	JointAccount valid1 = jacc.getAccount(3);
    	assertEquals(false, valid1.valid);
    }
    
    @Test
    public void namefromIDTest5() {
    	Customer name = cust.getInfo(1);
    	assertEquals("Mason", name.firstName);
    	assertEquals("Satterfield", name.lastName);
    }
    
    @Test
    public void getIDfromUsernameTest6() {
    	Customer id = cust.getID("masonsatt");
    	assertTrue(1 == id.customerID);
    }
    
    @Test
    public void usernameExistsTest7() {
    	boolean holder = login.usernameExists("masonsatt");
    	assertEquals(true, holder);
    }
    
    @Test
    public void passwordTest8() {
    	LogInCreds logIn = login.getUser("masonsatt");
    	assertEquals("test", logIn.password);
    	
    }
    
    @Test
    public void accOverdrawTest9() {
    	boolean holder = acc.overdraw(10, 1);
    	assertEquals(false, holder);
    }
    
    @Test
    public void jaccOverdrawTest10() {
    	boolean holder = jacc.overdraw(50, 1);
    	assertEquals(false, holder);
    }
    
    
}
