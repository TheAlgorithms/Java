package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TwoPointerPalindromeTest {

    @Test
    void testPalindrome() {
        assertTrue(TwoPointerPalindrome.isPalindrome("madam"));
        assertTrue(TwoPointerPalindrome.isPalindrome("racecar"));
        assertTrue(TwoPointerPalindrome.isPalindrome("a"));

        assertTrue(TwoPointerPalindrome.isPalindrome(null));
assertTrue(TwoPointerPalindrome.isPalindrome(""));

        assertFalse(TwoPointerPalindrome.isPalindrome("hello"));
        assertFalse(TwoPointerPalindrome.isPalindrome("world"));
    }
}
