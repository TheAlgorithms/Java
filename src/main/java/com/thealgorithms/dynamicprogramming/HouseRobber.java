package DP;

// https://leetcode.com/problems/house-robber/
public class HouseRobber {
    public int rob(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        
        if(nums.length == 1) {
            return nums[0];
        }
        
        if(nums.length == 2){
            Math.max(nums[0],nums[1]);
        }
        
        int n = nums.length;
        int dp[] = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i=2; i<n; i++){
            dp[i] = Math.max(dp[i-1], (dp[i-2]+nums[i]));
        }
        
        return dp[n-1];
    }
}
