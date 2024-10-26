package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Perfect_square
 */
public final class PerfectSquare {
    private PerfectSquare() {
    }

    /**
     * Check if a number is perfect square number
     *
     * @param number the number to be checked
     * @return <tt>true</tt> if {@code number} is perfect square, otherwise
     * <tt>false</tt>
     */
    public static boolean isPerfectSquare(final int number) {
        final int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    /**
     * Check if a number is perfect square or not
     *
     * @param number number to be checked
     * @return {@code true} if {@code number} is perfect square, otherwise
     * {@code false}
     */
    public static boolean isPerfectSquareUsingPow(long number) {
        long a = (long) Math.pow(number, 1.0 / 2);
        return a * a == number;
    }
}
