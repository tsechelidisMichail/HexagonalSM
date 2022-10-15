package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Server {
	private static final String TERMINATE = "TERMINATE";
	private static final String TCP = "TCP";
	private static final ArrayList<ServerThread> servers = new ArrayList<ServerThread>();
	
	private Server() {
		
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
		System.out.println("Please select Protocol:" + Server.getProtocols());	
		try {
			protocol = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return protocol;
	}
	
	private static String getProtocols() {
		String servers = "";
		for(Field field : Server.class.getDeclaredFields()) {
			servers += "\n" + field.getName().toString();
		}
		servers = servers.replace("servers", "");
		return servers;
	}
	
	private static void startServer(String protocol) {
		if(!protocol.equals("")){
			int serverID = servers.size() + 1;
			ServerThread server = null;
			if(protocol.equals(TCP)) {
				try {
					server = new TcpServer(serverID);
				} catch (Exception e) {
					System.out.println("already running");
				}
			}
			if(server!=null) {
				server.start();
				servers.add(server);
			}
		}
	}
	

}
