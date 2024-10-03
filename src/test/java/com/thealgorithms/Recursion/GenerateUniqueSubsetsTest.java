package com.thealgorithms.Recursion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public final class GenerateUniqueSubsetsTest {

    @Test
    void subsetRecursionTestOne() {
        String str = "aba";
        String[] expected = new String[] {"", "a", "aa", "ab", "aba", "b", "ba"};

        List<String> ans = GenerateUniqueSubsets.subsetRecursion(str);
        assertArrayEquals(ans.toArray(), expected);
    }

    @Test
    void subsetRecursionTestTwo() {
        String str = "abba";
        String[] expected = new String[] {"", "a", "aa", "ab", "aba", "abb", "abba", "b", "ba", "bb", "bba"};

        List<String> ans = GenerateUniqueSubsets.subsetRecursion(str);
        assertArrayEquals(ans.toArray(), expected);
    }

    @Test
    void subsetRecursionTestThree() {
        String str = "aaa";
        String[] expected = new String[] {"", "a", "aa", "aaa"};

        List<String> ans = GenerateUniqueSubsets.subsetRecursion(str);
        assertArrayEquals(ans.toArray(), expected);
    }
}