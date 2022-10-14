package com.thealgorithms.dynamicprogramming;
/* You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Explanation:- Given a number of stairs. Starting from the 0th stair we need to climb to the Nth stair.
 At a time we can climb either one or two steps. We need to return the total number of distinct ways to reach from 0th to Nth stair.
 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
Naive Approach:This approach is very simple.In each step one can go either 1 step or 2 step.We can use recursion for this...
code-snippet will look like--
f(n){
    if(n<=1) return 1;
    return f(n-1)+f(n-2);
}
if we carefullly observe by recursion we are facing overlapping subproblem
hence we have to apply Dynamic Programming.
I am going to use Tabulation format for the problem
 */

public class ClimbingStairs{
    public static int climbStairs(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
       
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(climbStairs(6));
        System.out.println(climbStairs(8));
        System.out.println(climbStairs(12));
        System.out.println(climbStairs(20));
        System.out.println(climbStairs(13));
    }
}
/*
 * Output:-
13
34
233
10946
377

Time-Complexity:- O(n)
 */
