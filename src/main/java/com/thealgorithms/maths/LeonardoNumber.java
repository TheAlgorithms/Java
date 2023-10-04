package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Leonardo_number
 */
public class LeonardoNumber {

    /**
     * Calculate nth Leonardo Number (1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, ...)
     *
     * @param n the index of Leonardo Number to calculate
     * @return nth number of Leonardo sequences
     */
    public static int leonardoNumber(int n) {
        if (n < 0) {
            throw new ArithmeticException();
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return (leonardoNumber(n - 1) + leonardoNumber(n - 2) + 1);
    }
}
