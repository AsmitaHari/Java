
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
    ClassName: Client
    @authors: Anmol Jaising, Asmita Hari

    This class creates the gui
 */
public class ClientGUI extends Application {
//variables for connecions

    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    static ServerSocket serverSocket;
    static Socket socket;
    static OutputStream outStream;
    static PrintWriter printWriter;
    static InputStream inputStream;
    static BufferedReader receiveRead;
    static MissileFire missile = new MissileFire(6);
    String[][] grid;
    Button button = null;
    TextField text;

    //sets the missile fire
    public static void setMisileFire(MissileFire missileFire) {
        missile = missileFire;
    }

    //sets the grid
    public void setGrid(String[][] temp) {
        grid = temp;
        System.out.println(grid.length);
    }
//Strts the application

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
                // bb.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonHandler());
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
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        text = tf1;
        //creates a thread to recieve data
        new Thread(() -> {
            String receiveMessage, sendMessage;
            String resString = "";
            while (true) {
                // flush the data
                try {
                    if ((receiveMessage = receiveRead.readLine()) != null) {

                        if (receiveMessage.equals("HIT")) {
                            // display on grid
                            try {
                                if (!button.equals(null)) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Update UI here.
                                            button.setStyle("-fx-color: #DA2B27");
                                            button.setText("Hit");
                                        }
                                    });

                                }
                            } catch (Exception e) {

                            }
                            System.out.println("HIT");
                            printWriter.println("10,10");
                        } else if (receiveMessage.equals("MISS")) {
                            // display on grid
                            try {
                                if (!button.equals(null)) {

                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Update UI here.
                                            button.setStyle("-fx-color: #ffff");
                                            button.setText("Miss");
                                        }
                                    });
                                }
                            } catch (Exception e) {
                            }
                            System.out.println("MISS");
                            printWriter.println("10,10");
                        } else if (receiveMessage.equals("SUNK")) {
                            // display on grid
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    // Update UI here.

                                    text.setText("You have sunk all ships ");
                                }
                            });
                            System.out.println("SUNK");
                            printWriter.println("10,10");
                        } else {
                            System.out.println("Received: " + receiveMessage);
                            String[] coordinates = receiveMessage.split(",");

                            String x = coordinates[0];
                            String y = coordinates[1];

                            if (x.equals("10") && y.equals("10")) {
                                System.out.println("Select the target ");
                            } else {
//                                System.out.println("val" + x + y);
                                resString = missile.letsBomb(x, y);
//                                System.out.println("Opponent has: " + resString + " you");
                                printWriter.println(resString);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //launch the gui
    public void launchGui() throws IOException {

        //accepts the connection
        socket = new Socket("127.0.0.1", 1234);
        outStream = socket.getOutputStream();
        printWriter = new PrintWriter(outStream, true);

        inputStream = socket.getInputStream();
        receiveRead = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println("Client ready");
        System.out.println("Select the target co-ordinates");
        missile.sendGrid(grid);

        launch();
    }

    //on button click action
    public void changeColor(Event event, Button bb) {

        String pos = ((Control) event.getSource()).getId();
        System.out.println(pos);
        String receiveMessage, sendMessage;
        button = bb;

        //create thread to send data
        new Thread(() -> {
            printWriter.println(pos);
        }).start();

    }
}
