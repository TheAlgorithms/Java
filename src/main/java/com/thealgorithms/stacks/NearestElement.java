package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * This class provides four algorithms to find the nearest greater or smaller elements
 * on either side of each element in an array using a stack.
 *
 * <p>These algorithms are fundamental examples of the monotonic stack approach, commonly
 * used in competitive programming and technical interviews. Each method runs in O(n)
 * time complexity and O(n) space complexity.
 */
public final class NearestElement {

    // Private constructor to prevent instantiation
    private NearestElement() {}

    /**
     * Finds the nearest greater element to the right for each element in the array.
     *
     * @param arr the input array
     * @return an array containing the nearest greater element to the right for each element
     */
    public static int[] nearestGreaterToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    /**
     * Finds the nearest greater element to the left for each element in the array.
     *
     * @param arr the input array
     * @return an array containing the nearest greater element to the left for each element
     */
    public static int[] nearestGreaterToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    /**
     * Finds the nearest smaller element to the right for each element in the array.
     *
     * @param arr the input array
     * @return an array containing the nearest smaller element to the right for each element
     */
    public static int[] nearestSmallerToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    /**
     * Finds the nearest smaller element to the left for each element in the array.
     *
     * @param arr the input array
     * @return an array containing the nearest smaller element to the left for each element
     */
    public static int[] nearestSmallerToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }
}
