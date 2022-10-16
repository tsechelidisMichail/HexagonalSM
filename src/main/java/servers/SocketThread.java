package servers;

import java.io.*;
import java.net.*;

final class SocketThread extends Thread
{
	private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
	private static final String EXIT = "EXIT";
	
	private ServerThread server;

   	public SocketThread(Socket socket,ServerThread server)
   	{
		try {
			this.dataSocket = socket;
			this.server = server;
			
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}

	public void run()
	{
   		String request = "";
   		String response = "";
		
		try {
			while(!response.equals(EXIT)) {
				out.println(server.getDataTranslationMethod());
				request = in.readLine();
				String[] data = server.translateRequest(request);
				response = server.getWebApp().mapMethodController(data[0], data);
				out.println(response);
			}		
			dataSocket.close();
			System.out.println("Connection closed");
		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}
