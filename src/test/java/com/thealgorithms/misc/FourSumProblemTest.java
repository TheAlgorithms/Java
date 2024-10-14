package com.thealgorithms.misc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FourSumProblemTest {

    @Test
    public void testFourSum1_basicCase() {
        FourSumProblem solver = new FourSumProblem();

        // Case 1: Standard case with multiple quadruplets
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        List<List<Integer>> result1 = solver.fourSum1(nums1, target1);
        assertEquals(3, result1.size()); // Expect 3 quadruplets
        assertTrue(result1.contains(List.of(-2, -1, 1, 2)));
        assertTrue(result1.contains(List.of(-2, 0, 0, 2)));
        assertTrue(result1.contains(List.of(-1, 0, 0, 1)));
    }

    @Test
    public void testFourSum1_edgeCase_emptyArray() {
        FourSumProblem solver = new FourSumProblem();

        // Case 2: Empty array
        int[] nums2 = {};
        int target2 = 0;
        List<List<Integer>> result2 = solver.fourSum1(nums2, target2);
        assertEquals(0, result2.size()); // Expect no quadruplets
    }

    @Test
    public void testFourSum1_edgeCase_noQuadruplet() {
        FourSumProblem solver = new FourSumProblem();

        // Case 3: No valid quadruplets
        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 100;
        List<List<Integer>> result3 = solver.fourSum1(nums3, target3);
        assertEquals(0, result3.size()); // Expect no quadruplets
    }

    @Test
    public void testFourSum2_basicCase() {
        FourSumProblem solver = new FourSumProblem();

        // Case 4: Standard case with multiple quadruplets using HashMap approach
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        List<List<Integer>> result1 = solver.fourSum2(nums1, target1);
        assertEquals(3, result1.size()); // Expect 3 quadruplets
        assertTrue(result1.contains(List.of(-2, -1, 1, 2)));
        assertTrue(result1.contains(List.of(-2, 0, 0, 2)));
        assertTrue(result1.contains(List.of(-1, 0, 0, 1)));
    }

    @Test
    public void testFourSum2_edgeCase_emptyArray() {
        FourSumProblem solver = new FourSumProblem();

        // Case 5: Empty array
        int[] nums2 = {};
        int target2 = 0;
        List<List<Integer>> result2 = solver.fourSum2(nums2, target2);
        assertEquals(0, result2.size()); // Expect no quadruplets
    }

    @Test
    public void testFourSum2_edgeCase_noQuadruplet() {
        FourSumProblem solver = new FourSumProblem();

        // Case 6: No valid quadruplets
        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 100;
        List<List<Integer>> result3 = solver.fourSum2(nums3, target3);
        assertEquals(0, result3.size()); // Expect no quadruplets
    }

    @Test
    public void testFourSum2_singleQuadruplet() {
        FourSumProblem solver = new FourSumProblem();

        // Case 7: Single quadruplet in the array
        int[] nums4 = {2, 2, 2, 2, 2};
        int target4 = 8;
        List<List<Integer>> result4 = solver.fourSum2(nums4, target4);
        assertEquals(1, result4.size()); // Expect only one quadruplet
        assertTrue(result4.contains(List.of(2, 2, 2, 2)));
    }
}
