package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The KnightsTour class solves the Knight's Tour problem using backtracking.
 *
 * Problem Statement:
 * Given an N*N board with a knight placed on the first block, the knight must
 * move according to chess rules and visit each square on the board exactly once.
 * The class outputs the sequence of moves for the knight.
 *
 * Example:
 * Input: N = 8 (8x8 chess board)
 * Output: The sequence of numbers representing the order in which the knight visits each square.
 */
public final class KnightsTour {
    private KnightsTour() {
    }

    // The size of the chess board (12x12 grid, with 2 extra rows/columns as a buffer around a 8x8 area)
    private static final int BASE = 12;

    // Possible moves for a knight in chess
    private static final int[][] MOVES = {
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1},
        {-1, -2},
    };

    // Chess grid representing the board
    static int[][] grid;

    // Total number of cells the knight needs to visit
    static int total;

    /**
     * Resets the chess board to its initial state.
     * Initializes the grid with boundary cells marked as -1 and internal cells as 0.
     * Sets the total number of cells the knight needs to visit.
     */
    public static void resetBoard() {
        grid = new int[BASE][BASE];
        total = (BASE - 4) * (BASE - 4);
        for (int r = 0; r < BASE; r++) {
            for (int c = 0; c < BASE; c++) {
                if (r < 2 || r > BASE - 3 || c < 2 || c > BASE - 3) {
                    grid[r][c] = -1; // Mark boundary cells
                }
            }
        }
    }

    /**
     * Recursive method to solve the Knight's Tour problem.
     *
     * @param row   The current row of the knight
     * @param column The current column of the knight
     * @param count  The current move number
     * @return True if a solution is found, False otherwise
     */
    static boolean solve(int row, int column, int count) {
        if (count > total) {
            return true;
        }

        List<int[]> neighbor = neighbors(row, column);

        if (neighbor.isEmpty() && count != total) {
            return false;
        }

        // Sort neighbors by Warnsdorff's rule (fewest onward moves)
        neighbor.sort(Comparator.comparingInt(a -> a[2]));

        for (int[] nb : neighbor) {
            int nextRow = nb[0];
            int nextCol = nb[1];
            grid[nextRow][nextCol] = count;
            if (!orphanDetected(count, nextRow, nextCol) && solve(nextRow, nextCol, count + 1)) {
                return true;
            }
            grid[nextRow][nextCol] = 0; // Backtrack
        }

        return false;
    }

    /**
     * Returns a list of valid neighboring cells where the knight can move.
     *
     * @param row   The current row of the knight
     * @param column The current column of the knight
     * @return A list of arrays representing valid moves, where each array contains:
     *         {nextRow, nextCol, numberOfPossibleNextMoves}
     */
    static List<int[]> neighbors(int row, int column) {
        List<int[]> neighbour = new ArrayList<>();

        for (int[] m : MOVES) {
            int x = m[0];
            int y = m[1];
            if (row + y >= 0 && row + y < BASE && column + x >= 0 && column + x < BASE && grid[row + y][column + x] == 0) {
                int num = countNeighbors(row + y, column + x);
                neighbour.add(new int[] {row + y, column + x, num});
            }
        }
        return neighbour;
    }

    /**
     * Counts the number of possible valid moves for a knight from a given position.
     *
     * @param row    The row of the current position
     * @param column The column of the current position
     * @return The number of valid neighboring moves
     */
    static int countNeighbors(int row, int column) {
        int num = 0;
        for (int[] m : MOVES) {
            int x = m[0];
            int y = m[1];
            if (row + y >= 0 && row + y < BASE && column + x >= 0 && column + x < BASE && grid[row + y][column + x] == 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * Detects if moving to a given position will create an orphan (a position with no further valid moves).
     *
     * @param count   The current move number
     * @param row     The row of the current position
     * @param column  The column of the current position
     * @return True if an orphan is detected, False otherwise
     */
    static boolean orphanDetected(int count, int row, int column) {
        if (count < total - 1) {
            List<int[]> neighbor = neighbors(row, column);
            for (int[] nb : neighbor) {
                if (countNeighbors(nb[0], nb[1]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
