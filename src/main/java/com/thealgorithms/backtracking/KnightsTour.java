package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Solves the Knight's Tour problem using backtracking combined with
 * Warnsdorff's heuristic for improved efficiency.
 *
 * A knight must visit every square on an N × N chessboard exactly once.
 */
public class KnightsTour {

    private final int n;              // Board dimension
    private final int[][] board;      // Stores visiting order
    private final int totalSquares;   // n * n

    // Knight's possible movements
    private static final int[][] MOVES = {
        {1, -2}, {2, -1}, {2, 1}, {1, 2},
        {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}
    };

    /**
     * Creates a Knight's Tour solver for an n × n board.
     *
     * @param n board size (must be >= 1)
     */
    public KnightsTour(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Board size must be positive");
        }
        this.n = n;
        this.board = new int[n][n];
        this.totalSquares = n * n;
    }

    /**
     * Attempts to solve the Knight's Tour starting from (row, col).
     *
     * @param row starting row
     * @param col starting column
     * @return true if a complete tour exists
     */
    public boolean solve(int row, int col) {
        board[row][col] = 1;
        return backtrack(row, col, 2);
    }

    /** Recursive solver using Warnsdorff's ordering */
    private boolean backtrack(int row, int col, int move) {
        if (move > totalSquares) {
            return true; // Successfully visited all squares
        }

        List<int[]> nextMoves = getSortedMoves(row, col);

        for (int[] m : nextMoves) {
            int nr = m[0], nc = m[1];
            board[nr][nc] = move;

            if (backtrack(nr, nc, move + 1)) {
                return true;
            }

            board[nr][nc] = 0; // Undo move (backtrack)
        }
        return false;
    }

    /**
     * Returns valid knight moves sorted by Warnsdorff degree rule.
     */
    private List<int[]> getSortedMoves(int row, int col) {
        List<int[]> moves = new ArrayList<>();

        for (int[] m : MOVES) {
            int nr = row + m[0];
            int nc = col + m[1];

            if (isValid(nr, nc)) {
                int degree = countDegree(nr, nc);
                moves.add(new int[] {nr, nc, degree});
            }
        }

        moves.sort(Comparator.comparingInt(a -> a[2])); // Fewest onward moves first
        return moves;
    }

    /** Counts onward valid knight moves */
    private int countDegree(int row, int col) {
        int count = 0;
        for (int[] m : MOVES) {
            int nr = row + m[0];
            int nc = col + m[1];
            if (isValid(nr, nc)) {
                count++;
            }
        }
        return count;
    }

    /** Checks bounds & whether square is unvisited */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n && board[row][col] == 0;
    }

    /**
     * Returns the solved board.
     *
     * @return board with visiting sequence
     */
    public int[][] getBoard() {
        return board;
    }
}
