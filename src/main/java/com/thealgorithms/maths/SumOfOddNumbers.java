package com.thealgorithms.maths;

/**
 * This program calculates the sum of the first n odd numbers.
 *
 * https://www.cuemath.com/algebra/sum-of-odd-numbers/
 */

public final class SumOfOddNumbers {
    private SumOfOddNumbers() {
    }

    /**
     * Calculate sum of the first n odd numbers
     *
     * @param n the number of odd numbers to sum
     * @return sum of the first n odd numbers
     */
    public static int sumOfFirstNOddNumbers(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative.");
        }
        return n * n;
    }
}
