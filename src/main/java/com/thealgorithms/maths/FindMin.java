package com.thealgorithms.maths;

public final class FindMin {
    private FindMin() {
    }

    /**
     * @brief finds the minimum value stored in the input array
     *
     * @param array the input array
     * @exception IllegalArgumentException input array is empty
     * @return the mimum value stored in the input array
     */
    public static int findMin(final int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array must be non-empty.");
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}
