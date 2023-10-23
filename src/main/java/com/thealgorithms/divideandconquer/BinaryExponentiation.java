package com.thealgorithms.divideandconquer;

// Java Program to Implement Binary Exponentiation (power in log n)

/*
 * Binary Exponentiation is a method to calculate a to the power of b.
 * It is used to calculate a^n in O(log n) time.
 *
 * Reference:
 * https://iq.opengenus.org/binary-exponentiation/
 */

public class BinaryExponentiation {

    // Recursive function to calculate a number raised to a power
    public static long calculatePower(long base, long exponent) {
        // Base case: If the exponent is 0, return 1.
        if (exponent == 0) {
            return 1;
        }

        // Divide the exponent by 2 and calculate the result for half of it.
        long resultHalf = calculatePower(base, exponent / 2);

        // If the exponent is even, multiply the result by itself.
        if (exponent % 2 == 0) {
            return resultHalf * resultHalf;
        } else {
            // If the exponent is odd, multiply the result by itself and the base.
            return resultHalf * resultHalf * base;
        }
    }

    // Iterative function to calculate a number raised to a power
    long power(long base, long exponent) {
        long result = 1;
        long baseValue = base;

        while (exponent > 0) {
            // Check if the least significant bit of the exponent is 1.
            if (exponent % 2 == 1) {
                result *= baseValue;
            }

            // Square the base value and halve the exponent.
            baseValue *= baseValue;
            exponent /= 2;
        }

        return result;
    }
}
