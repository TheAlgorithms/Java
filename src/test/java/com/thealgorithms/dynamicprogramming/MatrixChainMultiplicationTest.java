package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class MatrixChainMultiplicationTest {

    @Test
    void testMatrixCreation() {
        MatrixChainMultiplication.Matrix matrix1 = new MatrixChainMultiplication.Matrix(1, 10, 20);
        MatrixChainMultiplication.Matrix matrix2 = new MatrixChainMultiplication.Matrix(2, 20, 30);

        assertEquals(1, matrix1.count());
        assertEquals(10, matrix1.col());
        assertEquals(20, matrix1.row());

        assertEquals(2, matrix2.count());
        assertEquals(20, matrix2.col());
        assertEquals(30, matrix2.row());
    }

    @Test
    void testMatrixChainOrder() {
        // Create a list of matrices to be multiplied
        ArrayList<MatrixChainMultiplication.Matrix> matrices = new ArrayList<>();
        matrices.add(new MatrixChainMultiplication.Matrix(1, 10, 20)); // A(1) = 10 x 20
        matrices.add(new MatrixChainMultiplication.Matrix(2, 20, 30)); // A(2) = 20 x 30

        // Calculate matrix chain order
        MatrixChainMultiplication.Result result = MatrixChainMultiplication.calculateMatrixChainOrder(matrices);

        // Expected cost of multiplying A(1) and A(2)
        int expectedCost = 6000; // The expected optimal cost of multiplying A(1)(10x20) and A(2)(20x30)
        int actualCost = result.getM()[1][2];

        assertEquals(expectedCost, actualCost);
    }

    @Test
    void testOptimalParentheses() {
        // Create a list of matrices to be multiplied
        ArrayList<MatrixChainMultiplication.Matrix> matrices = new ArrayList<>();
        matrices.add(new MatrixChainMultiplication.Matrix(1, 10, 20)); // A(1) = 10 x 20
        matrices.add(new MatrixChainMultiplication.Matrix(2, 20, 30)); // A(2) = 20 x 30

        // Calculate matrix chain order
        MatrixChainMultiplication.Result result = MatrixChainMultiplication.calculateMatrixChainOrder(matrices);

        // Check the optimal split for parentheses
        assertEquals(1, result.getS()[1][2]); // s[1][2] should point to the optimal split
    }
}
