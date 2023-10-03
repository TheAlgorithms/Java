package com.thealgorithms.backtracking;

public class NQueens {

    private int[] board;
    private int n;

    public NQueens(int n) {
        this.n = n;
        board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }
    }

    public boolean solve() {
        return solve(0);
    }

    private boolean solve(int row) {
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                if (solve(row + 1)) {
                    return true;
                }
                board[row] = -1;
            }
        }

        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == row - i) {
                return false;
            }
        }

        return true;
    }

    public void printSolution() {
        for (int i = 0; i < n; i++) {
            System.out.print(board[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 4;
        NQueens queens = new NQueens(n);

        if (queens.solve()) {
            queens.printSolution();
        } else {
            System.out.println("No solution found");
        }
    }
}
