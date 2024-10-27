package com.thealgorithms.bitmanipulation;

/**
 * ClearLeftmostSetBit class contains a method to clear the leftmost set bit of a number.
 * The leftmost set bit is the leftmost bit that is set to 1 in the binary representation of a number.
 *
 * Example:
 * 26 (11010) -> 10 (01010)
 * 1 (1) -> 0 (0)
 * 7 (111) -> 3 (011)
 * 6 (0110) -> 2 (0010)
 *
 * @author Hardvan
 */
public final class ClearLeftmostSetBit {
    private ClearLeftmostSetBit() {
    }

    /**
     * Clears the leftmost set bit (1) of a given number.
     * Step 1: Find the position of the leftmost set bit
     * Step 2: Create a mask with all bits set except for the leftmost set bit
     * Step 3: Clear the leftmost set bit using AND with the mask
     *
     * @param num The input number.
     * @return The number after clearing the leftmost set bit.
     */
    public static int clearLeftmostSetBit(int num) {
        int pos = 0;
        int temp = num;
        while (temp > 0) {
            temp >>= 1;
            pos++;
        }

        int mask = ~(1 << (pos - 1));
        return num & mask;
    }
}
