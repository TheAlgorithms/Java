package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive JUnit 5 tests for NearestElement algorithms.
 * These tests cover standard cases, edge cases, negative numbers, and exceptions.
 */
public class NearestElementTest {

    // --- Standard Test Cases ---

    @Test
    void testStandardInput() {
        int[] input = {4, 5, 2, 10, 8};
        
        // Nearest Greater to Right: For 4->5, 5->10, 2->10, 10->none, 8->none
        assertArrayEquals(new int[]{5, 10, 10, -1, -1}, NearestElement.nearestGreaterToRight(input));
        
        // Nearest Greater to Left: For 4->none, 5->none, 2->5, 10->none, 8->10
        assertArrayEquals(new int[]{-1, -1, 5, -1, 10}, NearestElement.nearestGreaterToLeft(input));
        
        // Nearest Smaller to Right: For 4->2, 5->2, 2->none, 10->8, 8->none
        assertArrayEquals(new int[]{2, 2, -1, 8, -1}, NearestElement.nearestSmallerToRight(input));
        
        // Nearest Smaller to Left: For 4->none, 5->4, 2->none, 10->2, 8->2
        assertArrayEquals(new int[]{-1, 4, -1, 2, 2}, NearestElement.nearestSmallerToLeft(input));
    }

    // --- Edge Cases: Empty and Single Element ---

    @Test
    void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        // An empty array should return an empty array for all variations[cite: 1065].
        assertArrayEquals(expected, NearestElement.nearestGreaterToRight(input));
        assertArrayEquals(expected, NearestElement.nearestSmallerToLeft(input));
    }

    @Test
    void testSingleElementArray() {
        int[] input = {10};
        int[] expected = {-1};
        // A single element has no neighbors, so it should always return the sentinel -1.
        assertArrayEquals(expected, NearestElement.nearestGreaterToRight(input));
        assertArrayEquals(expected, NearestElement.nearestSmallerToLeft(input));
    }

    // --- Edge Cases: Uniform and Monotonic Sequences ---

    @Test
    void testAllIdenticalElements() {
        int[] input = {5, 5, 5, 5};
        int[] expected = {-1, -1, -1, -1};
        // Since elements are equal, none are strictly greater or smaller.
        assertArrayEquals(expected, NearestElement.nearestGreaterToRight(input));
        assertArrayEquals(expected, NearestElement.nearestSmallerToRight(input));
    }

    @Test
    void testStrictlyIncreasing() {
        int[] input = {1, 2, 3, 4};
        // Greater to Right: each element has a successor
        assertArrayEquals(new int[]{2, 3, 4, -1}, NearestElement.nearestGreaterToRight(input));
        // Smaller to Right: no element has a smaller value to its right
        assertArrayEquals(new int[]{-1, -1, -1, -1}, NearestElement.nearestSmallerToRight(input));
    }

    @Test
    void testStrictlyDecreasing() {
        int[] input = {4, 3, 2, 1};
        // Smaller to Right: each element has a smaller successor
        assertArrayEquals(new int[]{3, 2, 1, -1}, NearestElement.nearestSmallerToRight(input));
        // Greater to Right: no element has a larger value to its right
        assertArrayEquals(new int[]{-1, -1, -1, -1}, NearestElement.nearestGreaterToRight(input));
    }

    // --- Negative Numbers and Large Values ---

    @Test
    void testNegativeNumbers() {
        int[] input = {-10, -5, -2, -8};
        // Nearest Greater to Right: -10 -> -5, -5 -> -2, -2 -> none, -8 -> none
        assertArrayEquals(new int[]{-5, -2, -1, -1}, NearestElement.nearestGreaterToRight(input));
        // Nearest Smaller to Left: -10 -> none, -5 -> -10, -2 -> -5, -8 -> -10
        assertArrayEquals(new int[]{-1, -10, -5, -10}, NearestElement.nearestSmallerToLeft(input));
    }

    @Test
    void testExtremeValues() {
        int[] input = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};
        // Smaller to Right: MAX -> 0, 0 -> MIN, MIN -> none
        assertArrayEquals(new int[]{0, Integer.MIN_VALUE, -1}, NearestElement.nearestSmallerToRight(input));
        // Greater to Left: MAX -> none, 0 -> MAX, MIN -> 0
        assertArrayEquals(new int[]{-1, Integer.MAX_VALUE, 0}, NearestElement.nearestGreaterToLeft(input));
    }

    // --- Exception Handling ---

    @Test
    void testNullInput() {
        // Verifies that an IllegalArgumentException is thrown for null input[cite: 1065, 1068].
        assertThrows(IllegalArgumentException.class, () -> {
            NearestElement.nearestGreaterToRight(null);
        });
    }
}