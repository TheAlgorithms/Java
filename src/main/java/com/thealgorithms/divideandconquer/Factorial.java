package com.thealgorithms.divideandconquer;

/**
 * Computes the factorial of a non-negative integer using an iterative
 * approach to avoid recursion overhead and stack overflow risks.
 *
 * <p>This implementation improves upon the original recursive version by
 * eliminating deep recursion, reducing space complexity from O(n) to O(1),
 * and improving runtime efficiency on the JVM.
 *
 * <p>Time Complexity: O(n)
 * <br>Space Complexity: O(1)
 */
public final class Factorial {

    private Factorial() {
        // Utility class
    }

    /**
     * Returns the factorial of the given non-negative number.
     *
     * @param n the number to compute factorial for
     * @return factorial of n (n!)
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative input not allowed");
        }

        long result = 1;
        for (long i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}


