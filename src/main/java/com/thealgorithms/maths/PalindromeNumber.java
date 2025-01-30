package com.thealgorithms.maths;

public final class PalindromeNumber {
    private PalindromeNumber() {
    }
    /**
     * Check if {@code n} is palindrome number or not
     *
     * @param number the number
     * @return {@code true} if {@code n} is palindrome number, otherwise
     * {@code false}
     */
    public static boolean isPalindrome(int number) {        
        int reverseNumber = ReverseNumber.reverseNumber(number);      
        return number == reverseNumber;
    }
}
