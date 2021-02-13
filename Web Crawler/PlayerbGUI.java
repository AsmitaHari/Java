/*
    Class: PlayerBGUI
    Name: Anmol Jaising, Asmita Hari
    
    This class is used to launch the particular players GUI.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PlayerbGUI extends Application {

    TextField text;
    static RmiInterface obj;
    static String[][] grid;
    List<Button> bblist = new ArrayList<>();

    public void setgrid(String[][] grid) throws RemoteException {

        this.grid = grid;
    }

    public void setObj(RmiInterface obj) throws RemoteException {

        this.obj = obj;

    }

    /*
        This function is used to launch the GUI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        int SIZE = 10;
        int length = SIZE;
        int width = SIZE;
//creates a 10 * 10 grid
        GridPane root = new GridPane();
        TextField tf1 = new TextField();
        tf1.setEditable(false);
        tf1.setText("Game Begins");
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {

                Button bb = new Button();
                bb.setPrefHeight(50);
                bb.setPrefWidth(50);
                bb.setAlignment(Pos.CENTER);
                bb.setStyle("-fx-color: #256d7b");
                bb.setId(x + "," + y);
                bb.setOnAction(event -> changeColor(event, bb));

                // Iterate the Index using the loops
                root.setRowIndex(bb, x);
                root.setColumnIndex(bb, y);
                root.getChildren().add(bb);
            }
        }
//sets the text
        root.setRowIndex(tf1, 10);
        root.setColumnIndex(tf1, 9);
        root.getChildren().add(tf1);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Player B");
        primaryStage.setScene(scene);
        primaryStage.show();
        text = tf1;
    }

    public void launchGui() {

        System.out.println("Client ready");
        System.out.println("Select the target co-ordinates");
        launch();
    }

    /*
        This function is used to change the color of the button on click. 
     */
    public synchronized void changeColor(Event event, Button bb) {

        String receiveMessage = null;
        bblist.add(bb);

        String pos = ((Control) event.getSource()).getId();
        System.out.println(pos);
        try {
            obj.setGrid(grid, false);
            receiveMessage = obj.bomb(pos);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //button=bb;
        if (receiveMessage.equals("HIT")) {
            bb.setStyle("-fx-color: #DA2B27");
            bb.setText("Hit");
            System.out.println("HIT");

        } else if (receiveMessage.equals("MISS")) {
            bb.setStyle("-fx-color: #ffff");
            bb.setText("Miss");
            System.out.println("MISS");

        } else if (receiveMessage.equals("SUNK")) {
            // display on grid

            System.out.println("SUNK");

        }

        int count = 0;
        try {
            count = obj.getMissileCount();
            System.out.println(count);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (count == 6) {

            clearallcolor(bblist);
        }
    }

    /*
        Once any players ships are sunk, their grid is cleared and the gui is shut down.
     */
    void clearallcolor(List<Button> button) {

        String temp[][] = new String[10][10];
        try {
            obj.setGrid(temp, false);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (Button bb : button) {
            bb.setStyle("-fx-color: #256d7b");
            bb.setText("");

        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.

                text.setText("You have sunk all ships ");
            }
        });

    }

}
