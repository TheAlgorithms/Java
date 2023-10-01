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
    public static int[][] mirrorMatrix(int[][] originalMatrix) {
        if (originalMatrix == null || originalMatrix.length == 0) {
            // Handle invalid input
            return null;
        }
        int numRows = originalMatrix.length;
        int numCols = originalMatrix[0].length;

        int[][] mirroredMatrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Copy elements from the original matrix in reverse order
                mirroredMatrix[i][j] = originalMatrix[i][numCols - 1 - j];
            }
        }
        return mirroredMatrix;
    }
}
