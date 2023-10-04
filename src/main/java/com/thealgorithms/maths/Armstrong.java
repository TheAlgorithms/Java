package com.thealgorithms.maths;

/**
 It is a number that is equal to the sum of its own digits, each raised to the power of the number of digits. For
 * example, 370 is an Armstrong number because 3*3*3 + 7*7*7 + 0*0*0 = 370.
 * 1634 is an Armstrong number because 1*1*1*1 + 6*6*6*6 + 3*3*3*3 + 4*4*4*4 = 1634
 * An Armstrong number is often called Narcissistic number.
 *
 * @author satyabarghav
 */
public class Armstrong {

    /**
     * Checks whether a given number is an armstrong number or not.
     *
     * @param number number to check
     * @return {@code true} if given number is armstrong number, {@code false}
     * otherwise
     */
    public boolean isArmstrong(int number) {
        long sum = 0;
        String tempString = Integer.toString(number); // Converting the Given number to string 
        int power = tempString.length(); // extract the length of the number i.e; Number of Digits
        long number2 = number;
        while (number2 > 0) {
            long mod = number2 % 10;
            sum += Math.pow(mod, power); // The digit being raised to the Number of Digits and added to the sum.
            number2 /= 10;
        }
        return sum == number;
    }
}
