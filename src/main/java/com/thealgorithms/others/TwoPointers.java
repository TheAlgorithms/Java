package com.thealgorithms.others;

import java.util.Arrays;

/**
 * The two pointer technique is a useful tool to utilize when searching for
 * pairs in a sorted array.
 *
 * <p>
 * link: https://www.geeksforgeeks.org/two-pointers-technique/
 */
class TwoPointers {

    /**
     * Given a sorted array arr (sorted in ascending order). Find if there
     * exists any pair of elements such that their sum is equal to key.
     *
     * @param arr the array contains elements
     * @param key the number to search
     * @return {@code true} if there exists a pair of elements, {@code false}
     * otherwise.
     */
    public static boolean isPairedSum(int[] arr, int key) {
        /* array sorting is necessary for this algorithm to function correctly */
        Arrays.sort(arr);
        int i = 0;
        /* index of first element */
        int j = arr.length - 1;
        /* index of last element */

        while (i < j) {
            if (arr[i] + arr[j] == key) {
                return true;
            } else if (arr[i] + arr[j] < key) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
