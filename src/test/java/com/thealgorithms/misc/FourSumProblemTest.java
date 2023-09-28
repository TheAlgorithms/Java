package com.thealgorithms.misc;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for The Four Sum Problem.
 * @author mouss3abbb
 */

public class FourSumProblemTest {

    @Test
    void testFourSumExists() {
        final var values = new ArrayList<>(List.of(-2, 5, 0, 6));
        Collections.sort(values);
        final int target = 9;
        final var result = FourSumProblem.Hashing(values, target).get(0);
        Collections.sort(result);
        assertEquals(values, result);
    }

    @Test
    void testFourSumNoSolution() {
        final var values = new ArrayList<>(List.of(-2, 5, 0, 6));
        final int target = 3;
        assertTrue(FourSumProblem.Hashing(values, target).isEmpty());
    }

    @Test
    void testTwoSumMultipleSolutions() {
        final var values = new ArrayList<>(List.of(-2, 5, 0, 6,5,5,0,-1));
        final var solution1 = new ArrayList<>(List.of(-2, 5, 0, 6));
        final var solution2 = new ArrayList<>(List.of(5,5,0,-1));
        Collections.sort(solution1);
        Collections.sort(solution2);
        final int target = 9;
        final var result1 = FourSumProblem.Hashing(values,target).get(0);
        Collections.sort(result1);
        final var result2 = FourSumProblem.Hashing(values,target).get(1);
        Collections.sort(result2);
        assertEquals(solution1,result1);
        assertEquals(solution2,result2);
    }

    @Test
    void testTwoSumMultipleSolution() {
        final int[] values = {3, 4, 3, 3};
        final int target = 6;
        final var expected = Pair.of(0, 2); // values[0] + values[2] = 3 + 3 = 6
        assertEquals(expected, TwoSumProblem.twoSum(values, target).get());
    }
}
