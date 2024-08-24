package com.thealgorithms.maths;

/*
 * @author Ojasva Jain
 * Determinant of a Matrix Wikipedia link: https://en.wikipedia.org/wiki/Determinant
 */
public final class DeterminantOfMatrix {
    private DeterminantOfMatrix() {
    }

    /**
     * Calculates the determinant of a given matrix.
     *
     * @param a the input matrix
     * @param n the size of the matrix
     * @return the determinant of the matrix
     */
    static int determinant(int[][] a, int n) {
        int det = 0;
        int sign = 1;
        int p = 0;
        int q = 0;
        if (n == 1) {
            det = a[0][0];
        } else {
            int[][] b = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }
}
