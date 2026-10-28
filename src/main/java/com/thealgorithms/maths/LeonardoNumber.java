package com.thealgorithms.maths;

/**
 * Utility class for calculating Leonardo Numbers.
 * <p>
 * Leonardo numbers are a sequence of numbers defined by the recurrence:
 * L(n) = L(n-1) + L(n-2) + 1, with L(0) = 1 and L(1) = 1
 * <p>
 * The sequence begins: 1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, ...
 * <p>
 * This class provides both a recursive implementation and an optimized
 * iterative
 * implementation for calculating Leonardo numbers.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Leonardo_number">Leonardo Number
 *      - Wikipedia</a>
 * @see <a href="https://oeis.org/A001595">OEIS A001595</a>
 */
public final class LeonardoNumber {
    private LeonardoNumber() {
    }

    /**
     * Calculates the nth Leonardo Number using recursion.
     * <p>
     * Time Complexity: O(2^n) - exponential due to repeated calculations
     * Space Complexity: O(n) - due to recursion stack
     * <p>
     * Note: This method is not recommended for large values of n due to exponential
     * time complexity.
     * Consider using {@link #leonardoNumberIterative(int)} for better performance.
     *
     * @param n the index of the Leonardo Number to calculate (must be non-negative)
     * @return the nth Leonardo Number
     * @throws IllegalArgumentException if n is negative
     */
    public static int leonardoNumber(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative. Received: " + n);
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return leonardoNumber(n - 1) + leonardoNumber(n - 2) + 1;
    }

    /**
     * Calculates the nth Leonardo Number using an iterative approach.
     * <p>
     * This method provides better performance than the recursive version for large
     * values of n.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param n the index of the Leonardo Number to calculate (must be non-negative)
     * @return the nth Leonardo Number
     * @throws IllegalArgumentException if n is negative
     */
    public static int leonardoNumberIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative. Received: " + n);
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        int previous = 1;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int next = current + previous + 1;
            previous = current;
            current = next;
        }

        return current;
    }
}
