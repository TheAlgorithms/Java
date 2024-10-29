package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Dynamic Programming solution for the Egg Dropping Puzzle
 * The problem is to find the minimum number of attempts needed in the worst case to find the critical
 * floor from which if an egg is dropped, it will break.
 * Time Complexity: O(n * m * m), where n is number of eggs and m is number of floors
 * Space Complexity: O(n * m) to store the DP table
 * co-author @manishraj27
 */
public final class EggDropping {

    private EggDropping() {
        // private constructor to prevent instantiation
    }

    /**
     * Finds minimum number of trials needed in worst case for n eggs and m floors
     *
     * @param eggs The number of eggs available
     * @param floors The number of floors in the building
     * @return Minimum number of trials needed in worst case
     * @throws IllegalArgumentException if eggs <= 0 or floors < 0
     */
    public static int minTrials(int eggs, int floors) {
        if (eggs <= 0 || floors < 0) {
            throw new IllegalArgumentException("Number of eggs must be positive and floors must be non-negative");
        }

        // dp[i][j] represents minimum number of trials needed for i eggs and j floors
        int[][] dp = new int[eggs + 1][floors + 1];

        // Base case 1: Zero trials for zero floor
        // Base case 2: One trial for one floor
        for (int i = 1; i <= eggs; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        // Base case 3: With one egg, need to try every floor from bottom
        for (int j = 1; j <= floors; j++) {
            dp[1][j] = j;
        }

        // Fill rest of the entries in table using optimal substructure property
        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                // Try dropping egg from each floor and find minimum trials needed
                for (int k = 1; k <= j; k++) {
                    // Maximum of:
                    // 1) Egg breaks at floor k: Check below floors with i-1 eggs
                    // 2) Egg doesn't break: Check above floors with i eggs
                    int attempts = 1 + Math.max(dp[i - 1][k - 1], dp[i][j - k]);
                    dp[i][j] = Math.min(dp[i][j], attempts);
                }
            }
        }

        return dp[eggs][floors];
    }

    @Test
    void testBasicScenarios() {
        // Test with 2 eggs and 4 floors
        assertEquals(3, minTrials(2, 4));

        // Test with 3 eggs and 5 floors
        assertEquals(3, minTrials(3, 5));
    }

    @Test
    void testEdgeCases() {
        // Test with single egg
        assertEquals(0, minTrials(1, 0));
        assertEquals(1, minTrials(1, 1));
        assertEquals(2, minTrials(1, 2));

        // Test with multiple eggs but minimal floors
        assertEquals(0, minTrials(2, 0));
        assertEquals(1, minTrials(3, 1));
    }

    @Test
    void testLargeInputs() {
        assertEquals(4, minTrials(3, 10));
        assertEquals(7, minTrials(2, 36));
    }

    @Test
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> minTrials(0, 5));
        assertThrows(IllegalArgumentException.class, () -> minTrials(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> minTrials(2, -1));
    }
}
