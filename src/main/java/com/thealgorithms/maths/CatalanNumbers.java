package com.thealgorithms.maths;

import java.math.BigInteger;

/**
 * Calculate Catalan Numbers
 */
public final class CatalanNumbers {
    private static final int MAX_SUPPORTED_INDEX = 35;

    private CatalanNumbers() {
    }

    /**
     * Calculate the nth Catalan number using the factorial formula.
     *
     * @param n the index of the Catalan number to compute
     * @return the nth Catalan number
     * @throws IllegalArgumentException if n is negative or exceeds the maximum supported index for long
     */
    public static long catalan(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        }
        if (n > MAX_SUPPORTED_INDEX) {
            throw new IllegalArgumentException("Index exceeds maximum supported value for long");
        }
        return factorial(2 * n).divide(factorial(n + 1).multiply(factorial(n))).longValueExact();
    }

    /**
     * Calculate the factorial of a number using BigInteger to prevent integer overflow.
     *
     * @param n the number to compute the factorial for
     * @return the factorial of n as BigInteger
     */
    private static BigInteger factorial(final int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
