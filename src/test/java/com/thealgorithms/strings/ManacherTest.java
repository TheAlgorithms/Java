package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ManacherTest {

    @Test
    public void testLongestPalindrome() {
        assertEquals("aabcdefggfedcbaa", Manacher.longestPalindrome("abracadabraabcdefggfedcbaabracadabra")); // Long string with embedded palindrome
        assertEquals("racecar", Manacher.longestPalindrome("somelongtextwithracecarmiddletext")); // Longer string with racecar palindrome
        assertEquals("ananananananana", Manacher.longestPalindrome("bananananananana")); // Repetitive pattern with palindrome
        assertEquals("defgfed", Manacher.longestPalindrome("qwertydefgfedzxcvbnm")); // Palindrome in middle of long string
        assertEquals("abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba", Manacher.longestPalindrome("abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba")); // Symmetrical section
    }

    @Test
    public void testEmptyAndSingle() {
        assertEquals("", Manacher.longestPalindrome("")); // Empty string
        assertEquals("a", Manacher.longestPalindrome("a")); // Single character
    }

    @Test
    public void testComplexCases() {
        assertEquals("tattarrattat", Manacher.longestPalindrome("abcdefghijklmnopqrstuvwxyzttattarrattatabcdefghijklmnopqrstuvwxyz")); // Long palindrome inside a large string
        assertEquals("aaaaabaaaaa", Manacher.longestPalindrome("aaaaabaaaaacbaaaaa")); // Large repetitive character set
        assertEquals("abcdefghhgfedcba", Manacher.longestPalindrome("sometextrandomabcdefgabcdefghhgfedcbahijklmnopqrstuvwxyz")); // Large string with clear palindromic section
        assertEquals("madaminedenimadam", Manacher.longestPalindrome("therewasasignthatsaidmadaminedenimadamitwasthereallalong")); // Famous palindrome within a long string
    }

    @Test
    public void testSentencePalindromes() {
        assertEquals("lanacanal", Manacher.longestPalindrome("XThisisalongtextbuthiddeninsideisAmanaplanacanalPanamaWhichweknowisfamous"));
        assertEquals("everoddoreve", Manacher.longestPalindrome("AverylongstringthatcontainsNeveroddoreveninahiddenmanner")); // Another sentence-like palindrome
    }
}
