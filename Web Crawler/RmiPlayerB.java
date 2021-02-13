/*
    class: RMI Player B
    author: Asmita Hari, Anmol Jaising

    This class is used to define the RMI Player 2
*/

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiPlayerB {


    /*
        This is the main function which defines the players grid, creates it ship
        and then makes a call to launch the GUI
    */
    
    public static void main(String[] args) {

        SetUp setUp = new SetUp();
        String[][] tempGrid;
        setUp.createGrid();
        tempGrid = setUp.grid;

        System.out.println("Ship of length 2: ");

        setUp.createShip(2);

        System.out.println("Ship of length 4: ");

        setUp.createShip(4);


        RmiInterface obj = null;
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }



        try {

            obj = (RmiInterface) Naming.lookup("//"+ip+":54545/BattleshipObject");
            obj.setGrid(tempGrid,false);
            obj.setPlayer(false);
            System.out.println(obj.hello());

            System.out.println(obj.hello());
            PlayerbGUI gui= new PlayerbGUI();
            gui.setgrid(tempGrid);
            gui.setObj(obj);
            gui.launchGui();
           /* final RmiInterface obj1=obj;
            new Thread(()-> {
                try {
                    gui.setObj(obj1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();*/
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
