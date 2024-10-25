package com.thealgorithms.divideandconquer;

/**
 * This class provides a solution to the Tiling Problem using divide-and-conquer.
 * <p>
 * The Tiling Problem involves filling a 2^n x 2^n board with a single missing
 * square using L-shaped tiles (each tile covers exactly three squares).
 * The algorithm recursively divides the board into four quadrants, places an
 * L-shaped tile in the appropriate quadrant, and fills the remaining areas.
 *
 * <p>Applications:
 * - Used in graphics and image processing.
 * - Helpful in solving puzzles and tiling problems in competitive programming.
 *
 * @author Hardvan
 */
public final class TilingProblem {
    private TilingProblem() {
    }

    /**
     * A counter used to label the L-shaped tiles placed on the board.
     */
    private static int tile = 1;

    /**
     * A 2D array representing the board to be tiled.
     */
    private static int[][] board;

    /**
     * Solves the tiling problem for a 2^n x 2^n board with one missing square.
     *
     * @param size The size of the board (must be a power of 2).
     * @param missingRow The row index of the missing square.
     * @param missingCol The column index of the missing square.
     * @return A 2D array representing the tiled board with L-shaped tiles.
     */
    public static int[][] solveTiling(int size, int missingRow, int missingCol) {
        board = new int[size][size];
        fillBoard(size, 0, 0, missingRow, missingCol);
        return board;
    }

    /**
     * Recursively fills the board with L-shaped tiles.
     *
     * <p>The board is divided into four quadrants. Depending on the location of
     * the missing square, an L-shaped tile is placed at the center of the board
     * to cover three of the four quadrants. The process is then repeated for
     * each quadrant until the entire board is filled.
     *
     * @param size The current size of the sub-board.
     * @param row The starting row index of the current sub-board.
     * @param col The starting column index of the current sub-board.
     * @param missingRow The row index of the missing square within the board.
     * @param missingCol The column index of the missing square within the board.
     */
    private static void fillBoard(int size, int row, int col, int missingRow, int missingCol) {
        if (size == 1) {
            return;
        }

        int half = size / 2;
        int t = tile++;

        // Top-left quadrant
        if (missingRow < row + half && missingCol < col + half) {
            fillBoard(half, row, col, missingRow, missingCol);
        } else {
            board[row + half - 1][col + half - 1] = t;
            fillBoard(half, row, col, row + half - 1, col + half - 1);
        }

        // Top-right quadrant
        if (missingRow < row + half && missingCol >= col + half) {
            fillBoard(half, row, col + half, missingRow, missingCol);
        } else {
            board[row + half - 1][col + half] = t;
            fillBoard(half, row, col + half, row + half - 1, col + half);
        }

        // Bottom-left quadrant
        if (missingRow >= row + half && missingCol < col + half) {
            fillBoard(half, row + half, col, missingRow, missingCol);
        } else {
            board[row + half][col + half - 1] = t;
            fillBoard(half, row + half, col, row + half, col + half - 1);
        }

        // Bottom-right quadrant
        if (missingRow >= row + half && missingCol >= col + half) {
            fillBoard(half, row + half, col + half, missingRow, missingCol);
        } else {
            board[row + half][col + half] = t;
            fillBoard(half, row + half, col + half, row + half, col + half);
        }
    }
}
