package com.thealgorithms.strings;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Palindrome
 */
class Palindrome {

    /**
     * Check if a string is palindrome string or not using String Builder
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise
     * {@code false}
     */
    public static boolean isPalindrome(String s) {
        return (
            (s == null || s.length() <= 1) ||
            s.equals(new StringBuilder(s).reverse().toString())
        );
    }

    /**
     * Check if a string is palindrome string or not using recursion
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise
     * {@code false}
     */
    public static boolean isPalindromeRecursion(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }

        return isPalindromeRecursion(s.substring(1, s.length() - 1));
    }

    /**
     * Check if a string is palindrome string or not using two pointer technique
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise
     * {@code false}
     */
    public static boolean isPalindromeTwoPointer(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
