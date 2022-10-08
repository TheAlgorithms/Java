package com.thealgorithms.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Finds all permutations of given array
 * @author Alan Piao (https://github.com/cpiao3)
 */
public class Permutation {

    /**
     * Find all permutations of given array using backtracking
     * @param arr the array.
     * @param <T> the type of elements in the array.
     * @return a list of all permutations.
     */
    public static <T> List<T[]> permutation(T[] arr) {
        T[] array = arr.clone();
        List<T[]> result = new LinkedList<>();
        backtracking(array, 0, result);
        return result;
    }

    /**
     * Backtrack all possible orders of a given array
     * @param arr the array.
     * @param index the starting index.
     * @param result the list contains all permutations.
     * @param <T> the type of elements in the array.
     */
    private static <T> void backtracking(T[] arr, int index, List<T[]> result) {
        if (index == arr.length) {
            result.add(arr.clone());
        }
        for (int i = index; i < arr.length; i++) {
            swap(index, i, arr);
            backtracking(arr, index + 1, result);
            swap(index, i, arr);
        }
    }

    /**
     * Swap two element for a given array
     * @param a first index
     * @param b second index
     * @param arr the array.
     * @param <T> the type of elements in the array.
     */
    private static <T> void swap(int a, int b, T[] arr) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
