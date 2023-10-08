package com.thealgorithms.maths;

import java.util.Arrays;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Median
 */
public class Median {

    /**
     * Calculate average median
     * @param values sorted numbers to find median of
     * @return median of given {@code values}
     */
    public static double median(int[] values) {
        Arrays.sort(values);
        int length = values.length;
        return length % 2 == 0 ? (values[length / 2] + values[length / 2 - 1]) / 2.0 : values[length / 2];
    }
}
