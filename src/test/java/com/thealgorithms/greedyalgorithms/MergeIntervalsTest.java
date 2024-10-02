package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MergeIntervalsTest {

    @Test
    public void testMergeIntervalsWithOverlappingIntervals() {
        // Test case where some intervals overlap and should be merged
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeIntervalsWithNoOverlap() {
        // Test case where intervals do not overlap
        int[][] intervals = {{1, 2}, {3, 4}, {5, 6}};
        int[][] expected = {{1, 2}, {3, 4}, {5, 6}};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeIntervalsWithCompleteOverlap() {
        // Test case where intervals completely overlap
        int[][] intervals = {{1, 5}, {2, 4}, {3, 6}};
        int[][] expected = {{1, 6}};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeIntervalsWithSingleInterval() {
        // Test case where only one interval is given
        int[][] intervals = {{1, 2}};
        int[][] expected = {{1, 2}};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeIntervalsWithEmptyArray() {
        // Test case where the input array is empty
        int[][] intervals = {};
        int[][] expected = {};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeIntervalsWithIdenticalIntervals() {
        // Test case where multiple identical intervals are given
        int[][] intervals = {{1, 3}, {1, 3}, {1, 3}};
        int[][] expected = {{1, 3}};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMergeIntervalsWithRandomIntervals() {
        // Test case with a mix of overlapping and non-overlapping intervals
        int[][] intervals = {{1, 4}, {5, 7}, {2, 6}, {8, 10}};
        int[][] expected = {{1, 7}, {8, 10}};
        int[][] result = MergeIntervals.merge(intervals);
        assertArrayEquals(expected, result);
    }
}
