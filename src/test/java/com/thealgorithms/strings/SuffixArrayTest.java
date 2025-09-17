package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SuffixArrayTest {

    @Test
    void testEmptyString() {
        int[] result = SuffixArray.buildSuffixArray("");
        assertArrayEquals(new int[] {}, result, "Empty string should return empty suffix array");
    }

    @Test
    void testSingleCharacter() {
        int[] result = SuffixArray.buildSuffixArray("a");
        assertArrayEquals(new int[] {0}, result, "Single char string should return [0]");
    }

    @Test
    void testDistinctCharacters() {
        int[] result = SuffixArray.buildSuffixArray("abc");
        assertArrayEquals(new int[] {0, 1, 2}, result, "Suffixes already in order for distinct chars");
    }

    @Test
    void testBananaExample() {
        int[] result = SuffixArray.buildSuffixArray("banana");
        assertArrayEquals(new int[] {5, 3, 1, 0, 4, 2}, result, "Suffix array of 'banana' should be [5,3,1,0,4,2]");
    }

    @Test
    void testStringWithDuplicates() {
        int[] result = SuffixArray.buildSuffixArray("aaaa");
        assertArrayEquals(new int[] {3, 2, 1, 0}, result, "Suffix array should be descending indices for 'aaaa'");
    }

    @Test
    void testRandomString() {
        int[] result = SuffixArray.buildSuffixArray("mississippi");
        assertArrayEquals(new int[] {10, 7, 4, 1, 0, 9, 8, 6, 3, 5, 2}, result, "Suffix array for 'mississippi' should match expected");
    }
}
