package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThreeSumProblemTest {

    private ThreeSumProblem tsp;

    @BeforeEach
    public void setup() {
        tsp = new ThreeSumProblem(); // Initialize the class before each test
    }

    @Test
    public void testBruteForce_ValidTriplets() {
        int[] nums = {1, 2, -3, 4, -2, -1};
        int target = 0;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-3, 1, 2), Arrays.asList(-3, -1, 4));
        assertEquals(expected, tsp.bruteForce(nums, target));
    }

    @Test
    public void testBruteForce_NoTripletFound() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 50; // No valid triplet exists
        List<List<Integer>> expected = new ArrayList<>(); // Expecting an empty list
        assertEquals(expected, tsp.bruteForce(nums, target));
    }

    @Test
    public void testTwoPointer_ValidTriplets() {
        int[] nums = {0, -1, 2, -3, 1};
        int target = 0;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-3, 1, 2), Arrays.asList(-1, 0, 1));
        assertEquals(expected, tsp.twoPointer(nums, target));
    }

    @Test
    public void testTwoPointer_NegativeNumbers() {
        int[] nums = {-5, -4, -3, -2, -1};
        int target = -10;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-5, -4, -1), Arrays.asList(-5, -3, -2));
        assertEquals(expected, tsp.twoPointer(nums, target));
    }

    @Test
    public void testHashMap_ValidTriplets() {
        int[] nums = {1, 2, -1, -4, 3, 0};
        int target = 2;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-1, 0, 3), Arrays.asList(-1, 1, 2) // Check for distinct triplets
        );
        assertEquals(expected, tsp.hashMap(nums, target));
    }

    @Test
    public void testHashMap_NoTripletFound() {
        int[] nums = {5, 7, 9, 11};
        int target = 10;
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, tsp.hashMap(nums, target));
    }

    @Test
    public void testHashMap_EmptyArray() {
        int[] nums = {};
        int target = 0;
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, tsp.hashMap(nums, target));
    }
}
