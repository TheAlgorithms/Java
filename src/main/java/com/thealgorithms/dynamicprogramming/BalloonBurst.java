package com.thealgorithms.dynamicprogramming;

public class BalloonBurst {
    /*
     * This code implements the dynamic programming solution to the Balloon Burst problem.
     * Given an array of balloon values (represented by 'nums'), the goal is to find the maximum number of coins
     * that can be obtained by bursting the balloons in a specific order.
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n+2];
        arr[0] = arr[n+1] = 1;   // Giving padding of 1 to the corner elements
        for(int i=1;i<=n;i++){
            arr[i] = nums[i-1];   //final padded array
        }
        
        int dp[][] = new int[n+2][n+2];
        
        for(int window = 1;window<=n;window++){     // window size
            for(int left = 1;left<=n-window+1;left++){    // left pointer
                int right = left+window-1;               // right pointer
                for(int i=left;i<=right;i++){           // iterate from left to right
				
                    dp[left][right] = Math.max(dp[left][right], (arr[left-1]*arr[i]*arr[right+1]) + dp[left][i-1] + dp[i+1][right]);
                                    
                }
            }
        }
        return dp[1][n];
    }
}
