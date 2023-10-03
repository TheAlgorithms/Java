package com.thealgorithms.bitmanipulation;

/*
 * Author: lukasb1b (https://github.com/lukasb1b)
 */

public final class CountingBitLength {
    private CountingBitLength() {
    }

    /*
     * Counts the number of bits from num
     */
    public static int countingBitLength(int num) {
        int result = 0;

        while (!(num == 0 || num == -1)) {
            result++;
            num = num >>1;
        }
        return result;
    }
}
