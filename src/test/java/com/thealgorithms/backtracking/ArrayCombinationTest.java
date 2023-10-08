package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class ArrayCombinationTest {

    @Test
    void testNBeingZeroOrLess() {
        List<TreeSet<Integer>> zeroResult = ArrayCombination.combination(0, 1);
        List<TreeSet<Integer>> negativeResult = ArrayCombination.combination(-1, 1);
        assertNull(zeroResult);
        assertNull(negativeResult);
    }

    @Test
    void testNoLengthElement() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(2, 0);
        assertNull(result);
    }

    @Test
    void testLengthOne() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(2, 1);
        assert result != null;
        assertEquals(1, result.get(0).iterator().next());
        assertEquals(2, result.get(1).iterator().next());
    }

    @Test
    void testLengthTwo() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(2, 2);
        assert result != null;
        Integer[] arr = result.get(0).toArray(new Integer[2]);
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
    }

    @Test
    void testLengthFive() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(10, 5);
        assert result != null;
        Integer[] arr = result.get(0).toArray(new Integer[5]);
        assertEquals(1, arr[0]);
        assertEquals(5, arr[4]);
    }
}
