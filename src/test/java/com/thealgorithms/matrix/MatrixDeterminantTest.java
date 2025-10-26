package com.thealgorithms.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixDeterminantTest {

    @Test
    void test2x2Matrix() {
        double[][] matrix = {{1, 2}, {3, 4}};
        assertEquals(-2, MatrixDeterminant.determinant(matrix), 1e-9);
    }

    @Test
    void test3x3Matrix() {
        double[][] matrix = {{2, 0, 1}, {3, 0, 0}, {5, 1, 1}};
        assertEquals(3, MatrixDeterminant.determinant(matrix), 1e-9);
    }

    @Test
    void test1x1Matrix() {
        double[][] matrix = {{5}};
        assertEquals(5, MatrixDeterminant.determinant(matrix), 1e-9);
    }

    @Test
    void testSingularMatrix() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertEquals(0, MatrixDeterminant.determinant(matrix), 1e-9);
    }

    @Test
    void testNonSquareMatrix() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        assertThrows(IllegalArgumentException.class, () -> MatrixDeterminant.determinant(matrix));
    }
}
