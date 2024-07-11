package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class CombinationTest {

    @Test
    void testNoElement() {
        List<TreeSet<Integer>> result = Combination.combination(new Integer[] {1, 2}, 0);
        assertNull(result);
    }

    @Test
    void testLengthOne() {
        List<TreeSet<Integer>> result = Combination.combination(new Integer[] {1, 2}, 1);
        assertEquals(Arrays.asList(new TreeSet<>(Arrays.asList(1)), new TreeSet<>(Arrays.asList(2))), result);
    }

    @Test
    void testLengthTwo() {
        List<TreeSet<Integer>> result = Combination.combination(new Integer[] {1, 2}, 2);
        assert result != null;
        Integer[] arr = result.getFirst().toArray(new Integer[2]);
        assertEquals(1, (int) arr[0]);
        assertEquals(2, (int) arr[1]);
    }
}
