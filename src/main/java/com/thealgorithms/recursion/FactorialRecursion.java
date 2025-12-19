package com.thealgorithms.recursion;

/*
 * Factorial of a number n is the product of all positive integers less than
 * or equal to n.
 *
 * n! = n × (n - 1) × (n - 2) × ... × 1
 *
 * Examples:
 * 0! = 1
 * 1! = 1
 * 5! = 120
 */

public final class FactorialRecursion {

    private FactorialRecursion() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Computes the factorial of a non-negative integer using recursion.
     *
     * @param n the number whose factorial is to be computed
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
