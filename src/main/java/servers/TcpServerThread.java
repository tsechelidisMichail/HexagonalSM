package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import webapp.adapter.in.web.WebAppController;

class TcpServerThread extends ServerThread{
	private static final String EXIT = "EXIT";
	
	protected ServerSocket connectionSocket;
	protected Socket dataSocket;
	
	private PrintWriter out;
	private BufferedReader in;
	
	//This webApp may be selected in future.
	public TcpServerThread(int port){	
		super(new WebAppController());
		
		try {
			connectionSocket = new ServerSocket(port);
			System.out.println("Server is listening to port: " + port);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getDataTranslationMethod() {
		return "Data shall be splited with @ \nExample: DEPOSIT@123";
	}

	@Override
	protected boolean connectionIsUp() {
		return !connectionSocket.isClosed();
	}

	@Override
	protected void acceptConnection() {
		try {
			dataSocket = connectionSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected void prepareRequestHandle() {
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
	
	@Override
	protected boolean transactionIsEnded(String response) {
		return response.equals(EXIT);
	}
	
	@Override
	protected String[] translateRequest() {
		String request = "";
		try {
			out.println(getDataTranslationMethod());
			request = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request.split("@");
	}
	
	@Override
	protected void sendResponse(String response) {
		out.println(response);	
	}
	
	@Override
	protected void closeIO() {
		try {
			if(in != null && out != null) {
				in.close();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void terminateTransaction() {
		try {
			if(dataSocket != null) {
				dataSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void terminateConnection() {
		try {
			if(dataSocket != null) {
				connectionSocket.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}


