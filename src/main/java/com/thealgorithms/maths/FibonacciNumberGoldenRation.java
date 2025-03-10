package com.thealgorithms.maths;

/**
 * This class provides methods for calculating Fibonacci numbers using Binet's formula.
 * Binet's formula is based on the golden ratio and allows computing Fibonacci numbers efficiently.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_sequence#Binet's_formula">Binet's formula on Wikipedia</a>
 */
public final class FibonacciNumberGoldenRation {
    private FibonacciNumberGoldenRation() {
        // Private constructor to prevent instantiation of this utility class.
    }

    /**
     * Compute the limit for 'n' that fits in a long data type.
     * Reducing the limit to 70 due to potential floating-point arithmetic errors
     * that may result in incorrect results for larger inputs.
     */
    public static final int MAX_ARG = 70;

    /**
     * Calculates the nth Fibonacci number using Binet's formula.
     *
     * @param n The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number as a long.
     * @throws IllegalArgumentException if the input 'n' is negative or exceeds the range of a long data type.
     */
    public static long compute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input 'n' must be a non-negative integer.");
        }

        if (n > MAX_ARG) {
            throw new IllegalArgumentException("Input 'n' is too big to give accurate result.");
        }

        if (n <= 1) {
            return n;
        }

        // Calculate the nth Fibonacci number using the golden ratio formula
        final double sqrt5 = Math.sqrt(5);
        final double phi = (1 + sqrt5) / 2;
        final double psi = (1 - sqrt5) / 2;
        final double result = (Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5;

        // Round to the nearest integer and return as a long
        return Math.round(result);
    }
}
