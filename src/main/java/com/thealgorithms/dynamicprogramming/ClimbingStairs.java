package com.thealgorithms.dynamicprogramming;

/* A DynamicProgramming solution for Climbing Stairs' problem Returns the
    distinct ways can you climb to the staircase by either climbing 1 or 2 steps.

    Link : https://medium.com/analytics-vidhya/leetcode-q70-climbing-stairs-easy-444a4aae54e8
*/
public class ClimbingStairs {

    public static int numberOfWays(int n) {

        if (n == 1 || n == 0) {
            return n;
        }
        int prev = 1;
        int curr = 1;

        int next;

        for (int i = 2; i <= n; i++) {
            next = curr + prev;
            prev = curr;

            curr = next;
        }

        return curr;
    }
}
