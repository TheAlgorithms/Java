package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to find the number of unique subsequences that can be
 * produced from a given string.
 *
 * <p> This class contains static methods to compute the unique subsequence count
 * using dynamic programming and recursion. It ensures that duplicate characters
 * are not counted multiple times in the subsequences.</p>
 *
 * <p> Author: https://github.com/Tuhinm2002 </p>
 */
public final class UniqueSubsequencesCount {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * This class should only be used in a static context.
     *
     * @throws UnsupportedOperationException if attempted to instantiate.
     */
    private UniqueSubsequencesCount() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Finds the number of unique subsequences that can be generated from
     * the given string.
     *
     * <p> This method initializes a dynamic programming (DP) array and invokes
     * the recursive helper function to compute the subsequence count.</p>
     *
     * @param str the input string from which subsequences are generated
     * @return the total count of unique subsequences
     */
    public static int countSubseq(String str) {

        // DP array initialized to store intermediate results
        int[] dp = new int[str.length() + 1];
        Arrays.fill(dp, -1);

        // Calls the recursive function to compute the result
        return countSubsequences(str, 0, dp);
    }

    /**
     * Recursive helper function to count the number of unique subsequences
     * starting from the given index.
     *
     * <p> Uses a HashSet to avoid counting duplicate characters within
     * a single subsequence.</p>
     *
     * @param st the input string
     * @param idx the current index from which to calculate subsequences
     * @param dp dynamic programming array used to memoize results
     * @return the total number of unique subsequences starting from the
     *         current index
     */
    public static int countSubsequences(String st, int idx, int[] dp) {

        // Base case: when index exceeds the string length
        if (idx >= st.length()) {
            return 0;
        }

        // If result is already calculated, return the memoized value
        if (dp[idx] != -1) {
            return dp[idx];
        }

        // Set to store characters to avoid duplicates
        Set<Character> set = new HashSet<>();

        int res = 0;

        // Iterate over the string starting from current index
        for (int j = idx; j < st.length(); j++) {

            // If character is already in the set, skip it
            if (set.contains(st.charAt(j))) {
                continue;
            }

            // Add character to set and recursively calculate subsequences
            set.add(st.charAt(j));

            // 1 for the current subsequence + recursive call for the rest of the string
            res = 1 + countSubsequences(st, j + 1, dp) + res;
        }

        // Memoize the result
        dp[idx] = res;
        return dp[idx];
    }
}
