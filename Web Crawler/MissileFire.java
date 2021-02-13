/*
    Class: MissileFire
    Author: Anmol Jaising, Asmita Hari
    This class is used to take the player's coordinate and hit their opponents grid.
    
*/


public class MissileFire {

    int hitCount;
    String[][] temp;
    String result;

    public MissileFire(int shipLength) {
        this.hitCount = shipLength;
       System.out.println("Hitcount: "+hitCount);
    }
    
    // This method is used to send the grid to the Server.

    public void sendGrid(String[][] tempGrid) {
        this.temp = tempGrid;
    }
    
    // This function is used to bomb the opponenets grid and return that result back.

    public String letsBomb(String p, String q) {

        int x = Integer.parseInt(p);
        int y = Integer.parseInt(q);

        if (temp[x][y].equals("S")) {
            result = "HIT";
            temp[x][y] = "-";
            hitCount--;

        } else if (temp[x][y].equals("-")) {
            result = "MISS";
            temp[x][y] = "-";
        }
        System.out.println();

        if (hitCount == 0) {
            result = "SUNK";
        }
        System.out.println(hitCount);
        return result;
    }
}
