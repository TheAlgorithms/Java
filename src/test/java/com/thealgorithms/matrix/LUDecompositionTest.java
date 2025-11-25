package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LUDecompositionTest {

    private static final double EPSILON = 1e-10;

    @Test
    void testBasicLUDecomposition() {
        double[][] matrix = {{2, -1, -2}, {-4, 6, 3}, {-4, -2, 8}};

        LUDecomposition.Result result = LUDecomposition.decompose(matrix);

        double[][] expectedL = {{1.0, 0.0, 0.0}, {-2.0, 1.0, 0.0}, {-2.0, -1.0, 1.0}};

        double[][] expectedU = {{2.0, -1.0, -2.0}, {0.0, 4.0, -1.0}, {0.0, 0.0, 3.0}};

        assertMatrixEquals(expectedL, result.getL());
        assertMatrixEquals(expectedU, result.getU());
    }

    @Test
    void testIdentityMatrix() {
        double[][] matrix = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        LUDecomposition.Result result = LUDecomposition.decompose(matrix);

        assertMatrixEquals(matrix, result.getL());
        assertMatrixEquals(matrix, result.getU());
    }

    @Test
    void testSingularMatrix() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(matrix));
    }

    @Test
    void testEmptyMatrix() {
        double[][] matrix = {};

        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(matrix));
    }

    @Test
    void testNonSquareMatrix() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}};

        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(matrix));
    }

    @Test
    void testTwoByTwoMatrix() {
        double[][] matrix = {{4, 3}, {6, 3}};

        LUDecomposition.Result result = LUDecomposition.decompose(matrix);

        double[][] expectedL = {{1.0, 0.0}, {1.5, 1.0}};

        double[][] expectedU = {{4.0, 3.0}, {0.0, -1.5}};

        assertMatrixEquals(expectedL, result.getL());
        assertMatrixEquals(expectedU, result.getU());
    }

    private void assertMatrixEquals(double[][] expected, double[][] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], EPSILON);
        }
    }
}
