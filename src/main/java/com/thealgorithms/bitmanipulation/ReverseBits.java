package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to reverse the bits of a 32-bit integer.
 * Reversing the bits means that the least significant bit (LSB) becomes
 * the most significant bit (MSB) and vice versa.
 *
 * Example:
 * Input (binary): 00000010100101000001111010011100 (43261596)
 * Output (binary): 00111001011110000010100101000000 (964176192)
 *
 * Time Complexity: O(32) - A fixed number of 32 iterations
 * Space Complexity: O(1) - No extra space used
 *
 * Note:
 * - If the input is negative, Java handles it using two’s complement representation.
 * - This function works on 32-bit integers by default.
 *
 * @author Bama Charan Chhandogi
 */
public final class ReverseBits {
    private ReverseBits() {
    }

    /**
     * Reverses the bits of a 32-bit integer.
     *
     * @param n the integer whose bits are to be reversed
     * @return the integer obtained by reversing the bits of the input
     */
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
