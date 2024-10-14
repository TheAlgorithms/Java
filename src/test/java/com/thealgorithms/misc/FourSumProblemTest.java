package com.thealgorithms.misc;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FourSumProblemTest {
    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int target = 10;
        List<List<Integer>> result = FourSumProblem.fourSumProblem(arr, target);
        assertTrue(result.isEmpty(), "Expected no quadruplets for an empty array");
    }

    @Test
    public void testLessThanFourElements() {
        int[] arr = {1, 2, 3};
        int target = 6;
        List<List<Integer>> result = FourSumProblem.fourSumProblem(arr, target);
        assertTrue(result.isEmpty(), "Expected no quadruplets when array has less than 4 elements");
    }

    @Test
    public void testNoValidQuadruplet() {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 100;
        List<List<Integer>> result = FourSumProblem.fourSumProblem(arr, target);
        assertTrue(result.isEmpty(), "Expected no quadruplets when no combination matches the target");
    }

    @Test
    public void testValidQuadruplet() {
        int[] arr = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = FourSumProblem.fourSumProblem(arr, target);

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1));
        assertEquals(expected.size(), result.size(), "Expected 3 valid quadruplets");
        assertTrue(result.containsAll(expected), "The result should contain all expected quadruplets");
    }

    @Test
    public void testWithDuplicates() {
        int[] arr = {2, 2, 2, 2, 2};
        int target = 8;
        List<List<Integer>> result = FourSumProblem.fourSumProblem(arr, target);

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(2, 2, 2, 2));
        assertEquals(expected.size(), result.size(), "Expected 1 valid quadruplet for repeated numbers");
        assertTrue(result.containsAll(expected), "The result should contain the quadruplet [2, 2, 2, 2]");
    }

    @Test
    public void testNegativeNumbers() {
        int[] arr = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int target = 0;
        List<List<Integer>> result = FourSumProblem.fourSumProblem(arr, target);

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-5, -4, 4, 5), Arrays.asList(-5, -3, 3, 5), Arrays.asList(-5, -2, 2, 5), Arrays.asList(-5, -2, 3, 4), Arrays.asList(-5, -1, 1, 5), Arrays.asList(-5, -1, 2, 4), Arrays.asList(-5, 0, 1, 4), Arrays.asList(-5, 0, 2, 3),
            Arrays.asList(-4, -3, 2, 5), Arrays.asList(-4, -3, 3, 4), Arrays.asList(-4, -2, 1, 5), Arrays.asList(-4, -2, 2, 4), Arrays.asList(-4, -1, 0, 5), Arrays.asList(-4, -1, 1, 4), Arrays.asList(-4, -1, 2, 3), Arrays.asList(-4, 0, 1, 3), Arrays.asList(-3, -2, 0, 5), Arrays.asList(-3, -2, 1, 4),
            Arrays.asList(-3, -2, 2, 3), Arrays.asList(-3, -1, 0, 4), Arrays.asList(-3, -1, 1, 3), Arrays.asList(-3, 0, 1, 2), Arrays.asList(-2, -1, 0, 3), Arrays.asList(-2, -1, 1, 2));

        assertEquals(expected.size(), result.size(), "Expected 24 valid quadruplets for negative and positive numbers");
        assertTrue(result.containsAll(expected), "The result should contain all expected quadruplets");
    }
}
