package com.thealgorithms.bitmanipulation;

public final BitSwap {
    private BitSwap() {
    }
    /*
     * Swaps the bits at the position swap1 and swap2 from bit
     */
    public static int bitSwap(int bit, int swap1, int swap2) {
        int val1 = (bit >> swap1) & 1;
        int val2 = (bit >> swap2) & 1;

        if (val1 != val2) {
            bit = bit ^ (1 << swap1);
            bit = bit ^ (1 << swap2);
        }
        return bit;
     }
}
