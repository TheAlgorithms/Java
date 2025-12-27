package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StochasticMatrixTest {

    @Test
    void testRowStochasticMatrix() {
        double[][] matrix = {{0.2, 0.5, 0.3}, {0.1, 0.6, 0.3}};
        assertTrue(StochasticMatrix.isRowStochastic(matrix));
        assertFalse(StochasticMatrix.isColumnStochastic(matrix));
    }

    @Test
    void testColumnStochasticMatrix() {
        double[][] matrix = {{0.4, 0.2}, {0.6, 0.8}};
        assertTrue(StochasticMatrix.isColumnStochastic(matrix));
    }

    @Test
    void testInvalidMatrix() {
        double[][] matrix = {{0.5, -0.5}, {0.5, 1.5}};
        assertFalse(StochasticMatrix.isRowStochastic(matrix));
    }
}
