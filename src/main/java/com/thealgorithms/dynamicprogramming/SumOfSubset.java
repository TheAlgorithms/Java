package com.thealgorithms.dynamicprogramming;

public class SumOfSubset {

    public static boolean subsetSum(int[] arr, int num, int Key) {
        if (Key == 0) {
            return true;
        }
        if (num < 0 || Key < 0) {
            return false;
        }

        boolean include = subsetSum(arr, num - 1, Key - arr[num]);
        boolean exclude = subsetSum(arr, num - 1, Key);

        return include || exclude;
    }
}
