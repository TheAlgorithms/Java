package com.thealgorithms.misc;

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
            return new int[0][0];
        }
        int numRows = originalMatrix.length;
        int numCols = originalMatrix[0].length;

        int[][] mirroredMatrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            mirroredMatrix[i] = reverseRow(originalMatrix[i]);
        }
        return mirroredMatrix;
    }
}
