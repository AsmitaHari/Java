/*
    Classname:BinarySum

    Verson: 1.0
    Revisions: 0

    @authors: Anmol Jaising, Asmita Hari
 */

/*
   This find the sum of two binary numbers.
   finds the sum in decimal and hexadecimal
*/
public class BinarySum {

    /**
     * binary to decimal conversion
     *
     * @param binaryString a binary number
     *
     * @return decimal value of binary number
     *
     *
     */
    static int binarytoDecimal(String binaryString) {

        //intliaze decimal to 0
        int count = 0, decimalValue = 0;

        //convert the binary string number into integer value
        int binaryIntValue = Integer.parseInt(binaryString);

        //loop till the length of the string
        for (int index = 0; index < binaryString.length(); index++) {
            int lastvalue = binaryIntValue % 10;//reminder of the value
            decimalValue += lastvalue * Math.pow(2, index);/* add each value
            multpled by the reminder and the power of the 2 to the index*/
            binaryIntValue = binaryIntValue / 10;//quotient of the value

        }

        return decimalValue; //decimal value of the binary number

    }

    /**
     * decimal to hexadecimal conversion
     *
     * @param decimalvalue a number
     *
     * @return hexadecimal value of the number
     *
     *
     */
    static String decimaltoHexaDecimal(int decimalvalue) {

        int quotient = Integer.MAX_VALUE;//the quotient value
        int remainder;//reminder value
        String output = "";//output strinig value
        byte counter = 0;//counter value
        Character remaindingChar;//remaingn char

        while (quotient != 0) {//while quotient is not 0
            quotient = decimalvalue / 16;//get the quotient by divin by 0
            remainder = decimalvalue % 16;//get the reminder

            if (remainder >= 10) {//if reminder is greater than 0
                remaindingChar = (char) (remainder + 55);/*get the hexadecimla char
                after 9 */
                output = output + remaindingChar;//output string

            } else {
                output = output + remainder;//output string
            }

            decimalvalue = decimalvalue / 16;//get the next char
            counter++;//incrementing the counter
        }

        String newString = "";

        for (int index = output.length() - 1; index >= 0; index--) {
            newString = newString + output.charAt(index);
            /*output the string by displaying the characters in reverse order */
        }

        return "0x" + newString;

    }

    /**
     * sum of two binary numbers conversion
     *
     * @param a,b two binary string
     *
     * @return sum of two binary number
     *
     *
     */
    static String addBinary(String a, String b) {
        int carryValue = 0;//intlize carry value to 0
        int string1Length = a.length();//length of string 1
        int string2Length = b.length();//length of String 2
        String sumOfTwo = "";//final string value
        //maximum of the tow string legnth
        int maximunLength = Math.max(string1Length, string2Length);

        //loop till the length of maximun length
        for (int index = 0; index < maximunLength; index++) {

            int sum1 = 0, sum2 = 0;//intlaize sum1 and sum2 to 0
            if (index < string1Length) {/*if the value of index is less than the
            string length*/

                //get the decimal value of the charatcer
                sum1 = a.charAt(string1Length - 1 - index) - '0';

            } else {
                sum1 = 0;//if index exceeds the length sum1 is 0
            }
            if (index < string2Length) {/*if the value of index is less than the
                string length*/

                //get the decimal value of the charatcer
                sum2 = b.charAt(string2Length - 1 - index) - '0';

            } else {
                sum2 = 0;//if index exceeds the length sum2 is 0
            }
            //add the sum1 ,sum2 along with the carry
            int totalSum = sum1 + sum2 + carryValue;

            //get the value of carry by diving by 2
            carryValue = totalSum / 2;
            //contating the string with the last value of the totalsum
            sumOfTwo = totalSum % 2 + sumOfTwo;
        }
        return (carryValue == 0) ? sumOfTwo : "1" + sumOfTwo;/*if there is a
        carry return the string by contatcing the string to the first */

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
    public static void main(String args[]) {
        String aString = "", bString = "";
        if (args.length == 0) {//if args is zero
            System.out.println("Please provide arguments");
            System.exit(0);
        }
        if (args.length != 2) {//if args lenght is not 2
            System.out.println("Please provide two  arguments");
            System.exit(0);
        }

        if (args[0].length() != 8) {/*if length of string is not 8 prepend with zero */
            for (int index = 0; index < 8 - args[0].length(); index++) {
                aString += "0";
            }
        }
        if (args[0].length() != 8) {/*if length of string is not 8 prepend with zero */
            for (int index = 0; index < 8 - args[1].length(); index++) {
                bString += "0";
            }
        }
        aString = aString + args[0];//prepend zeros to the args
        bString = bString + args[1];//prepend zeros to the args

        String sum = addBinary(aString, bString);//call the add binary function
        System.out.println("Adding " + binarytoDecimal(aString) + " + " + binarytoDecimal(bString));
        System.out.println("Sum in Binary: b'" + sum +"'");
        int decimal = binarytoDecimal(sum);//call the binary to decimal
        System.out.println("Sum in Decimal: " + decimal);
        System.out.println("Sum in hexadecimal is :"
                + decimaltoHexaDecimal(decimal));//call the decimal to hexadecimal
    }

}
