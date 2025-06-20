/**
 * Randomized algorithm to verify if A * B = C without computing full multiplication.
 * Returns true if probably correct, false if definitely incorrect.
 * Uses Freivalds' algorithm.
 */

package com.thealgorithms.matrix;

public class RandomizedMatrixVerifier {

    public static boolean verify(int[][] A, int[][] B, int[][] C) {
        int n = A.length;
        int[] r = new int[n];

        // Generate random vector r
        for (int i = 0; i < n; i++) {
            r[i] = (int) (Math.random() * 10); // keep it simple
        }

        // Compute B * r
        int[] Br = new int[n];
        for (int i = 0; i < n; i++) {
            Br[i] = 0;
            for (int j = 0; j < n; j++) {
                Br[i] += B[i][j] * r[j];
            }
        }

        // Compute A * (B * r)
        int[] ABr = new int[n];
        for (int i = 0; i < n; i++) {
            ABr[i] = 0;
            for (int j = 0; j < n; j++) {
                ABr[i] += A[i][j] * Br[j];
            }
        }

        // Compute C * r
        int[] Cr = new int[n];
        for (int i = 0; i < n; i++) {
            Cr[i] = 0;
            for (int j = 0; j < n; j++) {
                Cr[i] += C[i][j] * r[j];
            }
        }

        // Compare ABr and Cr
        for (int i = 0; i < n; i++) {
            if (ABr[i] != Cr[i]) return false;
        }

        return true; // probably correct
    }
}
