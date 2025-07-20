package com.thealgorithms.maths;

import java.util.Arrays;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Median
 */
public final class Median {
    private Median() {
    }

    /**
     * Calculate average median
     * @param values sorted numbers to find median of
     * @return median of given {@code values}
     * @throws IllegalArgumentException If the input array is empty or null.
     */
    public static double median(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Values array cannot be empty or null");
        }

        Arrays.sort(values);
        int length = values.length;
        return length % 2 == 0 ? (values[length / 2] + values[length / 2 - 1]) / 2.0 : values[length / 2];
    }
}
