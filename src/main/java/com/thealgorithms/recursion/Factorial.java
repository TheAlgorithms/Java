package com.thealgorithms.recursion;

/**
 * Implementation of factorial using recursion.
 * <p>
 * The factorial of a non-negative integer n is defined as:
 * n! = n × (n-1) × (n-2) × ... × 1, with 0! = 1.
 */
public final class Factorial {

    // Private constructor to prevent instantiation
    private Factorial() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Calculates factorial recursively.
     *
     * @param n non-negative integer
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
