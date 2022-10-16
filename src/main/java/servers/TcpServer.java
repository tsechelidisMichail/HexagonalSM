package servers;

import webApp.adapter.in.web.BankAccount;

class TcpServer extends ServerThread{
	private static final int PORT = 1233;
	
	//TODO: This webApp may be selected in future.
	public TcpServer(int id) throws ServerAlreadyRunningException {
		super(id+PORT, new BankAccount());
	}

	@Override
	protected String[] translateRequest(String request) {
		return request.split("@");
	}

	@Override
	protected String getDataTranslationMethod() {
		return "Data shall be splited with @ \nExample: DEPOSIT@123";
	}

}


