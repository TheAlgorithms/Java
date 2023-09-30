package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class countPalindromicSubsequenceTest {

    @Test
    void hasAllDifferentCharacter() {
        assertEquals(4, CountPalindromicSubsequences.countPS("abcd"));
    }

    @Test
    void hasTwoSameChar() {
        assertEquals(4, CountPalindromicSubsequences.countPS("aab"));
    }

    @Test
    void hasAllSameChar() {
        assertEquals(15, CountPalindromicSubsequences.countPS("aaaa"));
    }
}
