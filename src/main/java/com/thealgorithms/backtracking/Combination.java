package com.thealgorithms.backtracking;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Finds all combinations of a given array using backtracking
 * 
 * @author Alan Piao
   @edit Sarthak Shelar
 */
public final class Combination {
    private Combination() {
    }

    /**
     * Find all combinations of a given array using backtracking
     * 
     * @param arr the array.
     * @param n   length of combination
     * @param <T> the type of elements in the array.
     * @return a list of all combinations of length n. If n == 0, return an empty list.
     */
    public static <T> List<TreeSet<T>> combination(T[] arr, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The combination length cannot be negative.");
        }

        if (n == 0) {
            return Collections.emptyList();
        }

        T[] array = arr.clone();
        Arrays.sort(array); // Sort to maintain consistent order

        List<TreeSet<T>> result = new LinkedList<>();
        backtrack(array, n, 0, new LinkedList<>(), result);
        return result;
    }

    /**
     * Backtrack all possible combinations of a given array
     * 
     * @param arr     the array.
     * @param n       length of the combination
     * @param index   the starting index.
     * @param current current combination under construction
     * @param result  the list that contains all valid combinations
     * @param <T>     the type of elements in the array.
     */
    private static <T> void backtrack(T[] arr, int n, int index, LinkedList<T> current, List<TreeSet<T>> result) {
        if (current.size() == n) {
            result.add(new TreeSet<>(current)); // Convert to TreeSet to ensure uniqueness and sorted order
            return;
        }

        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);
            backtrack(arr, n, i + 1, current, result);
            current.removeLast();
        }
    }
}
