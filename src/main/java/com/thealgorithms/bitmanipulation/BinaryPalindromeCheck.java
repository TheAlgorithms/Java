package com.thealgorithms.bitmanipulation;

/**
 * This class contains a method to check if the binary representation of a number is a palindrome.
 * <p>
 *     A binary palindrome is a number whose binary representation is the same when read from left to right and right to left.
 *     For example, the number 9 has a binary representation of 1001, which is a palindrome.
 *     The number 10 has a binary representation of 1010, which is not a palindrome.
 * </p>
 *
 * @author Hardvan
 */
public final class BinaryPalindromeCheck {
    private BinaryPalindromeCheck() {
    }

    /**
     * Checks if the binary representation of a number is a palindrome.
     *
     * @param x The number to check.
     * @return True if the binary representation is a palindrome, otherwise false.
     */
    public static boolean isBinaryPalindrome(int x) {
        int reversed = reverseBits(x);
        return x == reversed;
    }

    /**
     * Helper function to reverse all the bits of an integer.
     *
     * @param x The number to reverse the bits of.
     * @return The number with reversed bits.
     */
    private static int reverseBits(int x) {
        int result = 0;
        while (x > 0) {
            result <<= 1;
            result |= (x & 1);
            x >>= 1;
        }
        return result;
    }
}
