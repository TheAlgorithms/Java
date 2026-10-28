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

    /**
     * Performs Depth First Search (DFS) from the cell (x, y)
     * to search for the next character in the word.
     *
     * @param x       The current row index.
     * @param y       The current column index.
     * @param idx The index of the next character in the word to be matched.
     * @return True if a valid path is found to match the remaining characters of the word; false otherwise.
     */

    private boolean dfs(char[][] board, int x, int y, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }

        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != word.charAt(idx)) {
            return false;
        }

        char temp = board[x][y];
        board[x][y] = '#';

        boolean found = dfs(board, x + 1, y, word, idx + 1) || dfs(board, x - 1, y, word, idx + 1) || dfs(board, x, y + 1, word, idx + 1) || dfs(board, x, y - 1, word, idx + 1);

        board[x][y] = temp;

        return found;
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

        int m = board.length;
        int n = board[0].length;

        // DFS search
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }
}
