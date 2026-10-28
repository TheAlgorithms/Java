package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Rat in a Maze Problem using Backtracking.
 *
 * <p>Given an {@code n x n} binary maze where {@code 1} represents an open cell
 * and {@code 0} represents a blocked cell, find all paths for a rat starting at
 * the top-left cell {@code (0, 0)} to reach the bottom-right cell {@code (n-1, n-1)}.
 *
 * <p>The rat can move in four directions: Up (U), Down (D), Left (L), Right (R).
 * Each cell may be visited at most once per path.
 *
 * <p>Time Complexity: O(4^(n²)) in the worst case (four choices per cell).
 * Space Complexity: O(n²) for the visited matrix and recursion stack.
 *
 * <p>Example:
 * <pre>
 *   maze = { {1, 0, 0, 0},
 *            {1, 1, 0, 1},
 *            {0, 1, 0, 0},
 *            {0, 1, 1, 1} }
 *   Output: ["DDRDRR", "DRDDRR"]  (two valid paths)
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Maze_solving_algorithm">Maze solving algorithm</a>
 * @author the-Sunny-Sharma (<a href="https://github.com/the-Sunny-Sharma">GitHub</a>)
 */
public final class RatInAMaze {

    private RatInAMaze() {
    }

    /**
     * Finds all paths from the top-left to the bottom-right of the given maze.
     *
     * @param maze an {@code n x n} binary matrix where {@code 1} = open, {@code 0} = blocked
     * @return a sorted list of all valid path strings using directions D, L, R, U;
     *         an empty list if no path exists
     * @throws IllegalArgumentException if the maze is null, empty, or not square
     */
    public static List<String> findPaths(final int[][] maze) {
        if (maze == null || maze.length == 0) {
            throw new IllegalArgumentException("Maze must not be null or empty.");
        }
        int n = maze.length;
        for (int[] row : maze) {
            if (row.length != n) {
                throw new IllegalArgumentException("Maze must be a square (n x n) matrix.");
            }
        }
        List<String> results = new ArrayList<>();
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) {
            return results;
        }
        boolean[][] visited = new boolean[n][n];
        solve(maze, 0, 0, n, "", visited, results);
        return results;
    }

    /**
     * Recursive backtracking helper that explores all four directions.
     *
     * @param maze    the binary maze
     * @param row     current row position
     * @param col     current column position
     * @param n       maze dimension
     * @param path    path string built so far
     * @param visited tracks visited cells for the current path
     * @param results accumulates complete paths
     */
    private static void solve(final int[][] maze, final int row, final int col, final int n, final String path, final boolean[][] visited, final List<String> results) {
        // Base case: reached destination
        if (row == n - 1 && col == n - 1) {
            results.add(path);
            return;
        }

        // Mark current cell as visited
        visited[row][col] = true;

        // Explore in alphabetical order: Down, Left, Right, Up
        // Down
        if (isSafe(maze, row + 1, col, n, visited)) {
            solve(maze, row + 1, col, n, path + 'D', visited, results);
        }
        // Left
        if (isSafe(maze, row, col - 1, n, visited)) {
            solve(maze, row, col - 1, n, path + 'L', visited, results);
        }
        // Right
        if (isSafe(maze, row, col + 1, n, visited)) {
            solve(maze, row, col + 1, n, path + 'R', visited, results);
        }
        // Up
        if (isSafe(maze, row - 1, col, n, visited)) {
            solve(maze, row - 1, col, n, path + 'U', visited, results);
        }

        // Backtrack: unmark current cell
        visited[row][col] = false;
    }

    /**
     * Checks whether moving to {@code (row, col)} is valid.
     *
     * @param maze    the binary maze
     * @param row     target row
     * @param col     target column
     * @param n       maze dimension
     * @param visited tracks visited cells for the current path
     * @return {@code true} if the cell is within bounds, open, and not yet visited
     */
    private static boolean isSafe(final int[][] maze, final int row, final int col, final int n, final boolean[][] visited) {
        return row >= 0 && row < n && col >= 0 && col < n && maze[row][col] == 1 && !visited[row][col];
    }
}
