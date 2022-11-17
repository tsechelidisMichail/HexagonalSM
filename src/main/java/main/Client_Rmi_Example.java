package main;

import servers.WebApp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client_Rmi_Example{

    public static void main(String[] args){
        try {

            Registry registry = LocateRegistry.getRegistry();

            WebApp server = (WebApp) registry.lookup("WebApp");

            String responseMessage = server.mapMethodController("DEPOSIT", new String[]{"DEPOSIT","500"});

            System.out.println(responseMessage);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}