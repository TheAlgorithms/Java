package com.thealgorithms.bitmanipulation;
/**
 * Sets a specific bit to 1
 */

public class SetBit {
    public static int setBit(int num, int bit) {
        return num | (1 << bit);
    }
}
