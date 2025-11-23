package com.thealgorithms.maths;

/**
 * Armstrong Number (also known as Narcissistic Number)
 * A number is called an Armstrong number if the sum of cubes of its digits equals the number itself.
 * For example: 153 = 1³ + 5³ + 3³ = 1 + 125 + 27 = 153
 *
 * @author duvvuvenkataramana
 */
public final class ArmstrongNumber {
    private ArmstrongNumber() {
    }

    /**
     * Check if a number is an Armstrong number
     *
     * @param number the number to check
     * @return true if the number is an Armstrong number, false otherwise
     */
    public static boolean isArmstrong(int number) {
        if (number < 0) {
            return false;
        }

        int original = number;
        int sum = 0;
        int digits = String.valueOf(number).length();

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }

        return sum == original;
    }
}
