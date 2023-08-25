package com.thealgorithms.bitmanipulation;

/**
 * Find Highest Set Bit
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class HighestSetBit {

    public static int findHighestSetBit(int num) {
        if (num == 0) {
            return -1; // No set bits
        }

        int position = 0;
        while (num > 0) {
            num >>= 1;
            position++;
        }

        return position;
    }
}