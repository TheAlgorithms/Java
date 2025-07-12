package com.thealgorithms.strings;

/**
 * Utility class to check if a string is a palindrome.
 */
public class PalindromeChecker {

    /**
     * Checks if the given input is a palindrome.
     * Ignores spaces, punctuation, and case.
     *
     * @param input the string to check
     * @return true if it's a palindrome, false otherwise
     */
    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        // Remove non-letter characters and convert to lowercase
        String cleaned = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String test1 = "A man a plan a canal Panama";
        String test2 = "OpenAI";
        System.out.println("\"" + test1 + "\" is palindrome? " + isPalindrome(test1));
        System.out.println("\"" + test2 + "\" is palindrome? " + isPalindrome(test2));
    }
}
