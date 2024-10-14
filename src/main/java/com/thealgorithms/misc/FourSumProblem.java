package com.thealgorithms.misc;
import java.util.*;

public class FourSumProblem {

    /**
     * The function "fourSumProblem" takes an array of integers and a target integer as input, and returns a list
     * of unique quadruplets (four elements) from the array that sum up to the target value.
     * It avoids duplicate quadruplets by sorting the array and skipping repeated numbers.
     * The implementation uses a two-pointer approach to efficiently find the quadruplets.
     *
     * @param arr An array of integers.
     * @param target The target sum that the four integers in each quadruplet should add up to.
     * @return A list of lists, where each inner list contains four integers that sum up to the target value.
     * @author saivardhan (https://github.com/saivardhan15)
     */

    public static List<List<Integer>> fourSumProblem(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr.length < 4) {
            return ans;
        }
        // Sort the array to make it easier to detect duplicates
        Arrays.sort(arr);

        // Loop over the first two elements
        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) { // Skip duplicate numbers for 'i'
                continue;
            }
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) { // Skip duplicate numbers for 'j'
                    continue;
                }

                // Two-pointer technique for the remaining two elements
                int left = j + 1;
                int right = arr.length - 1;
                while (left < right) {
                    int sum = arr[i] + arr[j] + arr[left] + arr[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(arr[i], arr[j], arr[left], arr[right]));
                        while (left < right && arr[left] == arr[left + 1]) left++; // Skip duplicates for 'left'
                        while (left < right && arr[right] == arr[right - 1]) right--; // Skip duplicates for 'right'
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}
