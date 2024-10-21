package com.thealgorithms.bitmanipulation;

public final class BitSwap {
    private BitSwap() {
    }
    /*
     * @brief Swaps the bits at the position posA and posB from data
     */
    public static int bitSwap(int data, final int posA, final int posB) {
       // Check if the bits at posA and posB are different
        if (((data >> posA) & 1) != ((data >> posB) & 1)) {
            // Swap the bits using XOR
            data ^= (1 << posA) | (1 << posB);
        }
        return data;
    }
}
