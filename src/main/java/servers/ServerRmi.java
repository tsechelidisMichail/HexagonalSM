package servers;

import webapp.adapter.in.web.WebAppController;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRmi {
    private Registry registry;
    WebApp webApp;


    public ServerRmi() {
        webApp = WebAppController.singleton();
        try {
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("WebApp", webApp);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
