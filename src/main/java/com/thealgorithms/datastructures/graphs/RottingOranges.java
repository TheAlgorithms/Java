package com.thealgorithms.datastructures.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Multi-source Breadth-First Search (BFS) implementation for the Rotting Oranges problem.
 *
 * <p>Algorithm explanation:
 * https://en.wikipedia.org/wiki/Breadth-first_search
 *
 * <p>Problem reference:
 * https://leetcode.com/problems/rotting-oranges/
 *
 * <p>Given a grid where:
 * <ul>
 *     <li>0 represents an empty cell</li>
 *     <li>1 represents a fresh orange</li>
 *     <li>2 represents a rotten orange</li>
 * </ul>
 *
 * <p>Returns the minimum number of minutes required for all fresh oranges
 * to become rotten. Returns {@code -1} if it is impossible.
 *
 * <p>Time Complexity: O(m × n)
 * <br>Space Complexity: O(m × n)
 */
public class RottingOranges {

    private static final int[] DEL_ROW = {-1, 0, 1, 0};
    private static final int[] DEL_COL = {0, 1, 0, -1};

    private static final class Cell {
        private final int row;
        private final int col;
        private final int minute;

        Cell(int row, int col, int minute) {
            this.row = row;
            this.col = col;
            this.minute = minute;
        }
    }

    /**
     * Executes the Rotting Oranges algorithm.
     *
     * @param grid the input grid
     * @return minimum minutes required to rot all fresh oranges,
     *         or -1 if impossible
     */
    public int run(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // Create a copy so original input is not modified
        int[][] copy = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            copy[i] = grid[i].clone();
        }

        Queue<Cell> queue = new LinkedList<>();
        int freshOranges = 0;

        // Find all rotten oranges and count fresh oranges
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (copy[row][col] == 2) {
                    queue.offer(new Cell(row, col, 0));
                } else if (copy[row][col] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }

        int rottedFresh = 0;
        int minutes = 0;

        // Multi-source BFS
        while (!queue.isEmpty()) {

            Cell current = queue.poll();

            minutes = Math.max(minutes, current.minute);

            for (int i = 0; i < 4; i++) {

                int newRow = current.row + DEL_ROW[i];
                int newCol = current.col + DEL_COL[i];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && copy[newRow][newCol] == 1) {

                    copy[newRow][newCol] = 2;
                    rottedFresh++;

                    queue.offer(new Cell(newRow, newCol, current.minute + 1));
                }
            }
        }

        return rottedFresh == freshOranges ? minutes : -1;
    }
}
