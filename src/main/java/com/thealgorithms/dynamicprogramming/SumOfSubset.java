package com.thealgorithms.dynamicprogramming;

public final class SumOfSubset {

    private SumOfSubset() {
    }

    public static boolean subsetSum(int[] arr, int n, int key) {
        if (key == 0) {
            return true;
        }
        if (n < 0 || key < 0) {
            return false;
        }
        boolean[] prev = new boolean[key + 1];
        prev[0] = true;

        if (arr[0] <= key) {
            prev[arr[0]] = true;
        }

        for (int ind = 1; ind <= n; ind++) {
            boolean[] cur = new boolean[key + 1];
            cur[0] = true;

            for (int target = 1; target <= key; target++) {
                final boolean notTaken = prev[target];
                final boolean taken = (arr[ind] <= target) ? prev[target - arr[ind]] : false;
                cur[target] = notTaken || taken;
            }

            prev = cur;
        }

        return prev[key];
    }
}
