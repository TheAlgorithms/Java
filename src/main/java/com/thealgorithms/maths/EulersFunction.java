package com.thealgorithms.maths;

/**
 * Utility class for computing
 * <a href="https://en.wikipedia.org/wiki/Euler%27s_totient_function">Euler's totient function</a>.
 */
public final class EulersFunction {
    private EulersFunction() {
    }

    /**
     * Validates that the input is a positive integer.
     *
     * @param n the input number to validate
     * @throws IllegalArgumentException if {@code n} is non-positive
     */
    private static void checkInput(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive.");
        }
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
        checkInput(n);
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
