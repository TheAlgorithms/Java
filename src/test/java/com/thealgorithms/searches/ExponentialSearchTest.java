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

    /**
     * An element sitting exactly on the doubling boundary used to be reported as missing, because
     * the binary search was handed {@code range} as its exclusive upper bound instead of
     * {@code range + 1}.
     */
    @Test
    void testExponentialSearchElementOnRangeBoundary() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {-25, -9, 8, 21};
        assertEquals(2, exponentialSearch.find(array, 8), "The index of the found element should be 2.");
    }

    /**
     * Every element must be found regardless of the array length.
     */
    @Test
    void testExponentialSearchFindsEveryElement() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        for (int length = 1; length <= 50; length++) {
            Integer[] array = new Integer[length];
            for (int i = 0; i < length; i++) {
                array[i] = i * 2;
            }
            for (int i = 0; i < length; i++) {
                assertEquals(i, exponentialSearch.find(array, i * 2), "Element at index " + i + " should be found for length " + length + ".");
            }
        }
    }

    /**
     * A missing key has to yield -1 rather than the negative insertion point that
     * {@link java.util.Arrays#binarySearch} returns.
     */
    @Test
    void testExponentialSearchNotFoundReturnsMinusOne() {
        ExponentialSearch exponentialSearch = new ExponentialSearch();
        Integer[] array = {1, 3, 5, 7, 9, 11};
        assertEquals(-1, exponentialSearch.find(array, 4), "A key inside the range but absent should give -1.");
        assertEquals(-1, exponentialSearch.find(array, 0), "A key below the minimum should give -1.");
        assertEquals(-1, exponentialSearch.find(array, 12), "A key above the maximum should give -1.");
    }
}
