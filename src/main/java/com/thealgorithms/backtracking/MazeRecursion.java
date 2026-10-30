package com.thealgorithms.backtracking;

/**
 * This class contains methods to solve a maze using recursive backtracking.
 * The maze is represented as a 2D array where walls, paths, and visited/dead
 * ends are marked with different integers.
 *
 * Cell values: 0 = open, 1 = wall, 2 = path, 3 = dead end.
 */
public final class MazeRecursion {

    private static final int OPEN = 0;
    private static final int PATH = 2;
    private static final int DEAD_END = 3;

    private static final int DEFAULT_START_ROW = 1;
    private static final int DEFAULT_START_COL = 1;
    private static final int DEFAULT_TARGET_ROW = 6;
    private static final int DEFAULT_TARGET_COL = 5;

    // Each entry is {rowDelta, colDelta}
    // down -> right -> up -> left
    private static final int[][] FIRST_STRATEGY = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    // up -> right -> down -> left
    private static final int[][] SECOND_STRATEGY = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private MazeRecursion() {
    }

    /**
     * Solves the maze from (1,1) to (6,5) using "down -> right -> up -> left".
     *
     * @param map the maze
     * @return the solved maze, or null if no solution exists
     * @throws IllegalArgumentException if the maze is invalid or the default
     *                                  start/target lie outside the maze
     */
    public static int[][] solveMazeUsingFirstStrategy(int[][] map) {
        return solveMazeUsingFirstStrategy(map, DEFAULT_START_ROW, DEFAULT_START_COL, DEFAULT_TARGET_ROW, DEFAULT_TARGET_COL);
    }

    /**
     * Solves the maze from (1,1) to (6,5) using "up -> right -> down -> left".
     *
     * @param map the maze
     * @return the solved maze, or null if no solution exists
     * @throws IllegalArgumentException if the maze is invalid or the default
     *                                  start/target lie outside the maze
     */
    public static int[][] solveMazeUsingSecondStrategy(int[][] map) {
        return solveMazeUsingSecondStrategy(map, DEFAULT_START_ROW, DEFAULT_START_COL, DEFAULT_TARGET_ROW, DEFAULT_TARGET_COL);
    }

    /**
     * Solves the maze between custom positions using "down -> right -> up -> left".
     *
     * @param map         the maze
     * @param startRow    start row index
     * @param startCol    start column index
     * @param targetRow   target row index
     * @param targetCol   target column index
     * @return the solved maze, or null if no solution exists
     * @throws IllegalArgumentException if the maze is null, empty, jagged, or
     *                                  start/target are outside the maze
     */
    public static int[][] solveMazeUsingFirstStrategy(int[][] map, int startRow, int startCol, int targetRow, int targetCol) {
        return solve(map, startRow, startCol, targetRow, targetCol, FIRST_STRATEGY);
    }

    /**
     * Solves the maze between custom positions using "up -> right -> down -> left".
     *
     * @param map         the maze
     * @param startRow    start row index
     * @param startCol    start column index
     * @param targetRow   target row index
     * @param targetCol   target column index
     * @return the solved maze, or null if no solution exists
     * @throws IllegalArgumentException if the maze is null, empty, jagged, or
     *                                  start/target are outside the maze
     */
    public static int[][] solveMazeUsingSecondStrategy(int[][] map, int startRow, int startCol, int targetRow, int targetCol) {
        return solve(map, startRow, startCol, targetRow, targetCol, SECOND_STRATEGY);
    }

    private static int[][] solve(int[][] map, int startRow, int startCol, int targetRow, int targetCol, int[][] directions) {
        validate(map, startRow, startCol, targetRow, targetCol);
        return setWay(map, startRow, startCol, targetRow, targetCol, directions) ? map : null;
    }

    private static void validate(int[][] map, int startRow, int startCol, int targetRow, int targetCol) {
        if (map == null || map.length == 0) {
            throw new IllegalArgumentException("Maze must not be null or empty");
        }
        int cols = map[0] == null ? 0 : map[0].length;
        if (cols == 0) {
            throw new IllegalArgumentException("Maze rows must not be null or empty");
        }
        for (int[] row : map) {
            if (row == null || row.length != cols) {
                throw new IllegalArgumentException("Maze must be rectangular");
            }
        }
        if (!isInside(map, startRow, startCol)) {
            throw new IllegalArgumentException("Start position is outside the maze");
        }
        if (!isInside(map, targetRow, targetCol)) {
            throw new IllegalArgumentException("Target position is outside the maze");
        }
    }

    private static boolean isInside(int[][] map, int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }

    /**
     * Shared recursive backtracking logic used by both strategies.
     * Marks the route with 2 and dead ends with 3.
     */
    private static boolean setWay(int[][] map, int row, int col, int targetRow, int targetCol, int[][] directions) {
        if (!isInside(map, row, col) || map[row][col] != OPEN) {
            return false;
        }

        map[row][col] = PATH;

        if (row == targetRow && col == targetCol) {
            return true;
        }

        for (int[] direction : directions) {
            if (setWay(map, row + direction[0], col + direction[1], targetRow, targetCol, directions)) {
                return true;
            }
        }

        map[row][col] = DEAD_END;
        return false;
    }
} 