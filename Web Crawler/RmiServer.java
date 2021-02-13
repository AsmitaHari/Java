/*
    class: RMIServer
    author: Asmita Hari, Anmol Jaising.

    This class is used to create a local registry and bind the the RMI interface
    object to it.
*/

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class RmiServer {

    public static void main(String[] args) {
        try {
            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            RmiInterface obj = null;
            try {
                obj = new RmiImpl();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            try {

                LocateRegistry.createRegistry(54545);
                Naming.rebind("//" + ip + ":54545/BattleshipObject", obj);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }

            System.out.println("PCServer bound in registry");
        } catch (Exception e) {
            System.out.println("PCImpl err: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


