package servers;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WebApp extends Remote {
	String mapMethodController(String method, String[] data) throws RemoteException;
	String getMethods() throws RemoteException;
}
