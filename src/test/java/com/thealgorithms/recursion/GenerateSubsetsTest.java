package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public final class GenerateSubsetsTest {

    @Test
    void subsetRecursionTestOne() {
        String str = "abc";
        String[] expected = new String[] {"abc", "ab", "ac", "a", "bc", "b", "c", ""};

        List<String> ans = GenerateSubsets.subsetRecursion(str);
        assertArrayEquals(ans.toArray(), expected);
    }

    @Test
    void subsetRecursionTestTwo() {
        String str = "cbf";
        String[] expected = new String[] {"cbf", "cb", "cf", "c", "bf", "b", "f", ""};

        List<String> ans = GenerateSubsets.subsetRecursion(str);
        assertArrayEquals(ans.toArray(), expected);
    }

    @Test
    void subsetRecursionTestThree() {
        String str = "aba";
        String[] expected = new String[] {"aba", "ab", "aa", "a", "ba", "b", "a", ""};

        List<String> ans = GenerateSubsets.subsetRecursion(str);
        assertArrayEquals(ans.toArray(), expected);
    }
}
