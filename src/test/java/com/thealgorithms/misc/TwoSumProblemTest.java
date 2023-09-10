package com.thealgorithms.misc;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Test case for Two sum Problem.
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class TwoSumProblemTest {
    private int[] nums;

    @BeforeEach
    void setUp() {
        // Initialize the test data before each test case
        nums = new int[]{2, 7, 11, 15};
    }

    @Test
    void testTwoSumExists() {
        int target = 9;
        int[] expected = {0, 1}; // nums[0] + nums[1] = 2 + 7 = 9
        int[] result = TwoSumProblem.twoSum(nums, target);
        assertArrayEquals(expected, result);
    }

    @Test
    void testTwoSumNoSolution() {
        int target = 3;
        int[] expected = {}; // No two elements sum up to 3
        int[] result = TwoSumProblem.twoSum(nums, target);
        assertArrayEquals(expected, result);
    }

    @Test
    void testTwoSumMultipleSolutions() {
        int[] nums = {3, 3};
        int target = 6;
        int[] expected = {0, 1}; // nums[0] + nums[1] = 3 + 3 = 6
        int[] result = TwoSumProblem.twoSum(nums, target);
        assertArrayEquals(expected, result);
    }

    @Test
    void testTwoSumNegativeNumbers() {
        int[] nums = {-1, -2, -3, -4, -5};
        int target = -8;
        int[] expected = {2, 4}; // nums[2] + nums[4] = -3 + (-5) = -8
        int[] result = TwoSumProblem.twoSum(nums, target);
        assertArrayEquals(expected, result);
    }
}
