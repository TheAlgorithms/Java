package com.thealgorithms.bitmanipulation;

/**
 * A utility class to swap every pair of adjacent bits in a given integer.
 * This operation shifts the even-positioned bits to odd positions and vice versa.
 *
 * Example:
 * - Input: 2 (binary: `10`) → Output: 1 (binary: `01`)
 * - Input: 43 (binary: `101011`) → Output: 23 (binary: `010111`)
 *
 * **Explanation of the Algorithm:**
 * 1. Mask even-positioned bits: Using `0xAAAAAAAA` (binary: `101010...`),
 *    which selects bits in even positions.
 * 2. Mask odd-positioned bits: Using `0x55555555` (binary: `010101...`),
 *    which selects bits in odd positions.
 * 3. Shift bits:
 *    - Right-shift even-positioned bits by 1 to move them to odd positions.
 *    - Left-shift odd-positioned bits by 1 to move them to even positions.
 * 4. Combine both shifted results using bitwise OR (`|`) to produce the final result.
 *
 * Use Case: This algorithm can be useful in applications involving low-level bit manipulation,
 * such as encoding, data compression, or cryptographic transformations.
 *
 * Time Complexity: O(1) (constant time, since operations are bitwise).
 *
 * Author: Lakshyajeet Singh Goyal (https://github.com/DarkMatter-999)
 */
public final class SwapAdjacentBits {
    private SwapAdjacentBits() {
    }

    /**
     * Swaps every pair of adjacent bits of a given integer.
     * Steps:
     * 1. Mask the even-positioned bits.
     * 2. Mask the odd-positioned bits.
     * 3. Shift the even bits to the right and the odd bits to the left.
     * 4. Combine the shifted bits.
     *
     * @param num the integer whose bits are to be swapped
     * @return the integer after swapping every pair of adjacent bits
     */
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
