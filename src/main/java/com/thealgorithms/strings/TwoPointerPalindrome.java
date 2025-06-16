/**
 * Check if a given string is a palindrome using the two-pointer technique.
 * A palindrome is a string that reads the same forward and backward.
 * 
 * Example: "level", "madam", "12321" are palindromes.
 * 
 * Author: Sushma
 */

package com.thealgorithms.strings;

public class TwoPointerPalindrome {

    /**
     * This method checks if the given string is a palindrome using two pointers.
     *
     * @param s The input string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {

        // If the string is null or has only 1 or 0 characters, it's a palindrome
        if (s == null || s.length() <= 1) {
            return true;
        }

        // Initialize two pointers: left starting from the beginning,
        // right starting from the end of the string
        int left = 0;
        int right = s.length() - 1;

        // Loop until the two pointers meet in the middle
        while (left < right) {

            // If characters at left and right don't match, it's not a palindrome
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            // Move the left pointer to the right
            left++;

            // Move the right pointer to the left
            right--;
        }

        // If all characters matched, then it's a palindrome
        return true;
    }
}
