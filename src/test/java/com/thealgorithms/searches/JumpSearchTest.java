package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the JumpSearch class.
 */
class JumpSearchTest {

    /**
     * Test for finding an element present in the array.
     */
    @Test
    void testJumpSearchFound() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 5; // Element to find
        assertEquals(5, jumpSearch.find(array, key), "The index of the found element should be 5.");
    }

    /**
     * Test for finding the first element in the array.
     */
    @Test
    void testJumpSearchFirstElement() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 0; // First element
        assertEquals(0, jumpSearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for finding the last element in the array.
     */
    @Test
    void testJumpSearchLastElement() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 10; // Last element
        assertEquals(10, jumpSearch.find(array, key), "The index of the last element should be 10.");
    }

    /**
     * Test for finding an element not present in the array.
     */
    @Test
    void testJumpSearchNotFound() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = -1; // Element not in the array
        assertEquals(-1, jumpSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for finding an element in an empty array.
     */
    @Test
    void testJumpSearchEmptyArray() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = {}; // Empty array
        Integer key = 1; // Key not present
        assertEquals(-1, jumpSearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for finding an element in a large array.
     */
    @Test
    void testJumpSearchLargeArray() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2; // Fill the array with even numbers
        }
        Integer key = 256; // Present in the array
        assertEquals(128, jumpSearch.find(array, key), "The index of the found element should be 128.");
    }

    /**
     * Test for finding an element in a large array when it is not present.
     */
    @Test
    void testJumpSearchLargeArrayNotFound() {
        JumpSearch jumpSearch = new JumpSearch();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2; // Fill the array with even numbers
        }
        Integer key = 999; // Key not present
        assertEquals(-1, jumpSearch.find(array, key), "The element should not be found in the array.");
    }
}
