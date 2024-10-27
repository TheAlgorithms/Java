package com.thealgorithms.maths;

public final class FindMax {
    private FindMax() {
    }

    /**
     * @param array the input array
     * @return the maximum value stored in the input array
     * @throws IllegalArgumentException input array is empty
     * @brief finds the maximum value stored in the input array
     */
    public static int findMax(final int[] array) {
        int n = array.length;
        if (n == 0) {
            throw new IllegalArgumentException("Array must be non-empty.");
        }
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
