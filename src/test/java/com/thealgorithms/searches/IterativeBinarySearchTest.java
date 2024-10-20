package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the IterativeBinarySearch class.
 */
class IterativeBinarySearchTest {

    /**
     * Test for basic binary search functionality when the element is found.
     */
    @Test
    void testBinarySearchFound() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        Integer key = 128;
        int expectedIndex = 7; // Index of the key in the array
        assertEquals(expectedIndex, binarySearch.find(array, key), "The index of the found element should be 7.");
    }

    /**
     * Test for binary search when the element is not present in the array.
     */
    @Test
    void testBinarySearchNotFound() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {1, 2, 4, 8, 16};
        Integer key = 6; // Element not present in the array
        assertEquals(-1, binarySearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for binary search with the first element as the key.
     */
    @Test
    void testBinarySearchFirstElement() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {1, 2, 4, 8, 16};
        Integer key = 1; // First element
        assertEquals(0, binarySearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for binary search with the last element as the key.
     */
    @Test
    void testBinarySearchLastElement() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {1, 2, 4, 8, 16};
        Integer key = 16; // Last element
        assertEquals(4, binarySearch.find(array, key), "The index of the last element should be 4.");
    }

    /**
     * Test for binary search with a single element present.
     */
    @Test
    void testBinarySearchSingleElementFound() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {1};
        Integer key = 1; // Only element present
        assertEquals(0, binarySearch.find(array, key), "The index of the single element should be 0.");
    }

    /**
     * Test for binary search with a single element not present.
     */
    @Test
    void testBinarySearchSingleElementNotFound() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {1};
        Integer key = 2; // Key not present
        assertEquals(-1, binarySearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for binary search with an empty array.
     */
    @Test
    void testBinarySearchEmptyArray() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = {}; // Empty array
        Integer key = 1; // Key not present
        assertEquals(-1, binarySearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for binary search on a large array.
     */
    @Test
    void testBinarySearchLargeArray() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = new Integer[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        } // Array from 0 to 19998, step 2
        Integer key = 9998; // Present in the array
        assertEquals(4999, binarySearch.find(array, key), "The index of the found element should be 4999.");
    }

    /**
     * Test for binary search on large array with a non-existent key.
     */
    @Test
    void testBinarySearchLargeArrayNotFound() {
        IterativeBinarySearch binarySearch = new IterativeBinarySearch();
        Integer[] array = new Integer[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        } // Array from 0 to 19998, step 2
        Integer key = 9999; // Key not present
        assertEquals(-1, binarySearch.find(array, key), "The element should not be found in the array.");
    }
}
