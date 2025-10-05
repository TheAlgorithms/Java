package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

/**
 * The {@code NearestElement} class provides static utility methods to find the nearest greater or smaller elements
 * to the left or right of each element in an integer array using stack-based algorithms.
 *
 * <p>Each method runs in O(n) time complexity by maintaining a monotonic stack:
 * <ul>
 *   <li>{@code nearestGreaterToRight}: Finds the nearest greater element to the right of each element.</li>
 *   <li>{@code nearestGreaterToLeft}: Finds the nearest greater element to the left of each element.</li>
 *   <li>{@code nearestSmallerToRight}: Finds the nearest smaller element to the right of each element.</li>
 *   <li>{@code nearestSmallerToLeft}: Finds the nearest smaller element to the left of each element.</li>
 * </ul>
 */
public final class NearestElement {

    private NearestElement() {
        throw new UnsupportedOperationException("Utility class");
    }

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
