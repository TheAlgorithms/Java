package com.thealgorithms.randomized;

import java.util.Random;

public final class RandomizedMatrixMultiplicationVerification {

    private RandomizedMatrixMultiplicationVerification() {
        // Prevent instantiation of utility class
    }

    /**
     * Verifies whether A × B == C using Freivalds' algorithm.
     * @param A Left matrix
     * @param B Right matrix
     * @param C Product matrix to verify
     * @param iterations Number of randomized checks
     * @return true if likely A×B == C; false if definitely not
     */
    public static boolean verify(int[][] a, int[][] b, int[][] c, int iterations) {
        int n = a.length;
        Random random = new Random();

        for (int iter = 0; iter < iterations; iter++) {
            // Step 1: Generate random 0/1 vector
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                r[i] = random.nextInt(2);
            }

            // Step 2: Compute br = b × r
            int[] br = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    br[i] += b[i][j] * r[j];
                }
            }

            // Step 3: Compute a(br)
            int[] abr = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    abr[i] += a[i][j] * br[j];
                }
            }

            // Step 4: Compute cr = c × r
            int[] cr = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cr[i] += c[i][j] * r[j];
                }
            }

            // Step 5: Compare abr and cr
            for (int i = 0; i < n; i++) {
                if (abr[i] != cr[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
