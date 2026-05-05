package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class provides methods to generate all permutations
 * of a given array of any type using recursion.
 * <p>
 * Reference:
 * https://en.wikipedia.org/wiki/Permutation
 */
public final class Permutations {

    private Permutations() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Generates all permutations of a generic array.
     *
     * @param <T>   the type of elements in the array
     * @param items the input array
     * @return list of all permutations
     * @throws NullPointerException     if items is null
     * @throws IllegalArgumentException if any element in items is null
     */
    public static <T> List<List<T>> permutations(T[] items) {
        if (items == null) {
            throw new NullPointerException("Input array cannot be null");
        }
        for (T item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Array elements cannot be null");
            }
        }

        List<List<T>> result = new ArrayList<>();
        List<T> list = new ArrayList<>(Arrays.asList(items));
        generatePermutations(0, list, result);
        return result;
    }

    /**
     * Recursive backtracking to generate permutations.
     *
     * @param <T>    the type of elements
     * @param index  the current position being fixed
     * @param list   the working list of elements
     * @param result the accumulated list of permutations
     */
    private static <T> void generatePermutations(int index, List<T> list, List<List<T>> result) {
        if (index == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        Set<T> seen = new HashSet<>();
        for (int i = index; i < list.size(); i++) {
            if (seen.add(list.get(i))) { // skip duplicate values at this position
                swap(list, index, i);
                generatePermutations(index + 1, list, result);
                swap(list, index, i); // backtrack
            }
        }
    }

    /**
     * Swaps two elements in a list.
     *
     * @param <T>  the type of elements
     * @param list the list
     * @param i    first index
     * @param j    second index
     */
    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
