package com.thealgorithms.maths;

public final class FindMax {
    private FindMax() {}

    public static int findMax(final int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array must be non-empty.");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(array[i], max);
        }
        return max;
    }
}
