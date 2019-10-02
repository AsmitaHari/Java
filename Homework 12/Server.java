
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    ClassName: Client
    @authors: Anmol Jaising, Asmita Hari

    This methos defines the serve side operations.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        /*Here we are setting up the ships inital psotioans*/
        SetUp setUp = new SetUp();
        String[][] tempGrid;
        setUp.createGrid();
        tempGrid = setUp.grid;

        System.out.println("Ship of length 2: ");

        setUp.createShip(2);

        System.out.println("Ship of length 4: ");

        setUp.createShip(4);

        //calling th missile fire class
        MissileFire missileFire = new MissileFire(6);

        missileFire.sendGrid(tempGrid);

//setting up the client Gui as passing the data required foe the client gui
        ServerGUI serverGUI = new ServerGUI();
        serverGUI.setGrid(tempGrid);
        serverGUI.launchGui();

        ServerGUI.setMisileFire(missileFire);

    }
}
