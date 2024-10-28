package com.thealgorithms.shufflealgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public
class WeightedShuffleTest {

    @Test void testWeightedShuffleBasic() {
        int[] array = {10, 20, 30};
        int[] weights = {1, 3, 2};
        WeightedShuffle.weightedShuffle(array, weights);

        // Check the array is not in its original order (a simple shuffle check)
        Assertions.assertNotEquals(array[0], 10, "Array should be shuffled");
        Assertions.assertNotEquals(Arrays.toString(array),
                Arrays.toString(new int[]{10, 20, 30}),
                "Array should be shuffled");
    }

    // Test case for empty array
    @Test void testWeightedShuffleEmptyArray() {
        int[] array = {};
        int[] weights = {};
        WeightedShuffle.weightedShuffle(array, weights);
        Assertions.assertArrayEquals(new int[]{}, array);
    }

    // Test case for single element array
    @Test void testWeightedShuffleSingleElementArray() {
        int[] array = {5};
        int[] weights = {10};
        WeightedShuffle.weightedShuffle(array, weights);
        Assertions.assertArrayEquals(new int[]{5}, array);
    }

    // Test case for multiple elements with same weight
    @Test void testWeightedShuffleSameWeights() {
        int[] array = {1, 2, 3, 4};
        int[] weights = {1, 1, 1, 1}; // All elements have the same weight
        WeightedShuffle.weightedShuffle(array, weights);
        // The order should remain the same or be any permutation since weights are
        // equal
        boolean firstElementMatches =
                array[0] == 1 || array[0] == 2 || array[0] == 3 || array[0] == 4;
        Assertions.assertTrue(firstElementMatches);
    }

    // Test case for null array
    @Test void testWeightedShuffleNullArray() {
        int[] weights = {1, 2, 3};
        WeightedShuffle.weightedShuffle(null, weights);
        // Should not throw any exception, but we can't assert anything since input
        // is null
    }

    // Test case for null weights
    @Test void testWeightedShuffleNullWeights() {
        int[] array = {1, 2, 3};
        WeightedShuffle.weightedShuffle(array, null);
        // Should not throw any exception and array should remain unchanged
        Assertions.assertArrayEquals(new int[]{1, 2, 3}, array);
    }

    // Test case for different lengths of array and weights
    @Test void testWeightedShuffleDifferentLengths() {
        int[] array = {1, 2, 3};
        int[] weights = {1, 2}; // One less weight
        WeightedShuffle.weightedShuffle(array, weights);
        // Should not throw any exception, but we can't assert anything since input
        // is invalid
        Assertions.assertArrayEquals(
                new int[]{1, 2, 3},
                array); // Original array should remain unchanged
    }

    // Test case for all elements with zero weight
    @Test void testWeightedShuffleZeroWeights() {
        int[] array = {5, 10, 15};
        int[] weights = {0, 0, 0}; // All weights are zero
        WeightedShuffle.weightedShuffle(array, weights);
        // The order should remain the same or be any permutation since all weights
        // are equal
        boolean firstElementMatches =
                array[0] == 5 || array[0] == 10 || array[0] == 15;
        Assertions.assertTrue(firstElementMatches);
    }
}
