package com.thealgorithms.Recursion;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniquePermutationsTest {

    @Test
    void testUniquePermutations() {
        String str = "abc";
        List<String> expected = List.of("abc", "acb", "bac", "bca", "cab", "cba");
        List<String> result = UniquePermutations.getUniquePermutations(str);
        Collections.sort(expected); // Ensure expected is sorted
        Collections.sort(result); // Ensure result is sorted
        assertEquals(expected, result);
    }

    @Test
    void testUniquePermutationsWithDuplicates() {
        String str = "aab";
        List<String> expected = List.of("aab", "aba", "baa");
        List<String> result = UniquePermutations.getUniquePermutations(str);
        Collections.sort(expected); // Ensure expected is sorted
        Collections.sort(result); // Ensure result is sorted
        assertEquals(expected, result);
    }

    @Test
    void testEmptyString() {
        String str = "";
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(1, result.size()); // only 1 permutation which is an empty string
        assertEquals("", result.get(0)); // Verify the only permutation is the empty string
    }

    @Test
    void testSingleCharacter() {
        String str = "a";
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(1, result.size());
        assertEquals("a", result.get(0));
    }

    @Test
    void testAllIdenticalCharacters() {
        String str = "aaa";
        List<String> expected = List.of("aaa");
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
    }

    @Test
    void testMixedCaseCharacters() {
        String str = "aAb";
        List<String> expected = List.of("AaB", "AbA", "aAB", "aBA", "baA", "bAa");
        List<String> result = UniquePermutations.getUniquePermutations(str);
        Collections.sort(expected); // Ensure expected is sorted
        Collections.sort(result); // Ensure result is sorted
        assertEquals(expected, result);
    }

    @Test
    void testNullInput() {
        String str = null;
        List<String> result = UniquePermutations.getUniquePermutations(str);
        assertEquals(0, result.size()); // Expect an empty list for null input
    }
}
