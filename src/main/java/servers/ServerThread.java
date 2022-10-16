package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

abstract class ServerThread extends Thread{
	private ServerSocket connectionSocket;
	private Socket dataSocket;
	
	private WebApp webApp;
	
	protected abstract String[] translateRequest(String request);
	protected abstract String getDataTranslationMethod();
	
	protected ServerThread(int port, WebApp webApp) throws ServerAlreadyRunningException{
		try {
			this.webApp = webApp;
			
			connectionSocket = new ServerSocket(port);
			System.out.println("Server is listening to port: " + port);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void terminate() {
		try {
			if(dataSocket!=null) {
				dataSocket.close();
			}
			//TODO: Close properly the ServerSocket
			connectionSocket.close();
			this.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (!connectionSocket.isClosed()) {
			try {
				dataSocket = connectionSocket.accept();
				System.out.println("Received request from " + dataSocket.getInetAddress());

				SocketThread sthread = new SocketThread(dataSocket, this);
				sthread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public WebApp getWebApp() {
		return webApp;
	}
	
	class ServerAlreadyRunningException extends Exception { 
	    /**
		 * 
		 */
		private static final long serialVersionUID = 2876623959016730698L;

		public ServerAlreadyRunningException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
}
