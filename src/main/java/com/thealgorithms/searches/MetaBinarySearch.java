package com.thealgorithms.searches;

public class MetaBinarySearch {

    /**
     * Perform Meta Binary Search on a sorted array.
     *
     * @param arr The sorted array to search.
     * @param target The target element to search for.
     * @return The index of the target element if found, -1 otherwise.
     */
    public int metaBinarySearch(int[] arr, int target) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;

        // Calculate the number of bits required for the maximum index
        int maxBits = (int) Math.ceil(Math.log(n) / Math.log(2));

        // Iterate over the bit length
        for (int i = maxBits - 1; i >= 0; i--) {
            int mid = left + (1 << i);

            // Check if mid index is within bounds
            if (mid < n && arr[mid] <= target) {
                left = mid; // move to the right half if condition is true
            }
        }

        // Check if we have found the target
        if (left < n && arr[left] == target) {
            return left;
        }

        // If target not found
        return -1;
    }
}
