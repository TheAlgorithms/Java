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
     * Steps:
     * 1. Find {@code c}, the rightmost set bit of {@code n}.
     * 2. Find {@code r}, the rightmost set bit of {@code n + c}.
     * 3. Swap the bits of {@code r} and {@code n} to the right of {@code c}.
     * 4. Shift the bits of {@code r} and {@code n} to the right of {@code c} to the rightmost.
     * 5. Combine the results of steps 3 and 4.
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
