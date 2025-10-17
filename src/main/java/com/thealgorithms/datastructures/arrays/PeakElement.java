package com.thealgorithms.datastructures.arrays;

/**
 * A utility class to find a peak element in an array.
 *
 * <p>
 * A peak element is an element greater than or equal to its neighbors.
 *
 * Time Complexity: O(log n) using binary search
 * Space Complexity: O(1)
 *
 * Author: https://github.com/VeeruYadav45
 */
public final class PeakElement {

    // Private constructor to prevent instantiation
    private PeakElement() {
    }

    /**
     * Finds the index of a peak element using binary search.
     *
     * @param arr the input array
     * @return the index of a peak element
     */
    public static int findPeakElement(final int[] arr) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            boolean leftOk = (mid == 0) || (arr[mid] >= arr[mid - 1]);
            boolean rightOk = (mid == n - 1) || (arr[mid] >= arr[mid + 1]);

            if (leftOk && rightOk) {
                return mid;
            }

            if (mid > 0 && arr[mid - 1] > arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Example usage.
     *
     * @param args command line arguments (not used)
     */
    @SuppressWarnings("PMD.UselessMainMethod")
    public static void main(final String[] args) {
        int[] arr = {1, 3, 20, 4, 1, 0};
        int peakIndex = findPeakElement(arr);
        System.out.println("Peak element is " + arr[peakIndex]);
    }
}
