package com.thealgorithms.dynamicprogramming;

// Given an integer array nums, find a 
// subarray
//  that has the largest product, and return the product.

// author = divij kathuria (github = dikapitacion)

public class maxProductSubarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int ans = -11;
        int max = 1;
        int min = 1;
        for(int i=0;i<n;i++){
            if(nums[i]<0){
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(max*nums[i],nums[i]);
            min = Math.min(min*nums[i],nums[i]);
            ans = Math.max(ans,max);
        }
        return ans;
    }
}