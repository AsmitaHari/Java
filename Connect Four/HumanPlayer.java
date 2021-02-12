/*
    Classname: MyScanner

    Verson: 1.0
    Revisions: 0

    @authors: Anmol Jaising, Asmita Hari
 */

import java.util.Scanner;
import java.util.regex.Pattern;

/*
    This class is used to create the Human Player 
*/

public class HumanPlayer implements ConnectFourPlayerInterface {

    Scanner scanner = new Scanner(System.in);

    public boolean humanPlayerFlag = false;
    int firstCounter;
    int secondCounter;
    char seletedChar;
    String playerName;
    int playerNumber;
    int winCount = 0;
    int output;
    boolean large = false;
    int row;
    int col;

   

     /*
        This is the constructor which is used to assign the groupNummber and 
        set it.
        
        @params: String playerName which is the custom name.
    */
    
    public HumanPlayer(String playerName) {
        humanPlayerFlag = true;
        this.playerName = playerName;
        firstCounter++;
        setPlayerNumber(firstCounter);
    }

     /*
        This is the constructor which is used to assign the groupNummber and 
        set it.
        
        @param: None
    */
    
    
    public HumanPlayer() {
        humanPlayerFlag = true;
        playerName = "Group 5";
        secondCounter++;
        setPlayerNumber(secondCounter);
    }
    
    /*
        This method is used to check if the given input is a number or not.
        
        @params: String input, i.e. Number.
        @return: True/False
    */

    static boolean numberOrNot(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }
    
     /*
        This method is used to return whether is a human or computer playing.
        
        @params: None
        @return: True/False
    */
    
    public boolean getFlagValue()
    {
        return humanPlayerFlag;
    }
    
    /*
        This method is used to ask the user for an input for the column.
        It checks if the input is in the range 1 to 7. If it isn't, it sends an
        error
        
        @params: None
        @return: output i.e. which column the disk has to be inserted in.
    */
    
    public int takeTurn(){

        System.out.print(playerName + " select a column: ");
        String data = scanner.next();


        if(numberOrNot(data)== true)
        {
            output = Integer.parseInt(data)-1;
        }
        else
        {
            System.out.println("Enter a valid number");
            takeTurn();
        }

        if(output<0 || output>8)
            large = false;
        else
            large = true;

        while(large == false)
        {
            System.out.print(playerName + " select a column, again (1-7): ");
            takeTurn();
        }
        col=output;
        return output;
    }

    /*
        This method is used to get the user's name.
        
        @params: None
        @return: String playerName i.e. name of the player.
    */
    
    public String getName() {
        return playerName;
    }

    /*
        This method is used to get the number of wins the player has.
        
        @params: None
        @return: winCount.
    */
    
    public int getNumberOfWins() {
        return winCount;
    }

    /*
        This method is used to increament the win counter each time a player
        wins.
        
        @params: None
        @return: None
    */
    
    public void addWin() {
        winCount++;
    }

    /*
        This method is used to get the game piece.
        
        @params: None
        @return: selectedChar i.e. a/b
    */
    
    public char getGamePiece() {
        return seletedChar;
    }

    /*
        This method is used to set the game piece
        
        @params: gamePiece, that is the disk the user has selected.
        @return: None
    */
    
    public void setGamePiece(char gamePiece) {
        this.seletedChar = gamePiece;
    }
    
    /*
        This method is used to get the player's group number.
        
        @params: None
        @return: playerNumber
    */

    public int getPlayerNumber() {
        return playerNumber;
    }
    
    /*
        This method is used to set the players number.
        
        @params: num
        @return: None
    */

    public void setPlayerNumber(int num) {
        if (this.firstCounter == 1) {
            playerNumber = 1;
        } else if (this.secondCounter == 1) {
            playerNumber = 2;
        }
    }
    
    /*
        This method is used to get the pattern required to match to check.
        
        @params: None
        @return: patter, a Pattern class variable.a
    */

    public Pattern getPattern() {
        Pattern pattern = null;

        if (this.firstCounter == 1) {
            pattern = Pattern.compile(".*" + getGamePiece() + getGamePiece() + getGamePiece() + getGamePiece() + ".*");
        } else if (this.secondCounter == 1) {
            pattern = Pattern.compile(".*" + getGamePiece() + getGamePiece() + getGamePiece() + getGamePiece() + ".*");
        }

        return pattern;
    }
    
    /*
        This method is used to set the row number
        
        @params: 
        @return: None
    */
    

    public void setRow(int row){
        this.row=row;
    }
    
    /*
        This method is used to get the Row number
        
        @params: None
        @return: row i.e. row number.
    */
    
    public int getRow(){
        return row;
    }
    
    /*
        This method is used to get the Column number
        
        @params: None
        @return: col, i.e. column number.
    */

    public int getCol(){
        return col;
    }
}