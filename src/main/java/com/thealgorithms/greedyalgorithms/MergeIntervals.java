package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Statement:
 * Given an array of intervals where intervals[i] = [starti, endi].
 *
 * Merge all overlapping intervals and return an array of the non-overlapping
 * intervals
 * that cover all the intervals in the input.
 */
public final class MergeIntervals {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MergeIntervals() {
    }

    /**
     * Merges overlapping intervals from the given array of intervals.
     *
     * The method sorts the intervals by their start time, then iterates through the
     * sorted intervals
     * and merges overlapping intervals. If an interval overlaps with the last
     * merged interval,
     * it updates the end time of the last merged interval. Otherwise, it adds the
     * interval as a new entry.
     *
     * @param intervals A 2D array representing intervals where each element is an
     *                  interval [starti, endi].
     * @return A 2D array of merged intervals where no intervals overlap.
     *
     *         Example:
     *         Input: {{1, 3}, {2, 6}, {8, 10}, {15, 18}}
     *         Output: {{1, 6}, {8, 10}, {15, 18}}
     */
    public static int[][] merge(int[][] intervals) {
        // Sort the intervals by their start time (ascending order)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // List to store merged intervals
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) { // Each interval
            // If the merged list is empty or the current interval does not overlap with
            // the last merged interval, add it to the merged list.
            if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(interval);
            } else {
                // If there is an overlap, merge the intervals by updating the end time
                // of the last merged interval to the maximum end time between the two
                // intervals.
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        // Convert the list of merged intervals back to a 2D array and return it
        return merged.toArray(new int[merged.size()][]);
    }
}
