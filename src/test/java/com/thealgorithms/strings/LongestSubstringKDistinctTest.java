package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubstringKDistinctTest {

    @Test
    void testBasic() {
        assertEquals(3, LongestSubstringKDistinct.longestSubstringKDistinct("eceba", 2));
        assertEquals(2, LongestSubstringKDistinct.longestSubstringKDistinct("aa", 1));
    }

    @Test
    void testEdgeCases() {
        assertEquals(0, LongestSubstringKDistinct.longestSubstringKDistinct("", 2));
        assertEquals(0, LongestSubstringKDistinct.longestSubstringKDistinct("abc", 0));
    }

    @Test
    void testLarge() {
        assertEquals(4, LongestSubstringKDistinct.longestSubstringKDistinct("aabbcc", 2));
        assertEquals(6, LongestSubstringKDistinct.longestSubstringKDistinct("abcabcbb", 3));
    }
}
