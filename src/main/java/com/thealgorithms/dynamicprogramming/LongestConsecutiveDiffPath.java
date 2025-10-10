package com.thealgorithms.dynamicprogramming;

import java.util.HashMap;

/**
 * Problem: Longest Consecutive Difference Path
 *
 * Given an integer array, find the length of the longest path such that
 * the absolute difference between consecutive elements is exactly 1.
 *
 * Example:
 * Input:  arr = [3, 4, 2, 1, 2, 3, 4, 5]
 * Output: 5
 * Explanation: The longest path is [1, 2, 3, 4, 5]
 *
 * Category: Dynamic Programming with Hashing
 */
public class LongestConsecutiveDiffPath {

    /**
     * Function to find the longest consecutive difference path length
     * @param arr input integer array
     * @return length of longest path
     */
    public static int longestPath(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;

        for (int num : arr) {
            int len1 = map.getOrDefault(num - 1, 0);
            int len2 = map.getOrDefault(num + 1, 0);
            int currentLen = Math.max(len1, len2) + 1;
            map.put(num, currentLen);
            maxLength = Math.max(maxLength, currentLen);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1, 2, 3, 4, 5};
        System.out.println("Longest consecutive difference path length: " + longestPath(arr));

        int[] arr2 = {1, 2, 3, 4, 2, 3, 4, 5, 6};
        System.out.println("Longest consecutive difference path length: " + longestPath(arr2));

        int[] arr3 = {10, 9, 8, 7, 6};
        System.out.println("Longest consecutive difference path length: " + longestPath(arr3));
    }
}
