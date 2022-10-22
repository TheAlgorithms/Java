package com.thealgorithms.dynamicprogramming;

/**
 * Find the number of subsets present in the given array with a sum equal to target.
 * Based on Solution discussed on StackOverflow(https://stackoverflow.com/questions/22891076/count-number-of-subsets-with-sum-equal-to-k)
 * @author Samrat Podder(https://github.com/samratpodder)
 */
public class SubsetCount {


    /**
     * Dynamic Programming Implementation.
     * Method to find out the number of subsets present in the given array with a sum equal to target.
     * Time Complexity is O(n*target) and Space Complexity is O(n*target)
     * @param arr is the input array on which subsets are  to searched
     * @param target is the sum of each element of the subset taken together
     *
     */
    public int getCount(int[] arr, int target){
        /**
         * Base Cases - If target becomes zero, we have reached the required sum for the subset
         * If we reach the end of the array arr then, either if target==arr[end], then we add one to the final count
         * Otherwise we add 0 to the final count
         */
        int n = arr.length;
        int[][] dp = new int[n][target+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        if(arr[0]<=target) dp[0][arr[0]] = 1;
        for(int t=1;t<=target;t++){
            for (int idx = 1; idx < n; idx++) {
                int notpick = dp[idx-1][t];
                int pick =0;
                if(arr[idx]<=t) pick+=dp[idx-1][target-t];
                dp[idx][target] = pick+notpick;
            }
        }
        return dp[n-1][target];
    }


    /**
     * This Method is a Space Optimized version of the getCount(int[], int) method and solves the same problem
     * This approach is a bit better in terms of Space Used
     * Time Complexity is O(n*target) and Space Complexity is O(target)
     * @param arr is the input array on which subsets are  to searched
     * @param target is the sum of each element of the subset taken together
     */
    public int getCountSO(int[] arr, int target){
        int n = arr.length;
        int prev[]=new int[target+1];
        prev[0] =1;
        if(arr[0]<=target) prev[arr[0]] = 1;
        for(int ind = 1; ind<n; ind++){
            int cur[]=new int[target+1];
            cur[0]=1;
            for(int t= 1; t<=target; t++){
                int notTaken = prev[t];
                int taken = 0;
                if(arr[ind]<=t) taken = prev[t-arr[ind]];
                cur[t]= notTaken + taken;
            }
            prev = cur;
        }
        return prev[target];
    }

}
