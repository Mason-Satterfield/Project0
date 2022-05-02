package revature.Driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.javalin.Javalin;
import revature.Controller.AccountController;
import revature.Controller.CustomerController;
import revature.Controller.JointAccountController;
import revature.DAO.JointAccountDAO;
import revature.DAO.JointAccountDAOImpl;
import revature.MainMenu.MainMenu;
import revature.Model.JointAccount;

public class Driver 
{
	private static Logger log = LogManager.getLogger(Driver.class);
	
    public static void main( String[] args )
    {
    	
    	log.info("Application started successfully.");
        
    	MainMenu.mainMenu();
    	
    	log.info("Application ending successfully.\n\n");
        
        Javalin app = Javalin.create().start(7070);
        
        CustomerController customerController = new CustomerController(app);
        
        AccountController accountController = new AccountController(app);
        
        JointAccountController jaccountController = new JointAccountController(app);
        
        
    }
}
