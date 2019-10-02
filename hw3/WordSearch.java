
/*
    Classname:WordSearch

    Verson: 1.0
    Revisions: 0

    @authors: Anmol Jaising, Asmita Hari
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
   This class accepts the file from the user and
   accepts the word that need to found.Searches the word if found returns true
*/
public class WordSearch {

    /**
     * get line count
     *
     * @param filename file name
     *
     * @return count of the lines
     *
     *
     */

    public static int getLineCount(String filename){

        int countline=0;//variable to count line
        /* try and cathc bolck to throw exception if file is not found.
         * Get the file using scanner */
        try (Scanner reader = new Scanner(new File("scratch_3.txt"))) {


            while (reader.hasNextLine()) {//read till there is a line
                countline++;//increment the count
                reader.nextLine();//find the next line
            }
        }
        catch (FileNotFoundException ex){

        }

       return countline;//return count of the numer of lines
    }

    /**
     * find the word
     *
     * @param puzzle 2d array of characters
     *        wordPattern pattern to be matched
     *
     * @return true if word is found
     *
     *
     */

   public boolean find(String[][] puzzle,Pattern wordPattern,String word){

        /*check if word is found horizontallly */
        if(findhorizontal(puzzle,wordPattern,word)){

            return true;
        }
        //*else if check if word is found vertically *//*
        else if(findVertical(puzzle,wordPattern,word)){

            return true;
        }
        else if( findDiagonal(puzzle,wordPattern,word)){
            return true;
        }


        return false;
   }
    /**
     * find the word in verticla direction
     *
     * @param puzzle 2d array of characters
     *        wordPattern pattern to be matched
     *
     * @return true if word is found
     *
     *
     */
    public boolean findVertical(String[][] puzzle,Pattern wordPattern,String searchword){


        for(int col=0;col<puzzle.length;col++){//loop from col
            String word="";//string variable to contacinate
            for(int row=0;row<puzzle.length;row++){//loop from col
                word+=puzzle[row][col];//contatinate the word from the puzzle array
            }
            if(matchWord(word,wordPattern)) {//if word is found return
                System.out.println("Found \""+searchword+" \""+" in column: "+col);
                return true;
            }

        }
        return  false;

    }
    /**
     * find the word in horizontal direction
     *
     * @param puzzle 2d array of characters
     *        wordPattern pattern to be matched
     *
     * @return true if word is found
     *
     *
     */
    public boolean findhorizontal(String[][] puzzle,Pattern wordPattern,String searchword){


        for(int row=0;row<puzzle.length;row++){/*loop till row */
            String word="";//contatinate the string
            for(int col=0;col<puzzle.length;col++){/*loop till col*/
                word+=puzzle[row][col];//containcate the word
            }
            if(matchWord(word,wordPattern)) {//if word is found return true
                System.out.println("Found \""+searchword+" \""+" in row: "+row);
                return true;
            }

        }
       return  false;//if word is not found retunn false

    }
    public boolean findDiagonal(String [][] puzzle,Pattern wordPattern,String searchword){

        int rows = puzzle.length;
        int cols = puzzle[0].length;
        int maxSum = rows + cols - 2;

        for (int sum = 0; sum <= maxSum; sum++) {
            String word="";
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i + j - sum == 0) {
                        word=puzzle[i][j]+word;

                    }
                }
                if(matchWord(word,wordPattern)) {//if word is found return true
                    System.out.println("Found \""+searchword+" \""+" diagonally");
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * find the word in verticla direction
     *
     * @param word to be matched with the pattern
     *        wordPattern pattern to be matched
     *
     * @return true if word is found
     *
     *
     */
    public boolean matchWord(String  word,Pattern wordPattern){
       if (wordPattern.matcher(word).find()) {//find the pattern to be matched
           return true;//if pattern is found retutn true
        }
        else if(wordPattern.matcher(revString(word)).find()){/*else if
        reverse the string and find the matched pattern*/
           return true;//if pattern is found return true
        }

        return false;

    }

    /**
     * reverse a string
     *
     * @param word to reverse
     *
     * @return  reversed string
     *
     *
     */

    public String revString(String word){

            String reverse = "";
            for(int i = word.length() - 1; i >= 0; i--)/*loop till the lenght
            of the string*/
            {
                reverse = reverse + word.charAt(i);//reverse string
            }

            return reverse;
    }


    /**
     * Main Function
     *
     *
     * @param args command line arguments from user
     *
     * @return does not return a value
     *
     */

    public static void main(String[] args) {

            System.out.println("Please enter the file name");/*Ask the user to
            enter the file name*/
            Scanner input=new Scanner(System.in);/*Scanner for input */
            String fileName=input.nextLine();/*getting the input*/

           /* try and cathc bolck to throw exception if file is not found.
            * Get the file using scanner */
            try (Scanner reader = new Scanner(new File(fileName))) {

                 int count=0;//for 2d array

                /*getLine count method gets the count of the number of line */
                 String[][] characters=new String[getLineCount("")][];

                 while (reader.hasNext()) {//read till there is a line
                    String line=reader.nextLine(); //get the next line
                    characters[count]=line.split("\\s");/*split by space
                    of each character */
                    count++;//increment the count
                    System.out.println(line);//print the line
                 }
                 while(true) {
                     System.out.println("Please enter the word to search");
                     String word = input.nextLine();
                     if(word.toLowerCase().equals("exit")){
                         System.exit(1);
                     }
                     Pattern p = Pattern.compile(".*" + word + ".*");/*create the regex pattern by
                 taking th input from the user */
                     System.out.println("Searching for " + word + " ....");
                     WordSearch search = new WordSearch();//create instance of class
                     boolean TorF = search.find(characters, p,word);//call the find function

                     if (!TorF) {
                         System.out.println("Cannot find " + word);
                     }
                 }

            }
            catch (FileNotFoundException ex){

                System.out.println("File not found");

            }




    }


}
