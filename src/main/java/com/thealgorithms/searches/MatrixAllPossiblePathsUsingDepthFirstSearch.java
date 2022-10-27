package com.thealgorithms.searches;

/**
* Finding all the possible paths between two points of n x n matrix
* using Depth First Search algorithm
*
* @author Sineth Sankalpa (https://github.com/sinsankio)
* */

public class MatrixAllPossiblePathsUsingDepthFirstSearch {
    private static final int OBSTACLE_POS = -1; // position value to address an obstacle in the input matrix
    private static final int END_POS = 0; // position value to address the ending point in the input matrix
    private static final int VALID_POS = 1; // position value to address a valid point to be traversed in the input matrix
    public int countAllPossiblePaths(int matrix[][], int row, int col) {
        /**
         * @param matrix an array of integers which going to be searched
         * for possible paths between two points
         * @param row integer value which corresponds to the row index of start position
         * to initialize the traversal
         * @param col integer value which corresponds to the column index of start position
         * to initialize the traversal
         */
        if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] == OBSTACLE_POS) {
            return 0;
        }
        if(matrix[row][col] == END_POS) {
            return 1;
        }

        int visited = matrix[row][col]; // mark traversed points as visited
        matrix[row][col] = OBSTACLE_POS;

        int result = countAllPossiblePaths(matrix, row + 1, col) + // upward traversal
                countAllPossiblePaths(matrix, row - 1, col) + // downward traversal
                countAllPossiblePaths(matrix, row, col + 1) + // rightmost traversal
                countAllPossiblePaths(matrix, row, col - 1); // leftmost traversal

        matrix[row][col] = visited; // undo visited traversed points

        return result;
    }

    public static void main(String[] args) {
        MatrixAllPossiblePathsUsingDepthFirstSearch allPossiblePathsUsingDepthFirstSearch =
                new MatrixAllPossiblePathsUsingDepthFirstSearch();
        int matrix[][] = {
                {VALID_POS, VALID_POS, VALID_POS, OBSTACLE_POS},
                {VALID_POS, OBSTACLE_POS, VALID_POS, VALID_POS},
                {VALID_POS, VALID_POS, VALID_POS, OBSTACLE_POS},
                {OBSTACLE_POS, VALID_POS, END_POS, VALID_POS}
        };
        int startPosRow = 0;
        int startPosCol = 1;
        int numPossiblePaths = allPossiblePathsUsingDepthFirstSearch.countAllPossiblePaths(matrix, startPosRow, startPosCol);

        System.out.printf("Number of possible paths: %d\n", numPossiblePaths);
    }
}
