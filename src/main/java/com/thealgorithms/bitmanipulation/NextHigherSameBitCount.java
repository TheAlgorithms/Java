package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to find the next higher number
 * with the same number of set bits as the given number.
 *
 * @author Hardvan
 */
public final class NextHigherSameBitCount {
    private NextHigherSameBitCount() {
    }

    /**
     * Finds the next higher integer with the same number of set bits.
     *
     * @param n the input number
     * @return the next higher integer with the same set bit count
     */
    public static int nextHigherSameBitCount(int n) {
        int c = n & -n;
        int r = n + c;
        return (((r ^ n) >> 2) / c) | r;
    }
}
