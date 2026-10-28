package com.thealgorithms.maths;

import java.util.Arrays;

/**
 * Utility class for calculating the median of an array of integers.
 * The median is the middle value in a sorted list of numbers. If the list has
 * an even number
 * of elements, the median is the average of the two middle values.
 *
 * <p>
 * Time Complexity: O(n log n) due to sorting
 * <p>
 * Space Complexity: O(1) if sorting is done in-place
 *
 * @see <a href="https://en.wikipedia.org/wiki/Median">Median (Wikipedia)</a>
 * @see <a href=
 *      "https://www.khanacademy.org/math/statistics-probability/summarizing-quantitative-data/mean-median-basics/a/mean-median-and-mode-review">Mean,
 *      Median, and Mode Review</a>
 */
public final class Median {
    private Median() {
    }

    /**
     * Calculates the median of an array of integers.
     * The array is sorted internally, so the original order is not preserved.
     * For arrays with an odd number of elements, returns the middle element.
     * For arrays with an even number of elements, returns the average of the two
     * middle elements.
     *
     * @param values the array of integers to find the median of (can be unsorted)
     * @return the median value as a double
     * @throws IllegalArgumentException if the input array is empty or null
     */
    public static double median(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Values array cannot be empty or null");
        }

        Arrays.sort(values);
        int length = values.length;
        if (length % 2 == 0) {
            return (values[length / 2] + values[length / 2 - 1]) / 2.0;
        } else {
            return values[length / 2];
        }
    }
}
