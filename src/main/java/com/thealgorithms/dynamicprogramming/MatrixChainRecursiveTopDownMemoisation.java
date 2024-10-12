package com.thealgorithms.dynamicprogramming;

/**
 * The MatrixChainRecursiveTopDownMemoisation class implements the matrix-chain
 * multiplication problem using a top-down recursive approach with memoization.
 *
 * <p>Given a chain of matrices A1, A2, ..., An, where matrix Ai has dimensions
 * pi-1 × pi, this algorithm finds the optimal way to fully parenthesize the
 * product A1A2...An in a way that minimizes the total number of scalar
 * multiplications required.</p>
 *
 * <p>This implementation uses a memoization technique to store the results of
 * subproblems, which significantly reduces the number of recursive calls and
 * improves performance compared to a naive recursive approach.</p>
 */
public final class MatrixChainRecursiveTopDownMemoisation {
    private MatrixChainRecursiveTopDownMemoisation() {
    }

    /**
     * Calculates the minimum number of scalar multiplications needed to multiply
     * a chain of matrices.
     *
     * @param p an array of integers representing the dimensions of the matrices.
     *          The length of the array is n + 1, where n is the number of matrices.
     * @return the minimum number of multiplications required to multiply the chain
     *         of matrices.
     */
    static int memoizedMatrixChain(int[] p) {
        int n = p.length;
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        return lookupChain(m, p, 1, n - 1);
    }

    /**
     * A recursive helper method to lookup the minimum number of multiplications
     * for multiplying matrices from index i to index j.
     *
     * @param m the memoization table storing the results of subproblems.
     * @param p an array of integers representing the dimensions of the matrices.
     * @param i the starting index of the matrix chain.
     * @param j the ending index of the matrix chain.
     * @return the minimum number of multiplications needed to multiply matrices
     *         from i to j.
     */
    static int lookupChain(int[][] m, int[] p, int i, int j) {
        if (i == j) {
            m[i][j] = 0;
            return m[i][j];
        }
        if (m[i][j] < Integer.MAX_VALUE) {
            return m[i][j];
        } else {
            for (int k = i; k < j; k++) {
                int q = lookupChain(m, p, i, k) + lookupChain(m, p, k + 1, j) + (p[i - 1] * p[k] * p[j]);
                if (q < m[i][j]) {
                    m[i][j] = q;
                }
            }
        }
        return m[i][j];
    }
}
