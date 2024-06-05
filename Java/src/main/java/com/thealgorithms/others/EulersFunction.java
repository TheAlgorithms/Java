package com.thealgorithms.others;

/**
 * @brief utility class for <a href="https://en.wikipedia.org/wiki/Euler%27s_totient_function">Euler's totient function</a>
 */
public final class EulersFunction {
    private EulersFunction() {
    }

    private static void checkInput(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive.");
        }
    }

    /**
     * @brief computes the value of Euler's totient function for given input
     * @details has time complexity of O(sqrt(n))
     * @param n the input
     * @exception IllegalArgumentException n is non-positive
     * @return the value of Euler's totient function for the input
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
