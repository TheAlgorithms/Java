package com.thealgorithms.maths;

/**
 * calculate Power using Recursion
 * @author Vinayak (https://github.com/Vinayak-v12)
 */

public final class PowerUsingRecursion {
    private PowerUsingRecursion() {
    }

    /**
     * Computes base raised to the given exponent.
     *
     * @param base the number to be raised
     * @param exponent the power (can be negative)
     * @return base^exponent
     */
    public static double power(double base, int exponent) {

        // Handle negative exponent: a^-n = 1 / (a^n)
        if (exponent < 0) {
            return 1.0 / power(base, -exponent);
        }

        // Base cases
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent == 1) {
            return base;
        }

        // Exponentiation by Squaring
        // If exponent is even: a^n = (a^(n/2))^2
        if (exponent % 2 == 0) {
            double half = power(base, exponent / 2);
            return half * half;
        }

        // If exponent is odd: a^n = a * a^(n-1)
        return base * power(base, exponent - 1);
    }
}
