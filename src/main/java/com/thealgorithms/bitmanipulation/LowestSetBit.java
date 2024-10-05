package com.thealgorithms.bitmanipulation;

public final class LowestSetBit {

    /**
     * Method to isolate the lowest set bit of a given integer.
     * @param n the integer input
     * @return the isolated lowest set bit or 0 if n is 0
     */
    public int isolateLowestSetBit(int n) {
        return n & -n; // Works for both positive and negative
    }

    /**
     * Method to clear the lowest set bit of a given integer.
     * @param n the integer input
     * @return the integer with the lowest set bit cleared
     */
    public int clearLowestSetBit(int n) {
        return n & (n - 1);
    }
}
