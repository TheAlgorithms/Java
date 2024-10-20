package com.thealgorithms.misc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class FourSumProblemTest {
    
    private final FourSumProblem fourSumProblem = new FourSumProblem();

    @Test
    public void testFourSum_WithValidInput() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-2, 0, 0, 2),
            Arrays.asList(-1, 0, 0, 1)
        );

        List<List<Integer>> result = fourSumProblem.fourSum(nums, target);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testFourSum_WithNoSolution() {
        int[] nums = {1, 2, 3, 4};
        int target = 100;
        List<List<Integer>> result = fourSumProblem.fourSum(nums, target);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFourSum_WithDuplicates() {
        int[] nums = {1, 1, 1, 1};
        int target = 4;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1, 1, 1, 1)
        );

        List<List<Integer>> result = fourSumProblem.fourSum(nums, target);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testFourSum_WithNegativeNumbers() {
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-3, 1, 1, 1),
            Arrays.asList(-2, 0, 0, 2),
            Arrays.asList(-1, 0, 0, 1)
        );

        List<List<Integer>> result = fourSumProblem.fourSum(nums, target);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testFourSum_WithEmptyArray() {
        int[] nums = {};
        int target = 0;
        List<List<Integer>> result = fourSumProblem.fourSum(nums, target);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFourSum_WithLessThanFourElements() {
        int[] nums = {1, 2, 3};
        int target = 6;
        List<List<Integer>> result = fourSumProblem.fourSum(nums, target);
        assertTrue(result.isEmpty());
    }
}
