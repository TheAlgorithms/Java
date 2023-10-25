package com.thealgorithms.maths;

/**
 * This class provides a method to compute the rank of a matrix.
 * In linear algebra, the rank of a matrix is the maximum number of linearly independent rows or columns in the matrix.
 * For example, consider the following 3x3 matrix:
 * 1 2 3
 * 2 4 6
 * 3 6 9
 * Despite having 3 rows and 3 columns, this matrix only has a rank of 1 because all rows (and columns) are multiples of each other.
 * It's a fundamental concept that gives key insights into the structure of the matrix.
 * It's important to note that the rank is not only defined for square matrices but for any m x n matrix.
 *
 * @author Anup Omkar
 */
public final class MatrixRank {

    private MatrixRank() {
    }

    private static final double EPSILON = 1e-10;

    /**
     * @brief Computes the rank of the input matrix
     *
     * @param matrix The input matrix
     * @return The rank of the input matrix
     */
    public static int computeRank(double[][] matrix) {
        validateInputMatrix(matrix);

        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        int rank = 0;

        boolean[] rowMarked = new boolean[numRows];

        double[][] matrixCopy = deepCopy(matrix);

        for (int colIndex = 0; colIndex < numColumns; ++colIndex) {
            int pivotRow = findPivotRow(matrixCopy, rowMarked, colIndex);
            if (pivotRow != numRows) {
                ++rank;
                rowMarked[pivotRow] = true;
                normalizePivotRow(matrixCopy, pivotRow, colIndex);
                eliminateRows(matrixCopy, pivotRow, colIndex);
            }
        }
        return rank;
    }

    private static boolean isZero(double value) {
        return Math.abs(value) < EPSILON;
    }

    private static double[][] deepCopy(double[][] matrix) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        double[][] matrixCopy = new double[numRows][numColumns];
        for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
            System.arraycopy(matrix[rowIndex], 0, matrixCopy[rowIndex], 0, numColumns);
        }
        return matrixCopy;
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

    /**
     * @brief Checks if the input matrix is a jagged matrix.
     * Jagged matrix is a matrix where the number of columns in each row is not the same.
     *
     * @param matrix The input matrix
     * @return True if the input matrix is a jagged matrix, false otherwise
     */
    private static boolean isJaggedMatrix(double[][] matrix) {
        int numColumns = matrix[0].length;
        for (double[] row : matrix) {
            if (row.length != numColumns) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief The pivot row is the row in the matrix that is used to eliminate other rows and reduce the matrix to its row echelon form.
     * The pivot row is selected as the first row (from top to bottom) where the value in the current column (the pivot column) is not zero.
     * This row is then used to "eliminate" other rows, by subtracting multiples of the pivot row from them, so that all other entries in the pivot column become zero.
     * This process is repeated for each column, each time selecting a new pivot row, until the matrix is in row echelon form.
     * The number of pivot rows (rows with a leading entry, or pivot) then gives the rank of the matrix.
     *
     * @param matrix The input matrix
     * @param rowMarked An array indicating which rows have been marked
     * @param colIndex The column index
     * @return The pivot row index, or the number of rows if no suitable pivot row was found
     */
    private static int findPivotRow(double[][] matrix, boolean[] rowMarked, int colIndex) {
        int numRows = matrix.length;
        for (int pivotRow = 0; pivotRow < numRows; ++pivotRow) {
            if (!rowMarked[pivotRow] && !isZero(matrix[pivotRow][colIndex])) {
                return pivotRow;
            }
        }
        return numRows;
    }

    /**
     * @brief This method divides all values in the pivot row by the value in the given column.
     * This ensures that the pivot value itself will be 1, which simplifies further calculations.
     *
     * @param matrix The input matrix
     * @param pivotRow The pivot row index
     * @param colIndex The column index
     */
    private static void normalizePivotRow(double[][] matrix, int pivotRow, int colIndex) {
        int numColumns = matrix[0].length;
        for (int nextCol = colIndex + 1; nextCol < numColumns; ++nextCol) {
            matrix[pivotRow][nextCol] /= matrix[pivotRow][colIndex];
        }
    }

    /**
     * @brief This method subtracts multiples of the pivot row from all other rows,
     * so that all values in the given column of other rows will be zero.
     * This is a key step in reducing the matrix to row echelon form.
     *
     * @param matrix The input matrix
     * @param pivotRow The pivot row index
     * @param colIndex The column index
     */
    private static void eliminateRows(double[][] matrix, int pivotRow, int colIndex) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        for (int otherRow = 0; otherRow < numRows; ++otherRow) {
            if (otherRow != pivotRow && !isZero(matrix[otherRow][colIndex])) {
                for (int col2 = colIndex + 1; col2 < numColumns; ++col2) {
                    matrix[otherRow][col2] -= matrix[pivotRow][col2] * matrix[otherRow][colIndex];
                }
            }
        }
    }
}
