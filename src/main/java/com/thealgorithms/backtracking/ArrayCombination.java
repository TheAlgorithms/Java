package com.thealgorithms.backtracking;

import java.util.*;

/**
 * Finds all permutations of 1...n of length k
 * @author TheClerici (https://github.com/TheClerici)
 */
public class ArrayCombination {
    private static int length;

    /**
     * Find all combinations of 1..n by creating an array and using backtracking in Combination.java
     * @param n max value of the array.
     * @param k length of combination
     * @return a list of all combinations of length k. If k == 0, return null.
     */
    public static List<TreeSet<Integer>> combination(int n, int k) {
        if (n <= 0) {
            return null;
        }
        length = k;
        Integer[] arr = new Integer[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        return Combination.combination(arr, length);
    }
}