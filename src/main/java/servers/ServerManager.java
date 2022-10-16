package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

import servers.ServerThread.ServerAlreadyRunningException;

public abstract class ServerManager {
	private static final String TERMINATE = "TERMINATE";
	private static final String TCP = "TCP";
	private static final ArrayList<ServerThread> servers = new ArrayList<ServerThread>();
	
	private ServerManager() {
		
	}
	
	public static void createServers() {
		String protocol = "";
		while(!protocol.equals(TERMINATE)) {
			protocol = getProtocol();
			startServer(protocol);
		}
	}
	
	public static void terminateServers() {
		for(ServerThread server : servers) {
			server.terminate();
		}
		
	}
	
	private static String getProtocol() {
		String protocol = "";
		System.out.println("Please select Protocol:" + ServerManager.getProtocols());	
		try {
			protocol = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return protocol;
	}
	
	private static String getProtocols() {
		StringBuilder str = new StringBuilder();
		for(Field field : ServerManager.class.getDeclaredFields()) {
			str.append("\n");
			str.append(field.getName());
		}
		return str.toString().replace("servers", "");
	}
	
	private static void startServer(String protocol) {
		int serverID = servers.size() + 1;
		ServerThread server = null;
		try {
			switch(protocol) {
				case TCP:
					server = new TcpServer(serverID);
					break;
				default:
					System.out.println("Selected: " + protocol);
					break;
			}
			server.start();
			servers.add(server);
		}catch (ServerAlreadyRunningException e) {
			System.err.println("already running");
		}catch(NullPointerException e) {
			System.err.println("This Server option doesn't exist.");
		}
	}
	

}
