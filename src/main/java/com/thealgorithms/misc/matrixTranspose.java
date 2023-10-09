package com.thealgorithms.misc;

import java.util.Scanner;

/**
 *
 *
 * <h1>Find the Transpose of Matrix!</h1>
 *
 * Simply take input from the user and print the matrix before the transpose and
 * after the transpose.
 *
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more user
 * friendly and it is assumed as a high quality code.
 *
 * @author Rajat-Jain29
 * @version 11.0.9
 * @since 2014-03-31
 */
public class matrixTranspose {

    public static void main(String[] args) {
        /*
         * This is the main method
         *
         * @param args Unused.
         *
         * @return Nothing.
         */
        Scanner sc = new Scanner(System.in);
        int i, j, row, column;
        System.out.println("Enter the number of rows in the 2D matrix:");

        /*
         * Take input from user for how many rows to be print
         */
        row = sc.nextInt();

        System.out.println("Enter the number of columns in the 2D matrix:");

        /*
         * Take input from user for how many coloumn to be print
         */
        column = sc.nextInt();
        int[][] arr = new int[row][column];
        System.out.println("Enter the elements");
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        /*
         * Print matrix before the Transpose in proper way
         */
        System.out.println("The matrix is:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.print("\n");
        }

        /*
         * Print matrix after the tranpose in proper way Transpose means Interchanging
         * of rows wth column so we interchange the rows in next loop Thus at last
         * matrix of transpose is obtained through user input...
         */
        System.out.println("The Transpose of the given matrix is:");
        for (i = 0; i < column; i++) {
            for (j = 0; j < row; j++) {
                System.out.print(arr[j][i] + "\t");
            }
            System.out.print("\n");
        }
    }
}
