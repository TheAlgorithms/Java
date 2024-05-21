package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}