package com.thealgorithms.bitmanipulation;

/**
 * Utility class for checking if a number is a power of four.
 * A power of four is a number that can be expressed as 4^n where n is a non-negative integer.
 * This class provides a method to determine if a given integer is a power of four using bit manipulation.
 */
public final class IsPowerFour {
    private IsPowerFour() {
    }

    /**
     * Checks if the given integer is a power of four.
     *
     * A number is considered a power of four if:
     * - It is greater than zero
     * - It is a power of two i.e. (n & (n - 1)) == 0
     * - Its single set bit is in an even position — verified by (n & 0xAAAAAAAA) == 0
     *
     * @param number the integer to check
     * @return true if the number is a power of false, false otherwise
     */
    public static boolean IsPowerFour(int number) {
        if (number <= 0) {
            return false;
        }
        return (number & (number - 1)) == 0 && (number & 0xAAAAAAAA) == 0;
    }
}
