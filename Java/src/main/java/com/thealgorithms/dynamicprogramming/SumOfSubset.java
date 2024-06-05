package com.thealgorithms.dynamicprogramming;

public final class SumOfSubset {
    private SumOfSubset() {
    }

    public static boolean subsetSum(int[] arr, int num, int key) {
        if (key == 0) {
            return true;
        }
        if (num < 0 || key < 0) {
            return false;
        }

        boolean include = subsetSum(arr, num - 1, key - arr[num]);
        boolean exclude = subsetSum(arr, num - 1, key);

        return include || exclude;
    }
}
