package com.thealgorithms.bitmanipulation;

/**
 * CountLeadingZeros class contains a method to count the number of leading zeros in the binary representation of a number.
 * The number of leading zeros is the number of zeros before the leftmost 1 bit.
 * For example, the number 5 has 29 leading zeros in its 32-bit binary representation.
 * The number 0 has 32 leading zeros.
 * The number 1 has 31 leading zeros.
 * The number -1 has no leading zeros.
 *
 * @author Hardvan
 */
public final class CountLeadingZeros {
    private CountLeadingZeros() {
    }

    /**
     * Counts the number of leading zeros in the binary representation of a number.
     * Method: Keep shifting the mask to the right until the leftmost bit is 1.
     * The number of shifts is the number of leading zeros.
     *
     * @param num The input number.
     * @return The number of leading zeros.
     */
    public static int countLeadingZeros(int num) {
        if (num == 0) {
            return 32;
        }

        int count = 0;
        int mask = 1 << 31;
        while ((mask & num) == 0) {
            count++;
            mask >>>= 1;
        }

        return count;
    }
}
