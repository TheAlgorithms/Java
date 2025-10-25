package com.thealgorithms.matrix;

/**
 * This class provides a method to perform matrix multiplication using
 * Strassen's algorithm.
 *
 * <p>
 * Strassen's algorithm is a divide-and-conquer algorithm that is
 * asymptotically faster than the standard O(n^3) matrix multiplication.
 * It performs 7 recursive multiplications of sub-matrices of size n/2
 * instead of the 8 required by the standard recursive method.
 *
 * <p>
 * For more details:
 * https://en.wikipedia.org/wiki/Strassen_algorithm
 *
 * <p>
 * Time Complexity: O(n^log2(7)) ≈ O(n^2.807)
 *
 * <p>
 * Space Complexity: O(n^2) – for storing intermediate and result matrices.
 *
 * <p>
 * Note: Due to the high overhead of recursion and sub-matrix creation in
 * Java, this algorithm is often slower than the standard O(n^3)
 * {@link MatrixMultiplication} for smaller matrices. A threshold is used
 * to switch to the standard algorithm for small matrices.
 *
 * @author @ITZ-NIHALPATEL
 *
 */
public final class StrassenMatrixMultiplication {

    /**
     * Threshold for matrix size to switch from Strassen's to standard
     * multiplication. Tuned by performance testing, 64 is a common value.
     */
    private static final int THRESHOLD = 64;

    private StrassenMatrixMultiplication() {
    }

    /**
     * Multiplies two matrices using Strassen's algorithm.
     *
     * @param matrixA the first matrix (must be square, n x n)
     * @param matrixB the second matrix (must be square, n x n)
     * @return the product of the two matrices
     * @throws IllegalArgumentException if matrices are not square, not the
     *                                  same size, or cannot be multiplied.
     */
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        // --- 1. VALIDATION ---
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("Input matrices cannot be null");
        }
        if (matrixA.length == 0 || (matrixA.length > 0 && matrixA[0].length == 0)) {
            return new double[0][0]; // Handle empty matrix
        }

        int n = matrixA.length;
        if (n != matrixA[0].length || n != matrixB.length || n != matrixB[0].length) {
            throw new IllegalArgumentException("Strassen's algorithm requires square matrices of the same dimension (n x n).");
        }

        // --- 2. PADDING ---
        // Find the next power of 2
        int nextPowerOf2 = Integer.highestOneBit(n);
        if (nextPowerOf2 < n) {
            nextPowerOf2 <<= 1;
        }

        // Pad matrices to the next power of 2
        double[][] paddedA = pad(matrixA, nextPowerOf2);
        double[][] paddedB = pad(matrixB, nextPowerOf2);

        // --- 3. RECURSION ---
        double[][] paddedResult = multiplyRecursive(paddedA, paddedB);

        // --- 4. UNPADDING ---
        // Extract the original n x n result from the padded result
        return unpad(paddedResult, n);
    }

    /**
     * Recursive helper function for Strassen's algorithm.
     * Assumes input matrices are square and their size is a power of 2.
     */
    private static double[][] multiplyRecursive(double[][] matrixA, double[][] matrixB) {
        int n = matrixA.length;

        // --- BASE CASE ---
        // If the matrix is small, switch to the standard O(n^3) algorithm
        if (n <= THRESHOLD) {
            return MatrixMultiplication.multiply(matrixA, matrixB);
        }

        // --- DIVIDE ---
        // Split matrices into four n/2 x n/2 sub-matrices
        int newSize = n / 2;
        double[][] a11 = split(matrixA, 0, 0, newSize);
        double[][] a12 = split(matrixA, 0, newSize, newSize);
        double[][] a21 = split(matrixA, newSize, 0, newSize);
        double[][] a22 = split(matrixA, newSize, newSize, newSize);

        double[][] b11 = split(matrixB, 0, 0, newSize);
        double[][] b12 = split(matrixB, 0, newSize, newSize);
        double[][] b21 = split(matrixB, newSize, 0, newSize);
        double[][] b22 = split(matrixB, newSize, newSize, newSize);

        // --- CONQUER (7 Recursive Calls) ---
        // P1 = A11 * (B12 - B22)
        double[][] p1 = multiplyRecursive(a11, subtract(b12, b22));
        // P2 = (A11 + A12) * B22
        double[][] p2 = multiplyRecursive(add(a11, a12), b22);
        // P3 = (A21 + A22) * B11
        double[][] p3 = multiplyRecursive(add(a21, a22), b11);
        // P4 = A22 * (B21 - B11)
        double[][] p4 = multiplyRecursive(a22, subtract(b21, b11));
        // P5 = (A11 + A22) * (B11 + B22)
        double[][] p5 = multiplyRecursive(add(a11, a22), add(b11, b22));
        // P6 = (A12 - A22) * (B21 + B22)
        double[][] p6 = multiplyRecursive(subtract(a12, a22), add(b21, b22));
        // P7 = (A11 - A21) * (B11 + B12)
        double[][] p7 = multiplyRecursive(subtract(a11, a21), add(b11, b12));

        // --- COMBINE (Calculate Result Quadrants) ---
        // C11 = P5 + P4 - P2 + P6
        double[][] c11 = add(subtract(add(p5, p4), p2), p6);
        // C12 = P1 + P2
        double[][] c12 = add(p1, p2);
        // C21 = P3 + P4
        double[][] c21 = add(p3, p4);
        // C22 = P5 + P1 - P3 - P7
        double[][] c22 = subtract(subtract(add(p5, p1), p3), p7);

        // Join the four result quadrants into a single matrix
        return join(c11, c12, c21, c22);
    }

    // --- HELPER METHODS ---
    /**
     * Adds two matrices.
     */
    private static double[][] add(double[][] matrixA, double[][] matrixB) {
        int n = matrixA.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    /**
     * Subtracts matrixB from matrixA.
     */
    private static double[][] subtract(double[][] matrixA, double[][] matrixB) {
        int n = matrixA.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        return result;
    }

    /**
     * Splits a parent matrix into a new sub-matrix.
     */
    private static double[][] split(double[][] matrix, int rowStart, int colStart, int size) {
        double[][] subMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i + rowStart], colStart, subMatrix[i], 0, size);
        }
        return subMatrix;
    }

    /**
     * Joins four sub-matrices into one larger matrix.
     */
    private static double[][] join(double[][] c11, double[][] c12, double[][] c21, double[][] c22) {
        int n = c11.length;
        int newSize = n * 2;
        double[][] result = new double[newSize][newSize];
        for (int i = 0; i < n; i++) {
            // C11
            System.arraycopy(c11[i], 0, result[i], 0, n);
            // C12
            System.arraycopy(c12[i], 0, result[i], n, n);
            // C21
            System.arraycopy(c21[i], 0, result[i + n], 0, n);
            // C22
            System.arraycopy(c22[i], 0, result[i + n], n, n);
        }
        return result;
    }

    /**
     * Pads a matrix with zeros to a new larger size.
     */
    private static double[][] pad(double[][] matrix, int size) {
        if (matrix.length == size) {
            return matrix; // No padding needed
        }
        int n = matrix.length;
        double[][] padded = new double[size][size];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, padded[i], 0, matrix[i].length);
        }
        return padded;
    }

    /**
     * Unpads a matrix to a new smaller size.
     */
    private static double[][] unpad(double[][] matrix, int size) {
        if (matrix.length == size) {
            return matrix; // No unpadding needed
        }
        double[][] unpadded = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i], 0, unpadded[i], 0, size);
        }
        return unpadded;
    }
}
