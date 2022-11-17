package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class ServerManager {
	private static final String TERMINATE = "TERMINATE";
	private static final String TCP = "TCP";
	private static final String RMI = "RMI";
	private static final ArrayList<ServerThread> servers = new ArrayList<>();
	
	private ServerManager() {
		
	}
	
	public static void createServers() {
		final int PORT = 1233;

		int serverID = servers.size() + 1 + PORT;

		while(serverID<5000) {

			serverID = servers.size() + 1 + PORT;

			String protocol = getProtocol();
			
			if(protocol.equals(TERMINATE)) {
				System.out.println("Terminated");
				break;
			}else if(protocol.equals(TCP)){
				startServer(new TcpServerThread(serverID));
			}
			else if(protocol.equals(RMI)){
				new ServerRmi();
			}
		}
	}
	
	private static void startServer(ServerThread server) {
		server.start();
		servers.add(server);
	}
	
	/*TODO: IMPORTANT
	 * Socket Threading keeps on waiting for input even after everything is closed (when you use join() )
	 * So we use stop, enforce closing everything at terminate() before using stop()
	 * A normal useCase wouldn't want to terminate a server.
	 * Even in mid-transaction it just ends at the CURRENT input submission of user.(if it exists).
	 */
	@SuppressWarnings("removal")
	public static void terminateServers() {
		for(ServerThread server : servers) {
			server.terminate();
			server.stop();
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
	

}
