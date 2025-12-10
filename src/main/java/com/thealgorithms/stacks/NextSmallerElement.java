package com.thealgorithms.stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * Utility class to find the next smaller element for each element in a given integer array.
 *
 * <p>The next smaller element for an element x is the first smaller element on the left side of x in the array.
 * If no such element exists, the result will contain -1 for that position.</p>
 *
 * <p>Example:</p>
 * <pre>
 * Input:  {2, 7, 3, 5, 4, 6, 8}
 * Output: [-1, 2, 2, 3, 3, 4, 6]
 * </pre>
 */
public final class NextSmallerElement {
    private NextSmallerElement() {
    }

    /**
     * Finds the next smaller element for each element in the given array.
     *
     * @param array the input array of integers
     * @return an array where each element is replaced by the next smaller element on the left side in the input array,
     * or -1 if there is no smaller element.
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] findNextSmallerElements(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        int[] result = new int[array.length];
        Stack<Integer> stack = new Stack<>();

        // Initialize all elements to -1 (in case there is no smaller element)
        Arrays.fill(result, -1);

        // Traverse the array from left to right
        for (int i = 0; i < array.length; i++) {
            // Maintain the stack such that the top of the stack is the next smaller element
            while (!stack.isEmpty() && stack.peek() >= array[i]) {
                stack.pop();
            }

            // If stack is not empty, then the top is the next smaller element
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }

            // Push the current element onto the stack
            stack.push(array[i]);
        }

        return result;
    }
}
