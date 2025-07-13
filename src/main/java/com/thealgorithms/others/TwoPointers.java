package com.thealgorithms.others;

/**
 * The two-pointer technique is a useful tool to utilize when searching for
 * pairs in a sorted array.
 *
 * <p>
 * Link: https://www.geeksforgeeks.org/two-pointers-technique/
 */
public final class TwoPointers {

    private TwoPointers() {
    }

    /**
     * Checks whether there exists a pair of elements in a sorted array whose sum equals the specified key.
     *
     * @param arr a sorted array of integers in ascending order (must not be null)
     * @param key the target sum to find
     * @return {@code true} if there exists at least one pair whose sum equals {@code key}, {@code false} otherwise
     * @throws IllegalArgumentException if {@code arr} is {@code null}
     */
    public static boolean isPairedSum(int[] arr, int key) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array must not be null.");
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == key) {
                return true;
            }
            if (sum < key) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
