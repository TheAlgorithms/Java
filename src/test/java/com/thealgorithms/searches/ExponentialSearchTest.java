package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ExponentialSearch class.
 */
class ExponentialSearchTest {

    /**
     * Test for basic exponential search functionality.
     */
    @Test
    void testExponentialSearchFound() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int key = 7;
        int expectedIndex = 6; // Index of the key in the array
        assertEquals(expectedIndex, exponentialSearch.find(array, key), "The index of the found element should be 6.");
    }

    /**
     * Test for exponential search with the first element as the key.
     */
    @Test
    void testExponentialSearchFirstElement() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {1, 2, 3, 4, 5};
        int key = 1; // First element
        int expectedIndex = 0; // Index of the key in the array
        assertEquals(expectedIndex, exponentialSearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for exponential search with the last element as the key.
     */
    @Test
    void testExponentialSearchLastElement() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {1, 2, 3, 4, 5};
        int key = 5; // Last element
        int expectedIndex = 4; // Index of the key in the array
        assertEquals(expectedIndex, exponentialSearch.find(array, key), "The index of the last element should be 4.");
    }

    /**
     * Test for exponential search with a single element present.
     */
    @Test
    void testExponentialSearchSingleElementFound() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {1};
        int key = 1; // Only element present
        int expectedIndex = 0; // Index of the key in the array
        assertEquals(expectedIndex, exponentialSearch.find(array, key), "The index of the single element should be 0.");
    }

    /**
     * Test for exponential search with an empty array.
     */
    @Test
    void testExponentialSearchEmptyArray() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {}; // Empty array
        int key = 1; // Key not present
        int expectedIndex = -1; // Key not found
        assertEquals(expectedIndex, exponentialSearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for exponential search on large array.
     */
    @Test
    void testExponentialSearchLargeArray() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = IntStream.range(0, 10000).boxed().toArray(Integer[] ::new); // Array from 0 to 9999
        int key = 9999;
        int expectedIndex = 9999;
        assertEquals(expectedIndex, exponentialSearch.find(array, key), "The index of the last element should be 9999.");
    }
}
