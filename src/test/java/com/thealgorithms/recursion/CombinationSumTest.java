package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive test class for CombinationSum algorithm
 * Tests various scenarios including edge cases
 */
class CombinationSumTest {

    @Test
    void testBasicCase() {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = cs.combinationSum(candidates, target);
        
        assertTrue(result.contains(Arrays.asList(2, 2, 3)));
        assertTrue(result.contains(Arrays.asList(7)));
        assertEquals(2, result.size());
    }

    @Test
    void testMultipleCombinations() {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> result = cs.combinationSum(candidates, target);
        
        assertTrue(result.contains(Arrays.asList(2, 2, 2, 2)));
        assertTrue(result.contains(Arrays.asList(2, 3, 3)));
        assertTrue(result.contains(Arrays.asList(3, 5)));
        assertEquals(3, result.size());
    }

    @Test
    void testNoSolution() {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2};
        int target = 1;
        List<List<Integer>> result = cs.combinationSum(candidates, target);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleElement() {
        
        int[] candidates = {1};
        int target = 1;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        assertEquals(1, result.size());
        assertTrue(result.contains(Arrays.asList(1)));
    }

    @Test
    void testSingleElementRepeated() {
        
        int[] candidates = {2};
        int target = 8;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        assertEquals(1, result.size());
        assertTrue(result.contains(Arrays.asList(2, 2, 2, 2)));
    }

    @Test
    void testLargerNumbers() {
        
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        assertFalse(result.isEmpty());
        // Verify all combinations sum to target
        for (List<Integer> combination : result) {
            int sum = combination.stream().mapToInt(Integer::intValue).sum();
            assertEquals(target, sum);
        }
    }

    @Test
    void testTargetZero() {
        
        int[] candidates = {1, 2, 3};
        int target = 0;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        // Should return empty list in the combination
        assertEquals(1, result.size());
        assertTrue(result.get(0).isEmpty());
    }

    @Test
    void testEmptyCandidates() {
        
        int[] candidates = {};
        int target = 5;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void testLargeTarget() {
        int[] candidates = {3, 5, 8};
        int target = 11;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        assertTrue(result.contains(Arrays.asList(3, 3, 5)));
        assertTrue(result.contains(Arrays.asList(3, 8)));
        
        // Verify all combinations sum to target
        for (List<Integer> combination : result) {
            int sum = combination.stream().mapToInt(Integer::intValue).sum();
            assertEquals(target, sum);
        }
    }

    @Test
    void testAllCombinationsValid() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        
        // Verify each combination sums to target
        for (List<Integer> combination : result) {
            int sum = 0;
            for (int num : combination) {
                sum += num;
            }
            assertEquals(target, sum, "Each combination should sum to target");
        }
        
        // Verify no duplicates in result
        assertEquals(result.size(), result.stream().distinct().count());
    }
}
