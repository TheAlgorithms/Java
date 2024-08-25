package com.thealgorithms.others;

/**
 * The two-pointer technique is a useful tool to utilize when searching for
 * pairs in a sorted array.
 *
 * <p>
 * Link: https://www.geeksforgeeks.org/two-pointers-technique/
 */
final class TwoPointers {
    private TwoPointers() {
    }

    /**
     * Given a sorted array arr (sorted in ascending order), find if there exists
     * any pair of elements such that their sum is equal to the key.
     *
     * @param arr the array containing elements (must be sorted in ascending order)
     * @param key the number to search
     * @return {@code true} if there exists a pair of elements, {@code false} otherwise.
     */
    public static boolean isPairedSum(int[] arr, int key) {
        int i = 0; // index of the first element
        int j = arr.length - 1; // index of the last element

        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == key) {
                return true;
            } else if (sum < key) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
