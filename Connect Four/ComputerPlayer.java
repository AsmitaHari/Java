import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
    This class is the main class that runs the game. You can either play user vs
    user. Or user vs computer.

    It will ask you for input disk a or b. If you select a, then player 2 gets
    assigned b. Vice versa.

    Then the game begins.
 */

public class ComputerPlayer implements ConnectFourPlayerInterface {
    Scanner scanner = new Scanner(System.in);

    int firstCounter;
    int secondCounter;
    char seletedChar;
    String playerName;
    int playerNumber;
    int winCount = 0;
    HumanPlayer player1;
    char createGrid[][];
    int colCount[]={0,0,0,0,0,0,0};

    /*
        This is the constructor which is used to assign the groupNummber and
        set it.

        @params: String playerName which is the custom name.
    */

    public ComputerPlayer(HumanPlayer player1) {

        this.player1=player1;
        playerName = "AI";
        secondCounter++;
        setPlayerNumber(secondCounter);
    }

    /*
        This is the constructor which is used to assign the groupNummber and
        set it.

        @param: None
    */

    public ComputerPlayer() {

    }

    /*
       This method is used to ask the user for an input for the column.
       It checks if the input is in the range 1 to 7. If it isn't, it sends an
       error

       @params: None
       @return: output i.e. which column the disk has to be inserted in.
   */
    public int takeTurn() {

        String[] splitValue;

        System.out.print(playerName + " selected column: ");

        int output=player1.getCol()+1;



         String value=check(player1.getRow(),player1.getCol());

         String vertical=findhorizontal(player1.getRow(),player1.getCol(),
                 Pattern.compile(".*aa.*"), Pattern.compile(".*aaa.*"));
         if(vertical!="")
             value=vertical;
        String diagonal;

        if(value==""&& vertical=="") {
            diagonal = findDiagonal(player1.getRow(), player1.getCol(),
                    Pattern.compile(".*aa.*"), Pattern.compile(".*aaa.*"));
            value = diagonal;
        }

         if(!value.equals("")) {
             splitValue = value.split(",");
             output = Integer.parseInt(splitValue[1]);
         }


         int checkValue=checkcolCount(output);
        System.out.println("array"+ Arrays.toString(colCount));
         System.out.println("checkcal "+checkValue +" "+  colCount[output]);
         if(checkValue!=-1&& colCount[output]==6){
             output=checkValue;
         }
         System.out.println("op"+output);

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
       This method is used to get the user's name.

       @params: None
       @return: String playerName i.e. name of the player.
   */
    public int getNumberOfWins() {
        return winCount;
    }

    /*
       This method is used to get the user's name.

       @params: None
       @return: String playerName i.e. name of the player.
   */
    public void addWin() {
        winCount++;
    }

    /*
        This method is used to set the game piece

        @params: gamePiece, that is the disk the user has selected.
        @return: None
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
            This method is used to set the game piece

            @params: gamePiece, that is the disk the user has selected.
            @return: None
        */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /*
        This method is used to set the game piece

        @params: gamePiece, that is the disk the user has selected.
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
            This method is used to set the game piece

            @params: gamePiece, that is the disk the user has selected.
            @return: None
        */
    public Pattern getPattern() {
        Pattern pattern = null;

        if (this.firstCounter == 1) {
            pattern = Pattern.compile(".*"+"aaaa"+".*");
        } else if (this.secondCounter == 1) {
            pattern = Pattern.compile(".*"+"cccc"+".*");
        }

        return pattern;
    }
    /*
        This method is used to set the game piece

        @params: row,col
        @return: string
    */
    public String check(int row,int col){


         char humanPiece=player1.getGamePiece();

         String twoPiece=""+humanPiece+humanPiece;
         Pattern p1 = Pattern.compile(".*" +twoPiece  + ".*");
         String threePiece=""+humanPiece+humanPiece+humanPiece;
         Pattern p2 = Pattern.compile(".*" +threePiece  + ".*");
         String value=findhorizontal(row,col,p1,p2);
         String vertical="";
          if(value==""){
              vertical=findVeritcal(row,col,p1,p2);
              value=vertical;
          }

        if(value==""&& vertical==""){

            value=findDiagonal(row,col,p1,p2);
        }

    return value;

    }
    /*
           This method is used to set the grid

           @params: row,col
           @return: string
       */
    public void setGrid(char[][] gird){
        createGrid=gird;
    }
    public char[][] getCreateGrid(){
        return createGrid;
    }


    /*
           This method is find horixontal

           @params: row,col,patternn
           @return: string
       */
    public String findhorizontal(int row,int col,Pattern twoPiece,Pattern threePiece){



        if(col==0){

            String word=""+createGrid[row][col]+createGrid[row][col+1]
                    +createGrid[row][col+2];
            if (twoPiece.matcher(word).find()) {

                return ""+row+","+(col+2);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+3);
            }





        }
        if(col==1)
        {
            String word=""+createGrid[row][col]+createGrid[row][col-1]
                    +createGrid[row][col+1];
            if (twoPiece.matcher(word).find()) {

                if(createGrid[row][col+1]=='-')
                    return ""+row+","+(col+1);
                else
                    return ""+row+","+(col-1);

            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+2);
            }
            word=""+createGrid[row][col]+createGrid[row][col+1]
                    +createGrid[row][col+2];
            if (twoPiece.matcher(word).find()) {


                return ""+row+","+(col+2);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+3);
            }



        }
        if(col==2)
        {

            String word=""+createGrid[row][col]+createGrid[row][col-1]
                    +createGrid[row][col-2];
            if (twoPiece.matcher(word).find()) {


                return ""+row+","+(col+1);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+2);
            }
            word=""+createGrid[row][col]+createGrid[row][col+1]
                    +createGrid[row][col+2];

            if (twoPiece.matcher(word).find()) {

                if(createGrid[row][col+1]=='-') {

                    return "" + row + "," + (col + 1);
                }
                else {
                    return "" + row + "," + (col - 1);
                }
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+3);
            }



        }
        if(col==3)
        {
            String word=""+createGrid[row][col]+createGrid[row][col-1]
                    +createGrid[row][col-2];
            if (twoPiece.matcher(word).find()) {

                if(createGrid[row][col+1]=='-')
                    return ""+row+","+(col+1);
                else
                    return ""+row+","+(col-1);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+2);
            }
            word=""+createGrid[row][col]+createGrid[row][col+1]
                    +createGrid[row][col+2];
            if (twoPiece.matcher(word).find()) {

                if(createGrid[row][col+2]=='-')
                    return ""+row+","+(col+2);
                else
                    return ""+row+","+(col-1);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+3);
            }




        }
        if(col==4)
        {
            String word=""+createGrid[row][col]+createGrid[row][col-1]
                    +createGrid[row][col-2];
            if (twoPiece.matcher(word).find()) {


                if(createGrid[row][col-2]=='-')
                    return ""+row+","+(col-2);
                else
                    return ""+row+","+(col+1);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col+2);
            }
            word=""+createGrid[row][col]+createGrid[row][col+1]
                    +createGrid[row][col+2];
            if (twoPiece.matcher(word).find()) {


                return ""+row+","+(col+1);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col-1);
            }



        }
        if(col==5)
        {
            String word=""+createGrid[row][col]+createGrid[row][col-1]
                    +createGrid[row][col-2];
            if (twoPiece.matcher(word).find()) {


                if(createGrid[row][col-2]=='-')
                    return ""+row+","+(col-2);
                else
                    return ""+row+","+(col+1);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col-2);
            }
            word=""+createGrid[row][col]+createGrid[row][col+1]+createGrid[row][col-1];

            if (twoPiece.matcher(word).find()) {


                return ""+row+","+(col-2);
            }
            if (threePiece.matcher(word).find()) {


                return ""+row+","+(col-2);
            }

        }



        return "";
    }
    /*
           This method is find horixontal

           @params: row,col,patternn
           @return: string
       */
    public String findVeritcal(int row,int col,Pattern twoPiece,Pattern threePiece){

        if(row-1>=0 && row-2>=0) {
            String verticalValue = "" + createGrid[row][col] + "" +
                    createGrid[row - 1][col] + "" + createGrid[row - 2][col];


            if (twoPiece.matcher(verticalValue).find()) {


                return ""+row+3+","+(col);
            }
            if (threePiece.matcher(verticalValue).find()) {


                return ""+row+","+(col);
            }

        }
        if(row+1<=5){
            if(row==4){

                String verticalValue = "" + createGrid[row][col] + "" +
                        createGrid[row +1][col];


                if (twoPiece.matcher(verticalValue).find()) {


                    return ""+row+","+(col);
                }

            }
            else if(row+2<=5){
                String verticalValue = "" + createGrid[row][col] + "" +
                        createGrid[row +1][col] + "" + createGrid[row + 2][col];


                if (twoPiece.matcher(verticalValue).find()) {


                    return ""+row+-3+","+(col);
                }
                if (threePiece.matcher(verticalValue).find()) {


                    return ""+row+","+(col);
                }
            }
        }

        return "";

    }
