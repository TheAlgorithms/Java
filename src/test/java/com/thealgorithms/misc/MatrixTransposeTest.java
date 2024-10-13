package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MatrixTransposeTest {

    @Test
    public void testTransposeSquareMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] expected = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        assertArrayEquals(expected, MatrixTranspose.transpose(matrix), "Transpose of the square matrix is incorrect.");
    }

    @Test
    public void testTransposeRectangularMatrix() {
        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};

        int[][] expected = {{1, 3, 5}, {2, 4, 6}};

        assertArrayEquals(expected, MatrixTranspose.transpose(matrix), "Transpose of the rectangular matrix is incorrect.");
    }

    @Test
    public void testTransposeSingleRowMatrix() {
        int[][] matrix = {{1, 2, 3}};

        int[][] expected = {{1}, {2}, {3}};

        assertArrayEquals(expected, MatrixTranspose.transpose(matrix), "Transpose of the single-row matrix is incorrect.");
    }

    @Test
    public void testTransposeSingleColumnMatrix() {
        int[][] matrix = {{1}, {2}, {3}};

        int[][] expected = {{1, 2, 3}};

        assertArrayEquals(expected, MatrixTranspose.transpose(matrix), "Transpose of the single-column matrix is incorrect.");
    }

    @Test
    public void testTransposeEmptyMatrix() {
        int[][] matrix = new int[0][0];

        assertThrows(IllegalArgumentException.class, () -> MatrixTranspose.transpose(matrix), "Expected IllegalArgumentException for empty matrix.");
    }

    @Test
    public void testTransposeNullMatrix() {
        int[][] matrix = null;
        assertThrows(IllegalArgumentException.class, () -> MatrixTranspose.transpose(matrix), "Expected IllegalArgumentException for null matrix.");
    }
}
