package main;

import servers.ServerManager;

public class Main {
	
	public static void main(String[] args) {
		JdbcCreateTest.createDb();
		ServerManager.createServers();
		ServerManager.terminateServers();
		System.out.println("System exited.");
		System.exit(0);
	}
}
