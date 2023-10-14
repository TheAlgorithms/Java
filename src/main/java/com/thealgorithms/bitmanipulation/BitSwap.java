package com.thealgorithms.bitmanipulation;

public final class BitSwap {
    private BitSwap() {
    }
    /*
     * Swaps the bits at the position swap1 and swap2 from bit
     */
    public static int bitSwap(int data, final int posA, final int posB) {

        if (SingleBitOperations.getBit(data, posA) != SingleBitOperations.getBit(data, posB)) {
            data = data ^ (1 << posA);
            data = data ^ (1 << posB);
        }
        return data;
    }
}
