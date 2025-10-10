package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Backtracking: pick/not-pick with reuse of candidates. */
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // helps pruning
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
        // Note: result order is not guaranteed; compare sorted in tests if needed
    }

    private static void backtrack(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int val = nums[i];
            if (val > remain) break; // prune
            path.add(val);
            backtrack(nums, remain - val, i, path, res); // i (reuse allowed)
            path.remove(path.size() - 1);
        }
    }
}
