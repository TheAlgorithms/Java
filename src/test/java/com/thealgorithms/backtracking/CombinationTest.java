package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class CombinationTest {

    @Test
    void testNegativeElement() {
        Integer[] array = {1, 2};
        assertThrows(IllegalArgumentException.class, () -> { Combination.combination(array, -1); });
    }

    @Test
    void testNoElement() {
        List<TreeSet<Integer>> result = Combination.combination(new Integer[] {1, 2}, 0);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testLengthOne() {
        List<TreeSet<Integer>> result = Combination.combination(new Integer[] {1, 2}, 1);
        assertTrue(result.get(0).iterator().next() == 1);
        assertTrue(result.get(1).iterator().next() == 2);
    }

    @Test
    void testLengthTwo() {
        List<TreeSet<Integer>> result = Combination.combination(new Integer[] {1, 2}, 2);
        Integer[] arr = result.get(0).toArray(new Integer[2]);
        assertTrue(arr[0] == 1);
        assertTrue(arr[1] == 2);
    }

    @Test
    void testCombinationsWithStrings() {
        List<TreeSet<String>> result = Combination.combination(new String[] {"a", "b", "c"}, 2);
        assertEquals(3, result.size());
        assertTrue(result.contains(new TreeSet<>(Set.of("a", "b"))));
        assertTrue(result.contains(new TreeSet<>(Set.of("a", "c"))));
        assertTrue(result.contains(new TreeSet<>(Set.of("b", "c"))));
    }
}
