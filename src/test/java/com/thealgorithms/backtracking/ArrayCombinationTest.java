package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayCombinationTest {

    @Test
    public void testCombination() {
        // Test case 1: n = 4, k = 2
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(1, 2));
        expected.add(List.of(1, 3));
        expected.add(List.of(1, 4));
        expected.add(List.of(2, 3));
        expected.add(List.of(2, 4));
        expected.add(List.of(3, 4));

        List<List<Integer>> actual = ArrayCombination.combination(4, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyCombination() {
        // Test case 2: n = 4, k = 0 (invalid input)
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> actual = ArrayCombination.combination(4, 0);

        assertEquals(expected, actual);
    }

    @Test
    public void testSingleElementCombinations() {
        // Test case 3: n = 3, k = 1
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(1));
        expected.add(List.of(2));
        expected.add(List.of(3));

        List<List<Integer>> actual = ArrayCombination.combination(3, 1);

        assertEquals(expected, actual);
    }
}
