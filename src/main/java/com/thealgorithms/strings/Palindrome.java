package com.thealgorithms.strings;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Palindrome
 */
final class Palindrome {
    private Palindrome() {
    }

    /**
     * Check if a string is palindrome string or not using String Builder
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise
     * {@code false}
     */
    public static boolean isPalindrome(String s) {
        return ((s == null || s.length() <= 1) || s.equals(new StringBuilder(s).reverse().toString()));
        
        //above return statement can be understood as
        //if length of s is 0(null) or s is a string of a single character
        //or if s is equals to (build a new string builder initializer,
        //    reverse it and then make it into a new String) reversed s String
    
    }

    /**
     * Check if a string is palindrome string or not using recursion
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise
     * {@code false}
     */
    public static boolean isPalindromeRecursion(String s) {
        if (s == null || s.length() <= 1) {   //checking if s is null or it is a single character
            return true;                        
        }

        if (s.charAt(0) != s.charAt(s.length() - 1)) { //checking if first and last character are not equal
            return false;
        }

        return isPalindromeRecursion(s.substring(1, s.length() - 1));

        //if they are equal, then use a modified String s that excludes its first and last char as next parameter
        //thus we are recursively checking first and last character and at end we come to middle of s
        //if s length is odd, at the end parameter's length will be 1 and will return true from first condition
        //if its odd, then lenght will be null at the end
    }

    /**
     * Check if a string is palindrome string or not using two pointer technique
     *
     * @param s a string to check
     * @return {@code true} if given string is palindrome, otherwise
     * {@code false}
     */
    public static boolean isPalindromeTwoPointer(String s) {
        if (s == null || s.length() <= 1) {     //checking if s is null(its length 0) or its a single char
            return true;
        }
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {   //taken two variables i and j,
        //i's value will increase by 1, j will decrease by 1 after each loop
        
            if (s.charAt(i) != s.charAt(j)) {   //checking first and last charcter, second and second last and so on...
                return false;
            }
        }
        return true;
    }
}
