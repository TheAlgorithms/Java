package com.thealgorithms.dynamicprogramming;

public class RegularExpressionMatching {

    /**
     * Function to implement regex matching with '.' and '*'.
     * '.' Matches any single character
     * '*' Matches zero or more of the preceding element
     *
     * @param s input string
     * @param p pattern string
     * @return true if the string matches the pattern, false otherwise
     */
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    private boolean dp(int i, int j, String s, String p, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];

        boolean ans;
        if (j == p.length()) {
            // If pattern is finished, string must also be finished
            ans = (i == s.length());
        } else {
            // Check if first character matches
            boolean firstMatch = (i < s.length() &&
                    (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

            // Handle '*' in the pattern
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Two options:
                // 1. Skip the "x*" in the pattern
                // 2. If firstMatch is true, consume one char from string
                ans = dp(i, j + 2, s, p, memo) || (firstMatch && dp(i + 1, j, s, p, memo));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p, memo);
            }
        }

        memo[i][j] = ans;
        return ans;
    }

    // -----------------------
    // Main function with test cases
    // -----------------------
    public static void main(String[] args) {
        RegularExpressionMatching solution = new RegularExpressionMatching();

        String[][] testCases = {
                {"aa", "a", "false"},
                {"aa", "a*", "true"},
                {"ab", ".*", "true"},
                {"mississippi", "mis*is*p*.", "false"},
                {"ab", ".*c", "false"},
                {"aaa", "a*a", "true"},
                {"aab", "c*a*b", "true"},
                {"", ".*", "true"},
                {"", "", "true"},
                {"abcd", "d*", "false"}
        };

        System.out.println("Testing Regular Expression Matching (DP with Memoization)\n");

        for (String[] test : testCases) {
            String s = test[0];
            String p = test[1];
            boolean expected = Boolean.parseBoolean(test[2]);
            boolean result = solution.isMatch(s, p);
            System.out.printf("Input: s = \"%s\", p = \"%s\" | Output: %b | Expected: %b | %s\n",
                    s, p, result, expected, (result == expected ? "✅" : "❌"));
        }
    }
}
