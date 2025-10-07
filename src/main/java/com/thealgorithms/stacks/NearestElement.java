package com.thealgorithms.datastructures.stacks;

import java.util.Stack;
import java.util.Arrays;

public final class NearestElement {

    private NearestElement() {}

    public static int[] nearestGreaterToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> indexStack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!indexStack.isEmpty() && arr[i] >= arr[indexStack.peek()]) {
                indexStack.pop();
            }
            result[i] = indexStack.isEmpty() ? -1 : arr[indexStack.peek()];
            indexStack.push(i);
        }
        return result;
    }

    public static int[] nearestGreaterToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && arr[i] >= arr[indexStack.peek()]) {
                indexStack.pop();
            }
            result[i] = indexStack.isEmpty() ? -1 : arr[indexStack.peek()];
            indexStack.push(i);
        }
        return result;
    }

    public static int[] nearestSmallerToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> indexStack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!indexStack.isEmpty() && arr[i] <= arr[indexStack.peek()]) {
                indexStack.pop();
            }
            result[i] = indexStack.isEmpty() ? -1 : arr[indexStack.peek()];
            indexStack.push(i);
        }
        return result;
    }

    public static int[] nearestSmallerToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && arr[i] <= arr[indexStack.peek()]) {
                indexStack.pop();
            }
            result[i] = indexStack.isEmpty() ? -1 : arr[indexStack.peek()];
            indexStack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] sampleArray = {4, 5, 2, 10, 8};
        System.out.println("Nearest Greater to Right: " + Arrays.toString(nearestGreaterToRight(sampleArray)));
        System.out.println("Nearest Greater to Left: " + Arrays.toString(nearestGreaterToLeft(sampleArray)));
        System.out.println("Nearest Smaller to Right: " + Arrays.toString(nearestSmallerToRight(sampleArray)));
        System.out.println("Nearest Smaller to Left: " + Arrays.toString(nearestSmallerToLeft(sampleArray)));
    }
}
