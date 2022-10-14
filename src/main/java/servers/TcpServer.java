package servers;

class TcpServer extends ServerThread{
	private static final int PORT = 1233;
	
	public TcpServer(int id) throws Exception {
		super(id+PORT);
	}

	@Override
	protected String[] translateRequest(String request) {
		String[] data = request.split("-"); 
		return data;
	}

	@Override
	protected String getDataTranslationMethod() {
		return "Data shall be splited with - \nExample: DEPOSIT-123";
	}

}


