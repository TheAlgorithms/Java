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
}
