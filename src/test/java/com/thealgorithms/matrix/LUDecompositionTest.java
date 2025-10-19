package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class LUDecompositionTest {

    @Test
    public void testLUDecomposition() {
        double[][] a = {{4, 3}, {6, 3}};

        // Perform LU decomposition
        LUDecomposition.LU lu = LUDecomposition.decompose(a);
        double[][] l = lu.l;
        double[][] u = lu.u;

        // Reconstruct a from l and u
        double[][] reconstructed = multiplyMatrices(l, u);

        // Assert that reconstructed matrix matches original a
        for (int i = 0; i < a.length; i++) {
            assertArrayEquals(a[i], reconstructed[i], 1e-9);
        }
    }

    // Helper method to multiply two matrices
    private double[][] multiplyMatrices(double[][] a, double[][] b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
