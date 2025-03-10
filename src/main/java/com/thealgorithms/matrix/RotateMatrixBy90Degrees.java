package com.thealgorithms.matrix;

import java.util.Scanner;
/**
 * Given a matrix of size n x n We have to rotate this matrix by 90 Degree Here
 * is the algorithm for this problem .
 */
final class RotateMatrixBy90Degrees {
    private RotateMatrixBy90Degrees() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            Rotate.rotate(arr);
            printMatrix(arr);
        }
        sc.close();
    }

    static void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
}

/**
 * Class containing the algo to roate matrix by 90 degree
 */
final class Rotate {
    private Rotate() {
    }

    static void rotate(int[][] a) {
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
