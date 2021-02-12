/*
    Classname: ConnectFourDriver

    Verson: 1.0
    Revisions: 0

    @authors: Anmol Jaising, Asmita Hari
 */

import java.util.Scanner;

/*
    This class is the main class that runs the game. You can either play user vs
    user. Or user vs computer.

    It will ask you for input disk a or b. If you select a, then player 2 gets 
    assigned b. Vice versa.

    Then the game begins.
 */
public class ConnectFourDriver {

    /*
        Main method of the program.
     */
    public static void main(String[] args) {

        // We can choose eiter human vs human OR human vs computer.
        System.out.println("Choose : 1) Human vs Human 2)Human vs Computer");
        Scanner scanner = new Scanner(System.in);

        int output = scanner.nextInt();

        if (output == 1 | output == 2) {
            System.out.println("Okay,....");
        } else {
            System.out.println("Automatically assigning.. ");
        }

        ConnectFourPlayerInterface player1 = new HumanPlayer("Bob");
        ConnectFourPlayerInterface player2 = new ComputerPlayer(((HumanPlayer) player1));
        if (output == 1) {
            player1 = new HumanPlayer("Bob");
            player2 = new HumanPlayer();
        }
        if (output == 2) {
            player1 = new HumanPlayer("Bob");
            player2 = new ComputerPlayer(((HumanPlayer) player1));
        }

        // Displaying which player is what group
        System.out.println(player1.getName() + " is: " + player1.getPlayerNumber());
        System.out.println(player2.getName() + " is: " + player2.getPlayerNumber());

        // Selecting the piece
        System.out.print(player1.getName() + " select a single char game piece (a/b):  ");
        char firstChoise = scanner.next().toLowerCase().charAt(0);

        // Displaying what each player has got.
        player1.setGamePiece(firstChoise);
        System.out.println(player1.getName() + ", you have selected: " + player1.getGamePiece());

        // System.out.print(player2.getName() + " has selected the picece : ");
        char secondChoice;
        if (firstChoise == 'a') {

            secondChoice = 'b';
        } else {
            secondChoice = 'a';
        }
        player2.setGamePiece(secondChoice);
        System.out.println(player2.getName() + ", you have selected: " + player2.getGamePiece());

        System.out.println();

        // Begining the game.
        System.out.println("Welcome to Connect Four!!!!!");

        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        if (output == 2) {
            ((ComputerPlayer) player2).setGrid(((ConnectFourGame) game).characterGrid);
            ((ComputerPlayer) player2).setColCount(((ConnectFourGame) game).getColCount());
        }

        game.playGame();
//        game.getStats();
    }
}
