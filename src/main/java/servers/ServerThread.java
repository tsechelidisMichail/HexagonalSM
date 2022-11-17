package servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

abstract class ServerThread extends Thread{

	private static final String EXIT = "EXIT";
	private ServerSocket connectionSocket;
	private Socket dataSocket;
	private PrintWriter out;
	private BufferedReader in;
	private WebApp webApp;
	private boolean terminated;

	abstract String getDataTranslationMethod();
	abstract String[] translateRequest(String request);

	protected ServerThread(WebApp webApp, int port){
		this.webApp = webApp;
		terminated = false;
		try {
			connectionSocket = new ServerSocket(port);
			System.out.println("Server is listening to port: " + port);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!terminated && connectionIsUp()) {
			acceptConnection();
			prepareRequestHandle();

			String response = "";
			while(!terminated && !transactionIsEnded(response)) {
				String[] data = translateRequest(getRequest());
				getResponse(data[0], data);
				sendResponse(response);
			}
			terminateTransaction();
		}
		terminateConnection();
	}

	private boolean connectionIsUp() {
		return !connectionSocket.isClosed();
	}

	private void acceptConnection() {
		try {
			dataSocket = connectionSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void prepareRequestHandle() {
		try {

			InputStream is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
		}
		catch (IOException e)	{
	 		System.err.println("I/O Error " + e);
		}
	}

	private boolean transactionIsEnded(String response) {
		return response.equals(ServerThread.EXIT);
	}

	private String getRequest(){
		out.println(getDataTranslationMethod());
		String request = "";

		try {
			request = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return request;
	}

	private String getResponse(String method, String[] data){
		String response = "";
		try {
			response = webApp.mapMethodController(method, data);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return response;
	}

	private void sendResponse(String response) {
		out.println(response);
	}

	private void closeIO() {
		try {
			if(in != null && out != null) {
				in.close();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void terminateTransaction() {
		try {
			if(dataSocket != null) {
				dataSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void terminateConnection() {
		try {
			if(dataSocket != null) {
				connectionSocket.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	void terminate() {
		terminated = true;
		closeIO();
		terminateTransaction();
		terminateConnection();
	}
}
