package com.thealgorithms.backtracking;

/**
 * This class contains methods to solve a maze using recursive backtracking.
 * The maze is represented as a 2D array where walls, paths, and visited/dead
 * ends are marked with different integers.
 */
public final class MazeRecursion {
    private static final int OPEN = 0;
    private static final int PATH = 2;
    private static final int DEAD_END = 3;

    private static final int DEFAULT_START_ROW = 1;
    private static final int DEFAULT_START_COLUMN = 1;
    private static final int DEFAULT_GOAL_ROW = 6;
    private static final int DEFAULT_GOAL_COLUMN = 5;

    // Each entry is {rowDelta, colDelta}
    // down -> right -> up -> left
    private static final int[][] FIRST_STRATEGY_DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    // up -> right -> down -> left
    private static final int[][] SECOND_STRATEGY_DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private MazeRecursion() {
    }

    /**
     * Solves the maze using the "down -> right -> up -> left" movement strategy,
     * starting at (1, 1) and targeting (6, 5).
     *
     * @param map the 2D array representing the maze (walls, paths, etc.)
     * @return the solved maze with paths marked, or null if no solution exists or a fixed endpoint is outside the maze
     * @throws IllegalArgumentException if the maze is null, empty, or not rectangular
     */
    public static int[][] solveMazeUsingFirstStrategy(int[][] map) {
        return solveWithDefaultEndpoints(map, FIRST_STRATEGY_DIRECTIONS);
    }

    /**
     * Solves the maze using the "up -> right -> down -> left" movement strategy,
     * starting at (1, 1) and targeting (6, 5).
     *
     * @param map the 2D array representing the maze (walls, paths, etc.)
     * @return the solved maze with paths marked, or null if no solution exists or a fixed endpoint is outside the maze
     * @throws IllegalArgumentException if the maze is null, empty, or not rectangular
     */
    public static int[][] solveMazeUsingSecondStrategy(int[][] map) {
        return solveWithDefaultEndpoints(map, SECOND_STRATEGY_DIRECTIONS);
    }

    /**
     * Solves the maze using the "down -> right -> up -> left" movement strategy.
     *
     * @param map the 2D array representing the maze (0 = open, non-zero = blocked)
     * @param startRow the starting row
     * @param startColumn the starting column
     * @param goalRow the goal row
     * @param goalColumn the goal column
     * @return the solved maze with paths marked, or null if no solution exists
     * @throws IllegalArgumentException if the maze is null, empty, not rectangular,
     *                                  or a coordinate is outside the maze
     */
    public static int[][] solveMazeUsingFirstStrategy(int[][] map, int startRow, int startColumn, int goalRow, int goalColumn) {
        validateCoordinates(map, startRow, startColumn, goalRow, goalColumn);
        return solve(map, startRow, startColumn, goalRow, goalColumn, FIRST_STRATEGY_DIRECTIONS) ? map : null;
    }

    /**
     * Solves the maze using the "up -> right -> down -> left" movement strategy.
     *
     * @param map the 2D array representing the maze (0 = open, non-zero = blocked)
     * @param startRow the starting row
     * @param startColumn the starting column
     * @param goalRow the goal row
     * @param goalColumn the goal column
     * @return the solved maze with paths marked, or null if no solution exists
     * @throws IllegalArgumentException if the maze is null, empty, not rectangular,
     *                                  or a coordinate is outside the maze
     */
    public static int[][] solveMazeUsingSecondStrategy(int[][] map, int startRow, int startColumn, int goalRow, int goalColumn) {
        validateCoordinates(map, startRow, startColumn, goalRow, goalColumn);
        return solve(map, startRow, startColumn, goalRow, goalColumn, SECOND_STRATEGY_DIRECTIONS) ? map : null;
    }

    private static int[][] solveWithDefaultEndpoints(int[][] map, int[][] directions) {
        validateMaze(map);
        if (!isInBounds(map, DEFAULT_START_ROW, DEFAULT_START_COLUMN) || !isInBounds(map, DEFAULT_GOAL_ROW, DEFAULT_GOAL_COLUMN)) {
            return null;
        }
        if (map[DEFAULT_GOAL_ROW][DEFAULT_GOAL_COLUMN] == PATH) {
            return map;
        }
        return solve(map, DEFAULT_START_ROW, DEFAULT_START_COLUMN, DEFAULT_GOAL_ROW, DEFAULT_GOAL_COLUMN, directions) ? map : null;
    }

    private static boolean solve(int[][] map, int row, int column, int goalRow, int goalColumn, int[][] directions) {
        if (!isInBounds(map, row, column) || map[row][column] != OPEN) {
            return false;
        }

        map[row][column] = PATH;
        if (row == goalRow && column == goalColumn) {
            return true;
        }

        for (int[] direction : directions) {
            if (solve(map, row + direction[0], column + direction[1], goalRow, goalColumn, directions)) {
                return true;
            }
        }

        map[row][column] = DEAD_END;
        return false;
    }

    private static int validateMaze(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            throw new IllegalArgumentException("Maze must not be null or empty.");
        }

        int columnCount = map[0].length;
        for (int[] row : map) {
            if (row == null || row.length != columnCount) {
                throw new IllegalArgumentException("Maze must be a non-empty rectangular matrix.");
            }
        }
        return columnCount;
    }

    private static void validateCoordinates(int[][] map, int startRow, int startColumn, int goalRow, int goalColumn) {
        int columnCount = validateMaze(map);
        if (startRow < 0 || startRow >= map.length || startColumn < 0 || startColumn >= columnCount || goalRow < 0 || goalRow >= map.length || goalColumn < 0 || goalColumn >= columnCount) {
            throw new IllegalArgumentException("Start and goal coordinates must be inside the maze.");
        }
    }

    private static boolean isInBounds(int[][] map, int row, int column) {
        return row >= 0 && row < map.length && column >= 0 && column < map[row].length;
    }
}   
