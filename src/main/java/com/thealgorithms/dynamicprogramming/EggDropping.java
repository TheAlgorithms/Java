package com.thealgorithms.dynamicprogramming;

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

    /**
     * Example usage
     */
    public static void main(String[] args) {
        try {
            // Example: 2 eggs and 4 floors
            System.out.println("Minimum number of trials in worst case with 2 eggs and 4 floors: " + minTrials(2, 4));

            // Additional test case
            System.out.println("Minimum number of trials in worst case with 3 eggs and 5 floors: " + minTrials(3, 5));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
