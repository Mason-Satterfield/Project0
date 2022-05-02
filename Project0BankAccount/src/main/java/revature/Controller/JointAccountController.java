package revature.Controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import revature.DAO.JointAccountDAO;
import revature.DAO.JointAccountDAOImpl;
import revature.Model.Account;
import revature.Model.JointAccount;

public class JointAccountController {
	
	JointAccountDAO jointaccount;
	
	public JointAccountController(Javalin app) {
		
		jointaccount = new JointAccountDAOImpl();
		
		app.get("/jointaccounts/{jointaccountid}", getHandler);
		app.post("/jointaccounts/apply", postHandler);
		app.put("/jointaccounts/{approve}",putHandler);
		app.delete("/jointaccounts/{accountid}", deleteHandler);
	}
	
	public Handler getHandler = ctx ->{
		String jaccID = ctx.pathParam("jointaccountid");
		int jointaccID = Integer.valueOf(jaccID);
		JointAccount jaccount1 = jointaccount.getAccount(jointaccID);
		ctx.json(jaccount1);
	};
	
	public Handler postHandler = ctx ->{
		
		JointAccount accoun = ctx.bodyAsClass(JointAccount.class);
		
		jointaccount.apply(accoun);
		
		ctx.status(201);
	};
	
	public Handler putHandler = ctx ->{
		JointAccount accoun = ctx.bodyAsClass(JointAccount.class);
		
		jointaccount.approve(accoun);
		
		ctx.status(200);
	};
	
	public Handler deleteHandler = ctx ->{
		JointAccount accoun = ctx.bodyAsClass(JointAccount.class);
		
		jointaccount.delete(accoun);
		
		ctx.status(200);
	};
}
