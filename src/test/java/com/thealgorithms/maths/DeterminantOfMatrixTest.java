package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeterminantOfMatrixTest {

    @Test
    void testDeterminantOfMatrix() {
        double[][] matrix1 = { { 1, 0, 0}, {0, 1, 0}, {0, 0, 1} };
        assertEquals(1.0, DeterminantOfMatrix.operation(matrix1, 3));

        double[][] matrix2 = { { 2.0, 3.0 }, { -4.0, 5.0 } };
        assertEquals(22.0, DeterminantOfMatrix.operation(matrix2, 2));

        double[][] matrix3 = { { -1.0, 2.5, 3.0 }, { 4.0, -5.2, 6.0 }, { 7.3, -8.1, 9.6 } };
        assertEquals(31.50000000000001, DeterminantOfMatrix.operation(matrix3, 3));

        double[][] matrix4 = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 0, 1, 2}, {3, 4, 5, 6} };
        assertEquals(0.0, DeterminantOfMatrix.operation(matrix4, 4));

    }
}
