package com.thealgorithms.matrix;

/**
 * This class provides methods to compute the inverse of a square matrix
 * using Gaussian elimination. For more details, refer to:
 * https://en.wikipedia.org/wiki/Invertible_matrix
 */
public final class InverseOfMatrix {
    private InverseOfMatrix() {
    }

    public static double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];

        // Initialize the identity matrix
        for (int i = 0; i < n; ++i) {
            b[i][i] = 1;
        }

        // Perform Gaussian elimination
        gaussian(a, index);

        // Update matrix b with the ratios stored during elimination
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
                }
            }
        }

        // Perform backward substitution to find the inverse
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
    /**
     * Method to carry out the partial-pivoting Gaussian
     * elimination.  Here index[] stores pivoting order.
     **/
    private static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        // Initialize the index array
        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }

        // Find the rescaling factors for each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) {
                    c1 = c0;
                }
            }
            c[i] = c1;
        }

        // Perform pivoting
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            int k = j;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]) / c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Swap rows
            int temp = index[j];
            index[j] = index[k];
            index[k] = temp;

            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l) {
                    a[index[i]][l] -= pj * a[index[j]][l];
                }
            }
        }
    }
}
