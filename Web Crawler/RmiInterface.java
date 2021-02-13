/*
    class: RmiInterface 
    author: Asmtia Hari, Anmol Jaising

    This class is used to definr the RMI Interface
*/

public interface RmiInterface extends java.rmi.Remote  {


    String hello() throws java.rmi.RemoteException;
    void setPlayer(boolean playera) throws java.rmi.RemoteException;
    boolean getPlayer() throws java.rmi.RemoteException;
    void setGrid(String[][] grid,boolean Playera) throws java.rmi.RemoteException;
    void setObj(RmiInterface obj) throws java.rmi.RemoteException;
    String[][] getGrid() throws java.rmi.RemoteException;
    String bomb(String cordinates) throws java.rmi.RemoteException;
    int getMissileCount() throws java.rmi.RemoteException;
}
