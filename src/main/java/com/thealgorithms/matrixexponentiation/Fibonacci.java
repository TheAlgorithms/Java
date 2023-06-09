package com.thealgorithms.matrixexponentiation;

import java.util.Scanner;

/**
 * @author Anirudh Buvanesh (https://github.com/anirudhb11) For more information
 * see https://www.geeksforgeeks.org/matrix-exponentiation/
 *
 */
public class Fibonacci {

    // Exponentiation matrix for Fibonacci sequence
    private static final int[][] fibMatrix = {{1, 1}, {1, 0}};
    private static final int[][] identityMatrix = {{1, 0}, {0, 1}};
    // First 2 fibonacci numbers
    private static final int[][] baseFibNumbers = {{1}, {0}};

    /**
     * Performs multiplication of 2 matrices
     *
     * @param matrix1
     * @param matrix2
     * @return The product of matrix1 and matrix2
     */
    private static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
        // Check if matrices passed can be multiplied
        int rowsInMatrix1 = matrix1.length;
        int columnsInMatrix1 = matrix1[0].length;

        int rowsInMatrix2 = matrix2.length;
        int columnsInMatrix2 = matrix2[0].length;

        assert columnsInMatrix1 == rowsInMatrix2;
        int[][] product = new int[rowsInMatrix1][columnsInMatrix2];
        for (int rowIndex = 0; rowIndex < rowsInMatrix1; rowIndex++) {
            for (int colIndex = 0; colIndex < columnsInMatrix2; colIndex++) {
                int matrixEntry = 0;
                for (int intermediateIndex = 0; intermediateIndex < columnsInMatrix1; intermediateIndex++) {
                    matrixEntry += matrix1[rowIndex][intermediateIndex] * matrix2[intermediateIndex][colIndex];
                }
                product[rowIndex][colIndex] = matrixEntry;
            }
        }
        return product;
    }

    /**
     * Calculates the fibonacci number using matrix exponentiaition technique
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth * fibonacci number
     * @return a 2 X 1 array as { {F_n+1}, {F_n} }
     */
    public static int[][] fib(int n) {
        if (n == 0) {
            return Fibonacci.identityMatrix;
        } else {
            int[][] cachedResult = fib(n / 2);
            int[][] matrixExpResult = matrixMultiplication(cachedResult, cachedResult);
            if (n % 2 == 0) {
                return matrixExpResult;
            } else {
                return matrixMultiplication(Fibonacci.fibMatrix, matrixExpResult);
            }
        }
    }

    public static void main(String[] args) {
        // Returns [0, 1, 1, 2, 3, 5 ..] for n = [0, 1, 2, 3, 4, 5.. ]
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] result = matrixMultiplication(fib(n), baseFibNumbers);
        System.out.println("Fib(" + n + ") = " + result[1][0]);
        sc.close();
    }
}
