package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SaddlebackSearchTest {

    /**
     * Test searching for an element that exists in the array.
     */
    @Test
    void testFindElementExists() {
        int[][] arr = {{-10, -5, -3, 4, 9}, {-6, -2, 0, 5, 10}, {-4, -1, 1, 6, 12}, {2, 3, 7, 8, 13}, {100, 120, 130, 140, 150}};

        int[] result = SaddlebackSearch.find(arr, arr.length - 1, 0, 4);
        assertArrayEquals(new int[] {0, 3}, result, "Element 4 should be found at (0, 3)");
    }

    /**
     * Test searching for an element that does not exist in the array.
     */
    @Test
    void testFindElementNotExists() {
        int[][] arr = {{-10, -5, -3, 4, 9}, {-6, -2, 0, 5, 10}, {-4, -1, 1, 6, 12}, {2, 3, 7, 8, 13}, {100, 120, 130, 140, 150}};

        int[] result = SaddlebackSearch.find(arr, arr.length - 1, 0, 1000);
        assertArrayEquals(new int[] {-1, -1}, result, "Element 1000 should not be found");
    }

    /**
     * Test searching for the smallest element in the array.
     */
    @Test
    void testFindSmallestElement() {
        int[][] arr = {{-10, -5, -3, 4, 9}, {-6, -2, 0, 5, 10}, {-4, -1, 1, 6, 12}, {2, 3, 7, 8, 13}, {100, 120, 130, 140, 150}};

        int[] result = SaddlebackSearch.find(arr, arr.length - 1, 0, -10);
        assertArrayEquals(new int[] {0, 0}, result, "Element -10 should be found at (0, 0)");
    }

    /**
     * Test searching for the largest element in the array.
     */
    @Test
    void testFindLargestElement() {
        int[][] arr = {{-10, -5, -3, 4, 9}, {-6, -2, 0, 5, 10}, {-4, -1, 1, 6, 12}, {2, 3, 7, 8, 13}, {100, 120, 130, 140, 150}};

        int[] result = SaddlebackSearch.find(arr, arr.length - 1, 0, 150);
        assertArrayEquals(new int[] {4, 4}, result, "Element 150 should be found at (4, 4)");
    }

    /**
     * Test searching in an empty array.
     */
    @Test
    void testFindInEmptyArray() {
        int[][] arr = {};

        assertThrows(IllegalArgumentException.class, () -> { SaddlebackSearch.find(arr, 0, 0, 4); });
    }

    /**
     * Test searching in a single element array that matches the search key.
     */
    @Test
    void testFindSingleElementExists() {
        int[][] arr = {{5}};

        int[] result = SaddlebackSearch.find(arr, 0, 0, 5);
        assertArrayEquals(new int[] {0, 0}, result, "Element 5 should be found at (0, 0)");
    }

    /**
     * Test searching in a single element array that does not match the search key.
     */
    @Test
    void testFindSingleElementNotExists() {
        int[][] arr = {{5}};

        int[] result = SaddlebackSearch.find(arr, 0, 0, 10);
        assertArrayEquals(new int[] {-1, -1}, result, "Element 10 should not be found in single element array");
    }
}
