package com.thealgorithms.dynamicprogramming;

/**
 * @author <a href="https://github.com/siddhant2002">Siddhant Swarup Mallick</a>

 * In mathematics, the Golomb sequence is a non-decreasing integer sequence where n-th term is equal
 * to number of times n appears in the sequence.

 * <a href="https://en.wikipedia.org/wiki/Golomb_sequence">Wikipedia</a>
 * Program description - To find the Golomb sequence upto n
 */
public final class CountFriendsPairing {
    private CountFriendsPairing() {
    }

    public static boolean countFriendsPairing(int n, int[] a) {
        int[] dp = new int[n + 1];
        // array of n+1 size is created
        dp[0] = 1;
        // since 1st index position value is fixed so it's marked as 1
        for (int i = 1; i < n; i++) {
            dp[i] = 1 + dp[i - dp[dp[i - 1]]];
            // formula for ith golomb sequence is dp(i) = 1 + dp(i – dp(dp(i - 1)))
        }
        for (int i = 1; i < n; i++) {
            if (a[i - 1] != dp[i]) {
                return false;
                // checks whether the calculated answer matches with the expected answer
            }
        }
        return true;
        // returns true if calculated answer matches with the expected answer
    }
}
