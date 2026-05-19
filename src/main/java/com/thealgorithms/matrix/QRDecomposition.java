package com.thealgorithms.matrix;

/**
 * @brief Implementation of QR Decomposition using the Gram-Schmidt process
 * @details Decomposes a matrix A into an orthogonal matrix Q and an upper
 * triangular matrix R such that A = Q * R. The Gram-Schmidt process
 * orthogonalizes the columns of A to produce Q, and R is computed as Q^T * A.
 * This decomposition is useful for solving linear least squares problems,
 * eigenvalue computations, and numerical stability in linear algebra.
 * @see <a href="https://en.wikipedia.org/wiki/QR_decomposition">QR Decomposition</a>
 */
public final class QRDecomposition {

    private QRDecomposition() {
    }

    /**
     * A helper class to store both Q and R matrices
     */
    public static class QR {
        private final double[][] q;
        private final double[][] r;

        QR(double[][] q, double[][] r) {
            this.q = q;
            this.r = r;
        }

        public double[][] getQ() {
            return q;
        }

        public double[][] getR() {
            return r;
        }
    }

    /**
     * @brief Performs QR decomposition on a matrix using the Gram-Schmidt process
     * @param matrix the input matrix (m x n)
     * @return QR object containing orthogonal matrix Q (m x n) and upper triangular matrix R (n x n)
     * @throws IllegalArgumentException if the matrix is null, empty, or has invalid rows
     */
    public static QR decompose(double[][] matrix) {
        validateInputMatrix(matrix);

        int m = matrix.length;
        int n = matrix[0].length;

        double[][] q = new double[m][n];
        double[][] r = new double[n][n];

        for (int j = 0; j < n; j++) {
            double[] v = getColumn(matrix, j);

            for (int i = 0; i < j; i++) {
                double[] qi = getColumn(q, i);
                r[i][j] = dotProduct(qi, v);
                v = subtractVectors(v, scalarMultiply(qi, r[i][j]));
            }

            r[j][j] = norm(v);
            if (r[j][j] == 0) {
                throw new ArithmeticException("Matrix is rank deficient. Cannot perform QR decomposition.");
            }
            double[] qj = scalarMultiply(v, 1.0 / r[j][j]);
            setColumn(q, j, qj);
        }

        return new QR(q, r);
    }

    private static double[] getColumn(double[][] matrix, int col) {
        int m = matrix.length;
        double[] column = new double[m];
        for (int i = 0; i < m; i++) {
            column[i] = matrix[i][col];
        }
        return column;
    }

    private static void setColumn(double[][] matrix, int col, double[] column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = column[i];
        }
    }

    private static double dotProduct(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    private static double[] subtractVectors(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }

    private static double[] scalarMultiply(double[] v, double scalar) {
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i] * scalar;
        }
        return result;
    }

    private static double norm(double[] v) {
        return Math.sqrt(dotProduct(v, v));
    }

    private static void validateInputMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("The input matrix cannot be null");
        }
        if (matrix.length == 0) {
            throw new IllegalArgumentException("The input matrix cannot be empty");
        }
        if (!hasValidRows(matrix)) {
            throw new IllegalArgumentException("The input matrix cannot have null or empty rows");
        }
        if (isJaggedMatrix(matrix)) {
            throw new IllegalArgumentException("The input matrix cannot be jagged");
        }
    }

    private static boolean hasValidRows(double[][] matrix) {
        for (double[] row : matrix) {
            if (row == null || row.length == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isJaggedMatrix(double[][] matrix) {
        int numColumns = matrix[0].length;
        for (double[] row : matrix) {
            if (row.length != numColumns) {
                return true;
            }
        }
        return false;
    }
}
