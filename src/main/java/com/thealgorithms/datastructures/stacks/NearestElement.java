package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

/**
 * The NearestElement class provides implementations for classic stack-based
 * algorithms to find the nearest greater or smaller elements in an array. These
 * algorithms use a Monotonic Stack approach to achieve linear time complexity.
 */
public final class NearestElement {

    private NearestElement() {
    }

    /**
     * For each element in an array, finds the first element to its right that
     * is greater.
     *
     * @param arr the input array of integers
     * @return an array where each index i contains the nearest greater element
     * to the right of arr[i], or -1 if none exists
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] nearestGreaterToRight(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // Safety Check 1: Short-circuit && prevents peek() on empty stack
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            // Safety Check 2: Ternary operator handles empty stack case
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    /**
     * For each element in an array, finds the first element to its left that is
     * greater.
     *
     * @param arr the input array of integers
     * @return an array where each index i contains the nearest greater element
     * to the left of arr[i], or -1 if none exists
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] nearestGreaterToLeft(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Safety Check 3: Short-circuit && prevents peek() on empty stack
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            // Safety Check 4: Ternary operator handles empty stack case
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    /**
     * For each element in an array, finds the first element to its right that
     * is smaller.
     *
     * @param arr the input array of integers
     * @return an array where each index i contains the nearest smaller element
     * to the right of arr[i], or -1 if none exists
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] nearestSmallerToRight(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // Safety Check 5: Short-circuit && prevents peek() on empty stack
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            // Safety Check 6: Ternary operator handles empty stack case
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    /**
     * For each element in an array, finds the first element to its left that is
     * smaller.
     *
     * @param arr the input array of integers
     * @return an array where each index i contains the nearest smaller element
     * to the left of arr[i], or -1 if none exists
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] nearestSmallerToLeft(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Safety Check 7: Short-circuit && prevents peek() on empty stack
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            // Safety Check 8: Ternary operator handles empty stack case
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }
}
