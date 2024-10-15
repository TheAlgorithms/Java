package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public final class Palindrome {

    private Palindrome() {
    }

    /**
     * Check if a string is palindrome or not using StringBuilder.
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise {@code false}
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        String cleanedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleanedString.equals(new StringBuilder(cleanedString).reverse().toString());
    }

    /**
     * Check if a string is palindrome using optimized recursion (pointer-based).
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise {@code false}
     */
    public static boolean isPalindromeRecursion(String s) {
        if (s == null) {
            return true;
        }
        String cleanedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeRecursion(cleanedString, 0, cleanedString.length() - 1);
    }

    private static boolean isPalindromeRecursion(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        return isPalindromeRecursion(s, left + 1, right - 1);
    }

    /**
     * Check if a string is palindrome using the two-pointer technique.
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise {@code false}
     */
    public static boolean isPalindromeTwoPointer(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        String cleanedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = cleanedString.length() - 1;
        while (left < right) {
            if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * JUnit Test Cases for the Palindrome methods
     */
    public static class PalindromeTest {

        @Test
        void testIsPalindrome() {
            assertTrue(Palindrome.isPalindrome("madam"));
            assertFalse(Palindrome.isPalindrome("hello"));
            assertTrue(Palindrome.isPalindrome("A man, a plan, a canal: Panama"));
            assertTrue(Palindrome.isPalindrome(""));
            assertTrue(Palindrome.isPalindrome("a"));
        }

        @Test
        void testIsPalindromeRecursion() {
            assertTrue(Palindrome.isPalindromeRecursion("racecar"));
            assertFalse(Palindrome.isPalindromeRecursion("world"));
            assertTrue(Palindrome.isPalindromeRecursion("A man, a plan, a canal, Panama"));
            assertTrue(Palindrome.isPalindromeRecursion("a"));
            assertTrue(Palindrome.isPalindromeRecursion(null));
        }

        @Test
        void testIsPalindromeTwoPointer() {
            assertTrue(Palindrome.isPalindromeTwoPointer("madam"));
            assertFalse(Palindrome.isPalindromeTwoPointer("hello"));
            assertTrue(Palindrome.isPalindromeTwoPointer("A man, a plan, a canal: Panama"));
            assertTrue(Palindrome.isPalindromeTwoPointer("a"));
            assertTrue(Palindrome.isPalindromeTwoPointer(null));
        }
    }
}
