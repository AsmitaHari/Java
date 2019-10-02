/*
    Classname: MyScanner

    Verson: 1.0
    Revisions: 0

    @authors: Anmol Jaising, Asmita Hari
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
   This class takes in a file name as an input from user. It can perform a few
   custom operations that a scanner class can do. In addition to that it counts 
   the number of the lines and number of characters.
 */

public class MyScanner {

    /*
        Defining a scanner class' object, a file name, a lines and a count
        variable.
     */
    
    Scanner scanner = null;
    String fileName = null;
    int lines = 0;
    int count = 0;

    /*
        Scanner constructor taking in the file name as input.
        Initializes the scanner method.
    
        @param String fileName. Used to store the name of the file.
        @return None
     */
    public MyScanner(String fileName) {
        try {
            this.fileName = fileName;
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
        }

    }

    /*
       This method is used to check if there is another line in the text file
       @param None
       @return true/false depending on the whether there is a line or not.
     */
    public boolean hasNext() {
        return scanner.hasNext();
    }

    /*
        This method returns the next line of the text file.
        @param None
        @return a string containing the contents of the next line.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /*
        This method counts the number of lines present in the file.
        @param None
        @return lines, i.e an int which is the number of lines.
     */
    public int getLineCount() {
        try (Scanner scannerLineCount = new Scanner(new File(fileName))) {
            while (scannerLineCount.hasNext()) {
                scannerLineCount.nextLine();
                lines++;
            }
        } catch (FileNotFoundException ex) {
        }
        return lines;
    }

    /*
        This method is used to return the total number of characters present in
        in the file.
        
        @param None
        @return count - which holds the total count of characters.
     */
    public int getCharCount() {
        try (Scanner scannerCharCount = new Scanner(new File(fileName))) {
            while (scannerCharCount.hasNext()) {
                String output = scannerCharCount.nextLine();
                count = count + output.length();
            }
        } catch (FileNotFoundException ex) {

        }
        return count;
    }

    /*
        This method is used to close the scanner.
        @param None
        @return closed scanner
     */
    public void close() {
        System.out.println("Scanner closed!");
        scanner.close();

    }

    /*
        The main method that accepts a string text file in the constructor
        and performs the operations.
     */
    public static void main(String[] args) {
        MyScanner scannerTest = new MyScanner("info.txt");
        System.out.println("Is there a next line?");
        System.out.println("My Output: " + scannerTest.hasNext());
        System.out.println();
        System.out.println("Printing the next line...");
        System.out.println("My Output: " + scannerTest.nextLine());
        System.out.println();

        System.out.println("Printing the line count...");
        System.out.println("My Output: " + scannerTest.getLineCount());
        System.out.println();

        System.out.println("Printing the character ...");
        System.out.println("My Output: " + scannerTest.getCharCount());
        System.out.println();

        System.out.println("Is there a next line?");
        System.out.println("My Output: " + scannerTest.hasNext());
        System.out.println();

        System.out.println("Printing the remaning lines... ");
        while (scannerTest.hasNext()) {
            System.out.println(scannerTest.nextLine());
        }
        System.out.println("");

        System.out.println("Is there any lines now?");
        System.out.println("My Output: " + scannerTest.hasNext());
        System.out.println();

        System.out.println("Closing my scanner...");
        scannerTest.close();
    }
}
