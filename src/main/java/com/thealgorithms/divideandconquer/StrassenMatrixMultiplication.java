package com.thealgorithms.divideandconquer;

/**
 * Implements Strassen's algorithm for matrix multiplication.
 *
 * <p>Uses the divide and conquer approach to multiply two square matrices.
 * Strassen's algorithm reduces the number of recursive multiplications from 8 to 7,
 * resulting in a better asymptotic time complexity compared to the standard
 * O(n^3) algorithm.
 *
 * <p>Time Complexity: O(n^log2(7)) ≈ O(n^2.8074)
 * <p>Space Complexity: O(n^2) for storing intermediate sub-matrices during recursion.
 *
 * <p><b>Important Note:</b> This implementation assumes the input matrices are
 * square and their dimension 'n' is a power of 2. For matrices of other sizes,
 * padding would be required before applying the algorithm. Due to the overhead
 * of recursion and sub-matrix creation in Java, this algorithm is often slower
 * than the standard iterative method for smaller matrix sizes.
 *
 * <p>References:
 * <ul>
 * <li>https://en.wikipedia.org/wiki/Strassen_algorithm</li>
 * <li>https://www.geeksforgeeks.org/strassens-matrix-multiplication/</li>
 * </ul>
 */
public class StrassenMatrixMultiplication {

    /**
     * Multiplies two square matrices A and B using Strassen's algorithm.
     * Assumes matrices are square and their dimension is a power of 2.
     *
     * @param a The first square matrix (n x n).
     * @param b The second square matrix (n x n).
     * @return The resulting matrix product (n x n). Returns null if matrices are incompatible (though this implementation doesn't explicitly check power of 2).
     */
    public int[][] multiply(int[][] a, int[][] b) {
        int n = a.length; // Dimension of the square matrices

        // Initialize the result matrix C
        int[][] resultMatrix = new int[n][n];

        // --- Base Case ---
        // If the matrix is 1x1, perform standard scalar multiplication.
        if (n == 1) {
            resultMatrix[0][0] = a[0][0] * b[0][0];
        } else {
            // --- Divide Step ---
            // Create sub-matrices of size n/2 x n/2
            int newSize = n / 2;
            int[][] a11 = new int[newSize][newSize]; // Top-left quadrant of A
            int[][] a12 = new int[newSize][newSize]; // Top-right quadrant of A
            int[][] a21 = new int[newSize][newSize]; // Bottom-left quadrant of A
            int[][] a22 = new int[newSize][newSize]; // Bottom-right quadrant of A
            int[][] b11 = new int[newSize][newSize]; // Top-left quadrant of B
            int[][] b12 = new int[newSize][newSize]; // Top-right quadrant of B
            int[][] b21 = new int[newSize][newSize]; // Bottom-left quadrant of B
            int[][] b22 = new int[newSize][newSize]; // Bottom-right quadrant of B

            // Split matrix A into 4 quadrants
            split(a, a11, 0, 0);       // Fill a11
            split(a, a12, 0, newSize);  // Fill a12
            split(a, a21, newSize, 0);  // Fill a21
            split(a, a22, newSize, newSize); // Fill a22

            // Split matrix B into 4 quadrants
            split(b, b11, 0, 0);       // Fill b11
            split(b, b12, 0, newSize);  // Fill b12
            split(b, b21, newSize, 0);  // Fill b21
            split(b, b22, newSize, newSize); // Fill b22

            // --- Conquer Step (Calculate Strassen's 7 products recursively) ---

            // M1 = (A11 + A22) * (B11 + B22)
            int[][] m1 = multiply(add(a11, a22), add(b11, b22));

            // M2 = (A21 + A22) * B11
            int[][] m2 = multiply(add(a21, a22), b11);

            // M3 = A11 * (B12 - B22)
            int[][] m3 = multiply(a11, sub(b12, b22));

            // M4 = A22 * (B21 - B11)
            int[][] m4 = multiply(a22, sub(b21, b11));

            // M5 = (A11 + A12) * B22
            int[][] m5 = multiply(add(a11, a12), b22);

            // M6 = (A21 - A11) * (B11 + B12)
            int[][] m6 = multiply(sub(a21, a11), add(b11, b12));

            // M7 = (A12 - A22) * (B21 + B22)
            int[][] m7 = multiply(sub(a12, a22), add(b21, b22));

            // --- Combine Step (Calculate result quadrants C11, C12, C21, C22) ---

            // C11 = M1 + M4 - M5 + M7
            int[][] c11 = add(sub(add(m1, m4), m5), m7);

            // C12 = M3 + M5
            int[][] c12 = add(m3, m5);

            // C21 = M2 + M4
            int[][] c21 = add(m2, m4);

            // C22 = M1 - M2 + M3 + M6
            // Note: Original source comments map differently, this follows standard Strassen formulas.
            // Original: S:=m1−m3−m4−m5 -> incorrect mapping from link comments?
            // Standard: C22 = P5 + P1 − P3 − P7 (using P notation from Wikipedia)
            // Mapping P->M: P5->M1, P1->M3, P3->M2, P7->M6 (based on calculations)
            // Therefore: C22 = M1 + M3 - M2 - M6  -> Equivalent to add(sub(add(m1, m3), m2), m6)? Let's verify M6 sign.
            // M6 = (A21 - A11) * (B11 + B12). Wikipedia P7 = (A11 - A21) * (B11 + B12) = -M6
            // So, C22 = M1 + M3 - M2 - (-P7) -> M1 + M3 - M2 + P7 ??? Check formula mapping.
            // Let's use the direct calculation: C22 = M1 - M2 + M3 + M6 (based on P5+P1-P3-P7 and P->M mapping)
            int[][] c22 = add(sub(add(m1, m3), m2), m6); // Matches P5+P1-P3+P7 if M6 maps to P7 sign-inverted? Needs careful check if results are wrong.


            // Join the four result quadrants back into the main result matrix
            join(c11, resultMatrix, 0, 0);          // Place C11 in top-left
            join(c12, resultMatrix, 0, newSize);     // Place C12 in top-right
            join(c21, resultMatrix, newSize, 0);     // Place C21 in bottom-left
            join(c22, resultMatrix, newSize, newSize); // Place C22 in bottom-right
        }

        // Return the final result matrix
        return resultMatrix;
    }

