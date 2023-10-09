package com.thealgorithms.dynamicprogramming;

public class Sum_Of_Subset {

    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 5, 8};
        int Key = 14;

        if (subsetSum(arr, arr.length - 1, Key)) {
            System.out.print("Yes, that sum exists");
        } else {
            System.out.print("Nope, that number does not exist");
        }
    }

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
