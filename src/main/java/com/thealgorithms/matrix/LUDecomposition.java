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
package com.thealgorithms.matrix;

/**
 * LU Decomposition algorithm for square matrices
 * Decomposes a matrix A into L (lower triangular) and U (upper triangular)
 * such that A = L * U
 * 
 * Time Complexity: O(n^3)
 * Space Complexity: O(n^2)
 * 
 * @author Raghu0703
 * @see <a href="https://en.wikipedia.org/wiki/LU_decomposition">LU Decomposition</a>
 */
public final class LUDecomposition {
    private LUDecomposition() {
    }

    /**
     * Performs LU decomposition on a square matrix using Doolittle's method
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
                    throw new IllegalArgumentException("Matrix is singular or nearly singular");
                }
                
                l[i][j] = (matrix[i][j] - sum) / u[j][j];
            }
        }
        
        return new Result(l, u);
    }
    
    /**
     * Result class to hold L and U matrices from decomposition
     */
    public static class Result {
        private final double[][] lMatrix;
        private final double[][] uMatrix;
        
        public Result(double[][] l, double[][] u) {
            this.lMatrix = l;
            this.uMatrix = u;
        }
        
        public double[][] getL() {
            return lMatrix;
        }
        
        public double[][] getU() {
            return uMatrix;
        }
    }
}
