package revature.Controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import revature.DAO.CustomerDAO;
import revature.DAO.CustomerDAOImpl;
import revature.Model.Account;
import revature.Model.Customer;

public class CustomerController {

	CustomerDAO custer;
	
	public CustomerController(Javalin app) {
		
		custer = new CustomerDAOImpl();
		
		app.get("/customers/{customerid}", getHandler);
		app.post("/customers/", postHandler);
	}
	
	public Handler getHandler = ctx ->{
		String custerID = ctx.pathParam("customerid");
		int customerID = Integer.valueOf(custerID);
		Customer custy = custer.getInfo(customerID);
		ctx.json(custy);
	};
	
	public Handler postHandler = ctx ->{
		Customer custy = ctx.bodyAsClass(Customer.class);
		
		custer.create(custy);
		
		ctx.status(200);
	};
	
}
