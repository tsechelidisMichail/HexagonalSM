package main;

import servers.ServerManager;

public class Main {
	
	public static void main(String[] args) {
		ServerManager.createServers();
		ServerManager.terminateServers();
	}
}
