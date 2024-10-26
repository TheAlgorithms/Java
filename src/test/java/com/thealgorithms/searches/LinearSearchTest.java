package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LinearSearch class.
 */
class LinearSearchTest {

    /**
     * Test for finding an element present in the array.
     */
    @Test
    void testLinearSearchFound() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 5; // Element to find
        assertEquals(5, linearSearch.find(array, key), "The index of the found element should be 5.");
    }

    /**
     * Test for finding the first element in the array.
     */
    @Test
    void testLinearSearchFirstElement() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 0; // First element
        assertEquals(0, linearSearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for finding the last element in the array.
     */
    @Test
    void testLinearSearchLastElement() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 10; // Last element
        assertEquals(10, linearSearch.find(array, key), "The index of the last element should be 10.");
    }

    /**
     * Test for finding an element not present in the array.
     */
    @Test
    void testLinearSearchNotFound() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = -1; // Element not in the array
        assertEquals(-1, linearSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for finding an element in an empty array.
     */
    @Test
    void testLinearSearchEmptyArray() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = {}; // Empty array
        Integer key = 1; // Key not present
        assertEquals(-1, linearSearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for finding an element in a large array.
     */
    @Test
    void testLinearSearchLargeArray() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill the array with integers 0 to 999
        }
        Integer key = 256; // Present in the array
        assertEquals(256, linearSearch.find(array, key), "The index of the found element should be 256.");
    }

    /**
     * Test for finding an element in a large array when it is not present.
     */
    @Test
    void testLinearSearchLargeArrayNotFound() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill the array with integers 0 to 999
        }
        Integer key = 1001; // Key not present
        assertEquals(-1, linearSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for finding multiple occurrences of the same element in the array.
     */
    @Test
    void testLinearSearchMultipleOccurrences() {
        LinearSearch linearSearch = new LinearSearch();
        Integer[] array = {1, 2, 3, 4, 5, 3, 6, 7, 3}; // 3 occurs multiple times
        Integer key = 3; // Key to find
        assertEquals(2, linearSearch.find(array, key), "The index of the first occurrence of the element should be 2.");
    }

    /**
     * Test for performance with random large array.
     */
    @Test
    void testLinearSearchRandomArray() {
        LinearSearch linearSearch = new LinearSearch();
        Random random = new Random();
        Integer[] array = random.ints(0, 1000).distinct().limit(1000).boxed().toArray(Integer[] ::new);
        Integer key = array[random.nextInt(array.length)]; // Key should be in the array
        assertEquals(java.util.Arrays.asList(array).indexOf(key), linearSearch.find(array, key), "The index of the found element should match.");
    }
}
