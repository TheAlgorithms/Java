package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Finds all combinations of a given array of unique elements.
 */
public final class Combination {
    private Combination() {
    }

    /**
     * Finds all combinations of a given array of length n.
     * If n is 0, an empty list is returned.
     *
     * @param data            the array of elements.
     * @param combinationSize the desired size of combinations.
     * @param <T>             the type of elements in the array (assumed to be unique).
     * @return a list of all combinations of size `combinationSize`.
     * @throws IllegalArgumentException if combinationSize is negative.
     */
    public static <T> List<TreeSet<T>> combination(T[] data, int combinationSize) {
        if (combinationSize < 0) {
            throw new IllegalArgumentException("Combination size cannot be negative.");
        } else if (combinationSize == 0) {
            return null;
        }

        List<TreeSet<T>> combinations = new ArrayList<>();
        findCombinationsRecursive(data, 0, new ArrayList<>(), combinations, combinationSize);
        return combinations;
    }

    /**
     * Recursive helper function to find combinations using backtracking.
     *
     * @param data the array of elements.
     * @param startIndex the starting index for the current combination.
     * @param currentCombination the current combination being built.
     * @param combinations the list to store all found combinations.
     * @param combinationSize the desired size of combinations.
     * @param <T> the type of elements in the array.
     */
    private static <T> void findCombinationsRecursive(T[] data, int startIndex, List<T> currentCombination, List<TreeSet<T>> combinations, int combinationSize) {
        if (currentCombination.size() == combinationSize) {
            combinations.add(new TreeSet<>(currentCombination)); // Deep copy to avoid modification
            return;
        }

        for (int i = startIndex; i < data.length; i++) {
            currentCombination.add(data[i]);
            findCombinationsRecursive(data, i + 1, currentCombination, combinations, combinationSize);
            currentCombination.removeLast();
        }
    }
}
