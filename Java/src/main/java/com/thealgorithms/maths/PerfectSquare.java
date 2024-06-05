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
}