/*
           This method is find horixontal

           @params: row,col,patternn
           @return: string
       */

   public String findDiagonal(int row,int col,Pattern twoPiece,Pattern threePiec) {


       if((col==0 ||col==1||col==2) && row!=0 && row!=1 && row!=2) {


           String diagonalValue = "" + createGrid[row][col] + "" +
                   createGrid[row -1][col + 1] + "" + createGrid[row -2][col + 2];


           if (twoPiece.matcher(diagonalValue).find()) {

               if(colCount[3]==2)
                   return "" + row  + "," + (col+2);
               else if(colCount[col+1]!=6)
                   return "" + row  + "," + (col+3);
           }
           if (threePiec.matcher(diagonalValue).find()) {


               if(colCount[3]==3)
                   return "" + row  + "," + (col+3);
               else if(colCount[col+1]!=6)
                   return "" + row  + "," + (col+2);
           }
       }if((col==3 ||col==4))
       {
           if(row-1>=0 && row-2>=0 ) {
                String diagonalValue = "" + createGrid[row][col] + "" +
                       createGrid[row - 1][col + 1] + "" + createGrid[row - 2][col + 2];


               if (twoPiece.matcher(diagonalValue).find()) {

                   if (colCount[3] == 2)
                       return "" + row + "," + (col + 2);
                   else if (colCount[col + 1] != 6)
                       return "" + row + "," + (col + 3);
               }
               if (threePiec.matcher(diagonalValue).find()) {


                   if (colCount[3] == 3)
                       return "" + row + "," + (col + 3);
                   else if (colCount[col + 1] != 6)
                       return "" + row + "," + (col + 2);
               }
               String diagonalrevValue = "" + createGrid[row][col] + "" +
                       createGrid[row - 1][col + 1] + "" + createGrid[row - 2][col + 2];


               if (twoPiece.matcher(diagonalrevValue).find()) {

                   if (colCount[3] == 2)
                       return "" + row + "," + (col + 2);
                   else if (colCount[col + 1] != 6)
                       return "" + row + "," + (col + 3);
               }
               if (threePiec.matcher(diagonalrevValue).find()) {


                   if (colCount[col - 3] != 6)
                       return "" + row + "," + (col - 3);
                   else if (colCount[col - 1] != 6)
                       return "" + row + "," + (col - 1);
               }
           }
       }if((col==5 ||col==6))
       {
           if(row-1>=0 && row-2>=0 ) {
               String diagonalValue = "" + createGrid[row][col] + "" +
                       createGrid[row - 1][col - 1] + "" + createGrid[row - 2][col - 2];


               if (twoPiece.matcher(diagonalValue).find()) {

                   if (colCount[3] == 2)
                       return "" + row + "," + (col + 2);
                   else if (colCount[col + 1] != 6)
                       return "" + row + "," + (col + 3);
               }
               if (threePiec.matcher(diagonalValue).find()) {


                   if (colCount[3] == 3)
                       return "" + row + "," + (col + 3);
                   else if (colCount[col + 1] != 6)
                       return "" + row + "," + (col + 2);
               }

           }
       }


       return "";

   }
    /*
            This method is set count

            @params: row,col,patternn
            @return: string
        */
   public void setColCount(int[] colCount){
        this.colCount=colCount;
   }
   /*
           This method isuse to check col

           @params: row,col,patternn
           @return: string
       */

    public int checkcolCount(int col){

        if(colCount[0]==0 && colCount[1]==0
                &&colCount[2]==0 && colCount[3]==0 && colCount[4]==0 && colCount[5]==0
                && colCount[6]==0 )
            return -1;

       boolean flag=false;
        if(col==0 || col==1 || col==2 || col==3 || col==4 || col==5){

            if(colCount[0]==6) {
                flag=true;
                col = (1);
            }
            if(colCount[1]==6) {
                flag=true;
                col = (2);
            }

                if(colCount[2]==6) {
                flag=true;
                col = (3);
            }
             if(colCount[3]==6) {
                flag=true;
                col = (4);
            }
            if(colCount[4]==6) {
                flag=true;
                col = (6);
            }
             if(colCount[5]==6) {
                flag=true;
                col = (6);
            }
             if(colCount[6]==6) {
                flag=true;
                col = (-1);
            }
        }


        if(flag==false)
            return -1;


        return col;
    }
}
