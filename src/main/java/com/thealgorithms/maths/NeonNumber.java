package com.thealgorithms.maths;

/**
 * This class checks whether a number is a Neon Number.
 * A Neon number is a number where the sum of digits of
 * its square is equal to the number itself.
 *
 * Example:
 * 9^2 = 81 → 8 + 1 = 9 (Neon Number)
 */
public final class NeonNumber {

    private NeonNumber() {
        // Prevent instantiation
    }

    /**
     * Checks if a number is a Neon Number.
     *
     * @param number the input number
     * @return true if the number is a Neon Number, otherwise false
     * @throws IllegalArgumentException if the number is negative
     */
    public static boolean isNeon(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }

        int square = number * number;
        int sum = 0;

        while (square > 0) {
            sum += square % 10;
            square /= 10;
        }

        return sum == number;
    }
}
