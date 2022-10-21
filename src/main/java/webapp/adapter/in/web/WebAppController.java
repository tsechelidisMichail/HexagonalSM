package webapp.adapter.in.web;

import java.lang.reflect.Field;

import servers.WebApp;

public class WebAppController implements WebApp{
	private static final String DEPOSIT = "DEPOSIT";
	private static final String WITHDRAW = "WITHDRAW";
	private static final String EXIT = "EXIT";
	
	public String mapMethodController(String method, String[] data) {
		switch(method) {
			case DEPOSIT:
				DepositController depositController = new DepositController();
				return depositController.deposit(data);
			case WITHDRAW:
				WithdrawController withdrawController = new WithdrawController();
				return withdrawController.withdraw(data);
			case EXIT:
				return EXIT;
			default:
				return getMethods();
		}
	}
		
	public String getMethods() {
		StringBuilder str = new StringBuilder();
		for(Field field : WebAppController.class.getDeclaredFields()) {
			str.append(field.getName() + ",\n");
		}
		return str.toString();
	}
}
