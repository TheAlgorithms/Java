package com.thealgorithms.maths;

/**
 * Euclidean algorithm to compute the Greatest Common Divisor (GCD).
 * Wikipedia: https://en.wikipedia.org/wiki/Euclidean_algorithm
 */
public final class GCDUsingEuclid {

    private GCDUsingEuclid() {
        // Utility class
    }

    /**
     * Computes GCD of two integers using Euclidean algorithm.
     *
     * @param a first integer
     * @param b second integer
     * @return gcd(a, b)
     */
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
