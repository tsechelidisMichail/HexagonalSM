package servers;

import webApp.adapter.in.web.AccountController;

class TcpServer extends ServerThread{
	private static final int PORT = 1233;
	
	//TODO: This webApp may be selected in future.
	public TcpServer(int id) throws Exception {
		super(id+PORT, new AccountController());
	}

	@Override
	protected String[] translateRequest(String request) {
		String[] data = request.split("@"); 
		return data;
	}

	@Override
	protected String getDataTranslationMethod() {
		return "Data shall be splited with @ \nExample: DEPOSIT@123";
	}

}


