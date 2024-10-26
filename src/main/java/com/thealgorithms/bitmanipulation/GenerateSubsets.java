package com.thealgorithms.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a method to generate all subsets (power set)
 * of a given set using bit manipulation.
 *
 * @author Hardvan
 */
public final class GenerateSubsets {
    private GenerateSubsets() {
    }

    /**
     * Generates all subsets of a given set using bit manipulation.
     * Steps:
     * 1. Iterate over all numbers from 0 to 2^n - 1.
     * 2. For each number, iterate over all bits from 0 to n - 1.
     * 3. If the i-th bit of the number is set, add the i-th element of the set to the current subset.
     * 4. Add the current subset to the list of subsets.
     * 5. Return the list of subsets.
     *
     * @param set the input set of integers
     * @return a list of all subsets represented as lists of integers
     */
    public static List<List<Integer>> generateSubsets(int[] set) {
        int n = set.length;
        List<List<Integer>> subsets = new ArrayList<>();

        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(set[i]);
                }
            }
            subsets.add(subset);
        }

        return subsets;
    }
}
