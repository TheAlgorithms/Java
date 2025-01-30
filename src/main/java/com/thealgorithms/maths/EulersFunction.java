package com.thealgorithms.maths;

import static com.thealgorithms.maths.utils.MathsUtil.checkInputIsPositive;

/**
 * Utility class for computing
 * <a href="https://en.wikipedia.org/wiki/Euler%27s_totient_function">Euler's totient function</a>.
 */
public final class EulersFunction {
    private EulersFunction() {
    }   

    /**
     * Computes the value of Euler's totient function for a given input.
     * This function has a time complexity of O(sqrt(n)).
     *
     * @param n the input number
     * @return the value of Euler's totient function for the given input
     * @throws IllegalArgumentException if {@code n} is non-positive
     */
    public static int getEuler(int n) {
        checkInputIsPositive(n, "Input number should be positive.");
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }
}
