package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class LUDecompositionTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testBasicLUDecomposition() {
        double[][] matrix = {
            {2, -1, -2},
            {-4, 6, 3},
            {-4, -2, 8}
        };

        LUDecomposition.Result result = LUDecomposition.decompose(matrix);

        double[][] expectedL = {
            {1.0, 0.0, 0.0},
            {-2.0, 1.0, 0.0},
            {-2.0, -1.0, 1.0}
        };

        double[][] expectedU = {
            {2.0, -1.0, -2.0},
            {0.0, 4.0, -1.0},
            {0.0, 0.0, 3.0}
        };

        assertMatrixEquals(expectedL, result.getL());
        assertMatrixEquals(expectedU, result.getU());
    }

    @Test
    void testIdentityMatrix() {
        double[][] identity = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        LUDecomposition.Result result = LUDecomposition.decompose(identity);

        assertMatrixEquals(identity, result.getL());
        assertMatrixEquals(identity, result.getU());
    }

    @Test
    void testTwoByTwoMatrix() {
        double[][] matrix = {
            {4, 3},
            {6, 3}
        };

        LUDecomposition.Result result = LUDecomposition.decompose(matrix);

        double[][] expectedL = {
            {1.0, 0.0},
            {1.5, 1.0}
        };

        double[][] expectedU = {
            {4.0, 3.0},
            {0.0, -1.5}
        };

        assertMatrixEquals(expectedL, result.getL());
        assertMatrixEquals(expectedU, result.getU());
    }

    @Test
    void testNonSquareMatrix() {
        double[][] nonSquare = {
            {1, 2, 3},
            {4, 5, 6}
        };

        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(nonSquare));
    }

    @Test
    void testEmptyMatrix() {
        double[][] empty = {};

        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(empty));
    }

    @Test
    void testSingularMatrix() {
        double[][] singular = {
            {1, 2, 3},
            {2, 4, 6},
            {3, 6, 9}
        };

        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(singular));
    }

    private void assertMatrixEquals(double[][] expected, double[][] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], EPSILON);
        }
    }
}
