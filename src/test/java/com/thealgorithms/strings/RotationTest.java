package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RotationTest {

    @Test
    public void isLongestPalindromicSubstring() {
        assertEquals("eksge", Rotation.rotate("geeks", 2));
        assertEquals("anasban", LongestPalindromicSubstring.longestPalindrome("bananas", 3));
        assertEquals("abracadabra", LongestPalindromicSubstring.longestPalindrome("abracadabra", 0));
     }
}
