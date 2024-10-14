package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//In this problem, we are given an array of n integers and a target integer.
//We have to return a list containing all possible quadruplets from the given array which could add to get the target.

public class FourSumProblem {

    //Best approach - Sorting and two-pointers
    //Time Complexity - O(n^3)
    //Space Complexity - O(n)
    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) return ans;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-3; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1; j < nums.length - 2; j++) {
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                int left = j+1;
                int right = nums.length -1;
                while(left < right)  {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right];
                    if(sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while(left < right && nums[right] == nums[right-1]) right--;
                        left++; right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return ans;
    }


    //Another approach - Using HashMap
    //Time Complexity - O(n^3)
    //Space Complexity - O(n^2) (Storing the pair of sums)
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) continue;
                HashMap<Integer, Integer> map = new HashMap<>();
                for (int k = j + 1; k < nums.length; k++) {
                    int complement = target - nums[i] - nums[j] - nums[k];
                    if (map.containsKey(complement)) {
                        ans.add(Arrays.asList(nums[i], nums[j], complement, nums[k]));
                        while (k + 1 < nums.length && nums[k] == nums[k + 1]) k++;
                    }
                    map.put(nums[k], k);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println(fourSum1(arr1, target1));
        System.out.println(fourSum2(arr1, target1));

        int[] arr2 = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target2 = 9;
        System.out.println(fourSum2(arr2, target2));
        System.out.println(fourSum2(arr2, target2));
    }
}