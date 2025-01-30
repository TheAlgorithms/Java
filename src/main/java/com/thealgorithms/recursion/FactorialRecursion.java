package com.thealgorithms.recursion;

import static com.thealgorithms.maths.utils.MathsUtil.checkInputIsPositive;

public final class FactorialRecursion {
    private FactorialRecursion() {
    }
    /**
     * Recursive FactorialRecursion Method
     *
     * @param n The number to factorial
     * @return The factorial of the number
     */
    public static long factorial(int n) {
        checkInputIsPositive(n, "Input number should be positive.");

        return n == 0 || n == 1 ? 1 : n * factorial(n - 1);
    }
}
