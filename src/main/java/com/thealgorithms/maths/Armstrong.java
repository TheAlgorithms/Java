package com.thealgorithms.maths;

/**
 * This class checks whether a given number is an Armstrong number or not.
 * An Armstrong number is a number that is equal to the sum of its own digits,
 * each raised to the power of the number of digits.
 *
 * For example, 370 is an Armstrong number because 3^3 + 7^3 + 0^3 = 370.
 * 1634 is an Armstrong number because 1^4 + 6^4 + 3^4 + 4^4 = 1634.
 * An Armstrong number is often called a Narcissistic number.
 *
 * @author satyabarghav
 */
public class Armstrong {

    /**
     * Checks whether a given number is an Armstrong number or not.
     *
     * @param number the number to check
     * @return {@code true} if the given number is an Armstrong number, {@code false} otherwise
     */
    public boolean isArmstrong(int number) {
        long sum = 0;
        String temp = Integer.toString(number); // Convert the given number to a string
        int power = temp.length(); // Extract the length of the number (number of digits)
        long originalNumber = number;

        while (originalNumber > 0) {
            long digit = originalNumber % 10;
            sum += (long) Math.pow(digit, power); // The digit raised to the power of the number of digits and added to the sum.
            originalNumber /= 10;
        }

        return sum == number;
    }
}
