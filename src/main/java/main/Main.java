package main;

import servers.Server;

public class Main {
	
	public static void main(String[] args) {
		Server.createServers();
		Server.terminateServers();
	}
}
