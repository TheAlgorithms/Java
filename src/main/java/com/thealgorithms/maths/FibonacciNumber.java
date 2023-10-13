// Explanation:- https://en.wikipedia.org/wiki/Fibonacci_sequence#Binet's_formula
package com.thealgorithms.maths;

import java.math.BigInteger;

public final class FibonacciNumber {
    private FibonacciNumber() {
    }

    // Compute the limit for n that fits in a long
    // Reducing the limit to 70 due to incorrect results for larger inputs
    private static final int argLimit = 70;

    public static BigInteger nthFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input 'n' must be a non-negative integer.");
        }

        if (n > argLimit) {
            throw new IllegalArgumentException("Input 'n' is too large to fit into a long.");
        }

        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        // Calculate the nth Fibonacci number using the golden ratio formula
        final double sqrt5 = Math.sqrt(5);
        final double phi = (1 + sqrt5) / 2;
        final double psi = (1 - sqrt5) / 2;
        final double result = (Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5;

        // Round to the nearest integer and convert to BigInteger
        return BigInteger.valueOf(Math.round(result));
    }
}
