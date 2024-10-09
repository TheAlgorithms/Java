package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the InterpolationSearch class.
 */
class InterpolationSearchTest {

    /**
     * Test for basic interpolation search functionality when the element is found.
     */
    @Test
    void testInterpolationSearchFound() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        int key = 128;
        int expectedIndex = 7; // Index of the key in the array
        assertEquals(expectedIndex, interpolationSearch.find(array, key), "The index of the found element should be 7.");
    }

    /**
     * Test for interpolation search when the element is not present in the array.
     */
    @Test
    void testInterpolationSearchNotFound() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = {1, 2, 4, 8, 16};
        int key = 6; // Element not present in the array
        assertEquals(-1, interpolationSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for interpolation search with the first element as the key.
     */
    @Test
    void testInterpolationSearchFirstElement() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = {1, 2, 4, 8, 16};
        int key = 1; // First element
        assertEquals(0, interpolationSearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for interpolation search with a single element not present.
     */
    @Test
    void testInterpolationSearchSingleElementNotFound() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = {1};
        int key = 2; // Key not present
        assertEquals(-1, interpolationSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for interpolation search with an empty array.
     */
    @Test
    void testInterpolationSearchEmptyArray() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = {}; // Empty array
        int key = 1; // Key not present
        assertEquals(-1, interpolationSearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for interpolation search on large uniformly distributed array.
     */
    @Test
    void testInterpolationSearchLargeUniformArray() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = IntStream.range(0, 10000).map(i -> i * 2).toArray(); // Array from 0 to 19998, step 2
        int key = 9998; // Last even number in the array
        assertEquals(4999, interpolationSearch.find(array, key), "The index of the last element should be 4999.");
    }

    /**
     * Test for interpolation search on large non-uniformly distributed array.
     */
    @Test
    void testInterpolationSearchLargeNonUniformArray() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int[] array = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144}; // Fibonacci numbers
        int key = 21; // Present in the array
        assertEquals(6, interpolationSearch.find(array, key), "The index of the found element should be 6.");
    }
}
