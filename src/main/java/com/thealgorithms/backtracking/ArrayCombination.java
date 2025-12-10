package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to find all combinations of integers from 0 to n-1
 * of a specified length k using backtracking.
 */
public final class ArrayCombination {
    private ArrayCombination() {
    }

    /**
     * Generates all possible combinations of length k from the integers 0 to n-1.
     *
     * @param n The total number of elements (0 to n-1).
     * @param k The desired length of each combination.
     * @return A list containing all combinations of length k.
     * @throws IllegalArgumentException if n or k are negative, or if k is greater than n.
     */
    public static List<List<Integer>> combination(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException("Invalid input: n must be non-negative, k must be non-negative and less than or equal to n.");
        }

        List<List<Integer>> combinations = new ArrayList<>();
        combine(combinations, new ArrayList<>(), 0, n, k);
        return combinations;
    }

    /**
     * A helper method that uses backtracking to find combinations.
     *
     * @param combinations The list to store all valid combinations found.
     * @param current The current combination being built.
     * @param start The starting index for the current recursion.
     * @param n The total number of elements (0 to n-1).
     * @param k The desired length of each combination.
     */
    private static void combine(List<List<Integer>> combinations, List<Integer> current, int start, int n, int k) {
        // Base case: combination found
        if (current.size() == k) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < n; i++) {
            current.add(i);
            combine(combinations, current, i + 1, n, k);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
