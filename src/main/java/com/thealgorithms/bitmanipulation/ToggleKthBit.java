package com.thealgorithms.bitmanipulation;

/**
 * The ToggleKthBit class provides a method to toggle the K-th bit of a given number.
 *
 * Toggling means: if the K-th bit is 1, it becomes 0; if it's 0, it becomes 1.
 *
 * Example:
 *   Input: n = 10 (1010 in binary), k = 1
 *   Output: 8  (1000 in binary)
 *
 * @author Rahul
 */
public final class ToggleKthBit {

    private ToggleKthBit() {
        // Utility class, no need to instantiate
    }

    /**
     * Toggles the K-th bit (0-based index from right) of a number.
     *
     * @param n the number to toggle the bit in
     * @param k the position of the bit to toggle (0-based)
     * @return the number after toggling the K-th bit
     */
    public static int toggleKthBit(int n, int k) {
        return n ^ (1 << k);
    }
}
