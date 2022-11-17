package servers;

import webapp.adapter.in.web.WebAppController;

class TcpServerThread extends ServerThread{

	public TcpServerThread(int port){
		super(WebAppController.singleton(), port);

	}

	@Override
	String getDataTranslationMethod() {
		return "Data shall be splited with @ \nExample: DEPOSIT@123";
	}

	@Override
	String[] translateRequest(String request) {
		return request.split("@");
	}

}


