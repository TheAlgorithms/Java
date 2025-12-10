package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Utility class to calculate the largest rectangle area in a histogram.
 * Each bar's width is assumed to be 1 unit.
 *
 * <p>This implementation uses a monotonic stack to efficiently calculate
 * the area of the largest rectangle that can be formed from the histogram bars.</p>
 *
 * <p>Example usage:
 * <pre>{@code
 *     int[] heights = {2, 1, 5, 6, 2, 3};
 *     String area = LargestRectangle.largestRectangleHistogram(heights);
 *     // area is "10"
 * }</pre>
 */
public final class LargestRectangle {

    private LargestRectangle() {
    }

    /**
     * Calculates the largest rectangle area in the given histogram.
     *
     * @param heights an array of non-negative integers representing bar heights
     * @return the largest rectangle area as a {@link String}
     */
    public static String largestRectangleHistogram(int[] heights) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] popped = stack.pop();
                maxArea = Math.max(maxArea, popped[1] * (i - popped[0]));
                start = popped[0];
            }
            stack.push(new int[] {start, heights[i]});
        }

        int totalLength = heights.length;
        while (!stack.isEmpty()) {
            int[] remaining = stack.pop();
            maxArea = Math.max(maxArea, remaining[1] * (totalLength - remaining[0]));
        }

        return Integer.toString(maxArea);
    }
}
