/*
    class: RMI Implementation
    author: Anmol Jaising, Asmita Hari

    This class is used to provide the implementation for the RMI Interface.
*/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiImpl extends UnicastRemoteObject implements RmiInterface {

    String[][] grid;

    RmiInterface obj;
    boolean playera=false;
            MissileFire missile = new MissileFire(12);
    int playeracount=0;
    int playerbcount=0;

    protected RmiImpl() throws java.rmi.RemoteException {
    }

    @Override
    public String hello() throws java.rmi.RemoteException {

       return "hello from the other side:"+playera;

    }
    
    /*
        This method is used to set the player 
    */

    @Override
    public void setPlayer(boolean playera) throws RemoteException {
       this.playera=playera;
    }
    
    /*
        This method is used to get the player.
    */

    @Override
    public boolean getPlayer() throws RemoteException {
        return playera;
    }
    
    /*
        This method is used to set Player A's grid.
    */

    @Override
    public void setGrid(String[][] grid,boolean Playera) throws java.rmi.RemoteException {
        this.grid=grid;
        this.playera=Playera;
    }
    
    /*
        This is used to set the Object
    */

    @Override
    public void setObj(RmiInterface obj) throws RemoteException {
        this.obj=obj;

    }
    
    /*
        This is used to get the get.
    */

    @Override
    public String[][] getGrid() throws java.rmi.RemoteException {
        return grid;
    }

    /*
        This method is used to bomb the the opponents grid. returns H/M/S
    */

    @Override
    public String bomb(String cordinates) throws RemoteException {
        String data=null;

            missile.sendGrid(grid);

            String[] coordinates = cordinates.split(",");

            String x = coordinates[0];
            String y = coordinates[1];

            if (x.equals("10") && y.equals("10")) {
                System.out.println("Select the target ");
            } else {
//
                data= missile.letsBomb(x, y);
//
            }

            if(playera && data.equals("HIT")){
                playeracount++;
            }
            else if(!playera && data.equals("HIT")){
                playerbcount++;
            }


        return data;
    }
    
    /*
        This method is used to return the missile count.
    */

    @Override
    public int getMissileCount() throws RemoteException {
        if(playera)
        return playeracount;
        else
            return playerbcount;
    }
}
