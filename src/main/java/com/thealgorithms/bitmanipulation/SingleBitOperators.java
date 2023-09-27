package com.thealgorithms.bitmanipulation;

/*
 * Author: lukasb1b (https://github.com/lukasb1b)
 */

public class SingleBitOperators {
    /**
     * Flip the bit at position 'bit' in 'num'
     */
    public static int flipBit(int num, int bit) {
        return num ^ (1 << bit);
    }
    /**
     * Set the bit at position 'bit' to 1 in the 'num' variable
     */
    public static int setBit(int num, int bit) {
        return num | (1 << bit);
    }
    /**
     * Clears the bit located at 'bit' from 'num'
     */
    public static int clearBit(int num, int bit) {
        return num & ~(1 << clear);
    }
    /**
     * Get the bit located at 'bit' from 'num'
     */
    public static int getBit(int num, int bit){
        return ((num >> get) & 1);
    }
}
