package com.thealgorithms.maths;

/**
 * An Armstrong number is a number that is equal to the sum of its own digits
 * each raised to the power of the number of digits.
 * For example: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
 *
 * @author duvvuvenkataramana
 */
public final class ArmstrongNumber {
    private ArmstrongNumber() {
    }

    /**
     * Checks if a number is an Armstrong number.
     *
     * @param number the number to check
     * @return {@code true} if Armstrong number, {@code false} otherwise
     */
    public static boolean isArmstrong(int number) {
        if (number < 0) {
            return false;
        }

        int original = number;
        int numDigits = String.valueOf(number).length();
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += (int) Math.pow(digit, numDigits);
            number /= 10;
        }

        return sum == original;
    }
}
