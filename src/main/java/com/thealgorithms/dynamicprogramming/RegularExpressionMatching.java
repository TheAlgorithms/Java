package com.thealgorithms.dynamicprogramming;

/**
 * Implements regular expression matching with support for '.' and '*'.
 * 
 * Problem: Given an input string s and a pattern p, implement regular expression
 * matching with support for '.' and '*' where:
 * - '.' Matches any single character
 * - '*' Matches zero or more of the preceding element
 * - The matching should cover the entire input string (not partial)
 * 
 * This solution uses dynamic programming with memoization for efficient computation.
 * 
 * Example:
 * Input: s = "aa", p = "a" → Output: false
 * Input: s = "aa", p = "a*" → Output: true  
 * Input: s = "ab", p = ".*" → Output: true
 * 
 * Time Complexity: O(m * n) where m is length of s and n is length of p
 * Space Complexity: O(m * n) for the memoization table
 * 
 * @author Your Name (replace with your GitHub username)
 */
public final class RegularExpressionMatching {
    private RegularExpressionMatching() {
        // Private constructor to prevent instantiation
    }

    /**
     * Determines if the input string matches the given pattern.
     *
     * @param s the input string to match (contains only lowercase English letters)
     * @param p the pattern (contains lowercase English letters, '.', and '*')
     * @return true if the entire string matches the pattern, false otherwise
     * @throws IllegalArgumentException if input strings are null or pattern is invalid
     */
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        
        if (!isValidPattern(p)) {
            throw new IllegalArgumentException("Invalid pattern format");
        }

        // Create memoization table with Boolean wrapper for null checks
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    /**
     * Helper method that performs the actual dynamic programming computation.
     *
     * @param i current index in string s
     * @param j current index in pattern p  
     * @param s the input string
     * @param p the pattern
     * @param memo memoization table storing computed results
     * @return true if s[i:] matches p[j:], false otherwise
     */
    private static boolean dp(int i, int j, String s, String p, Boolean[][] memo) {
        // Return cached result if available
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean result;
        
        // Base case: pattern is exhausted
        if (j == p.length()) {
            result = (i == s.length());
        } else {
            // Check if current characters match
            boolean currentMatch = i < s.length() && 
                                  (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));

            // Handle '*' operator (lookahead)
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Two possibilities:
                // 1. Use '*' as zero occurrences (skip current pattern character and '*')
                // 2. Use '*' as one or more occurrences (if current characters match)
                result = dp(i, j + 2, s, p, memo) || 
                        (currentMatch && dp(i + 1, j, s, p, memo));
            } else {
                // No '*' operator, simply advance both pointers if current characters match
                result = currentMatch && dp(i + 1, j + 1, s, p, memo);
            }
        }

        // Cache the result
        memo[i][j] = result;
        return result;
    }

    /**
     * Validates that the pattern follows the constraints:
     * - Only contains lowercase English letters, '.', and '*'
     * - '*' always follows a valid character (not at start and not after another '*')
     *
     * @param p the pattern to validate
     * @return true if pattern is valid, false otherwise
     */
    private static boolean isValidPattern(String p) {
        if (p.isEmpty()) {
            return true;
        }

        // Check first character is not '*'
        if (p.charAt(0) == '*') {
            return false;
        }

        // Check all characters are valid and '*' always follows valid character
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (!isValidPatternChar(c)) {
                return false;
            }
            
            // Check if '*' is properly positioned
            if (c == '*' && (i == 0 || p.charAt(i - 1) == '*')) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a character is valid in a pattern (lowercase letter, '.', or '*')
     */
    private static boolean isValidPatternChar(char c) {
        return (c >= 'a' && c <= 'z') || c == '.' || c == '*';
    }

    /**
     * Alternative iterative DP solution (bottom-up approach)
     * This version uses a 2D boolean array for tabulation.
     *
     * @param s the input string
     * @param p the pattern
     * @return true if string matches pattern, false otherwise
     */
    public static boolean isMatchIterative(String s, String p) {
        if (s == null || p == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        
        if (!isValidPattern(p)) {
            throw new IllegalArgumentException("Invalid pattern format");
        }

        int m = s.length();
        int n = p.length();
        
        // dp[i][j] means s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Empty string matches empty pattern
        dp[0][0] = true;
        
        // Handle patterns like a*, a*b*, a*b*c* that can match empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                
                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    // Zero occurrences of previous character
                    dp[i][j] = dp[i][j - 2];
                    // One or more occurrences if previous character matches
                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}
