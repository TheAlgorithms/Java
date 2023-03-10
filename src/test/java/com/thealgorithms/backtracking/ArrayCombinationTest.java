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
        assertTrue(zeroResult == null);
        assertTrue(negativeResult == null);
    }

    @Test
    void testNoLengthElement() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(2, 0);
        assertTrue(result == null);
    }

    @Test
    void testLengthOne() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(2, 1);
        assertTrue(result.get(0).iterator().next() == 1);
        assertTrue(result.get(1).iterator().next() == 2);
    }

    @Test
    void testLengthTwo() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(2, 2);
        Integer[] arr = result.get(0).toArray(new Integer[2]);
        assertTrue(arr[0] == 1);
        assertTrue(arr[1] == 2);
    }

    @Test
    void testLengthFive() {
        List<TreeSet<Integer>> result = ArrayCombination.combination(10, 5);
        Integer[] arr = result.get(0).toArray(new Integer[5]);
        assertTrue(arr[0] == 1);
        assertTrue(arr[4] == 5);
    }
}
