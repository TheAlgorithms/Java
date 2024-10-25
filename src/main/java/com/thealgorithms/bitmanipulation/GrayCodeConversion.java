package com.thealgorithms.bitmanipulation;

/**
 * Gray code is a binary numeral system where two successive values differ in only one bit.
 * This is a simple conversion between binary and Gray code.
 * Example:
 * 7 -> 0111 -> 0100 -> 4
 * 4 -> 0100 -> 0111 -> 7
 * 0 -> 0000 -> 0000 -> 0
 * 1 -> 0001 -> 0000 -> 0
 * 2 -> 0010 -> 0011 -> 3
 * 3 -> 0011 -> 0010 -> 2
 *
 * @author Hardvan
 */
public final class GrayCodeConversion {
    private GrayCodeConversion() {
    }

    /**
     * Converts a binary number to Gray code.
     *
     * @param num The binary number.
     * @return The corresponding Gray code.
     */
    public static int binaryToGray(int num) {
        return num ^ (num >> 1);
    }

    /**
     * Converts a Gray code number back to binary.
     *
     * @param gray The Gray code number.
     * @return The corresponding binary number.
     */
    public static int grayToBinary(int gray) {
        int binary = gray;
        while (gray > 0) {
            gray >>= 1;
            binary ^= gray;
        }
        return binary;
    }
}
