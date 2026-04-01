package com.thealgorithms.maths;

import java.math.BigInteger;

public final class Factorial {
    private Factorial() {
    }

    /**
     * Calculate factorial N using iteration
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number cannot be negative");
        }
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) {
            factorial = factorial.multiply(BigInteger.valueOf(i));  
        }
        return factorial;
    }
}