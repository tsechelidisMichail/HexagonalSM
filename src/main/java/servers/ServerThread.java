package servers;

abstract class ServerThread extends Thread{
	
	private WebApp webApp;
	private boolean terminated;

	protected abstract boolean connectionIsUp();
	protected abstract void acceptConnection();
	protected abstract void prepareRequestHandle();
	protected abstract boolean transactionIsEnded(String response);
	protected abstract String[] translateRequest();
	protected abstract void sendResponse(String response);
	protected abstract String getDataTranslationMethod();
	protected abstract void closeIO();
	protected abstract void terminateTransaction();
	protected abstract void terminateConnection();
	
	protected ServerThread(int port, WebApp webApp){
		this.webApp = webApp;
		terminated = false;
	}
	
	@Override
	public void run() {
		while (!terminated && connectionIsUp()) {
			acceptConnection();
			//at this step we may  want to select the webApp/protocol maybe?
			prepareRequestHandle();
			
			String response = "";
			while(!terminated && !transactionIsEnded(response)) {
				String[] data = translateRequest();
				response = webApp.mapMethodController(data[0], data);
				sendResponse(response);
			}
			terminateTransaction();
		}
		terminateConnection();
	}
	
	public void terminate() {
		terminated = true;
		closeIO();
		terminateTransaction();
		terminateConnection();
	}
}
