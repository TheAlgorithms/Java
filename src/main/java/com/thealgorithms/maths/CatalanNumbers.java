package com.thealgorithms.maths;

/**
 * Calculate Catalan Numbers
 */
public final class CatalanNumbers {
    private CatalanNumbers() {
    }

    /**
     * Calculate the nth Catalan number using a recursive formula.
     *
     * @param n the index of the Catalan number to compute
     * @return the nth Catalan number
     */
    public static long catalan(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        }
        return factorial(2 * n) / (factorial(n + 1) * factorial(n));
    }

    /**
     * Calculate the factorial of a number.
     *
     * @param n the number to compute the factorial for
     * @return the factorial of n
     */
    private static long factorial(final int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Main method to test the Catalan number calculation.
     */
    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println("Catalan number " + i + " is: " + catalan(i));
        }
    }
}