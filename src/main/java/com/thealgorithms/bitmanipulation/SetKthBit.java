package com.thealgorithms.bitmanipulation;

/***
 * Sets the kth bit of a given integer to 1
 * e.g. setting 3rd bit in binary of 17 (binary 10001) gives 25 (binary 11001)
 * @author inishantjain
 */

public class SetKthBit {
    /**
     * Sets the kth bit of a given integer.
     *
     * @param num The original integer.
     * @param k   The position of the bit to set (0-based index).
     * @return The integer with the kth bit set.
     */
    public static int setKthBit(int num, int k) {
        int mask = 1 << k;
        num = num | mask;
        return num;
    }
}
