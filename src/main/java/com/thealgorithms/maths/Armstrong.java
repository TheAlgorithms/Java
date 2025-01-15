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
 * @modifier rahul katteda - (13/01/2025) - [updated the logic for getting total number of digits]
 */
public class Armstrong {

    /**
     * Checks whether a given number is an Armstrong number or not.
     *
     * @param number the number to check
     * @return {@code true} if the given number is an Armstrong number, {@code false} otherwise
     */
    public boolean isArmstrong(int number) {
        if (number < 0) {
            return false; // Negative numbers cannot be Armstrong numbers
        }
        long sum = 0;
        int totalDigits = (int) Math.log10(number) + 1; // get the length of the number (number of digits)
        long originalNumber = number;

        while (originalNumber > 0) {
            long digit = originalNumber % 10;
            sum += (long) Math.pow(digit, totalDigits); // The digit raised to the power of total number of digits and added to the sum.
            originalNumber /= 10;
        }

        return sum == number;
    }
}
