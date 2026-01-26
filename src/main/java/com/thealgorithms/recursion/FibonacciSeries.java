package com.thealgorithms.recursion;

/**
 * The Fibonacci series is a sequence of numbers where each number is the sum of the two preceding ones,
 * starting with 0 and 1.
 * <p>
 * Example:
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 ...
 * </p>
 */

public final class FibonacciSeries {
    private FibonacciSeries() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Calculates the nth term in the Fibonacci sequence using recursion.
     *
     * @param n the position in the Fibonacci sequence (must be non-negative)
     * @return the nth Fibonacci number
     * @throws IllegalArgumentException if n is negative
     */
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a non-negative integer");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
