package com.thealgorithms.bitmanipulation;

public class CountLeadingZeros {

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
