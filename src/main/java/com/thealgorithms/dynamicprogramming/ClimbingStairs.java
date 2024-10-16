package com.thealgorithms.dynamicprogramming;

/*
 * A dynamic programming solution for the "Climbing Stairs" problem.
 * Returns the no. of distinct ways to climb to the top
 * of a staircase when you can climb either 1 or 2 steps at a time.
 *
 * For example, if there are 5 steps, the possible ways to climb the
 * staircase are:
 * 1. 1-1-1-1-1
 * 2. 1-1-1-2
 * 3. 1-2-1-1
 * 4. 2-1-1-1
 * 5. 2-2-1
 * 6. 1-1-2-1
 * 7. 1-2-2
 * 8. 2-1-2
 * Ans: 8 ways
 */
public final class ClimbingStairs {

    private ClimbingStairs() {
    }

    /**
     * Calculates the no. of distinct ways to climb a staircase with n steps.
     *
     * @param n the no. of steps in the staircase (non-negative integer)
     * @return the no. of distinct ways to climb to the top
     *         - Returns 0 if n is 0 (no steps to climb).
     *         - Returns 1 if n is 1 (only one way to climb).
     *         - For n > 1, it returns the total no. of ways to climb.
     */
    public static int numberOfWays(int n) {

        // Base case: if there are no steps or only one step, return n.
        if (n == 1 || n == 0) {
            return n;
        }

        int prev = 1; // Ways to reach the step before the current one (step 1)
        int curr = 1; // Ways to reach the current step (step 2)
        int next; // Total ways to reach the next step

        for (int i = 2; i <= n; i++) { // step 2 to n
            next = curr + prev;

            // Move the pointers to the next step
            prev = curr;
            curr = next;
        }

        return curr; // Ways to reach the nth step
    }
}
