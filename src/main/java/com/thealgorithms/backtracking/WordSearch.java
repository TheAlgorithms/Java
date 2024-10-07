package com.thealgorithms.backtracking;

/**
 * Word Search Problem
 *
 * This class solves the word search problem where given an m x n grid of characters (board)
 * and a target word, the task is to check if the word exists in the grid.
 * The word can be constructed from sequentially adjacent cells (horizontally or vertically),
 * and the same cell may not be used more than once in constructing the word.
 *
 * Example:
 * - For board =
 *     [
 *       ['A','B','C','E'],
 *       ['S','F','C','S'],
 *       ['A','D','E','E']
 *     ]
 *   and word = "ABCCED", -> returns true
 *   and word = "SEE",    -> returns true
 *   and word = "ABCB",   -> returns false
 *
 * Solution:
 * - Depth First Search (DFS) with backtracking is used to explore possible paths from any cell
 *   matching the first letter of the word. DFS ensures that we search all valid paths, while
 *   backtracking helps in reverting decisions when a path fails to lead to a solution.
 *
 * Time Complexity: O(m * n * 3^L)
 *  - m = number of rows in the board
 *  - n = number of columns in the board
 *  - L = length of the word
 *  - For each cell, we look at 3 possible directions (since we exclude the previously visited direction),
 *    and we do this for L letters.
 *
 * Space Complexity: O(L)
 *  - Stack space for the recursive DFS function, where L is the maximum depth of recursion (length of the word).
 */
public class WordSearch {
    private final int[] dx = {0, 0, 1, -1};
    private final int[] dy = {1, -1, 0, 0};
    private boolean[][] visited;
    private char[][] board;
    private String word;

    /**
     * Checks if the given (x, y) coordinates are valid positions in the board.
     *
     * @param x The row index.
     * @param y The column index.
     * @return True if the coordinates are within the bounds of the board; false otherwise.
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    /**
     * Performs Depth First Search (DFS) from the cell (x, y)
     * to search for the next character in the word.
     *
     * @param x       The current row index.
     * @param y       The current column index.
     * @param nextIdx The index of the next character in the word to be matched.
     * @return True if a valid path is found to match the remaining characters of the word; false otherwise.
     */
    private boolean doDFS(int x, int y, int nextIdx) {
        visited[x][y] = true;
        if (nextIdx == word.length()) {
            return true;
        }

        for (int i = 0; i < 4; ++i) {
            int xi = x + dx[i];
            int yi = y + dy[i];
            if (isValid(xi, yi) && board[xi][yi] == word.charAt(nextIdx) && !visited[xi][yi]) {
                boolean exists = doDFS(xi, yi, nextIdx + 1);
                if (exists) {
                    return true;
                }
            }
        }

        visited[x][y] = false; // Backtrack
        return false;
    }

    /**
     * Main function to check if the word exists in the board. It initiates DFS from any
     * cell that matches the first character of the word.
     *
     * @param board The 2D grid of characters (the board).
     * @param word  The target word to search for in the board.
     * @return True if the word exists in the board; false otherwise.
     */
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == word.charAt(0)) {
                    visited = new boolean[board.length][board[0].length];
                    boolean exists = doDFS(i, j, 1);
                    if (exists) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
