package com.thealgorithms.bitmanipulation;

/**
 * Swap every pair of adjacent bits of a given number.
 * @author Lakshyajeet Singh Goyal (https://github.com/DarkMatter-999)
 */

public final class SwapAdjacentBits {
    private SwapAdjacentBits() {
    }

    public static int swapAdjacentBits(int num) {
        // mask the even bits (0xAAAAAAAA => 10101010...)
        int evenBits = num & 0xAAAAAAAA;

        // mask the odd bits (0x55555555 => 01010101...)
        int oddBits = num & 0x55555555;

        // right shift even bits and left shift odd bits
        evenBits >>= 1;
        oddBits <<= 1;

        // combine shifted bits
        return evenBits | oddBits;
    }
}
