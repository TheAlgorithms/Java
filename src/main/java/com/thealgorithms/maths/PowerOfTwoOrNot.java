package com.thealgorithms.maths;

/**
 * A utility to check if a given number is power of two or not. For example 8,16
 * etc.
 */
public final class PowerOfTwoOrNot {
    private PowerOfTwoOrNot() {
    }

    /**
     * Checks whether given number is power of two or not.
     *
     * @param number the number to check
     * @return {@code true} if given number is power of two, otherwise
     * {@code false}
     */
    public static boolean checkIfPowerOfTwoOrNot(final int number) {
        return number != 0 && ((number & (number - 1)) == 0);
    }
}
