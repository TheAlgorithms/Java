package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SherLockAndCostTest {

    @Test
    public void testPositiveIntegers() {
        List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5);
        int result = SherLockAndCost.sherlockAndCostProblem(inputList);
        assertEquals(8, result);
    }

    @Test
    public void testNegativeInteger() {
        List<Integer> list = Arrays.asList(-1, -2, -3, -4, -5);
        int result = SherLockAndCost.sherlockAndCostProblem(list);
        assertEquals(0, result);
    }

    @Test
    public void testMixedElements() {
        List<Integer> list = Arrays.asList(-1, 1);
        int result = SherLockAndCost.sherlockAndCostProblem(list);
        assertEquals(0, result);
    }

    @Test
    public void testEdge() {
        List<Integer> list = Arrays.asList(-1, 2, 3, 4, 5);
        int result = SherLockAndCost.sherlockAndCostProblem(list);
        assertEquals(8, result);
    }

    @Test
    public void testNullElements() {
        List<Integer> list = Arrays.asList(null, null, null, null);
        assertThrows(NullPointerException.class, () -> SherLockAndCost.sherlockAndCostProblem(list));
    }

    @Test
    public void testEmptyList() {
        List<Integer> list = Arrays.asList();
        int result = SherLockAndCost.sherlockAndCostProblem(list);
        assertEquals(0, result);
    }

    @Test
    public void testSingleFList() {
        List<Integer> list = Arrays.asList(500);
        int result = SherLockAndCost.sherlockAndCostProblem(list);
        assertEquals(0, result);
    }
}
