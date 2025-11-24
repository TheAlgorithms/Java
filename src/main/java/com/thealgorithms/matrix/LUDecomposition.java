package com.thealgorithms.matrix;

/**
 * LU Decomposition algorithm for square matrices.
 * Decomposes a matrix A into L (lower triangular) and U (upper triangular)
 * such that A = L * U
 *
 * <p>Time Complexity: O(n^3)
 * <p>Space Complexity: O(n^2)
 *
 * @author Raghu0703
 * @see <a href="https://en.wikipedia.org/wiki/LU_decomposition">LU Decomposition</a>
 */
public final class LUDecomposition {
    private LUDecomposition() {
    }

    /**
     * Performs LU decomposition on a square matrix using Doolittle's method.
     *
     * @param matrix The input square matrix
     * @return A Result object containing L and U matrices
     * @throws IllegalArgumentException if matrix is not square or singular
     */
    public static Result decompose(double[][] matrix) {
        int n = matrix.length;

        // Validate input
        if (n == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty");
        }
        for (double[] row : matrix) {
            if (row.length != n) {
                throw new IllegalArgumentException("Matrix must be square");
            }
        }

        double[][] l = new double[n][n];
        double[][] u = new double[n][n];

        // Initialize L with identity matrix
        for (int i = 0; i < n; i++) {
            l[i][i] = 1.0;
        }

        // Perform LU decomposition using Doolittle's method
        for (int j = 0; j < n; j++) {
            // Calculate U matrix elements
            for (int i = 0; i <= j; i++) {
                double sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += l[i][k] * u[k][j];
                }
                u[i][j] = matrix[i][j] - sum;
            }

            // Calculate L matrix elements
            for (int i = j + 1; i < n; i++) {
                double sum = 0.0;
                for (int k = 0; k < j; k++) {
                    sum += l[i][k] * u[k][j];
                }

                if (Math.abs(u[j][j]) < 1e-10) {
                    throw new IllegalArgumentException(
                        "Matrix is singular or nearly singular"
                    );
                }

                l[i][j] = (matrix[i][j] - sum) / u[j][j];
            }
        }

        return new Result(l, u);
    }

    /**
     * Main method for demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Example from the issue
        double[][] matrix = {
            {2, -1, -2},
            {-4, 6, 3},
            {-4, -2, 8}
        };

        System.out.println("LU Decomposition Example");
        System.out.println("========================\n");

        Result result = decompose(matrix);

        System.out.println("Original Matrix A:");
        printMatrix(matrix);

        System.out.println("\nLower Triangular Matrix L:");
        printMatrix(result.getL());

        System.out.println("\nUpper Triangular Matrix U:");
        printMatrix(result.getU());

        // Verify that L * U = A
        System.out.println("\nVerification: L * U = A");
        double[][] product = multiplyMatrices(result.getL(), result.getU());
        printMatrix(product);
    }

    /**
     * Helper method to print a matrix.
     *
     * @param matrix the matrix to print
     */
    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.print("{ ");
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%.3f", row[i]);
                if (i < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" }");
        }
    }

    /**
     * Helper method to multiply two matrices.
     *
     * @param a first matrix
     * @param b second matrix
     * @return the product matrix
     */
    private static double[][] multiplyMatrices(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Result class to hold L and U matrices from decomposition.
     */
    public static class Result {
        private final double[][] lMatrix;
        private final double[][] uMatrix;

        /**
         * Constructor for Result.
         *
         * @param l Lower triangular matrix
         * @param u Upper triangular matrix
         */
        public Result(double[][] l, double[][] u) {
            this.lMatrix = l;
            this.uMatrix = u;
        }

        /**
         * Gets the lower triangular matrix.
         *
         * @return L matrix
         */
        public double[][] getL() {
            return lMatrix;
        }

        /**
         * Gets the upper triangular matrix.
         *
         * @return U matrix
         */
        public double[][] getU() {
            return uMatrix;
        }
    }
}
