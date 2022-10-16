package webApp.adapter.in.web;

import java.lang.reflect.Field;

import servers.WebApp;

public class AccountController implements WebApp{
	private static final String DEPOSIT = "DEPOSIT";
	private static final String EXIT = "EXIT";
	
	public String mapMethodController(String method, String[] data) {
		switch(method) {
			case DEPOSIT:
				DepositController controller = new DepositController();
				return controller.deposit(data);
			case EXIT:
				return EXIT;
			default:
				return getMethods();
		}
	}
		
	public String getMethods() {
		String response = "";
		for(Field field : AccountController.class.getDeclaredFields()) {
			response += field.getName().toString() + ",\n";
		}
		return response;
	}
}
