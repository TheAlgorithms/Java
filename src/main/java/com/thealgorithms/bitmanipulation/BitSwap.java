package com.thealgorithms.bitmanipulation;

public final class BitSwap {
    private BitSwap() {
    }
    /*
     * Swaps the bits at the position swap1 and swap2 from bit
     */
    public static int bitSwap(int data, final int posA, final int posB) {
        int val1 = (data >> posA) & 1;
        int val2 = (data >> posB) & 1;

        if (val1 != val2) {
            data = data ^ (1 << posA);
            data = data ^ (1 << posB);
        }
        return data;
    }
}
