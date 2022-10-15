package account.adapter.in.web;

import java.lang.reflect.Field;

public abstract class AccountController {
	private static final String DEPOSIT = "DEPOSIT";
	private static final String EXIT = "EXIT";
	
	private AccountController() {
		
	}
	
	public static String mapMethodController(String method, String[] data) {
		switch(method) {
			case DEPOSIT:
				DepositController controller = new DepositController();
				return controller.deposit(data);
			case EXIT:
				return EXIT;
			default:
				return AccountController.getMethods();
		}
	}
		
	private static String getMethods() {
		String response = "";
		for(Field field : AccountController.class.getDeclaredFields()) {
			response += field.getName().toString() + ",\n";
		}
		return response;
	}
}
