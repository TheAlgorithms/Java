package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumProblem {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return result; // if array is too small to have 4 numbers, return empty result
        }

        // Sort the array first
        Arrays.sort(nums);

        // Iterate through the array, fixing the first two elements
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for the first element

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates for the second element

                // Use two pointers for the remaining two elements
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // If we found a quadruplet, add it to the result list
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for the third element
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // Skip duplicates for the fourth element
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        // Move the pointers
                        left++;
                        right--;
                    } else if (sum < target) {
                        // If the sum is less than the target, move the left pointer to increase the sum
                        left++;
                    } else {
                        // If the sum is greater than the target, move the right pointer to decrease the sum
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
