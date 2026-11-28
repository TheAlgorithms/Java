package com.thealgorithms.maths;

/**
 * Utility class to calculate power using recursion.
 *
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public final class PowerUsingRecursion {
    private PowerUsingRecursion() {
    }

    /**
     * Calculates the power of a base raised to an exponent using recursion.
     *
     * @param base the base value
     * @param exponent the power to raise the base to
     * @return the result of base^exponent
     * @throws ArithmeticException if base is 0 and exponent is negative
     */
    public static double power(double base, int exponent) {
        // Base case: anything raised to the power of 0 is 1
        if (exponent == 0) {
            return 1;
        }

        // Handle negative exponents: base^(-e) = 1 / (base^e)
        if (exponent < 0) {
            if (base == 0.0) {
                throw new ArithmeticException("Base cannot be 0 with a negative exponent.");
            }
            if (exponent == Integer.MIN_VALUE) {
                return 1.0 / base * power(base, -(exponent + 1));
            }
            return 1.0 / power(base, -exponent);
        }

        // Recursive case: divide-and-conquer for even exponents to reduce depth to O(log n)
        if (exponent % 2 == 0) {
            double half = power(base, exponent / 2);
            return half * half;
        }

        // Recursive case for odd exponents
        return base * power(base, exponent - 1);
    }
}
