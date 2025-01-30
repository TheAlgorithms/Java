package com.thealgorithms.maths;

import static com.thealgorithms.maths.utils.MathsUtil.checkInputIsPositive;

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
        checkInputIsPositive(n, "");
        long factorial = 1;
        for (int i = 1; i <= n; ++i) {
            factorial *= i;
        }
        return factorial;
    }
}
