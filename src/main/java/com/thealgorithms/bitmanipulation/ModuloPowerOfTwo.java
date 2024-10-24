package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to compute the remainder
 * of a number when divided by a power of two (2^n)
 * without using division or modulo operations.
 *
 * @author Hardvan
 */
public final class ModuloPowerOfTwo {
    private ModuloPowerOfTwo() {
    }

    /**
     * Computes the remainder of a given integer when divided by 2^n.
     *
     * @param x the input number
     * @param n the exponent (power of two)
     * @return the remainder of x divided by 2^n
     */
    public static int moduloPowerOfTwo(int x, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The exponent must be positive");
        }

        return x & ((1 << n) - 1);
    }
}
