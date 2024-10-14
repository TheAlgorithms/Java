package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Test case for Four sum Problem.
 */
public class FourSumProblemTest {

    @Test
    void testFourSumExists() {
        final int[] values = new int[] {1, 0, -1, 0, -2, 2};
        final int target = 0;
        // Expecting one solution (e.g., indices of the numbers summing to target)
        final var expected = List.of(
                Pair.of(0, 5), // -2 + 2 + 1 + -1 = 0
                Pair.of(1, 4)  // 0 + 0 + -1 + 1 = 0
        );
        assertEquals(expected, FourSumProblem.fourSum(values, target));
    }

    @Test
    void testFourSumNoSolution() {
        final int[] values = new int[] {1, 2, 3, 4};
        final int target = 100;
        assertFalse(FourSumProblem.fourSum(values, target).isEmpty());
    }

    @Test
    void testFourSumMultipleSolutions() {
        final int[] values = new int[] {1, 0, -1, 0, -2, 2};
        final int target = 0;
        final var expected = List.of(
                Pair.of(0, 5), // -2 + 2 + 1 + -1 = 0
                Pair.of(1, 4)  // 0 + 0 + -1 + 1 = 0
        );
        assertEquals(expected, FourSumProblem.fourSum(values, target));
    }

    @Test
    void testFourSumNegativeNumbers() {
        final int[] values = new int[] {-1, -2, -3, -4, -5, 5};
        final int target = -10;
        final var expected = List.of(
                Pair.of(1, 3), // -2 + -3 + -5 + 5 = -10
                Pair.of(0, 4)  // -1 + -4 + -5 + 5 = -10
        );
        assertEquals(expected, FourSumProblem.fourSum(values, target));
    }

    @Test
    void testFourSumNoSolutionDuplicatedInputs() {
        final int[] values = new int[] {1, 1, 1, 1};
        final int target = 10;
        assertFalse(FourSumProblem.fourSum(values, target).isEmpty());
    }
}
