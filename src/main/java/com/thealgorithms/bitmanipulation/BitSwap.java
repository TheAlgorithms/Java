package com.thealgorithms.bitmanipulation;

/**
 * Utility class for performing bit-swapping operations on integers.
 * This class cannot be instantiated.
 */
public final class BitSwap {
    private BitSwap() {
    }

    /**
     * Swaps two bits at specified positions in an integer.
     *
     * @param data The input integer whose bits need to be swapped
     * @param posA The position of the first bit (0-based, from least significant)
     * @param posB The position of the second bit (0-based, from least significant)
     * @return The modified value with swapped bits
     * @throws IllegalArgumentException if either position is negative or ≥ 32
     */
    public static int bitSwap(int data, final int posA, final int posB) {
        if (posA < 0 || posA >= Integer.SIZE || posB < 0 || posB >= Integer.SIZE) {
            throw new IllegalArgumentException("Bit positions must be between 0 and 31");
        }

        if (SingleBitOperations.getBit(data, posA) != SingleBitOperations.getBit(data, posB)) {
            data ^= (1 << posA) ^ (1 << posB);
        }
        return data;
    }
}
