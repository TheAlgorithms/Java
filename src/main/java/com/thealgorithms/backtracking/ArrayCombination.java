package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds all combinations of 0...n-1 of length k
 */
public final class ArrayCombination {
    private ArrayCombination() {
    }

    /**
     * Finds all combinations of length k of 0..n-1 using backtracking.
     *
     * @param n Number of the elements.
     * @param k Length of the combination.
     * @return A list of all combinations of length k.
     */
    public static List<List<Integer>> combination(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException("Wrong input.");
        }

        List<List<Integer>> combinations = new ArrayList<>();
        combine(combinations, new ArrayList<>(), 0, n, k);
        return combinations;
    }

    private static void combine(List<List<Integer>> combinations, List<Integer> current, int start, int n, int k) {
        if (current.size() == k) { // Base case: combination found
            combinations.add(new ArrayList<>(current)); // Copy to avoid modification
            return;
        }

        for (int i = start; i < n; i++) {
            current.add(i);
            combine(combinations, current, i + 1, n, k);
            current.removeLast(); // Backtrack
        }
    }
}
