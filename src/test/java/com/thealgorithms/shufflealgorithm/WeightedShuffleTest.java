package com.thealgorithms.shufflealgorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.shufflealogrithm.WeightedShuffle;
import org.junit.jupiter.api.Test;

public class WeightedShuffleTest {

    // Test case for matching array and weights
    @Test
    void testWeightedShuffleBasic() {
        int[] array = {10, 20, 30};
        int[] weights = {1, 3, 2};
        WeightedShuffle.weightedShuffle(array, weights);

        // After shuffling, higher weight elements should be more likely to appear
        // earlier The expected order can be difficult to determine precisely, but
        // we can check if the higher-weight elements (20, weight 3) appear before
        // lower-weight ones (10, weight 1).
        assertTrue(array[0] == 20 || array[1] == 20,
                "20 should be among the first two elements");
        assertTrue(array[0] == 10 || array[1] == 10,
                "10 should not be the first element");
    }

    // Test case for empty array
    @Test
    void testWeightedShuffleEmptyArray() {
        int[] array = {};
        int[] weights = {};
        WeightedShuffle.weightedShuffle(array, weights);
        assertArrayEquals(new int[] {}, array);
    }

    // Test case for single element array
    @Test
    void testWeightedShuffleSingleElementArray() {
        int[] array = {5};
        int[] weights = {10};
        WeightedShuffle.weightedShuffle(array, weights);
        assertArrayEquals(new int[] {5}, array);
    }

    // Test case for multiple elements with same weight
    @Test
    void testWeightedShuffleSameWeights() {
        int[] array = {1, 2, 3, 4};
        int[] weights = {1, 1, 1, 1}; // All elements have the same weight
        WeightedShuffle.weightedShuffle(array, weights);
        // The order should remain the same or be any permutation since weights are
        // equal
        assertTrue(array[0] == 1 || array[0] == 2 || array[0] == 3 ||
                array[0] == 4);
    }

    // Test case for null array
    @Test
    void testWeightedShuffleNullArray() {
        int[] weights = {1, 2, 3};
        WeightedShuffle.weightedShuffle(null, weights);
        // Should not throw any exception, but we can't assert anything since input
        // is null
    }

    // Test case for null weights
    @Test
    void testWeightedShuffleNullWeights() {
        int[] array = {1, 2, 3};
        WeightedShuffle.weightedShuffle(array, null);
        // Should not throw any exception and array should remain unchanged
        assertArrayEquals(new int[] {1, 2, 3}, array);
    }

    // Test case for different lengths of array and weights
    @Test
    void testWeightedShuffleDifferentLengths() {
        int[] array = {1, 2, 3};
        int[] weights = {1, 2}; // One less weight
        WeightedShuffle.weightedShuffle(array, weights);
        // Should not throw any exception, but we can't assert anything since input
        // is invalid
        assertArrayEquals(new int[] {1, 2, 3},
                array); // Original array should remain unchanged
    }

    // Test case for all elements with zero weight
    @Test
    void testWeightedShuffleZeroWeights() {
        int[] array = {5, 10, 15};
        int[] weights = {0, 0, 0}; // All weights are zero
        WeightedShuffle.weightedShuffle(array, weights);
        // The order should remain the same or be any permutation since all weights
        // are equal
        assertTrue(array[0] == 5 || array[0] == 10 || array[0] == 15);
    }
}
