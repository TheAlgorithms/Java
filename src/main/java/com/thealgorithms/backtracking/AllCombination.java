package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
public class AllCombination {
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        int[] nums = new int[n];
        
        // Initialize the nums array with values from 1 to n
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        
        backtrack(0, new ArrayList<Integer>(), nums, k, results);
        
        return results;
    }
    
    private static void backtrack(int start, List<Integer> curr, int[] nums, int k, List<List<Integer>> results) {
        if (curr.size() == k) {
            results.add(new ArrayList<Integer>(curr));
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(i + 1, curr, nums, k, results);
            curr.remove(curr.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        List<List<Integer>> results = combinations(n, k);
        
        System.out.println("All possible combinations of " + k + " numbers out of 1 to " + n + ": ");
        for (List<Integer> result : results) {
            System.out.println(result.toString());
        }
    }
}
