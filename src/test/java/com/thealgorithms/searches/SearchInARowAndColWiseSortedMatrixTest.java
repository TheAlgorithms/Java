package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SearchInARowAndColWiseSortedMatrixTest {

    private final SearchInARowAndColWiseSortedMatrix searcher = new SearchInARowAndColWiseSortedMatrix();

    @Test
    void testSearchValueExistsInMatrix() {
        int[][] matrix = {{10, 20, 30, 40}, {15, 25, 35, 45}, {27, 29, 37, 48}, {32, 33, 39, 50}};
        int value = 29;
        int[] expected = {2, 1}; // Row 2, Column 1
        assertArrayEquals(expected, searcher.search(matrix, value), "Value should be found in the matrix");
    }

    @Test
    void testSearchValueNotExistsInMatrix() {
        int[][] matrix = {{10, 20, 30, 40}, {15, 25, 35, 45}, {27, 29, 37, 48}, {32, 33, 39, 50}};
        int value = 100;
        int[] expected = {-1, -1}; // Not found
        assertArrayEquals(expected, searcher.search(matrix, value), "Value should not be found in the matrix");
    }

    @Test
    void testSearchInEmptyMatrix() {
        int[][] matrix = {};
        int value = 5;
        int[] expected = {-1, -1}; // Not found
        assertArrayEquals(expected, searcher.search(matrix, value), "Should return {-1, -1} for empty matrix");
    }

    @Test
    void testSearchInSingleElementMatrixFound() {
        int[][] matrix = {{5}};
        int value = 5;
        int[] expected = {0, 0}; // Found at (0,0)
        assertArrayEquals(expected, searcher.search(matrix, value), "Value should be found in single element matrix");
    }

    @Test
    void testSearchInSingleElementMatrixNotFound() {
        int[][] matrix = {{10}};
        int value = 5;
        int[] expected = {-1, -1}; // Not found
        assertArrayEquals(expected, searcher.search(matrix, value), "Should return {-1, -1} for value not found in single element matrix");
    }

    @Test
    void testSearchInRowWiseSortedMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int value = 6;
        int[] expected = {1, 2}; // Found at (1, 2)
        assertArrayEquals(expected, searcher.search(matrix, value), "Value should be found in the row-wise sorted matrix");
    }

    @Test
    void testSearchInColWiseSortedMatrix() {
        int[][] matrix = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int value = 5;
        int[] expected = {1, 1}; // Found at (1, 1)
        assertArrayEquals(expected, searcher.search(matrix, value), "Value should be found in the column-wise sorted matrix");
    }
}
