package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MatrixMultiplicationTest {

    private static final double EPSILON = 1e-9; // for floating point comparison

    @Test
    void testMultiply1by1() {
        double[][] matrixA = {{1.0}};
        double[][] matrixB = {{2.0}};
        double[][] expected = {{2.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testMultiply2by2() {
        double[][] matrixA = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] matrixB = {{5.0, 6.0}, {7.0, 8.0}};
        double[][] expected = {{19.0, 22.0}, {43.0, 50.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result); // Use custom method due to floating point issues
    }

    @Test
    void testMultiply3by2and2by1() {
        double[][] matrixA = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};
        double[][] matrixB = {{7.0}, {8.0}};
        double[][] expected = {{23.0}, {53.0}, {83.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testMultiplyNonRectangularMatrices() {
        double[][] matrixA = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double[][] matrixB = {{7.0, 8.0}, {9.0, 10.0}, {11.0, 12.0}};
        double[][] expected = {{58.0, 64.0}, {139.0, 154.0}};

        double[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testNullMatrixA() {
        double[][] b = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> MatrixMultiplication.multiply(null, b));
    }

    @Test
    void testNullMatrixB() {
        double[][] a = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> MatrixMultiplication.multiply(a, null));
    }

    @Test
    void testMultiplyNull() {
        double[][] matrixA = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] matrixB = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> MatrixMultiplication.multiply(matrixA, matrixB));

        String expectedMessage = "Input matrices cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testIncompatibleDimensions() {
        double[][] a = {{1.0, 2.0}};
        double[][] b = {{1.0, 2.0}};
        assertThrows(IllegalArgumentException.class, () -> MatrixMultiplication.multiply(a, b));
    }

    @Test
    void testEmptyMatrices() {
        double[][] a = new double[0][0];
        double[][] b = new double[0][0];
        assertThrows(IllegalArgumentException.class, () -> MatrixMultiplication.multiply(a, b));
    }

    private void assertMatrixEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length, "Column count mismatch at row " + i);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j], EPSILON, "Mismatch at (" + i + "," + j + ")");
            }
        }
    }
}
