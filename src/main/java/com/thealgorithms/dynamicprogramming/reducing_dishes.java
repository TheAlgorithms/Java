// A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

// Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].

// Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.

// Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

import java.util.Arrays;

public class Solution {

    //RECURSIVE
    public int solve(int index, int time, int[] satisfaction) {
        if (index == satisfaction.length) {
            return 0;
        }

        int take = satisfaction[index] * (time + 1) + solve(index + 1, time + 1, satisfaction);
        int notTake = 0 + solve(index + 1, time, satisfaction);
        return Math.max(take, notTake);
    }

    //MEMOIZATION
    public int solveMem(int index, int time, int[] satisfaction, int[][] dp) {
        if (index == satisfaction.length) {
            return 0;
        }

        if (dp[index][time] != -1) {
            return dp[index][time];
        }

        int take = satisfaction[index] * (time + 1) + solveMem(index + 1, time + 1, satisfaction, dp);
        int notTake = 0 + solveMem(index + 1, time, satisfaction, dp);
        dp[index][time] = Math.max(take, notTake);

        return dp[index][time];
    }

    //TABULATION
    public int solveTab(int[] satisfaction) {
        int n = satisfaction.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int time = index; time >= 0; time--) {
                int take = satisfaction[index] * (time + 1) + dp[index + 1][time + 1];
                int notTake = 0 + dp[index + 1][time];
                dp[index][time] = Math.max(take, notTake);
            }
        }
        return dp[0][0];
    }

    //SPACE OPTIMISATION
    public int spaceOpt(int[] satisfaction) {
        int n = satisfaction.length;
        int[] curr = new int[n + 1];
        int[] next = new int[n + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int time = index; time >= 0; time--) {
                int take = satisfaction[index] * (time + 1) + next[time + 1];
                int notTake = 0 + next[time];
                curr[time] = Math.max(take, notTake);
            }
            next = Arrays.copyOf(curr, n + 1);
        }
        return next[0];
    }

    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);

        // You can choose any of the following methods:
        // return solve(0, 0, satisfaction);
        // int[][] dp = new int[n + 1][n + 1];
        // return solveMem(0, 0, satisfaction, dp);
        return spaceOpt(satisfaction);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] satisfaction = {4, 3, 2, 1};
        System.out.println(solution.maxSatisfaction(satisfaction));
    }
}

// Example 1:

// Input: satisfaction = [-1,-8,0,5,-9]
// Output: 14
// Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
// Each dish is prepared in one unit of time.

// Example 2:

// Input: satisfaction = [4,3,2]
// Output: 20
// Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)

// Example 3:

// Input: satisfaction = [-1,-4,-5]
// Output: 0
// Explanation: People do not like the dishes. No dish is prepared.
