package com.thealgorithms.misc;

import java.util.Scanner;

// Problem Statement
/*
We have given an array of m x n (where m is the number of rows and n is the number of columns).
Print the new matrix in such a way that the new matrix is the mirror image of the original matrix.

The Original matrix is:   |   The Mirror matrix is:
1	2	3	              |   3	 2	 1
4	5	6	              |   6	 5	 4
7	8	9	              |   9	 8	 7

@author - Aman (https://github.com/Aman28801)
*/

public final class MirrorOfMatrix {
    private MirrorOfMatrix() {
    }
    // Function Mirror that is used to mirror the Matrix
    public static void Mirror(final int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            int li = 0;
            int hi = arr[i].length - 1;
            while (li < hi) {
                int temp = arr[i][li];
                arr[i][li] = arr[i][hi];
                arr[i][hi] = temp;

                li++;
                hi--;
            }
        }
        // For printing the output of Code OR Mirror Image of Matrix
        System.out.println("The Mirror matrix is:");
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // To take Inputs From the User
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows in the 2D matrix:");
        int row = sc.nextInt();
        System.out.println("Enter the number of columns in the 2D matrix:");
        int column = sc.nextInt();
        int[][] arr = new int[row][column];
        System.out.println("Enter the elements");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // To Print the Original MAtrix
        System.out.println("The Original matrix is:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.print("\n");
        }
        // Calling Function Mirror
        Mirror(arr);
    }
}
