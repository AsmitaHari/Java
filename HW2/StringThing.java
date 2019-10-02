/*
    This program is a Gravity Calculator class, which is used to calculate the
    velocity and distance travelled by an object.

    Verson: 1.0
    Revisions: 0

    @authors: Anmol Jaising, Asmita Hari
 */

/*
   This class shows how string comarsions works using == and equals().
   It also shows how the hashCode() and identityHashCode() methods work, along
   with an introduction to recursive funtions.
*/
public class StringThing {

    /*
        There are 5 static methods which describe 5 different scenarios in which
        strings are compared.
     */
    private static boolean equalityTest1(String a, String b) {
        return a == b;
    }

    private static boolean equalityTest2(String a, String b) {
        return a.equals(b);
    }

    private static boolean equalityTest3(String a, String b) {
        return System.identityHashCode(a) == System.identityHashCode(b);
    }

    private static boolean equalityTest4(String a, String b) {
        if (a.length() == 0 && b.length() == 0) {
            return true;
        } else {
            if (a.length() == 0 || b.length() == 0) {
                return false;
            }
            if (a.charAt(0) == b.charAt(0)) {
                return equalityTest4(a.substring(1), b.substring(1));
            } else {
                return false;
            }
        }
    }

    private static boolean equalityTest5(String a, String b) {
        return a.hashCode() == b.hashCode();
    }

    /*

        This is the main method of the program from which we define 8 different
        string scenarios.

     */
    public static void main(String[] args) {
        String abcV1 = "abc";
        String abcV2 = "a" + "b" + "c";
        String abcV3 = "abcd".substring(0, abcV1.length());
        String abcV4 = "" + abcV2;
        String abcV5 = "a" + (char) 98 + 99;
        String abcV6 = new String("abc");
        String abcV7 = abcV3.intern();
        String abcv8 = abcV6;

        System.out.println("Equality Test - 1");
        /*
            For equality test 1:

            string1 == string 2 - compares the mempory location of two strings
            and checks for equality.

            abcV2 is formed by concatenating string literals. After concatenating,
            the content of the string formed is checked in the String Internal Pool.
            Since abcV1 and abcV2 point to the same memory location, their equality
            is TRUE.

            .intern() creates an exact copy of the heap string object to the
            string intern pool.
            abcV3 has it's memory location pointing to the heap. abcV7 will make
            a copy of it in the String internal pool. As 'abc' is already present
            in the SIP, abcV1 and abcV7 point to the same memory location,
            their equality is TRUE.
         */

        System.out.println(StringThing.equalityTest1(abcV1, abcV2));
        System.out.println(StringThing.equalityTest1(abcV1, abcV7));
        System.out.println(StringThing.equalityTest1(abcV2, abcV1));
        System.out.println(StringThing.equalityTest1(abcV2, abcV7));

        System.out.println();
        System.out.println("Equality Test - 2");

        /*

            For equality test 2:

            string1.equals(string2) - comapres the content of two strings.
            Returns true if the contents are equal

		abcV1==abcV2==abcV3==abcV4==abcV6==abcV7==abcV8
         */
        System.out.println(StringThing.equalityTest2(abcV1, abcV2));
        System.out.println(StringThing.equalityTest2(abcV1, abcV3));
        System.out.println(StringThing.equalityTest2(abcV1, abcV4));
        System.out.println(StringThing.equalityTest2(abcV1, abcV6));
        System.out.println(StringThing.equalityTest2(abcV1, abcV7));
        System.out.println(StringThing.equalityTest2(abcV1, abcv8));

        System.out.println();
        System.out.println("Equality Test - 3");

        /*

        For equality test 3:

		The java.lang.System.identityHashCode() is the method which is used to return
		the same hash code for any given object that is returned by the default method hashCode().
		Also, for every hash code with a null reference zero is returned.

        string.identityHashCode() will generate a hashCode not considering what
        is present as contents to it.

        if we have two string s1 and s2.
        s1 = 'abc'
        s2 = new String("abc");

        Since they are two different instances having the same value,
        the identity hashcodes generated for them is the differet.

        abcV1, abcV2 and abcV7 are all strings who are present in the String
        Internal Pool. They have the same content, hence they point to the same
        memory location. That's why their identity hash code is the same.

         */
        System.out.println(StringThing.equalityTest3(abcV1, abcV2));
        System.out.println(StringThing.equalityTest3(abcV1, abcV7));

        System.out.println("");
        System.out.println("Equality Test - 4");

        /*

            For equality test 4:

            This method recursively calls itself as long as the first character
            of the strings being compared match.

            Each time if the above condition is true, then a first character
            is removed from the string.

			abcV1==abcV2==abcV3==abcV4==abcV6==abcV7==abcV8

         */
        System.out.println(StringThing.equalityTest4(abcV1, abcV2));
        System.out.println(StringThing.equalityTest4(abcV1, abcV3));
        System.out.println(StringThing.equalityTest4(abcV1, abcV4));
        System.out.println(StringThing.equalityTest4(abcV1, abcV6));
        System.out.println(StringThing.equalityTest4(abcV1, abcV7));
        System.out.println(StringThing.equalityTest4(abcV1, abcv8));

        System.out.println("");
        System.out.println("Equality Test - 5");

        /*

            For equality test 5:

            string.hashcode() will return the hashcode of the string instance.
            Here, if two strings have the same content, their hashcodes match.

		   abcV1==abcV2==abcV3==abcV4==abcV6==abcV7==abcV8
         */
        System.out.println(StringThing.equalityTest5(abcV1, abcV2));
        System.out.println(StringThing.equalityTest5(abcV1, abcV3));
        System.out.println(StringThing.equalityTest5(abcV1, abcV4));
        System.out.println(StringThing.equalityTest5(abcV1, abcV6));
        System.out.println(StringThing.equalityTest5(abcV1, abcV7));
        System.out.println(StringThing.equalityTest5(abcV1, abcv8));

    }
}
