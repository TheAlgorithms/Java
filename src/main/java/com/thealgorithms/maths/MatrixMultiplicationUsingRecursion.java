package com.thealgorithms.maths;

/**
 * Matrix Multiplication using recursion
 * @author Akanksha Singh (https://github.com/singhakanksha03)
 */

public class MatrixMultiplicationUsingRecursion {
    public static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;
        int[][] result = new int[m][p];
        if (n != B.length) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication");
        }
        if (m == 1 && n == 1 && p == 1) {
            // Base case: Multiply single elements
            result[0][0] = A[0][0] * B[0][0];
        } else {
            int halfM = m / 2;
            int halfN = n / 2;
            int halfP = p / 2;
            int[][] A11 = new int[halfM][halfN];
            int[][] A12 = new int[halfM][halfN];
            int[][] A21 = new int[halfM][halfN];
            int[][] A22 = new int[halfM][halfN];
            int[][] B11 = new int[halfN][halfP];
            int[][] B12 = new int[halfN][halfP];
            int[][] B21 = new int[halfN][halfP];
            int[][] B22 = new int[halfN][halfP];
            // Split matrices into submatrices
            splitMatrix(A, A11, A12, A21, A22);
            splitMatrix(B, B11, B12, B21, B22);
            int[][] C11 = addMatrix(multiplyMatrix(A11, B11), multiplyMatrix(A12, B21));
            int[][] C12 = addMatrix(multiplyMatrix(A11, B12), multiplyMatrix(A12, B22));
            int[][] C21 = addMatrix(multiplyMatrix(A21, B11), multiplyMatrix(A22, B21));
            int[][] C22 = addMatrix(multiplyMatrix(A21, B12), multiplyMatrix(A22, B22));
            // Combine submatrices to get the result
            combineMatrix(result, C11, C12, C21, C22);
        }
        return result;
    }
    public static void splitMatrix(int[][] source, int[][] A11, int[][] A12, int[][] A21, int[][] A22) {
        int halfRows = source.length / 2;
        int halfCols = source[0].length / 2;
        for (int i = 0; i < halfRows; i++) {
            for (int j = 0; j < halfCols; j++) {
                A11[i][j] = source[i][j];
                A12[i][j] = source[i][j + halfCols];
                A21[i][j] = source[i + halfRows][j];
                A22[i][j] = source[i + halfRows][j + halfCols];
            }
        }
    }
    public static int[][] addMatrix(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }
    public static void combineMatrix(int[][] target, int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
        int halfRows = target.length / 2;
        int halfCols = target[0].length / 2;
        for (int i = 0; i < halfRows; i++) {
            for (int j = 0; j < halfCols; j++) {
                target[i][j] = C11[i][j];
                target[i][j + halfCols] = C12[i][j];
                target[i + halfRows][j] = C21[i][j];
                target[i + halfRows][j + halfCols] = C22[i][j];
            }
        }
    }
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] A = { { 1, 2 }, { 3, 4 } };
        int[][] B = { { 5, 6 }, { 7, 8 } };
        int[][] result = multiplyMatrix(A, B);
        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);
        System.out.println("Matrix A * B:");
        printMatrix(result);
    }
}
