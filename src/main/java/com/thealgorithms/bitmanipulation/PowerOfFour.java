/*
 * TheAlgorithms (https://github.com/TheAlgorithms/Java)
 * Author: Shewale41
 * This file is licensed under the MIT License.
 */

package com.thealgorithms.bitmanipulation;

/**
 * Check if a given integer is a power of four using bit manipulation.
 *
 * <p>A number is a power of four if:
 * <ul>
 *   <li>It is positive.</li>
 *   <li>It has only one set bit in its binary representation.</li>
 *   <li>The only set bit is in an even position (checked with 0xAAAAAAAA mask).</li>
 * </ul>
 *
 * <p>Example:
 * 4 -> true (2^2)
 * 16 -> true (4^2)
 * 8 -> false (not power of 4)
 */
public final class PowerOfFour {

    private PowerOfFour() {
        // Utility class
    }

    /**
     * Checks whether a given integer is a power of four.
     *
     * @param n number to check
     * @return true if n is a power of four, false otherwise
     */
    public static boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xAAAAAAAA) == 0;
    }
}
