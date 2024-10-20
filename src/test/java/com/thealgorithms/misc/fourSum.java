package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {  // Class name changed
    // Method name changed to findFourSum
    public static List<List<Integer>> findFourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;  // Added curly braces for 'if'
        }

        Arrays.sort(nums); // Sort the array first

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;  // Added curly braces for 'if'
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;  // Added curly braces for 'if'
                }
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;  // Added curly braces for 'while'
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;  // Added curly braces for 'while'
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;  // Added curly braces for 'if'
                    } else {
                        right--;  // Added curly braces for 'else'
                    }
                }
            }
        }
        return result;
    }

    // Private constructor to prevent instantiation (utility class)
    private FourSum() {}

    public static void main(String[] args) {
        int[] arr1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println(findFourSum(arr1, target1));  // Updated method call

        int[] arr2 = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target2 = 9;
        System.out.println(findFourSum(arr2, target2));  // Updated method call
    }
}
