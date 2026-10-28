package com.thealgorithms.matrix;

/**
 * LU Decomposition algorithm
 * --------------------------
 * Decomposes a square matrix a into a product of two matrices:
 * a = l * u
 * where:
 * - l is a lower triangular matrix with 1s on its diagonal
 * - u is an upper triangular matrix
 *
 * Reference:
 * https://en.wikipedia.org/wiki/lu_decomposition
 */
public final class LUDecomposition {

    private LUDecomposition() {
    }

    /**
     * A helper class to store both l and u matrices
     */
    public static class LU {
        double[][] l;
        double[][] u;

        LU(double[][] l, double[][] u) {
            this.l = l;
            this.u = u;
        }
    }

    /**
     * Performs LU Decomposition on a square matrix a
     *
     * @param a input square matrix
     * @return LU object containing l and u matrices
     */
    public static LU decompose(double[][] a) {
        int n = a.length;
        double[][] l = new double[n][n];
        double[][] u = new double[n][n];

        for (int i = 0; i < n; i++) {
            // upper triangular matrix
            for (int k = i; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += l[i][j] * u[j][k];
                }
                u[i][k] = a[i][k] - sum;
            }

            // lower triangular matrix
            for (int k = i; k < n; k++) {
                if (i == k) {
                    l[i][i] = 1; // diagonal as 1
                } else {
                    double sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += l[k][j] * u[j][i];
                    }
                    l[k][i] = (a[k][i] - sum) / u[i][i];
                }
            }
        }

        return new LU(l, u);
    }

    /**
     * Utility function to print a matrix
     *
     * @param m matrix to print
     */
    public static void printMatrix(double[][] m) {
        for (double[] row : m) {
            System.out.print("[");
            for (int j = 0; j < row.length; j++) {
                System.out.printf("%7.3f", row[j]);
                if (j < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
}
