package com.thealgorithms.dynamicprogramming;

// Matrix-chain Multiplication
// Problem Statement
// we have given a chain A1,A2,...,Ani of n matrices, where for i = 1,2,...,n, 
// matrix Ai has dimension pi−1 ×pi
// , fully parenthesize the product A1A2 ···An in a way that
// minimizes the number of scalar multiplications.
public class MatrixChainRecursiveTopDownMemoisation {

    static int Memoized_Matrix_Chain(int p[]) {
        int n = p.length;
        int m[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        return Lookup_Chain(m, p, 1, n - 1);
    }

    static int Lookup_Chain(int m[][], int p[], int i, int j) {
        if (i == j) {
            m[i][j] = 0;
            return m[i][j];
        }
        if (m[i][j] < Integer.MAX_VALUE) {
            return m[i][j];
        } else {
            for (int k = i; k < j; k++) {
                int q = Lookup_Chain(m, p, i, k) + Lookup_Chain(m, p, k + 1, j) + (p[i - 1] * p[k] * p[j]);
                if (q < m[i][j]) {
                    m[i][j] = q;
                }
            }
        }
        return m[i][j];
    }

    // in this code we are taking the example of 4 matrixes whose orders are 1x2,2x3,3x4,4x5 respectively
    // output should be  Minimum number of multiplications is 38
    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 5};
        System.out.println("Minimum number of multiplications is " + Memoized_Matrix_Chain(arr));
    }
}
