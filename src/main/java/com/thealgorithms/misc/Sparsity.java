package com.thealgorithms.misc;

/*
 *A matrix is sparse if many of its coefficients are zero (In general if 2/3rd of matrix elements
 *are 0, it is considered as sparse). The interest in sparsity arises because its exploitation can
 *lead to enormous computational savings and because many large matrix problems that occur in
 *practice are sparse.
 *
 * @author Ojasva Jain
 */

final class Sparsity {
    private Sparsity() {
    }

    /*
     * @param mat the input matrix
     * @return Sparsity of matrix
     *
     * where sparsity = number of zeroes/total elements in matrix
     *
     */
    static double sparsity(double[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }

        int zero = 0;
        // Traversing the matrix to count number of zeroes
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    zero++;
                }
            }
        }
        // return sparsity
        return ((double) zero / (mat.length * mat[0].length));
    }
}