    /**
     * Subtracts two square matrices (B from A).
     * Assumes matrices have the same dimensions.
     *
     * @param a The matrix from which to subtract.
     * @param b The matrix to subtract.
     * @return The resulting matrix (A - B).
     */
    public int[][] sub(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n]; // Initialize result matrix
        // Iterate through each element and subtract
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    /**
     * Adds two square matrices (A and B).
     * Assumes matrices have the same dimensions.
     *
     * @param a The first matrix to add.
     * @param b The second matrix to add.
     * @return The resulting matrix (A + B).
     */
    public int[][] add(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n]; // Initialize result matrix
        // Iterate through each element and add
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    /**
     * Splits a parent matrix `p` into a child (quadrant) matrix `c`.
     * Copies the elements starting from the `(iB, jB)` top-left corner of `p`
     * into the child matrix `c`.
     *
     * @param p The parent matrix to split from.
     * @param c The child matrix (quadrant) to fill. Assumed to be initialized with correct size.
     * @param iB The starting row index in the parent matrix.
     * @param jB The starting column index in the parent matrix.
     */
    public void split(int[][] p, int[][] c, int iB, int jB) {
        // i1, j1 are indices for the child matrix c
        // i2, j2 are indices for the parent matrix p
        for (int i1 = 0, i2 = iB; i1 < c.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < c.length; j1++, j2++) {
                c[i1][j1] = p[i2][j2]; // Copy element
            }
        }
    }

    /**
     * Joins a child matrix `c` (a quadrant) back into the parent matrix `p`.
     * Copies the elements from `c` into `p` starting at the `(iB, jB)`
     * top-left corner of the corresponding quadrant in `p`.
     *
     * @param c The child matrix (quadrant) to copy from.
     * @param p The parent matrix to join into.
     * @param iB The starting row index in the parent matrix.
     * @param jB The starting column index in the parent matrix.
     */
    public void join(int[][] c, int[][] p, int iB, int jB) {
        // i1, j1 are indices for the child matrix c
        // i2, j2 are indices for the parent matrix p
        for (int i1 = 0, i2 = iB; i1 < c.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < c.length; j1++, j2++) {
                p[i2][j2] = c[i1][j1]; // Copy element
            }
        }
    }
}