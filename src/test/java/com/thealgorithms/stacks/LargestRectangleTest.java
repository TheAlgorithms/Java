package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LargestRectangleTest {

    @Test
    void testLargestRectangleHistogramWithTypicalCases() {
        // Typical case with mixed heights
        int[] heights = {2, 1, 5, 6, 2, 3};
        String expected = "10";
        String result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);

        // Another typical case with increasing heights
        heights = new int[] {2, 4};
        expected = "4";
        result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);

        // Case with multiple bars of the same height
        heights = new int[] {4, 4, 4, 4};
        expected = "16";
        result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);
    }

    @Test
    void testLargestRectangleHistogramWithEdgeCases() {
        // Edge case with an empty array
        int[] heights = {};
        String expected = "0";
        String result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);

        // Edge case with a single bar
        heights = new int[] {5};
        expected = "5";
        result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);

        // Edge case with all bars of height 0
        heights = new int[] {0, 0, 0};
        expected = "0";
        result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);
    }

    @Test
    void testLargestRectangleHistogramWithLargeInput() {
        // Large input case
        int[] heights = new int[10000];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = 1;
        }
        String expected = "10000";
        String result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);
    }

    @Test
    void testLargestRectangleHistogramWithComplexCases() {
        // Complex case with a mix of heights
        int[] heights = {6, 2, 5, 4, 5, 1, 6};
        String expected = "12";
        String result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);

        // Case with a peak in the middle
        heights = new int[] {2, 1, 5, 6, 2, 3, 1};
        expected = "10";
        result = LargestRectangle.largestRectanglehistogram(heights);
        assertEquals(expected, result);
    }
}
