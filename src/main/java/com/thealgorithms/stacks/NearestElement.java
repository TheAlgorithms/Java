package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Implementation of classic stack-based algorithms:
 * 1. Nearest Greater to Right
 * 2. Nearest Greater to Left
 * 3. Nearest Smaller to Right
 * 4. Nearest Smaller to Left
 *
 * These algorithms are fundamental for technical interviews and array-stack problems.
 */
public final class NearestElement {

    // Private constructor to prevent instantiation
    private NearestElement() {
    }

    public static int[] nearestGreaterToRight(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");

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
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");

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
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");

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
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");

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
