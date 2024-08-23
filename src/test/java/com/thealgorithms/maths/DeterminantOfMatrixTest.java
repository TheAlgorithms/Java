package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeterminantOfMatrixTest {

    @Test
    public void testDeterminant2x2Matrix() {
        int[][] matrix = {{1, 2}, {3, 4}};
        int expected = -2;
        assertEquals(expected, DeterminantOfMatrix.determinant(matrix, 2));
    }

    @Test
    public void testDeterminant3x3Matrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int expected = 0;
        assertEquals(expected, DeterminantOfMatrix.determinant(matrix, 3));
    }

    @Test
    public void testDeterminant3x3MatrixNonZero() {
        int[][] matrix = {{1, 2, 3}, {0, 1, 4}, {5, 6, 0}};
        int expected = 1;
        assertEquals(expected, DeterminantOfMatrix.determinant(matrix, 3));
    }

    @Test
    public void testDeterminant1x1Matrix() {
        int[][] matrix = {{7}};
        int expected = 7;
        assertEquals(expected, DeterminantOfMatrix.determinant(matrix, 1));
    }

    @Test
    public void testDeterminant4x4Matrix() {
        int[][] matrix = {{1, 0, 0, 1}, {0, 1, 0, 0}, {0, 0, 1, 0}, {1, 0, 0, 1}};
        int expected = 0;
        assertEquals(expected, DeterminantOfMatrix.determinant(matrix, 4));
    }

    @Test
    public void testDeterminant4x4MatrixZero() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int expected = 0;
        assertEquals(expected, DeterminantOfMatrix.determinant(matrix, 4));
    }
}
