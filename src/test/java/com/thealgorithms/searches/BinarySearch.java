package com.thealgorithms.searches;

class BinarySearch {

    // Enhanced binary search method
    public static int binarySearch(int[] arr, int target) {
        // Edge case: If the array is empty
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty or null.");
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Prevent overflow for large values by calculating mid this way
            int mid = left + (right - left) / 2;

            // Debugging statement for tracking the mid element
            System.out.println("Mid index: " + mid + ", Mid element: " + arr[mid]);

            if (arr[mid] == target) {
                return mid; // Target found at index 'mid'
            } else if (arr[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        // If the target is not found
        System.out.println("Target not found.");
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19}; // Sample sorted array
        int target = 7;

        int result = binarySearch(arr, target);

        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}
