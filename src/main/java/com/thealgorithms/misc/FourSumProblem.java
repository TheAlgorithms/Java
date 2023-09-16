package com.thealgorithms.misc;

import java.util.*;


public final class FourSumProblem {

    private FourSumProblem() {
    }
    // Approach 1: Hashing O(n^2)
    public static List<List<Integer>> Hashing4Sum(int[] Nums, int target) {
        List<List<Integer>> List = new ArrayList<>();
        Arrays.sort(Nums);
        int n = Nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                Set<Integer> seen = new HashSet<>();
                int target2 = target - Nums[i] - Nums[j];

                for (int k = j + 1; k < n; k++) {
                    int complement = target2 - Nums[k];
                    if (seen.contains(complement)) {
                        List.add(Arrays.asList(Nums[i], Nums[j], Nums[k], complement));
                    }
                    seen.add(Nums[k]);
                }
                // Skip duplicates
                while (j < n - 2 && Nums[j] == Nums[j + 1]) j++;
            }

            // Skip duplicates
            while (i < n - 3 && Nums[i] == Nums[i + 1]) i++;
        }

        return List;
    }
}

