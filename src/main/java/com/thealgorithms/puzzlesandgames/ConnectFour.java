package com.thealgorithms.puzzlesandgames;

import java.util.Arrays;

/**
 * Connect Four on a 6-row by 7-column board.
 *
 * <p>
 * Players take turns dropping a disc into a column; the disc falls to the
 * lowest free row. The first player to line up four discs horizontally,
 * vertically or diagonally wins. If the board fills up the game is a draw.
 * </p>
 *
 * <p>
 * Win detection only inspects the four lines through the last disc played:
 * Time Complexity O(1) per move, Space Complexity O(1) besides the board.
 * </p>
 *
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Connect_Four">Connect Four on Wikipedia</a>.
 * </p>
 */
public final class ConnectFour {

    public static final char EMPTY = '.';
    public static final char RED = 'R';
    public static final char YELLOW = 'Y';
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final int CONNECT = 4;
    // {rowStep, colStep}: horizontal, vertical, and the two diagonals
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

    private final char[][] board = new char[ROWS][COLS];
    private char currentPlayer = RED;
    private char winner = EMPTY;
    private int moves;

    /** Creates an empty board; {@code RED} moves first. */
    public ConnectFour() {
        for (char[] row : board) {
            Arrays.fill(row, EMPTY);
        }
    }

    /**
     * Drops a disc for the current player into a column.
     *
     * @param col column index, 0-6
     * @return the row (0 is the top) where the disc landed
     * @throws IllegalStateException    if the game is already over
     * @throws IllegalArgumentException if the column is invalid or full
     */
    public int dropDisc(int col) {
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over");
        }
        if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Column is outside the board");
        }
        int row = lowestFreeRow(col);
        if (row < 0) {
            throw new IllegalArgumentException("Column is full");
        }
        board[row][col] = currentPlayer;
        moves++;
        if (isWinningMove(row, col)) {
            winner = currentPlayer;
        }
        currentPlayer = currentPlayer == RED ? YELLOW : RED;
        return row;
    }

    /** @return the winning disc, or {@link #EMPTY} if nobody has won yet */
    public char getWinner() {
        return winner;
    }

    /** @return true if the board is full and nobody has won */
    public boolean isDraw() {
        return winner == EMPTY && moves == ROWS * COLS;
    }

    /** @return true if someone has won or the board is full */
    public boolean isGameOver() {
        return winner != EMPTY || moves == ROWS * COLS;
    }

    /** @return the disc of the player whose turn it is */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /** @return a copy of the board, indexed {@code [row][col]} with row 0 at the top */
    public char[][] getBoard() {
        char[][] copy = new char[ROWS][];
        for (int i = 0; i < ROWS; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    private int lowestFreeRow(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                return row;
            }
        }
        return -1;
    }

    private boolean isWinningMove(int row, int col) {
        char disc = board[row][col];
        for (int[] d : DIRECTIONS) {
            int count = 1 + countInDirection(row, col, d[0], d[1], disc) + countInDirection(row, col, -d[0], -d[1], disc);
            if (count >= CONNECT) {
                return true;
            }
        }
        return false;
    }

    private int countInDirection(int row, int col, int rowStep, int colStep, char disc) {
        int count = 0;
        int r = row + rowStep;
        int c = col + colStep;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == disc) {
            count++;
            r += rowStep;
            c += colStep;
        }
        return count;
    }
}
