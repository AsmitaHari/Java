import java.io.IOException;

/*
    ClassName: Client
    @authors: Anmol Jaising, Asmita Hari

    This methos defines the clinet side operations.
 */
public class Client {

    public static void main(String[] args) throws IOException {
//        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        /*Here we are setting up the ships inital psotioans*/
        SetUp setUp = new SetUp();
        String resString = "";
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
        ClientGUI clientGUI = new ClientGUI();
        clientGUI.setGrid(tempGrid);
        clientGUI.launchGui();

        ClientGUI.setMisileFire(missileFire);

    }
}
