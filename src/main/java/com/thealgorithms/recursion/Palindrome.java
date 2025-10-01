package com.thealgorithms.recursion;

/*
    Check if a string is a palindrome using recursion.
    Example: "madam" → true, "hello" → false
*/
public final class Palindrome {
    private Palindrome() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isPalindrome(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null string not allowed");
        }
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        return isPalindromeHelper(str, left + 1, right - 1);
    }
}
