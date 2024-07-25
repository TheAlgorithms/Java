package com.thealgorithms.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


/**
 * Finds all combinations of a given array that are a specific length by using backtracking.
 * @author Alan Piao (<a href="https://github.com/cpiao3">git-Alan Piao</a>)
 */
public final class Combination{
    // Default private constructor to prevent instantiation

    private Combination() {
    }

    private static int length; //Define length outside all methods so that it can be accessed anywhere in the class.


    /**
     * Find all combinations of given array using backtracking
     * @param arr the array we want to find combinations of.
     * @param desiredLength the length of our desired combinations.
     * @param <T> the type of elements in the array.
     * @return a list of all combinations of length desiredLength. If desiredLength is 0, return null.
     */
    public static <T> List<TreeSet<T>> combination(T[] arr, int desiredLength) {
        if (desiredLength == 0) {
            return null;
        }
        length = desiredLength;
        T[] array = arr.clone();
        Arrays.sort(array);
        List<TreeSet<T>> result = new LinkedList<>();
        backtracking(array, 0, new TreeSet<T>(), result);
        return result;
    }


    /**
     * Backtrack all possible combinations of a given array
     * @param arr the array from earlier.
     * @param index the starting index.
     * @param currSet set that tracks current combination
     * @param result the list contains all combination.
     * @param <T> the type of elements in the array.
     */
    private static <T> void backtracking(T[] arr, int index, TreeSet<T> currSet, List<TreeSet<T>> result) {
        if (index + length - currSet.size() > arr.length) {
            return;
        }
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
