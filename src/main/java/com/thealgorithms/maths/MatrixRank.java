package com.thealgorithms.maths;

/**
 *
 *
 * <h1>Rank of Matrix</h1>
 *
 * <p>
 * The rank of a matrix is the largest number of linearly independent rows/columns of the matrix.
 * The rank is not only defined for square matrices.
 * </p>
 *
 * <p>
 * The rank of a matrix can also be defined as the largest order of any non-zero minor in the matrix.
 * </p>
 *
 * <p> Read more about rank of matrix <a href="https://en.wikipedia.org/wiki/Rank_(linear_algebra)">here</a></p>
 *
 * @author Anup Omkar
 */
public class MatrixRank {

    // Small value to handle precision issues with floating point numbers
    private final static double EPSILON = 1e-10;

    /**
     * Returns rank of matrix
     *
     * @param matrix matrix whose rank is to be found
     * @return rank of matrix
     */
    public static int computeRank(double[][] matrix) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        int rank = 0;

        boolean[] rowMarked = new boolean[numRows];
        for (int colIndex = 0; colIndex < numColumns; ++colIndex) {
            int pivotRow;
            for (pivotRow = 0; pivotRow < numRows; ++pivotRow) {
                if (!rowMarked[pivotRow] && Math.abs(matrix[pivotRow][colIndex]) > EPSILON) {
                    break;
                }
            }
            if (pivotRow != numRows) {
                ++rank;
                rowMarked[pivotRow] = true;
                for (int nextCol = colIndex + 1; nextCol < numColumns; ++nextCol) {
                    matrix[pivotRow][nextCol] /= matrix[pivotRow][colIndex];
                }
                for (int otherRow = 0; otherRow < numRows; ++otherRow) {
                    if (otherRow != pivotRow && Math.abs(matrix[otherRow][colIndex]) > EPSILON) {
                        for (int col2 = colIndex + 1; col2 < numColumns; ++col2) {
                            matrix[otherRow][col2] -= matrix[pivotRow][col2] * matrix[otherRow][colIndex];
                        }
                    }
                }
            }
        }
        return rank;
    }
}
