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

    public static int[][] mirrorMatrix(final int[][] originalMatrix) {
        if (originalMatrix == null) {
            // Handle invalid input
            return null;
        }
        if (originalMatrix.length == 0) {
            return new int[0][0];
        }

        checkInput(originalMatrix);

        int numRows = originalMatrix.length;
        int numCols = originalMatrix[0].length;

        int[][] mirroredMatrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            mirroredMatrix[i] = reverseRow(originalMatrix[i]);
        }
        return mirroredMatrix;
    }
    private static int[] reverseRow(final int[] inRow) {
        int[] res = new int[inRow.length];
        for (int i = 0; i < inRow.length; ++i) {
            res[i] = inRow[inRow.length - 1 - i];
        }
        return res;
    }

    private static void checkInput(final int[][] matrix) {
        // Check if all rows have the same number of columns
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("The input is not a matrix.");
            }
        }
    }
}
