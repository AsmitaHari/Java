/*
 * Classname:Zero
 *
 * Version information:1
 *
 * Date:01/20/2019
 *@author : Asmita Hari, Anmol Jaisingh
 */

/**
 * The Zero class finds all the subsets and check if the sum of the subsets
 * is zero and prints the arrays as a string
 * passed to the function
 * ...
 */
public class Zero {

    /**
     * checkSet Function
     *
     * @param       testSet   the array of all the numbers


     * @return      true if sum of subset is 0 else false
     *
     *
     */

    public boolean checkSet(int[] testSet) {


        int arrayLength = testSet.length;/*variable thats contains the length of
                                            array*/
        int flag = 0;/*flag to check if the subset is zero or not */
        int bitLen = (1 << arrayLength);/*size of power set*/

        for (int index = 0; index < bitLen; index++) { /*loop from 0 to sizeof
                                                      powerset*/
              int sum = 0;/*Initlaize variable sum to 0*/

            for (int jIndex = 0; jIndex < arrayLength; jIndex++) {
               /* checking the jindex is set or not set .If it is set then
                * finds the subset  */
                if ((index & (1 << jIndex)) > 0) {
                    flag = 1;/*Makes flag as 1 */
                    sum = sum + testSet[jIndex];/*sum of each subsets */

                }
            }

            if (flag == 1 && sum == 0) { /*checks if the subset is not empty
              and if the sum is zero then return true*/
                return true;
            }

        }
        return false; /*false if the sum is not zero */

    }
    /**
     * arrayToString Function
     *
     * @param       testSet   the array of all the numbers


     * @return      String of array of numbers comma seperated
     *
     *
     */

    public String arrayToString(int[] testSet) {

        String set = ""; /*String which contains the comma
                            seperated value
                 */

         /*loop from 0 to size of the array */
        for (int index = 0; index < testSet.length; index++) {
            set = set + testSet[index] + ","; /*Concatinates the string by comma*/
        }

        if (set.length() != 0) { /*if the string is not empty then remve
                              the last comma */
            set = set.substring(0, set.length() - 1);
        }
        return set;//return the string
    }

    /**
     * Main Function
     *

     @param       args  command line arguments from user

     * @return     does not return a value
     *
     */
    public static void main(String[] args) {

        Zero zero = new Zero();//Object of class zero
        int[] testSet = {4, 2, -3, 1, 6};//array of numbers
        int[] testSet2 = {-1, 2, 2};//array of numbers

        System.out.println("Checking " + "[" + zero.arrayToString(testSet) + "]"
                           + " - is " + zero.checkSet(testSet));
        /*Prints the output by calling the checkset and arraytoString functions*/
        System.out.println("Checking " + "[" + zero.arrayToString(testSet2) + "]"
                           + " - is " + zero.checkSet(testSet2));

    }

}


