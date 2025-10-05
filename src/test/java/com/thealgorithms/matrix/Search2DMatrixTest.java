package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Search2DMatrixTest {

    @Test
    public void testSearchMatrixSortedRowsAndCols() {
        int[][] matrix = {
            {1, 4, 7, 11},
            {2, 5, 8, 12},
            {3, 6, 9, 16},
            {10, 13, 14, 17}
        };
        assertTrue(Search2DMatrix.searchMatrixSortedRowsAndCols(matrix, 5));
        assertFalse(Search2DMatrix.searchMatrixSortedRowsAndCols(matrix, 20));
    }

    @Test
    public void testSearchMatrixFlattenedSorted() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };
        assertTrue(Search2DMatrix.searchMatrixFlattenedSorted(matrix, 3));
        assertFalse(Search2DMatrix.searchMatrixFlattenedSorted(matrix, 4));
    }
}
