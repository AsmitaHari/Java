
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

 /*
          This Class exteds the connectfour fame interfance
       */

public class ConnectFourGame implements ConnectFourGameInterface {

    char firstChar;
    char secondChar;
    char[][] characterGrid = new char[6][7];
    int height = 6;
    int width = 7;
    int counter = 1;
    int chanceCount = 1;
    ConnectFourPlayerInterface player1;
    ConnectFourPlayerInterface player2;
    boolean status = false;
    boolean toContinue = true;
    Scanner scanner = new Scanner(System.in);
    int colCount[]={0,0,0,0,0,0,0};

 /*
           This method constitorxct to set values

           @params: row,col,patternn
           @return: string
       */

    public ConnectFourGame(ConnectFourPlayerInterface firstPlayer, ConnectFourPlayerInterface secondPlayer) {
        this.player1 = firstPlayer;
        this.player2 = secondPlayer;
        firstChar = firstPlayer.getGamePiece();
        secondChar = secondPlayer.getGamePiece();
        createGrid();
        displayGrid();
    }

    /*
          This method isuse to cretae grid

          @params: row,col,patternn
          @return: string
      */
    public void createGrid() {
        for (int index = 0; index < 6; index++) {
            for (int jIndex = 0; jIndex < 7; jIndex++) {
                characterGrid[index][jIndex] = '-';
            }
        }
    }

    /*
          This method isuse to display grid

          @params: row,col,patternn
          @return: string
      */
    public void displayGrid() {
        for (int index = 0; index < 6; index++) {
            for (int jIndex = 0; jIndex < 7; jIndex++) {
                System.out.print(characterGrid[index][jIndex] +"\t");

            }
            System.out.println();

        }
    }

    /*
          This method isuse to add discs

          @params: row,col,patternn
          @return: string
      */
    public void Drop(int playercounter,int column, ConnectFourPlayerInterface player) {
        boolean gameFlag = true;


        here:
        while (gameFlag == true) {
//                if (chanceCount == 42) {
//                    gameFlag = false;
//                }
            for (int h = height - 1; h >= 0; h--) {
                if (characterGrid[0][column] != '-') {
                    gameFlag = false;
                    
                } else if (characterGrid[h][column] == '-') {
                    if (playercounter==1){((HumanPlayer)player).setRow(h);}
                    characterGrid[h][column] = player.getGamePiece();
                    colCount[column]=colCount[column]+1;
                    displayGrid();
                    find(characterGrid, player.getPattern(), player);
                    return;
                }

            }

        }

        System.out.println("Column full, try again!");
        Drop(playercounter,player.takeTurn(), player);
    }

    /*
          This method find the patter

          @params: row,col,patternn
          @return: string
      */
    public boolean find(char[][] grid, Pattern wordPattern, ConnectFourPlayerInterface player) {
        if (findVertical(grid, wordPattern, player)) {
            status = true;
            return status;
        } else if (findHorizontal(grid, wordPattern, player)) {
            status = true;
            return status;
        } else if (findDiagonalLeftRight(grid, wordPattern, player)) {
            status = true;
            return status;
        }
        return status;

    }

    /*
          This method find the patter

          @params: row,col,patternn
          @return: string
      */
    public boolean findDiagonalRightLeft(char[][] grid, Pattern wordPattern, ConnectFourPlayerInterface player) {

        return false;
    }
    /*
          This method find the patter

          @params: row,col,patternn
          @return: string
      */

    public boolean findDiagonalLeftRight(char[][] grid, Pattern wordPattern, ConnectFourPlayerInterface player) {

        int rows = grid.length;
        int cols = grid[0].length;
        int maxSum = rows + cols - 2;

        for (int sum = 0; sum <= maxSum; sum++) {
            String word = "";
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i + j - sum == 0) {
                        word = grid[i][j] + word;

                    }
                }
                if (matchWord(word, wordPattern, player)) {
                    return true;
                }
            }

        }
        return false;
    }

    /*
          This method find the patter

          @params: row,col,patternn
          @return: string
      */
    public boolean findHorizontal(char[][] grid, Pattern wordPattern, ConnectFourPlayerInterface player) {

        for (int row = 0; row < height; row++) {
            String word = "";
            for (int column = 0; column < width; column++) {
                word += grid[row][column];
            }

            if (matchWord(word, wordPattern, player)) {
                return true;
            }
        }
        return false;
    }

    /*
          This method find the patter

          @params: row,col,patternn
          @return: string
      */
    public boolean findVertical(char[][] grid, Pattern wordPattern, ConnectFourPlayerInterface player) {

        for (int column = 0; column < width; column++) {
            String word = "";
            for (int row = 0; row < height; row++) {
                word += grid[row][column];
            }

            if (matchWord(word, wordPattern, player)) {
                return true;
            }
        }
        return false;
    }

    /*
          This method matched the word

          @params: row,col,patternn
          @return: string
      */
    public boolean matchWord(String word, Pattern wordPattern, ConnectFourPlayerInterface player) {

        if (wordPattern.matcher(word).find()) {
            System.out.println(player.getName() + " wins");
            player.addWin();
            return true;
        } else if (wordPattern.matcher(revString(word)).find()) {
            System.out.println(player.getName() + " wins");
            player.addWin();
            return true;
        }

        return false;
    }

    /*
        This method to reverse string

        @params: row,col,patternn
        @return: string
    */
    public String revString(String word) {
        String reverse = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reverse = reverse + word.charAt(i);
        }

        return reverse;
    }

    /*
        This method to continue game
        @params: row,col,patternn
        @return: string
    */
    public boolean continueGame() {
        System.out.print("Would you like to continue? (y/n):  ");
        String output = scanner.next().toLowerCase();

        if (output.equals("y")) {
            for(int i=0;i<colCount.length;i++)
                colCount[i]=0;
            toContinue = true;
            status = false;
        } else if (output.equals("n")) {
            toContinue = false;
        }

        return toContinue;
    }
    /*
        This method to play game
        @params: row,col,patternn
        @return: string
    */

    public void playGame() {
        here:
        while (status == false) {
            if (counter == 1) {
                 int value=player1.takeTurn();
                Drop(1,value, player1);

                chanceCount++;
                counter = 2;
            } else if (counter == 2) {
                counter = 1;
                int value=player2.takeTurn();
                Drop(2,value , player2);
                chanceCount++;
            }
        }

        boolean flag = continueGame();
        // System.out.println(flag);

        if (flag == false) {
            getStats();
        } else {
            // status = false;
            System.out.println("Let's play the game, again!");
            System.out.println();
            counter = 1;
            createGrid();
            displayGrid();
            playGame();
        }

    }
    /*
        This method to get stast
        @params: row,col,patternn
        @return: string
    */

    public void getStats() {
        System.out.println(player1.getName() + " has " + player1.getNumberOfWins() + " number of wins.");
        System.out.println(player2.getName() + " has " + player2.getNumberOfWins() + " number of wins.");
    }

    /*
        This method to conut
        @params: row,col,patternn
        @return: string
    */
    public int[] getColCount() {
        return colCount;
    }
}
