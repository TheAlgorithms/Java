package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeTest {

    @Test
    public void palindrome() {

        String[] palindromes = { null, "", "aba", "123321", "kayak" };
        for (String s : palindromes) {
            Assertions.assertTrue(isPalindrome(s) &&
            isPalindromeRecursion(s) &&
            isPalindromeTwoPointer(s));
        }

        String[] notPalindromes = { "abb", "abc", "abc123", "kayaks" };
        for (String s : notPalindromes) {
            Assertions.assertFalse(isPalindrome(s) &&
            !isPalindromeRecursion(s) &&
            !isPalindromeTwoPointer(s));
        }
        
    }
}
