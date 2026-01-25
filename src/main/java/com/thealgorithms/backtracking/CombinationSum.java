package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Backtracking: pick/not-pick with reuse of candidates. */
public final class CombinationSum {
    private CombinationSum() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }

        // Sort to help with pruning duplicates and early termination
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }

    private static void backtrack(int[] candidates, int remaining, int start, List<Integer> combination, List<List<Integer>> results) {
        if (remaining == 0) {
            // Found valid combination; add a copy
            results.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];

            // If candidate is greater than remaining target, further candidates (sorted) will also be too big
            if (candidate > remaining) {
                break;
            }

            // include candidate
            combination.add(candidate);
            // Because we can reuse the same element, we pass i (not i + 1)
            backtrack(candidates, remaining - candidate, i, combination, results);
            // backtrack: remove last
            combination.remove(combination.size() - 1);
        }
    }
}
