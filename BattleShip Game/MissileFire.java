/*
    Class: Missile File
    @author: Anmol Jaising, Asmita Hari
    
    This class is used to define if the bomb hits, misses or sinks the ship.
 */

public class MissileFire {

    int hitCount;
    String[][] temp;
    String result;

    /*
        Constructor used to define the total hit count.
     */
    public MissileFire(int shipLength) {
        this.hitCount = shipLength;
    }

    /*
        This is used to store the grid after the ships are created. 
     */
    public void sendGrid(String[][] tempGrid) {
        this.temp = tempGrid;
    }

    /*
        This take the coordinates from the users click on the grid.
    
        It checks across what he has hit. If its matching with the opponents grid,
        it returns a HIT.
    
        If not, it returns a MISS.
    
        If all the blocks on the opponents grid has been hit, it returns a SINK.
     */
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
        return result;
    }
}
