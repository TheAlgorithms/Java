package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

/**
 *
 * @author mohd rameez github.com/rameez471
 */

public class LargestRectangle {
	public static  String largestRectanglehistogram(int[] heights) {
		int n = heights.length, maxArea = 0;
		Stack<int[]> st = new Stack<>();
		for(int i=0;i<n;i++) {
			int start = i;
			while(!st.isEmpty() && st.peek()[1] > heights[i]) {
				int[] tmp = st.pop();
				maxArea = Math.max(maxArea, tmp[1]*(i - tmp[0]));
				start = tmp[0];
			}
			st.push(new int[]{start, heights[i]});
		}
		while(!st.isEmpty()) {
			int[] tmp = st.pop();
			maxArea = Math.max(maxArea, tmp[1]*(n-tmp[0]));
		}
		return Integer.toString(maxArea);
	}
	public static void main(String[] args) {
        assert largestRectanglehistogram(new int[]{2, 1, 5, 6, 2, 3}).equals("10");
        assert largestRectanglehistogram(new int[]{2, 4}).equals("4");
    }
}
