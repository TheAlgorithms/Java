package com.thealgorithms.maths;

import java.math.BigInteger;

/**
 * Implementation of factorial function with Big Integer to find large factorial values
 * https://en.wikipedia.org/wiki/Factorial
 * @author Prashal Ruchiranga
 */
public class FactorialBigInteger {

    /**
     * Calculate factorial n using iteration
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number is negative");
        }
        BigInteger FACTORIAL = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            FACTORIAL = FACTORIAL.multiply(BigInteger.valueOf(i));
        }
        return FACTORIAL;
    }
}
