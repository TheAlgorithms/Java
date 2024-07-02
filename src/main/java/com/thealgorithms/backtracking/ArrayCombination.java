package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds all permutations of 1...n of length k
 * @author TheClerici (<a href="https://github.com/TheClerici">git-TheClerici</a>)
 */
public final class ArrayCombination {
    private ArrayCombination() {
    }

    /**
     * Find all combinations of 1..n using backtracking.
     *
     * @param n Max value of the elements.
     * @param k Length of the combination.
     * @return A list of all combinations of length k.
     *         Returns an empty list if k is 0 or n is less than k.
     */
    public static List<List<Integer>> combination(int n, int k) {
        if (k <= 0 || n < k) {
            return new ArrayList<>(); // Return empty list for invalid input
        }

        List<List<Integer>> combinations = new ArrayList<>();
        combine(combinations, new ArrayList<>(), 1, n, k);
        return combinations;
    }

    private static void combine(List<List<Integer>> combinations, List<Integer> current, int start, int n, int k) {
        if (current.size() == k) { // Base case: combination found
            combinations.add(new ArrayList<>(current)); // Copy to avoid modification
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            combine(combinations, current, i + 1, n, k); // Recursive call
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
