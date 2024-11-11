package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BinarySearch class.
 */
class BinarySearchTest {

    /**
     * Test for basic binary search functionality.
     */
    @Test
    void testBinarySearchFound() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int key = 7;
        int expectedIndex = 6; // Index of the key in the array
        assertEquals(expectedIndex, binarySearch.find(array, key), "The index of the found element should be 6.");
    }

    /**
     * Test for binary search when the element is not present.
     */
    @Test
    void testBinarySearchNotFound() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {1, 2, 3, 4, 5};
        int key = 6; // Element not present in the array
        int expectedIndex = -1; // Key not found
        assertEquals(expectedIndex, binarySearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for binary search with first element as the key.
     */
    @Test
    void testBinarySearchFirstElement() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {1, 2, 3, 4, 5};
        int key = 1; // First element
        int expectedIndex = 0; // Index of the key in the array
        assertEquals(expectedIndex, binarySearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for binary search with last element as the key.
     */
    @Test
    void testBinarySearchLastElement() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {1, 2, 3, 4, 5};
        int key = 5; // Last element
        int expectedIndex = 4; // Index of the key in the array
        assertEquals(expectedIndex, binarySearch.find(array, key), "The index of the last element should be 4.");
    }

    /**
     * Test for binary search with a single element present.
     */
    @Test
    void testBinarySearchSingleElementFound() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {1};
        int key = 1; // Only element present
        int expectedIndex = 0; // Index of the key in the array
        assertEquals(expectedIndex, binarySearch.find(array, key), "The index of the single element should be 0.");
    }

    /**
     * Test for binary search with a single element not present.
     */
    @Test
    void testBinarySearchSingleElementNotFound() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {1};
        int key = 2; // Key not present
        int expectedIndex = -1; // Key not found
        assertEquals(expectedIndex, binarySearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for binary search with an empty array.
     */
    @Test
    void testBinarySearchEmptyArray() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = {}; // Empty array
        int key = 1; // Key not present
        int expectedIndex = -1; // Key not found
        assertEquals(expectedIndex, binarySearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for binary search on large array.
     */
    @Test
    void testBinarySearchLargeArray() {
        BinarySearch binarySearch = new BinarySearch();
        Integer[] array = IntStream.range(0, 10000).boxed().toArray(Integer[] ::new); // Array from 0 to 9999
        int key = 9999; // Last element
        int expectedIndex = 9999; // Index of the last element
        assertEquals(expectedIndex, binarySearch.find(array, key), "The index of the last element should be 9999.");
    }
}
