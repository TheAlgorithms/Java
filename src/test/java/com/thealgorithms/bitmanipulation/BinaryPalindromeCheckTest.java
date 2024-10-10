package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinaryPalindromeCheckTest {

    @Test
    public void testIsBinaryPalindrome() {
        assertTrue(BinaryPalindromeCheck.isBinaryPalindrome(9)); // 1001 is a palindrome
        assertFalse(BinaryPalindromeCheck.isBinaryPalindrome(10)); // 1010 is not a palindrome
        assertTrue(BinaryPalindromeCheck.isBinaryPalindrome(0)); // 0 is a palindrome
        assertTrue(BinaryPalindromeCheck.isBinaryPalindrome(1)); // 1 is a palindrome
        assertFalse(BinaryPalindromeCheck.isBinaryPalindrome(12)); // 1100 is not a palindrome
    }
}
