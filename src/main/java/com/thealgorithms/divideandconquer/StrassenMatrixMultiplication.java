package com.thealgorithms.divideandconquer;

// Java Program to Implement Strassen Algorithm for Matrix Multiplication

/*
 * Uses the divide and conquer approach to multiply two matrices.
 * Time Complexity: O(n^2.8074) better than the O(n^3) of the standard matrix multiplication
 * algorithm. Space Complexity: O(n^2)
 *
 * This Matrix multiplication can be performed only on square matrices
 * where n is a power of 2. Order of both of the matrices are n × n.
 *
 * Reference:
 * https://www.tutorialspoint.com/design_and_analysis_of_algorithms/design_and_analysis_of_algorithms_strassens_matrix_multiplication.htm#:~:text=Strassen's%20Matrix%20multiplication%20can%20be,matrices%20are%20n%20%C3%97%20n.
 * https://www.geeksforgeeks.org/strassens-matrix-multiplication/
 */

public class StrassenMatrixMultiplication {

    // Function to multiply matrices
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;

        int[][] R = new int[n][n];

        if (n == 1) {
            R[0][0] = A[0][0] * B[0][0];
        } else {
            // Dividing Matrix into parts
            // by storing sub-parts to variables
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            // Dividing matrix A into 4 parts
            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);

            // Dividing matrix B into 4 parts
            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            // Using Formulas as described in algorithm
            // M1:=(A1+A3)×(B1+B2)
            int[][] M1 = multiply(add(A11, A22), add(B11, B22));

            // M2:=(A2+A4)×(B3+B4)
            int[][] M2 = multiply(add(A21, A22), B11);

            // M3:=(A1−A4)×(B1+A4)
            int[][] M3 = multiply(A11, sub(B12, B22));

            // M4:=A1×(B2−B4)
            int[][] M4 = multiply(A22, sub(B21, B11));

            // M5:=(A3+A4)×(B1)
            int[][] M5 = multiply(add(A11, A12), B22);

            // M6:=(A1+A2)×(B4)
            int[][] M6 = multiply(sub(A21, A11), add(B11, B12));

            // M7:=A4×(B3−B1)
            int[][] M7 = multiply(sub(A12, A22), add(B21, B22));

            // P:=M2+M3−M6−M7
            int[][] C11 = add(sub(add(M1, M4), M5), M7);

            // Q:=M4+M6
            int[][] C12 = add(M3, M5);

            // R:=M5+M7
            int[][] C21 = add(M2, M4);

            // S:=M1−M3−M4−M5
            int[][] C22 = add(sub(add(M1, M3), M2), M6);

            join(C11, R, 0, 0);
            join(C12, R, 0, n / 2);
            join(C21, R, n / 2, 0);
            join(C22, R, n / 2, n / 2);
        }

        return R;
    }

    // Function to subtract two matrices
    public int[][] sub(int[][] A, int[][] B) {
        int n = A.length;

        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }

    // Function to add two matrices
    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;

        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    // Function to split parent matrix into child matrices
    public void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    // Function to join child matrices into (to) parent matrix
    public void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }
}
