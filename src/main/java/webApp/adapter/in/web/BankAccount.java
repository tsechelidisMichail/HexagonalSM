package webApp.adapter.in.web;

import java.lang.reflect.Field;

import servers.WebApp;

public class BankAccount implements WebApp{
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
		StringBuilder str = new StringBuilder();
		for(Field field : BankAccount.class.getDeclaredFields()) {
			str.append(field.getName().toString() + ",\n");
		}
		return str.toString();
	}
}
