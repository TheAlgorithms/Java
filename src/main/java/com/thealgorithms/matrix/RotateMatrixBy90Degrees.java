package com.thealgorithms.matrix;

/**
 * Given a matrix of size n x n We have to rotate this matrix by 90 Degree Here
 * is the algorithm for this problem .
 */
final class RotateMatrixBy90Degrees {
    private RotateMatrixBy90Degrees() {
    }

    public static void rotate(int[][] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    int temp = a[i][j];
                    a[i][j] = a[j][i];
                    a[j][i] = temp;
                }
            }
        }
        int i = 0;
        int k = n - 1;
        while (i < k) {
            for (int j = 0; j < n; j++) {
                int temp = a[i][j];
                a[i][j] = a[k][j];
                a[k][j] = temp;
            }

            i++;
            k--;
        }
    }
}