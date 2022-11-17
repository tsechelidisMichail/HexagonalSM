package webapp.adapter.in.web;

import servers.WebApp;

import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WebAppController extends UnicastRemoteObject implements WebApp{
	private static final String DEPOSIT = "DEPOSIT";
	private static final String WITHDRAW = "WITHDRAW";
	private static final String EXIT = "EXIT";
	private static WebAppController webAppController;

	private WebAppController() throws RemoteException {
		super();
	}

	public static WebAppController singleton(){
		if(webAppController == null){
			try {
				webAppController = new WebAppController();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return webAppController;
	}

	@Override
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

	@Override
	public String getMethods() {
		StringBuilder str = new StringBuilder();
		for(Field field : WebAppController.class.getDeclaredFields()) {
			str.append(field.getName());
			str.append(",\n");
		}
		return str.toString();
	}
}
