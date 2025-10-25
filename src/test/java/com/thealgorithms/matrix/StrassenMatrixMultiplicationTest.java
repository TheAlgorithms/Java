package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the StrassenMatrixMultiplication class.
 */
class StrassenMatrixMultiplicationTest {

    // Define some test matrices
    private static final double[][] MATRIX_2X2_A = {{1, 2}, {3, 4}};
    private static final double[][] MATRIX_2X2_B = {{5, 6}, {7, 8}};
    private static final double[][] EXPECTED_2X2_PRODUCT = {{19, 22}, {43, 50}};

    private static final double[][] MATRIX_4X4_A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    private static final double[][] MATRIX_4X4_B = {{5, 8, 1, 2}, {6, 7, 3, 0}, {4, 5, 9, 1}, {2, 6, 10, 14}};
    private static final double[][] EXPECTED_4X4_PRODUCT = {{37, 61, 74, 61}, {105, 165, 166, 129}, {173, 269, 258, 197}, {241, 373, 350, 265}};

    private static final double[][] MATRIX_3X3_A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private static final double[][] MATRIX_3X3_B = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
    private static final double[][] EXPECTED_3X3_PRODUCT = {{30, 24, 18}, {84, 69, 54}, {138, 114, 90}};

    private static final double[][] MATRIX_IDENTITY_2X2 = {{1, 0}, {0, 1}};
    private static final double[][] MATRIX_ZERO_2X2 = {{0, 0}, {0, 0}};

    private static final double[][] MATRIX_NON_SQUARE = {{1, 2, 3}, {4, 5, 6}};

    // Tolerance for floating-point comparisons
    private static final double DELTA = 1e-9;

    /**
     * Helper method to compare two matrices with tolerance.
     */
    private void assertMatrixEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length, "Number of rows differ");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], DELTA, "Row " + i + " differs");
        }
    }

    @Test
    void testMultiply2x2() {
        double[][] result = StrassenMatrixMultiplication.multiply(MATRIX_2X2_A, MATRIX_2X2_B);
        assertMatrixEquals(EXPECTED_2X2_PRODUCT, result);
    }

    @Test
    void testMultiply4x4() {
        double[][] result = StrassenMatrixMultiplication.multiply(MATRIX_4X4_A, MATRIX_4X4_B);
        assertMatrixEquals(EXPECTED_4X4_PRODUCT, result);
    }

    @Test
    void testMultiply3x3RequiresPadding() {
        // Strassen requires padding for non-power-of-2 dimensions
        double[][] result = StrassenMatrixMultiplication.multiply(MATRIX_3X3_A, MATRIX_3X3_B);
        assertMatrixEquals(EXPECTED_3X3_PRODUCT, result);
    }

    @Test
    void testMultiplyByIdentity() {
        double[][] result = StrassenMatrixMultiplication.multiply(MATRIX_2X2_A, MATRIX_IDENTITY_2X2);
        assertMatrixEquals(MATRIX_2X2_A, result);

        double[][] result2 = StrassenMatrixMultiplication.multiply(MATRIX_IDENTITY_2X2, MATRIX_2X2_A);
        assertMatrixEquals(MATRIX_2X2_A, result2);
    }

    @Test
    void testMultiplyByZero() {
        double[][] result = StrassenMatrixMultiplication.multiply(MATRIX_2X2_A, MATRIX_ZERO_2X2);
        assertMatrixEquals(MATRIX_ZERO_2X2, result);

        double[][] result2 = StrassenMatrixMultiplication.multiply(MATRIX_ZERO_2X2, MATRIX_2X2_A);
        assertMatrixEquals(MATRIX_ZERO_2X2, result2);
    }

    @Test
    void testMultiply1x1() {
        double[][] a = {{5.0}};
        double[][] b = {{6.0}};
        double[][] expected = {{30.0}};
        double[][] result = StrassenMatrixMultiplication.multiply(a, b);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> StrassenMatrixMultiplication.multiply(null, MATRIX_2X2_B), "Multiplying with null matrix A should throw exception");
        assertThrows(IllegalArgumentException.class, () -> StrassenMatrixMultiplication.multiply(MATRIX_2X2_A, null), "Multiplying with null matrix B should throw exception");
    }

    @Test
    void testNonSquareInput() {
        assertThrows(IllegalArgumentException.class, () -> StrassenMatrixMultiplication.multiply(MATRIX_NON_SQUARE, MATRIX_2X2_B), "Multiplying non-square matrix A should throw exception");
        assertThrows(IllegalArgumentException.class, () -> StrassenMatrixMultiplication.multiply(MATRIX_2X2_A, MATRIX_NON_SQUARE), "Multiplying non-square matrix B should throw exception");
    }

    @Test
    void testDifferentSquareDimensions() {
        assertThrows(IllegalArgumentException.class, () -> StrassenMatrixMultiplication.multiply(MATRIX_2X2_A, MATRIX_3X3_A), "Multiplying matrices of different square dimensions should throw exception");
    }

    @Test
    void testEmptyMatrix() {
        double[][] empty = {};
        double[][] result = StrassenMatrixMultiplication.multiply(empty, empty);
        assertEquals(0, result.length, "Multiplying empty matrices should result in an empty matrix");

        double[][] emptyRows = {{}};
        assertThrows(IllegalArgumentException.class, // Or handle as empty depending on strictness
            () -> StrassenMatrixMultiplication.multiply(emptyRows, emptyRows), "Multiplying matrices with zero columns might throw or return empty");
    }
}