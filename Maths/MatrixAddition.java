package Maths;

/**
 * Find the area of various geometric shapes
 */
public class MatrixAddition {
    public static void main(String[] args) {
        int[][] a = new int[][] {{1, 2, 3}, {1, 2, 3}};
        int[][] b = new int[][] {{0, 0, 0}, {1, 1, 1}};
        int[][] c = new int[][] {{1, 2, 3}, {2, 3, 4}};

        /* test addition */
        assert addMatrices(a, b) == c;
    }

    /**
     * Calculate the sum of two matrices.
     *
     * @param firstMatrix the first matrix to be added
     * @param secondMatrix the second matrix to be added
     * @return sum of both matrices
     */
    private static int[][] addMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length) {
            throw new IllegalArgumentException("matrices are not the same size");
        }

        int[][] c = new int[firstMatrix.length][firstMatrix[0].length];

        for(int i = 0; i < firstMatrix.length; i++) {
            for(int j = 0; j < firstMatrix[0].length; j++) {
                c[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }

        return c;
    }
}