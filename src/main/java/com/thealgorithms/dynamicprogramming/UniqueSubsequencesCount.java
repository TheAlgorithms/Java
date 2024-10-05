package com.thealgorithms.dynamicprogramming;

// Program to find the number of Subsequences can be produced from a string

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Author -> https://github.com/Tuhinm2002

public final class UniqueSubsequencesCount {

    private UniqueSubsequencesCount() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int subseqCount(String str) {

        int[] dp = new int[str.length() + 1];
        Arrays.fill(dp, -1);

        return recursiveCall(str, 0, dp);
    }

    public static int recursiveCall(String st, int idx, int[] dp) {

        if (idx >= st.length()) {
            return 0;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        Set<Character> set = new HashSet<>();

        int res = 0;

        for (int j = idx; j < st.length(); j++) {

            if (set.contains(st.charAt(j))) {
                continue;
            }

            set.add(st.charAt(j));

            res = 1 + recursiveCall(st, j + 1, dp) + res;
        }

        dp[idx] = res;
        return dp[idx];
    }
}
