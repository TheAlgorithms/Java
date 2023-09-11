package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * Test case for Two sum Problem.
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class TwoSumProblemTest {

    @Test
    void testTwoSumExists() {
        final int[] values = new int[] {2, 7, 11, 15};
        final int target = 9;
        final var expected = Pair.of(0, 1); // values[0] + values[1] = 2 + 7 = 9
        assertEquals(expected, TwoSumProblem.twoSum(values, target).get());
    }

    @Test
    void testTwoSumNoSolution() {
        final int[] values = new int[] {2, 7, 11, 15};
        final int target = 3;
        assertFalse(TwoSumProblem.twoSum(values, target).isPresent());
    }

    @Test
    void testTwoSumMultipleSolutions() {
        final int[] values = {3, 3};
        final int target = 6;
        final var expected = Pair.of(0, 1); // values[0] + values[1] = 3 + 3 = 6
        assertEquals(expected, TwoSumProblem.twoSum(values, target).get());
    }

    @Test
    void testTwoSumMultipleSolution() {
        final int[] values = {3, 4, 3, 3};
        final int target = 6;
        final var expected = Pair.of(0, 2); // values[0] + values[2] = 3 + 3 = 6
        assertEquals(expected, TwoSumProblem.twoSum(values, target).get());
    }

    @Test
    void testTwoSumNegativeNumbers() {
        final int[] values = {-1, -2, -3, -4, -5};
        final int target = -8;
        final var expected = Pair.of(2, 4); // values[2] + values[4] = -3 + (-5) = -8
        assertEquals(expected, TwoSumProblem.twoSum(values, target).get());
    }

    @Test
    void testTwoSumNoSolutionDuplicatedInputs() {
        final int[] values = {0, 0, 0};
        final int target = 100;
        assertFalse(TwoSumProblem.twoSum(values, target).isPresent());
    }
}
