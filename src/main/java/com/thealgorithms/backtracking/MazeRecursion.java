package com.thealgorithms.backtracking;

/**
 * This class contains methods to solve a maze using recursive backtracking.
 * The maze is represented as a 2D array where walls, paths, and visited/dead
 * ends are marked with different integers.
 */
public final class MazeRecursion {

    private MazeRecursion() {
    }

    /**
     * Solves the maze using the down -> right -> up -> left strategy.
     *
     * @param map The 2D array representing the maze.
     * @return The solved maze, or null if no solution exists.
     */
    public static int[][] solveMazeUsingFirstStrategy(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return null;
        }

        return solveMazeUsingFirstStrategy(
                map, 1, 1, map.length - 2, map[0].length - 2
        );
    }

    /**
     * Solves the maze using the up -> right -> down -> left strategy.
     *
     * @param map The 2D array representing the maze.
     * @return The solved maze, or null if no solution exists.
     */
    public static int[][] solveMazeUsingSecondStrategy(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return null;
        }

        return solveMazeUsingSecondStrategy(
                map, 1, 1, map.length - 2, map[0].length - 2
        );
    }

    /**
     * Solves the maze using the first strategy with custom coordinates.
     *
     * @param map The maze.
     * @param startRow Starting row.
     * @param startCol Starting column.
     * @param destinationRow Destination row.
     * @param destinationCol Destination column.
     * @return The solved maze, or null if no solution exists.
     */
    public static int[][] solveMazeUsingFirstStrategy(
            int[][] map,
            int startRow,
            int startCol,
            int destinationRow,
            int destinationCol) {

        int[][] directions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        return solveMaze(map, startRow, startCol,
                destinationRow, destinationCol, directions);
    }

    /**
     * Solves the maze using the second strategy with custom coordinates.
     *
     * @param map The maze.
     * @param startRow Starting row.
     * @param startCol Starting column.
     * @param destinationRow Destination row.
     * @param destinationCol Destination column.
     * @return The solved maze, or null if no solution exists.
     */
    public static int[][] solveMazeUsingSecondStrategy(
            int[][] map,
            int startRow,
            int startCol,
            int destinationRow,
            int destinationCol) {

        int[][] directions = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        return solveMaze(map, startRow, startCol,
                destinationRow, destinationCol, directions);
    }

    /**
     * Validates the maze and starts the recursive solving process.
     */
    private static int[][] solveMaze(
            int[][] map,
            int startRow,
            int startCol,
            int destinationRow,
            int destinationCol,
            int[][] directions) {

        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return null;
        }

        int columns = map[0].length;

        for (int[] row : map) {
            if (row == null || row.length != columns) {
                return null;
            }
        }

        if (startRow < 0 || startRow >= map.length
                || startCol < 0 || startCol >= columns
                || destinationRow < 0 || destinationRow >= map.length
                || destinationCol < 0 || destinationCol >= columns) {
            return null;
        }

        if (solveMazeRecursive(map, startRow, startCol,
                destinationRow, destinationCol, directions)) {
            return map;
        }

        return null;
    }

    /**
     * Recursively explores the maze using the provided movement directions.
     */
    private static boolean solveMazeRecursive(
            int[][] map,
            int row,
            int col,
            int destinationRow,
            int destinationCol,
            int[][] directions) {

        if (map[row][col] != 0) {
            return false;
        }

        map[row][col] = 2;

        if (row == destinationRow && col == destinationCol) {
            return true;
        }

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (nextRow >= 0 && nextRow < map.length
                    && nextCol >= 0 && nextCol < map[0].length) {

                if (solveMazeRecursive(map, nextRow, nextCol,
                        destinationRow, destinationCol, directions)) {
                    return true;
                }
            }
        }

        map[row][col] = 3;
        return false;
    }
}
