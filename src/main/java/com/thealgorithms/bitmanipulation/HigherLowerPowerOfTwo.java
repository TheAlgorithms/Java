package com.thealgorithms.bitmanipulation;

/**
 * HigherLowerPowerOfTwo class has two methods to find the next higher and lower power of two.
 * <p>
 *     nextHigherPowerOfTwo method finds the next higher power of two.
 *     nextLowerPowerOfTwo method finds the next lower power of two.
 *     Both methods take an integer as input and return the next higher or lower power of two.
 *     If the input is less than 1, the next higher power of two is 1.
 *     If the input is less than or equal to 1, the next lower power of two is 0.
 *     nextHigherPowerOfTwo method uses bitwise operations to find the next higher power of two.
 *     nextLowerPowerOfTwo method uses Integer.highestOneBit method to find the next lower power of two.
 *     The time complexity of both methods is O(1).
 *     The space complexity of both methods is O(1).
 * </p>
 *
 * @author Hardvan
 */
public final class HigherLowerPowerOfTwo {
    private HigherLowerPowerOfTwo() {
    }

    /**
     * Finds the next higher power of two.
     *
     * @param x The given number.
     * @return The next higher power of two.
     */
    public static int nextHigherPowerOfTwo(int x) {
        if (x < 1) {
            return 1;
        }
        x--;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return x + 1;
    }

    /**
     * Finds the next lower power of two.
     *
     * @param x The given number.
     * @return The next lower power of two.
     */
    public static int nextLowerPowerOfTwo(int x) {
        if (x < 1) {
            return 0;
        }
        return Integer.highestOneBit(x);
    }
}
