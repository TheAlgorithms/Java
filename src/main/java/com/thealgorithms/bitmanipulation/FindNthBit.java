package com.thealgorithms.bitmanipulation;

/**
 * A utility class to find the Nth bit of a given number.
 *
 * <p>This class provides a method to extract the value of the Nth bit (either 0 or 1)
 * from the binary representation of a given integer.
 *
 * <p>Example:
 * <pre>{@code
 * int result = FindNthBit.findNthBit(5, 2); // returns 0 as the 2nd bit of 5 (binary 101) is 0.
 * }</pre>
 *
 * <p>Author: <a href="https://github.com/Tuhinm2002">Tuhinm2002</a>
 */
public final class FindNthBit {

    /**
     * Private constructor to prevent instantiation.
     *
     * <p>This is a utility class, and it should not be instantiated.
     * Attempting to instantiate this class will throw an UnsupportedOperationException.
     */
    private FindNthBit() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Finds the value of the Nth bit of the given number.
     *
     * <p>This method uses bitwise operations to extract the Nth bit from the
     * binary representation of the given integer.
     *
     * @param num the integer number whose Nth bit is to be found
     * @param n   the bit position (1-based) to retrieve
     * @return    the value of the Nth bit (0 or 1)
     * @throws IllegalArgumentException if the bit position is less than 1
     */
    public static int findNthBit(int num, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Bit position must be greater than or equal to 1.");
        }
        // Shifting the number to the right by (n - 1) positions and checking the last bit
        return (num & (1 << (n - 1))) >> (n - 1);
    }
}
