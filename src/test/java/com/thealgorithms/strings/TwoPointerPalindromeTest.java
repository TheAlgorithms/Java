package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TwoPointerPalindromeTest {

    @Test
    void testPalindrome() {
        assertTrue(TwoPointerPalindrome.isPalindrome("madam"));
        assertTrue(TwoPointerPalindrome.isPalindrome("racecar"));
        assertTrue(TwoPointerPalindrome.isPalindrome("a"));

        assertFalse(TwoPointerPalindrome.isPalindrome("hello"));
        assertFalse(TwoPointerPalindrome.isPalindrome("world"));
    }
}
