package com.thealgorithms.bitmanipulation;

/**
 * A utility class for performing single-bit operations on integers.
 * These operations include flipping, setting, clearing, and getting
 * individual bits at specified positions.
 *
 * Bit positions are zero-indexed (i.e., the least significant bit is at position 0).
 * These methods leverage bitwise operations for optimal performance.
 *
 * Examples:
 * - `flipBit(3, 1)` flips the bit at index 1 in binary `11` (result: `1`).
 * - `setBit(4, 0)` sets the bit at index 0 in `100` (result: `101` or 5).
 * - `clearBit(7, 1)` clears the bit at index 1 in `111` (result: `101` or 5).
 * - `getBit(6, 0)` checks if the least significant bit is set (result: `0`).
 *
 * Time Complexity: O(1) for all operations.
 *
 * Author: lukasb1b (https://github.com/lukasb1b)
 */
public final class SingleBitOperations {
    private SingleBitOperations() {
    }

    /**
     * Flips (toggles) the bit at the specified position.
     *
     * @param num the input number
     * @param bit the position of the bit to flip (0-indexed)
     * @return the new number after flipping the specified bit
     */
    public static int flipBit(final int num, final int bit) {
        return num ^ (1 << bit);
    }

    /**
     * Sets the bit at the specified position to 1.
     *
     * @param num the input number
     * @param bit the position of the bit to set (0-indexed)
     * @return the new number after setting the specified bit to 1
     */
    public static int setBit(final int num, final int bit) {
        return num | (1 << bit);
    }

    /**
     * Clears the bit at the specified position (sets it to 0).
     *
     * @param num the input number
     * @param bit the position of the bit to clear (0-indexed)
     * @return the new number after clearing the specified bit
     */
    public static int clearBit(final int num, final int bit) {
        return num & ~(1 << bit);
    }

    /**
     * Gets the bit value (0 or 1) at the specified position.
     *
     * @param num the input number
     * @param bit the position of the bit to retrieve (0-indexed)
     * @return 1 if the bit is set, 0 otherwise
     */
    public static int getBit(final int num, final int bit) {
        return (num >> bit) & 1;
    }
}
