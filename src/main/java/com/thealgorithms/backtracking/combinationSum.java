package com.thealgorithms.backtracking;

import java.util.*;

/**
 * Java program for solving the Combination Sum problem.
 * Author: Yash Kesharwani
 * File: CombinationSum.java
 * Comments: This program finds all combinations of candidates that sum to the target.
 */
public class combinationSum {
    /**
     * Recursively find combinations that sum to the target.
     *
     * @param res       The list to store valid combinations.
     * @param candidates The array of candidate numbers.
     * @param target    The target sum to achieve.
     * @param sum1      The current sum during the recursion.
     * @param start     The starting index for candidates.
     * @param list1     The current combination being constructed.
     */
    public void check(List<List<Integer> > res, int[] candidates, int target, int sum1, int start, List<Integer> list1) {
        if (sum1 == target) {
            res.add(new ArrayList<>(list1));
            return;
        } else if (sum1 > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            sum1 += candidates[i];
            list1.add(candidates[i]);
            check(res, candidates, target, sum1, i, list1);
            sum1 -= candidates[i];
            list1.remove(list1.size() - 1);
        }
    }

    /**
     * Find all combinations of candidates that sum to the target.
     *
     * @param candidates The array of candidate numbers.
     * @param target    The target sum to achieve.
     * @return A list of combinations that sum to the target.
     */
    public List<List<Integer> > combinationsum(int[] candidates, int target) {
        List<List<Integer> > res = new ArrayList<>();
        int start = 0, sum1 = 0;
        List<Integer> list1 = new ArrayList<>();
        check(res, candidates, target, 0, 0, list1);
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the list of candidates (space-separated): ");
        String input = scanner.nextLine();
        String[] candidateStrings = input.split(" ");
        int[] candidates = new int[candidateStrings.length];
        for (int i = 0; i < candidateStrings.length; i++) {
            candidates[i] = Integer.parseInt(candidateStrings[i]);
        }

        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();

        combinationSum solution = new combinationSum();
        List<List<Integer> > combinations = solution.combinationsum(candidates, target);

        System.out.println("Combinations that sum to the target:");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }
}
