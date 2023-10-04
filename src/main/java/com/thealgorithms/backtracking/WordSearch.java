package com.thealgorithms.backtracking;

/*
Word Search Problem (https://en.wikipedia.org/wiki/Word_search)

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

/*
   Solution
   Depth First Search in matrix (as multiple sources possible) with backtracking
   like finding cycle in a directed graph. Maintain a record of path

   Tx = O(m * n * 3^L): for each cell, we look at 3 options (not 4 as that one will be visited), we
   do it L times Sx = O(L) : stack size is max L
*/

public class WordSearch {
    private final int[] dx = {0, 0, 1, -1};
    private final int[] dy = {1, -1, 0, 0};
    private boolean[][] visited;
    private char[][] board;
    private String word;

    private boolean isValid(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

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
                if (exists) return true;
            }
        }
        visited[x][y] = false;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == word.charAt(0)) {
                    visited = new boolean[board.length][board[0].length];
                    boolean exists = doDFS(i, j, 1);
                    if (exists) return true;
                }
            }
        }
        return false;
    }
}
