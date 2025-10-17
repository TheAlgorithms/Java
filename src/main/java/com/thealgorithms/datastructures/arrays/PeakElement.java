package com.thealgorithms.datastructures.arrays;

/**
 * A program to find a peak element in an array.
 * https://www.geeksforgeeks.org/dsa/leaders-in-an-array/
 *
 * <p>
 * A peak element is an element that is greater than or equal to its neighbors.
 * For corner elements, we need to consider only one neighbor.
 *
 * Example:
 * Input: [1, 3, 20, 4, 1, 0]
 * Output: Peak element is 20
 *
 * Time Complexity: O(log n) using binary search
 * Space Complexity: O(1)
 *
 * Author: https://github.com/VeeruYadav45
 */
public class PeakElement {

    /**
     * Finds a peak element in the array using binary search.
     *
     * @param arr the input array
     * @return the index of any one peak element
     */
    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is a peak
            boolean leftOk = (mid == 0) || (arr[mid] >= arr[mid - 1]);
            boolean rightOk = (mid == n - 1) || (arr[mid] >= arr[mid + 1]);

            if (leftOk && rightOk) {
                return mid;
            }

            // If left neighbor is greater, move left
            if (mid > 0 && arr[mid - 1] > arr[mid]) {
                high = mid - 1;
            } else { // Otherwise move right
                low = mid + 1;
            }
        }

        return -1; // Should never reach here if input is valid
    }

    // Example usage
    public static void main(String[] args) {
        int[] arr = { 1, 3, 20, 4, 1, 0 };
        int peakIndex = findPeakElement(arr);
        System.out.println("Peak element is " + arr[peakIndex]);
    }
}
