package revature.Controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import revature.DAO.AccountDAO;
import revature.DAO.AccountDAOImpl;
import revature.Model.Account;

public class AccountController {
	
	AccountDAO account;
	
	public AccountController(Javalin app) {
		
		account = new AccountDAOImpl();
		
		app.get("/accounts/{accountid}", getHandler);
		app.post("/accounts/apply", postHandler);
		app.put("/accounts/{approve}",putHandler);
		app.delete("/accounts/{accountid}", deleteHandler);
		
		
	}
	
	public Handler getHandler = ctx ->{
		String accID = ctx.pathParam("accountid");
		int accountID = Integer.valueOf(accID);
		Account account1 = account.getAccount(accountID);
		ctx.json(account1);
	};
	
	public Handler postHandler = ctx ->{
		
		Account accoun = ctx.bodyAsClass(Account.class);
		
		account.apply(accoun);
		
		ctx.status(201);
	};
	
	public Handler putHandler = ctx ->{
		Account accoun = ctx.bodyAsClass(Account.class);
		
		account.approve(accoun);
		
		ctx.status(200);
	};
	
	public Handler deleteHandler = ctx ->{
		Account accoun = ctx.bodyAsClass(Account.class);
		
		account.delete(accoun);
		
		ctx.status(200);
	};
	
}
