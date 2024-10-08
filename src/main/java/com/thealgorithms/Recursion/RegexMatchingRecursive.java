package com.thealgorithms.Recursion;

public class RegexMatchingRecursive {
    public boolean isMatch(String s, String p) {
        // Base case: Return is p is empty
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // Check if the first character of s matches the first character of p.
        boolean firstMatch = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        // So...If there's a '*' in the second position of p i.e., p.charAt(1) == '*'
        // it means we can either:
        // 1. Ignore the '*' and the preceding character (move to p.substring(2)).
        // 2. Use the '*' to match the first character of s if there's a match.

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) ||
                    (firstMatch && isMatch(s.substring(1), p)));
        } else {
            // Otherwise, check the next characters of both s and p.
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public static void main(String[] args) {
        RegexMatchingRecursive solution = new RegexMatchingRecursive();

        // Test cases
        System.out.println(solution.isMatch("aab", "c*a*b")); // Output: true
        System.out.println(solution.isMatch("mississippi", "mis*is*p*.")); // Output: false
    }
}
