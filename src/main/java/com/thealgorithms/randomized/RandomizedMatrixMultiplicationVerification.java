package com.thealgorithms.randomized;

import java.util.Random;

public class RandomizedMatrixMultiplicationVerification {

    /**
     * Verifies whether A × B == C using Freivalds' algorithm.
     * @param A Left matrix
     * @param B Right matrix
     * @param C Product matrix to verify
     * @param iterations Number of randomized checks
     * @return true if likely A×B == C; false if definitely not
     */
    public static boolean verify(int[][] A, int[][] B, int[][] C, int iterations) {
        int n = A.length;
        Random random = new Random();

        for (int iter = 0; iter < iterations; iter++) {
            // Step 1: Generate random 0/1 vector
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                r[i] = random.nextInt(2);
            }

            // Step 2: Compute Br = B × r
            int[] Br = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Br[i] += B[i][j] * r[j];
                }
            }

            // Step 3: Compute A(Br)
            int[] ABr = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ABr[i] += A[i][j] * Br[j];
                }
            }

            // Step 4: Compute Cr = C × r
            int[] Cr = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Cr[i] += C[i][j] * r[j];
                }
            }

            // Step 5: Compare ABr and Cr
            for (int i = 0; i < n; i++) {
                if (ABr[i] != Cr[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
