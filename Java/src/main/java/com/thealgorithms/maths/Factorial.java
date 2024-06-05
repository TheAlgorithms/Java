package com.thealgorithms.maths;

public final class Factorial {
    private Factorial() {
    }

    /**
     * Calculate factorial N using iteration
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number cannot be negative");
        }
        long factorial = 1;
        for (int i = 1; i <= n; ++i) {
            factorial *= i;
        }
        return factorial;
    }
}
