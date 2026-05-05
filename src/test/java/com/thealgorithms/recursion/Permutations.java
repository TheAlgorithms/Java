package com.thealgorithms.recursion;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermutationsTest {

    // ─────────────────────────────────────────────
    // Null / Invalid Input Tests
    // ─────────────────────────────────────────────

    @Test
    void testNullArrayThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Permutations.permutations((Integer[]) null));
    }

    @Test
    void testArrayWithNullElementThrowsIllegalArgumentException() {
        Integer[] items = {1, null, 3};
        assertThrows(IllegalArgumentException.class, () -> Permutations.permutations(items));
    }

    // ─────────────────────────────────────────────
    // Edge Case Tests
    // ─────────────────────────────────────────────

    @Test
    void testEmptyArrayReturnsOneEmptyPermutation() {
        Integer[] items = {};
        List<List<Integer>> result = Permutations.permutations(items);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isEmpty());
    }

    @Test
    void testSingleElementReturnsOnePermutation() {
        Integer[] items = {42};
        List<List<Integer>> result = Permutations.permutations(items);
        assertEquals(1, result.size());
        assertEquals(List.of(42), result.get(0));
    }

    // ─────────────────────────────────────────────
    // Integer Permutation Tests
    // ─────────────────────────────────────────────

    @Test
    void testTwoIntegersReturnsTwoPermutations() {
        Integer[] items = {1, 2};
        List<List<Integer>> result = Permutations.permutations(items);
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(1, 2)));
        assertTrue(result.contains(List.of(2, 1)));
    }

    @Test
    void testThreeIntegersReturnsSixPermutations() {
        Integer[] items = {1, 2, 3};
        List<List<Integer>> result = Permutations.permutations(items);
        assertEquals(6, result.size());
    }

    @Test
    void testIntegerPermutationsContainAllExpectedOrders() {
        Integer[] items = {1, 2, 3};
        List<List<Integer>> result = Permutations.permutations(items);
        assertTrue(result.contains(List.of(1, 2, 3)));
        assertTrue(result.contains(List.of(1, 3, 2)));
        assertTrue(result.contains(List.of(2, 1, 3)));
        assertTrue(result.contains(List.of(2, 3, 1)));
        assertTrue(result.contains(List.of(3, 1, 2)));
        assertTrue(result.contains(List.of(3, 2, 1)));
    }

    // ─────────────────────────────────────────────
    // Duplicate Handling Tests
    // ─────────────────────────────────────────────

    @Test
    void testTwoDuplicateIntegersReturnsOnePermutation() {
        Integer[] items = {1, 1};
        List<List<Integer>> result = Permutations.permutations(items);
        assertEquals(1, result.size());
        assertEquals(List.of(1, 1), result.get(0));
    }

    @Test
    void testArrayWithDuplicatesReturnsCorrectCount() {
        Integer[] items = {1, 1, 2};
        List<List<Integer>> result = Permutations.permutations(items);
        // 3!/2! = 3 unique permutations
        assertEquals(3, result.size());
        assertTrue(result.contains(List.of(1, 1, 2)));
        assertTrue(result.contains(List.of(1, 2, 1)));
        assertTrue(result.contains(List.of(2, 1, 1)));
    }

    @Test
    void testAllDuplicatesReturnsOnePermutation() {
        Integer[] items = {5, 5, 5};
        List<List<Integer>> result = Permutations.permutations(items);
        assertEquals(1, result.size());
        assertEquals(List.of(5, 5, 5), result.get(0));
    }

    // ─────────────────────────────────────────────
    // String Permutation Tests
    // ─────────────────────────────────────────────

    @Test
    void testTwoStringsReturnsTwoPermutations() {
        String[] items = {"a", "b"};
        List<List<String>> result = Permutations.permutations(items);
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of("a", "b")));
        assertTrue(result.contains(List.of("b", "a")));
    }

    @Test
    void testThreeStringsReturnsSixPermutations() {
        String[] items = {"x", "y", "z"};
        List<List<String>> result = Permutations.permutations(items);
        assertEquals(6, result.size());
    }

    @Test
    void testDuplicateStringsReturnsCorrectCount() {
        String[] items = {"a", "a", "b"};
        List<List<String>> result = Permutations.permutations(items);
        assertEquals(3, result.size());
        assertTrue(result.contains(List.of("a", "a", "b")));
        assertTrue(result.contains(List.of("a", "b", "a")));
        assertTrue(result.contains(List.of("b", "a", "a")));
    }

    // ─────────────────────────────────────────────
    // Character Permutation Tests
    // ─────────────────────────────────────────────

    @Test
    void testCharacterPermutations() {
        Character[] items = {'a', 'b', 'c'};
        List<List<Character>> result = Permutations.permutations(items);
        assertEquals(6, result.size());
    }

    @Test
    void testDuplicateCharactersReturnsCorrectCount() {
        Character[] items = {'a', 'a', 'b'};
        List<List<Character>> result = Permutations.permutations(items);
        assertEquals(3, result.size());
    }
}
