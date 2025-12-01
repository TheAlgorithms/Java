package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to check if a given number is a power of four.
 */
public final class PowerOfFour {

    /** Private constructor to prevent instantiation. */
    private PowerOfFour() {
        throw new AssertionError("Cannot instantiate utility class.");
    }

    /**
     * Checks whether the given integer is a power of four.
     *
     * @param n the number to check
     * @return true if n is a power of four, false otherwise
     */
    public static boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        } else {
            return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
        }
    }
}
