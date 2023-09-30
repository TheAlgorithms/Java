package com.thealgorithms.searches;

public class RecursiveBinarySearch {
    /**
     * Binary search is a popular and useful algorithm used when searching in for a value in a sorted arrays.
     * The algorithm finds the index of a target value within the array
     *
     * <p>
     * Best Case: O(1)
     * Average Case: O(log N)
     * Worst Case: O(log N)
     * Space Complexity Iterative Approach: O(1)
     * Space Complexity Recursive Approach: O(logN)
     *
     * @author Kevin Estrella (https://github.com/Kstrella)
     */

    public static int recBinarySearch(int[] arr, int start, int end, int target)
    {
        if (end >= start) {
            // Find the middle of the array
            int mid = start + (end - start) / 2;

            // If the target is the middle of the array return it
            if (arr[mid] == target)
                return mid;

            // If target is smaller than mid, then we search the left sub-array
            if (arr[mid] > target)
                return recBinarySearch(arr, start, mid - 1, target);

            // If target is greater than mid, then we search the right sub-array
            return recBinarySearch(arr, mid + 1, end, target);
        }

        //If the target does not exist in the array
        return -1;
    }

    public static void main(String[] args)
    {
        int[] arr = {1, 2, 4, 7, 10, 22, 31, 40,55,97,100};
        int target = 13;
        int result = recBinarySearch(arr, 0, arr.length - 1, target);
        if (result == -1)
            System.out.println("Target element is not in the array");
        else
            System.out.println("Target element is at index " + result);
    }
}
