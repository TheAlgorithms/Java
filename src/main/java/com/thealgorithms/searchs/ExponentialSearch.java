package com.thealgorithms.searchs;

import java.util.Arrays;

public class ExponentialSearch {
    // Exponential Search algorithm
    public static int exponentialSearch(int[] arr, int x) {
        int n = arr.length;

        // If the element is present at the first position
        if (arr[0] == x) return 0;

        // Find the range for binary search by repeated doubling
        int i = 1;
        while (i < n && arr[i] <= x) {
            i = i * 2;
        }

        // Do binary search in the found range
        return binarySearch(arr, x, i / 2, Math.min(i, n - 1));
    }

    // Binary Search algorithm
    private static int binarySearch(int[] arr, int x, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) return mid;

            if (arr[mid] < x) low = mid + 1;
            else high = mid - 1;
        }
        return -1; // Element not found
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21};
        int x = 15;

        int result = exponentialSearch(arr, x);

        if (result == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index " + result);
        }
    }
}
