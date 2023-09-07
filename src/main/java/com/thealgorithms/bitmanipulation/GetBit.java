package com.thealgorithms.bitmanipulation;
/**
 * Returns the value of bit from num located at get
 */

public class GetBit {
    public static int getBit(int num, int get) {
        return (num >> get) & 1;
    }
}
