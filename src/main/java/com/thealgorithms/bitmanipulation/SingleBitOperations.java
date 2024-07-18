package com.thealgorithms.bitmanipulation;

/*
 * Author: lukasb1b (https://github.com/lukasb1b)
 */

public final class SingleBitOperations {
    private SingleBitOperations() {
    }
    /**
     * Flip the bit at position 'bit' in 'num'
     */
    public static int flipBit(final int num, final int bit) {
        return num ^ (1 << bit);
    }
    /**
     * Set the bit at position 'bit' to 1 in the 'num' variable
     */
    public static int setBit(final int num, final int bit) {
        return num | (1 << bit);
    }
    /**
     * Clears the bit located at 'bit' from 'num'
     */
    public static int clearBit(final int num, final int bit) {
        return num & ~(1 << bit);
    }
    /**
     * Get the bit located at 'bit' from 'num'
     */
    public static int getBit(final int num, final int bit) {
        return ((num >> bit) & 1);
    }
}
