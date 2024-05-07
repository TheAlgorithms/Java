package com.thealgorithms.others;

public final class ArrayRightRotation {
    private ArrayRightRotation() {
    }
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
