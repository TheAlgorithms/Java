package com.thealgorithms.dynamicprogramming;

/**
 * Implements regular expression matching with support for '.' and '*'.
 * 
 * <p>
 * The regular expression matching problem involves determining if a given string
 * matches a pattern containing special characters '.' and '*'. The '.' matches
 * any single character, while '*' matches zero or more of the preceding element.
 * 
 * <p>
 * This solution uses dynamic programming with memoization for efficient computation.
 * 
 * <p>
 * For more information:
 * @see <a href="https://en.wikipedia.org/wiki/Regular_expression">Regular Expression</a>
 * @see <a href="https://leetcode.com/problems/regular-expression-matching/">LeetCode Problem 10</a>
 * 
 * <p>
 * Example:
 * <pre>
 * Input: s = "aa", p = "a" → Output: false
 * Input: s = "aa", p = "a*" → Output: true  
 * Input: s = "ab", p = ".*" → Output: true
 * </pre>
 * 
 * <p>
 * Time Complexity: O(m * n) where m is length of s and n is length of p
 * Space Complexity: O(m * n) for the memoization table
 */
public final class RegularExpressionMatching {
    private RegularExpressionMatching() {
        // Private constructor to prevent instantiation
    }

    /**
     * Determines if the input string matches the given pattern.
     *
     * @param inputString the input string to match (contains only lowercase English letters)
     * @param pattern the pattern (contains lowercase English letters, '.', and '*')
     * @return true if the entire string matches the pattern, false otherwise
     * @throws IllegalArgumentException if input strings are null or pattern is invalid
     */
    public static boolean isMatch(String inputString, String pattern) {
        if (inputString == null || pattern == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        
        if (!isValidPattern(pattern)) {
            throw new IllegalArgumentException("Invalid pattern format");
        }

        Boolean[][] memo = new Boolean[inputString.length() + 1][pattern.length() + 1];
        return dynamicProgramming(0, 0, inputString, pattern, memo);
    }

    /**
     * Helper method that performs the actual dynamic programming computation.
     *
     * @param stringIndex current index in string s
     * @param patternIndex current index in pattern p  
     * @param inputString the input string
     * @param pattern the pattern
     * @param memo memoization table storing computed results
     * @return true if s[i:] matches p[j:], false otherwise
     */
    private static boolean dynamicProgramming(int stringIndex, int patternIndex, 
                                             String inputString, String pattern, 
                                             Boolean[][] memo) {
        if (memo[stringIndex][patternIndex] != null) {
            return memo[stringIndex][patternIndex];
        }

        boolean result;
        
        if (patternIndex == pattern.length()) {
            result = (stringIndex == inputString.length());
        } else {
            boolean currentMatch = stringIndex < inputString.length() && 
                                  (pattern.charAt(patternIndex) == '.' || 
                                   pattern.charAt(patternIndex) == inputString.charAt(stringIndex));

            if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
                result = dynamicProgramming(stringIndex, patternIndex + 2, inputString, pattern, memo) || 
                        (currentMatch && dynamicProgramming(stringIndex + 1, patternIndex, inputString, pattern, memo));
            } else {
                result = currentMatch && dynamicProgramming(stringIndex + 1, patternIndex + 1, inputString, pattern, memo);
            }
        }

        memo[stringIndex][patternIndex] = result;
        return result;
    }

    /**
     * Validates that the pattern follows the constraints.
     *
     * @param pattern the pattern to validate
     * @return true if pattern is valid, false otherwise
     */
    private static boolean isValidPattern(String pattern) {
        if (pattern.isEmpty()) {
            return true;
        }

        if (pattern.charAt(0) == '*') {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);
            if (!isValidPatternChar(currentChar)) {
                return false;
            }
            
            if (currentChar == '*' && (i == 0 || pattern.charAt(i - 1) == '*')) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a character is valid in a pattern.
     */
    private static boolean isValidPatternChar(char character) {
        return (character >= 'a' && character <= 'z') || character == '.' || character == '*';
    }

    /**
     * Alternative iterative DP solution (bottom-up approach).
     *
     * @param inputString the input string
     * @param pattern the pattern
     * @return true if string matches pattern, false otherwise
     */
    public static boolean isMatchIterative(String inputString, String pattern) {
        if (inputString == null || pattern == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        
        if (!isValidPattern(pattern)) {
            throw new IllegalArgumentException("Invalid pattern format");
        }

        int stringLength = inputString.length();
        int patternLength = pattern.length();
        
        boolean[][] dp = new boolean[stringLength + 1][patternLength + 1];
        
        dp[0][0] = true;
        
        for (int j = 2; j <= patternLength; j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        for (int i = 1; i <= stringLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                char stringChar = inputString.charAt(i - 1);
                char patternChar = pattern.charAt(j - 1);
                
                if (patternChar == '.' || patternChar == stringChar) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (patternChar == '*') {
                    char previousChar = pattern.charAt(j - 2);
                    dp[i][j] = dp[i][j - 2];
                    if (previousChar == '.' || previousChar == stringChar) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[stringLength][patternLength];
    }
}
