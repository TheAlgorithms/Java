package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Combination Sum algorithm using recursion and backtracking.
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * The same number may be chosen from candidates an unlimited number of times.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Subset_sum_problem">Subset Sum Problem (Wikipedia)</a>
 * @see <a href="https://leetcode.com/problems/combination-sum/">Combination Sum (LeetCode)</a>
 * @author Tejas Rahane
 */
public final class CombinationSum {
    private CombinationSum() {
    }

    /**
     * Finds all unique combinations that sum to target.
     *
     * @param candidates Array of distinct integers
     * @param target Target sum
     * @return List of all unique combinations that sum to target
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking helper method to find all combinations.
     *
     * @param candidates Array of distinct integers
     * @param target Remaining target sum
     * @param start Starting index for candidates
     * @param current Current combination being built
     * @param result List to store all valid combinations
     */
    private static void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
