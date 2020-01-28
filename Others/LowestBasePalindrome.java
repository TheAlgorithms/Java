package Others;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for finding the lowest base in which a given integer is a palindrome.
 * Includes auxiliary methods for converting between bases and reversing strings.
 * <p>
 * NOTE: There is potential for error, see note at line 63.
 *
 * @author RollandMichael
 * @version 2017.09.28
 */
public class LowestBasePalindrome {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        while (true) {
            try {
                System.out.print("Enter number: ");
                n = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                in.next();
            }
        }
        System.out.println(n + " is a palindrome in base " + lowestBasePalindrome(n));
        System.out.println(base2base(Integer.toString(n), 10, lowestBasePalindrome(n)));
        in.close();
    }

    /**
     * Given a number in base 10, returns the lowest base in which the
     * number is represented by a palindrome (read the same left-to-right
     * and right-to-left).
     *
     * @param num A number in base 10.
     * @return The lowest base in which num is a palindrome.
     */
    public static int lowestBasePalindrome(int num) {
        int base, num2 = num;
        int digit;
        char digitC;
        boolean foundBase = false;
        String newNum = "";
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (!foundBase) {
            // Try from bases 2 to num-1
            for (base = 2; base < num2; base++) {
                newNum = "";
                while (num > 0) {
                    // Obtain the first digit of n in the current base,
                    // which is equivalent to the integer remainder of (n/base).
                    // The next digit is obtained by dividing n by the base and
                    // continuing the process of getting the remainder. This is done
                    // until n is <=0 and the number in the new base is obtained.
                    digit = (num % base);
                    num /= base;
                    // If the digit isn't in the set of [0-9][A-Z] (beyond base 36), its character
                    // form is just its value in ASCII.

                    // NOTE: This may cause problems, as the capital letters are ASCII values
                    // 65-90. It may cause false positives when one digit is, for instance 10 and assigned
                    // 'A' from the character array and the other is 65 and also assigned 'A'.

                    // Regardless, the character is added to the representation of n
                    // in the current base.
                    if (digit >= digits.length()) {
                        digitC = (char) (digit);
                        newNum += digitC;
                        continue;
                    }
                    newNum += digits.charAt(digit);
                }
                // Num is assigned back its original value for the next iteration.
                num = num2;
                // Auxiliary method reverses the number.
                String reverse = reverse(newNum);
                // If the number is read the same as its reverse, then it is a palindrome.
                // The current base is returned.
                if (reverse.equals(newNum)) {
                    foundBase = true;
                    return base;
                }
            }
        }
        // If all else fails, n is always a palindrome in base n-1. ("11")
        return num - 1;
    }

    private static String reverse(String str) {
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse += str.charAt(i);
        }
        return reverse;
    }

    private static String base2base(String n, int b1, int b2) {
        // Declare variables: decimal value of n,
        // character of base b1, character of base b2,
        // and the string that will be returned.
        int decimalValue = 0, charB2;
        char charB1;
        String output = "";
        // Go through every character of n
        for (int i = 0; i < n.length(); i++) {
            // store the character in charB1
            charB1 = n.charAt(i);
            // if it is a non-number, convert it to a decimal value >9 and store it in charB2
            if (charB1 >= 'A' && charB1 <= 'Z')
                charB2 = 10 + (charB1 - 'A');
                // Else, store the integer value in charB2
            else
                charB2 = charB1 - '0';
            // Convert the digit to decimal and add it to the
            // decimalValue of n
            decimalValue = decimalValue * b1 + charB2;
        }

        // Converting the decimal value to base b2:
        // A number is converted from decimal to another base
        // by continuously dividing by the base and recording
        // the remainder until the quotient is zero. The number in the
        // new base is the remainders, with the last remainder
        // being the left-most digit.

        // While the quotient is NOT zero:
        while (decimalValue != 0) {
            // If the remainder is a digit < 10, simply add it to
            // the left side of the new number.
            if (decimalValue % b2 < 10)
                output = Integer.toString(decimalValue % b2) + output;
                // If the remainder is >= 10, add a character with the
                // corresponding value to the new number. (A = 10, B = 11, C = 12, ...)
            else
                output = (char) ((decimalValue % b2) + 55) + output;
            // Divide by the new base again
            decimalValue /= b2;
        }
        return output;
    }
}
