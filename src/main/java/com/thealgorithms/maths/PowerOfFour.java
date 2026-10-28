package com.thealgorithms.maths;

/**
 * Utility class for checking if a number is a power of four.
 * A power of four is a number that can be expressed as 4^n where n is a non-negative integer.
 * This class provides a method to determine if a given integer is a power of four using bit manipulation.
 *
 * @author krishna-medapati (https://github.com/krishna-medapati)
 */
public final class PowerOfFour {
    private PowerOfFour() {
    }

    /**
     * Checks if the given integer is a power of four.
     *
     * A number is considered a power of four if:
     * 1. It is greater than zero
     * 2. It has exactly one '1' bit in its binary representation (power of two)
     * 3. The '1' bit is at an even position (0, 2, 4, 6, ...)
     *
     * The method uses the mask 0x55555555 (binary: 01010101010101010101010101010101)
     * to check if the set bit is at an even position.
     *
     * @param number the integer to check
     * @return true if the number is a power of four, false otherwise
     */
    public static boolean isPowerOfFour(int number) {
        if (number <= 0) {
            return false;
        }
        boolean isPowerOfTwo = (number & (number - 1)) == 0;
        boolean hasEvenBitPosition = (number & 0x55555555) != 0;
        return isPowerOfTwo && hasEvenBitPosition;
    }
}
