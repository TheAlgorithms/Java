package Stacks;

import java.util.Stack;

public class LargestRectAreaInHistogram {

	static int findMaxArea(int hist[], int n) {
		Stack<Integer> stack = new Stack<Integer>();
		int max_area = 0;
		int top;
		int currentAreaSoFar;

		// Run through all bars of given histogram
		int i = 0;
		while (i < n) {
			// If this bar is longer then stack.push
			if (stack.empty() || hist[stack.peek()] <= hist[i])
				stack.push(i++);

			// If this bar is lower than top of stack, then calculate area of
			// rectangle
			// with stack top as the smallest (or minimum height) bar. 'i' is
			// 'right index' for the top and element before top in stack is
			// 'left index'
			else {
				top = stack.peek(); // store the top index
				stack.pop(); // pop the top

				// Calculate the area with hist[tp] stack as smallest bar
				currentAreaSoFar = hist[top]
						* (stack.empty() ? i : i - stack.peek() - 1);

				// update max area, if needed
				if (max_area < currentAreaSoFar)
					max_area = currentAreaSoFar;
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (stack.empty() == false) {
			top = stack.peek();
			stack.pop();
			currentAreaSoFar = hist[top]
					* (stack.empty() ? i : i - stack.peek() - 1);

			if (max_area < currentAreaSoFar)
				max_area = currentAreaSoFar;
		}
		return max_area;

	}

	// main method to test
	public static void main(String[] args) {
		int hist[] = { 5, 4, 9, 6 };
		System.out.println("Maximum area of a rectangle possible is "
				+ findMaxArea(hist, hist.length));
	}
}