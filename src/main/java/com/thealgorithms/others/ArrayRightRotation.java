package com.thealgorithms.others;

/**
 * Provides a method to perform a right rotation on an array.
 * A left rotation operation shifts each element of the array
 * by a specified number of positions to the right.
 *
 * https://en.wikipedia.org/wiki/Right_rotation *
 */
public final class ArrayRightRotation {
    private ArrayRightRotation() {
    }

    /**
     * Performs a right rotation on the given array by the specified number of positions.
     *
     * @param arr the array to be rotated
     * @param k the number of positions to rotate the array to the left
     * @return a new array containing the elements of the input array rotated to the left
     */
    public static int[] rotateRight(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = arr.length;
        k = k % n; // Handle cases where k is larger than the array length

        reverseArray(arr, 0, n - 1);
        reverseArray(arr, 0, k - 1);
        reverseArray(arr, k, n - 1);

        return arr;
    }

    /**
     * Performs reversing of a array
     * @param arr the array to be reversed
     * @param start starting position
     * @param end ending position
     */
    private static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
