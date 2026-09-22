package com.thealgorithms.strings;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import java.time.Duration;
public class LongestCommonSubstringTest {

    @Test
    public void testNullOrEmptyInputs() {
        assertEquals("", LongestCommonSubstring.longestCommonSubstring(null, "abc"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", null));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("", "abc"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", ""));
    }

    @Test
    public void testNormalSubstrings() {
        assertEquals("cde", LongestCommonSubstring.longestCommonSubstring("abcdef", "zcdemf"));
        assertEquals("abc", LongestCommonSubstring.longestCommonSubstring("abc", "abc"));
        assertEquals("cdef", LongestCommonSubstring.longestCommonSubstring("abcdef", "cdefgh"));
    }

    @Test
    public void testSingleCharacterAndNoMatch() {
        assertEquals("a", LongestCommonSubstring.longestCommonSubstring("a", "a"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", "xyz"));
    }

    @Test
    public void testMultipleMatchesFirstLongest() {
        // Keeps the first matched longest substring when lengths are tied
        assertEquals("abc", LongestCommonSubstring.longestCommonSubstring("abcXdef", "abcYdef"));
    }
    // NEW

    @Test
    public void testSpacesAndSpecialCharacters() {
        assertEquals(" Hello World! ", LongestCommonSubstring.longestCommonSubstring("123 Hello World! 456", "ABC Hello World! XYZ"));
        assertEquals("@#$%^", LongestCommonSubstring.longestCommonSubstring("test@#$%^123", "abc@#$%^xyz"));
    }

    @Test
    public void testCoincidenceAtBoundaries() {
        // Match at the beginning
        assertEquals("PREFIX_", LongestCommonSubstring.longestCommonSubstring("PREFIX_12345", "PREFIX_67890"));
        // Match at the end
        assertEquals("_SUFFIX", LongestCommonSubstring.longestCommonSubstring("12345_SUFFIX", "67890_SUFFIX"));
    }

    @Test
    public void testRepeatedPatterns() {
        assertEquals("anabanana", LongestCommonSubstring.longestCommonSubstring("bananabanana", "anabanana"));
    }

    @Test
    public void testLargeInputsPerformanceAndTimeout() {
        // Generate two 3,000-character strings containing a common substring in the middle
        int size = 3000;
        StringBuilder sb1 = new StringBuilder(size);
        StringBuilder sb2 = new StringBuilder(size);

        for (int i = 0; i < 1000; i++) {
            sb1.append('A');
            sb2.append('B');
        }

        String commonPart = "COMMON_LONG_SUBSTRING_TEST_1234567890";
        sb1.append(commonPart);
        sb2.append(commonPart);

        for (int i = 0; i < 1500; i++) {
            sb1.append('X');
            sb2.append('Y');
        }

        // Verify that the algorithm completes within 2 seconds
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            String result = LongestCommonSubstring.longestCommonSubstring(sb1.toString(), sb2.toString());
            assertEquals(commonPart, result);
        });
    }

    @Test
    public void testVeryLargeInputsTimeoutFailure() {
        // Generate two very large strings (4,000 characters each)
        int size = 4000;
        StringBuilder sb1 = new StringBuilder(size);
        StringBuilder sb2 = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            sb1.append('A');
            sb2.append('B');
        }

        // Enforce a strict 50ms time limit which this O(N * M) computation will exceed
        assertTimeoutPreemptively(Duration.ofMillis(50), () -> {
            LongestCommonSubstring.longestCommonSubstring(sb1.toString(), sb2.toString());
        });
    }

    @Test
    public void testCaseSensitivityAndUnicode() {
        // Case sensitivity test
        assertEquals("ABC", LongestCommonSubstring.longestCommonSubstring("ABCdef", "123ABCxyz"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", "ABC"));

        // Full substring containment
        assertEquals("inside", LongestCommonSubstring.longestCommonSubstring("inside", "text_inside_here"));

        // Unicode characters
        assertEquals("_áéíóú_", LongestCommonSubstring.longestCommonSubstring("hola_áéíóú_mundo", "test_áéíóú_abc"));
    }
    @Test
    public void testWhitespaceAndControlCharacters() {
        // Test with newlines and tabs
        assertEquals("\t\n", LongestCommonSubstring.longestCommonSubstring("start\t\nend", "begin\t\nfinish"));
        
        // Test with multiple consecutive spaces
        assertEquals("   ", LongestCommonSubstring.longestCommonSubstring("a   b", "x   y"));
    }

    @Test
    public void testOverlappingSubstrings() {
        // Test overlapping matches like "AAAA" in "AAAAA" vs "AAAA"
        assertEquals("AAAA", LongestCommonSubstring.longestCommonSubstring("AAAAA", "AAAA"));
        assertEquals("ABAB", LongestCommonSubstring.longestCommonSubstring("ABABAB", "CABAB"));
    }
}
