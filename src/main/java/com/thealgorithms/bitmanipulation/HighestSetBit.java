package com.thealgorithms.bitmanipulation;

import java.util.Optional;

/**
 * Find Highest Set Bit
 *
 * This class provides a utility method to calculate the position of the highest
 * (most significant) bit that is set to 1 in a given non-negative integer.
 * It is often used in bit manipulation tasks to find the left-most set bit in binary
 * representation of a number.
 *
 * Example:
 * - For input 18 (binary 10010), the highest set bit is at position 4 (zero-based index).
 *
 * @author Bama Charan Chhandogi
 * @version 1.0
 * @since 2021-06-23
 */
public final class HighestSetBit {

    private HighestSetBit() {
    }

    /**
     * Finds the highest (most significant) set bit in the given integer.
     * The method returns the position (index) of the highest set bit as an {@link Optional}.
     *
     * - If the number is 0, no bits are set, and the method returns {@link Optional#empty()}.
     * - If the number is negative, the method throws {@link IllegalArgumentException}.
     *
     * @param num The input integer for which the highest set bit is to be found. It must be non-negative.
     * @return An {@link Optional} containing the index of the highest set bit (zero-based).
     *         Returns {@link Optional#empty()} if the number is 0.
     * @throws IllegalArgumentException if the input number is negative.
     */
    public static Optional<Integer> findHighestSetBit(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Input cannot be negative");
        }

        if (num == 0) {
            return Optional.empty();
        }

        int position = 0;
        while (num > 0) {
            num >>= 1;
            position++;
        }

        return Optional.of(position - 1); // Subtract 1 to convert to zero-based index
    }
}
