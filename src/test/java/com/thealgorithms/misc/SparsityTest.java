package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SparsityTest {

    private static final double DELTA = 1e-9;

    @Test
    public void testAllZeroElements() {
        double[][] mat = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double expectedSparsity = 1.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of a matrix with all zero elements should be 1.0");
    }

    @Test
    public void testNoZeroElements() {
        double[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double expectedSparsity = 0.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of a matrix with no zero elements should be 0.0");
    }

    @Test
    public void testMixedElements() {
        double[][] mat = {{0, 2, 0}, {4, 0, 6}, {0, 8, 0}};
        double expectedSparsity = 5.0 / 9.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of the matrix should be 5/9");
    }

    @Test
    public void testSingleRowMatrix() {
        double[][] mat = {{0, 1, 0, 2, 0}};
        double expectedSparsity = 3.0 / 5.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of the single-row matrix should be 3/5");
    }

    @Test
    public void testSingleColumnMatrix() {
        double[][] mat = {{1}, {0}, {0}, {2}};
        double expectedSparsity = 2.0 / 4.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of the single-column matrix should be 2/4");
    }

    @Test
    public void testEmptyMatrix() {
        double[][] mat = {};
        assertThrows(IllegalArgumentException.class, () -> Sparsity.sparsity(mat), "Sparsity of an empty matrix should throw an IllegalArgumentException");
    }

    @Test
    public void testMatrixWithSingleElementZero() {
        double[][] mat = {{0}};
        double expectedSparsity = 1.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of a matrix with a single zero element should be 1.0");
    }

    @Test
    public void testMatrixWithSingleElementNonZero() {
        double[][] mat = {{5}};
        double expectedSparsity = 0.0;
        assertEquals(expectedSparsity, Sparsity.sparsity(mat), DELTA, "Sparsity of a matrix with a single non-zero element should be 0.0");
    }
}
