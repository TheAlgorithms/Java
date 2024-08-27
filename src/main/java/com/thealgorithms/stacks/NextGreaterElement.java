package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Utility class to find the next greater element for each element in a given integer array.
 *
 * <p>The next greater element for an element x is the first greater element on the right side of x in the array.
 * If no such element exists, the result will contain 0 for that position.</p>
 *
 * <p>Example:</p>
 * <pre>
 * Input:  {2, 7, 3, 5, 4, 6, 8}
 * Output: {7, 0, 5, 6, 6, 8, 0}
 * </pre>
 */
public final class NextGreaterElement {
    private NextGreaterElement() {
    }

    /**
     * Finds the next greater element for each element in the given array.
     *
     * @param array the input array of integers
     * @return an array where each element is replaced by the next greater element on the right side in the input array,
     * or 0 if there is no greater element.
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] findNextGreaterElements(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        int[] result = new int[array.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] < array[i]) {
                result[stack.pop()] = array[i];
            }
            stack.push(i);
        }

        return result;
    }
}
