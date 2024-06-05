package com.thealgorithms.bitmanipulation;

/**
 * Converts any Octal Number to a Binary Number
 * @author Bama Charan Chhandogi
 */

public final class ReverseBits {
    private ReverseBits() {
    }

    public static int reverseBits(int n) {
        int result = 0;
        int bitCount = 32;
        for (int i = 0; i < bitCount; i++) {
            result <<= 1; // Left shift the result to make space for the next bit
            result |= (n & 1); // OR operation to set the least significant bit of result with the current bit of n
            n >>= 1; // Right shift n to move on to the next bit
        }
        return result;
    }
}
