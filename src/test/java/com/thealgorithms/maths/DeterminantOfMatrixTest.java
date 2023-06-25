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
        assertEquals(58.9, DeterminantOfMatrix.operation(matrix3, 3));

        double[][] matrix4 = { { -1.2, 2.3, -3.4, 4.5 }, { 5.6, -6.7, 7.8, -8.9 }, { -9.0, 10.1, -11.2, 12.3 }, { 13.4, -14.5, 15.6, -16.7 } };
        assertEquals(2549.1228, DeterminantOfMatrix.operation(matrix4, 4));

    }
}
