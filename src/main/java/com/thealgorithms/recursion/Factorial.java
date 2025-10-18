package com.thealgorithms.recursion;

/**
 * This class provides a recursive implementation of the factorial function.
 */
public class Factorial {

    /**
     * Recursive method to calculate factorial.
     *
     * @param n the number to find factorial of
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
