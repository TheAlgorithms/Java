package com.thealgorithms.maths;

import java.util.*;

/*
 * @author Ojasva Jain
 * Determinant of Matrix Wikipedia link : https://en.wikipedia.org/wiki/Determinant
 */
public class DeterminantOfMatrix {

    // Determinant calculator
    //@return determinant of the input matrix
    static int determinant(int[][] a, int n) {
        int det = 0, sign = 1, p = 0, q = 0;
        if (n == 1) {
            det = a[0][0];
        } else {
            int[][] b = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }

    // Driver Method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Input Matrix
        System.out.println("Enter matrix size (Square matrix only)");
        int n = in.nextInt();
        System.out.println("Enter matrix");
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        System.out.println(determinant(a, n));
    }
}
