package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class LUDecompositionTest {

    @Test
    public void testLUDecomposition() {
        double[][] A = {{4, 3}, {6, 3}};

        // Perform LU decomposition
        LUDecomposition.LU lu = LUDecomposition.decompose(A);
        double[][] L = lu.l;
        double[][] U = lu.u;

        // Reconstruct A from L and U
        double[][] reconstructed = multiplyMatrices(L, U);

        // Assert that reconstructed matrix matches original A
        for (int i = 0; i < A.length; i++) {
            assertArrayEquals(A[i], reconstructed[i], 1e-9);
        }
    }

    // Helper method to multiply two matrices
    private double[][] multiplyMatrices(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
