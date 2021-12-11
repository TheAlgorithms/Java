package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Problem statement:
Given an array nums of distinct integers, return all the possible permutations.
You can return the answer in any order.

Example:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


 */
public class Permutation {
    public static void main(String[] arg){
        int[] input = {1,2,3};
        List<List<Integer>> answer  = permute(input);
        for(List<Integer> list : answer){
            for(Integer i:list){
                System.out.print(i+" ");
            }
            System.out.print("\n");
        }

    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(result, nums, 0, nums.length - 1);
        return result;
    }

    private static void permute(List<List<Integer>> result, int[] nums, int start, int end) {
        if(start == end) {
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }
        else {
            for(int i = start; i <= end; i++) {
                swap(nums, start, i);
                permute(result, nums, start + 1, end);
                swap(nums, start, i);
            }
        }
    }

    private static void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
