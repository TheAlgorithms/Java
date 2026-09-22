package com.thealgorithms.strings;

/**
 * Longest Common Substring finds the longest string that is a
 * contiguous substring of two input strings.
 * Example: "abcdef" and "zcdemf" -> "cde"
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_common_substring">
 *     Wikipedia: Longest Common Substring</a>
 *
 * author: Vraj Prajapati @Rosander0
 */
public final class LongestCommonSubstring {

    private LongestCommonSubstring() {
        // Utility class
    }

    /**
     * Finds the longest common substring of two strings.
     *
     * @param a First input string
     * @param b Second input string
     * @return The longest common substring, or empty string if none exists.
     *     If multiple substrings share the maximum length, the first one found is returned.
     */
    public static String longestCommonSubstring(final String a, final String b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return "";
        }

        int[] dp = new int[b.length() + 1];
        int maxLength = 0;
        int endIndex = 0;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = b.length(); j >= 1; j--) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;
                    if (dp[j] > maxLength) {
                        maxLength = dp[j];
                        endIndex = i;
                    }
                } else {
                    dp[j] = 0;
                }
            }
        }

        if (maxLength == 0) {
            return "";
        }
        return a.substring(endIndex - maxLength, endIndex);
    }
}
