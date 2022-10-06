package com.thealgorithms.backtracking;

import java.util.*;

/**
 * Finds all permutations of given array
 * @author Alan Piao (https://github.com/cpiao3)
 */
public class Combination {

    private static int length;

    /**
     * Find all combinations of given array using backtracking
     * @param arr the array.
     * @param n length of combination
     * @param <T> the type of elements in the array.
     * @return a list of all combinations of length n. If n == 0, return null.
     */
    public static <T> List<TreeSet<T>> combination(T[] arr, int n) {
        if (n == 0) {
            return null;
        }
        length = n;
        T[] array = arr.clone();
        Arrays.sort(array);
        List<TreeSet<T>> result = new LinkedList<>();
        backtracking(array, 0, new TreeSet<T>(), result);
        return result;
    }

    /**
     * Backtrack all possible combinations of a given array
     * @param arr the array.
     * @param index the starting index.
     * @param currSet set that tracks current combination
     * @param result the list contains all combination.
     * @param <T> the type of elements in the array.
     */
    private static <T> void backtracking(
        T[] arr,
        int index,
        TreeSet<T> currSet,
        List<TreeSet<T>> result
    ) {
        if (index + length - currSet.size() > arr.length) return;
        if (length - 1 == currSet.size()) {
            for (int i = index; i < arr.length; i++) {
                currSet.add(arr[i]);
                result.add((TreeSet<T>) currSet.clone());
                currSet.remove(arr[i]);
            }
        }
        for (int i = index; i < arr.length; i++) {
            currSet.add(arr[i]);
            backtracking(arr, i + 1, currSet, result);
            currSet.remove(arr[i]);
        }
    }
}
