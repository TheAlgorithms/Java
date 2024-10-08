package com.thealgorithms.recursion;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniquePermutationsTest {

    @Test
    void testUniquePermutations() {
        String str = "abc";
        List<String> expected = List.of("abc", "acb", "bac", "bca", "cab", "cba");
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
    }

    @Test
    void testUniquePermutationsWithDuplicates() {
        String str = "aab";
        List<String> expected = List.of("aab", "aba", "baa");
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
    }

    @Test
    void testEmptyString() {
        String str = "";
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(1, result.size()); // only 1 permutation which is an empty string
    }

    @Test
    void testSingleCharacter() {
        String str = "a";
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(1, result.size());
        assertEquals("a", result.get(0));
    }
}
