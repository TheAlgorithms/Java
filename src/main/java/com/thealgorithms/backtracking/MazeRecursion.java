package com.thealgorithms.backtracking;

/**
 * This class contains methods to solve a maze using recursive backtracking.
 * The maze is represented as a 2D array where walls, paths, and visited/dead
 * ends
 * are marked with different integers.
 *
 * The goal is to find a path from a starting position to the target position
 * (map[6][5]) while navigating through the maze.
 */
public final class MazeRecursion {

    private MazeRecursion() {
    }

    /**
     * This method sets up a maze as a 2D array, where '1' represents walls and '0'
     * represents open paths. It then calls recursive functions to find paths using
     * two different movement strategies. The results are printed to the console.
     */
    public static void mazeRecursion() {

        int[][] map = new int[8][7]; // Create an 8x7 maze map (2D array)
        int[][] map2 = new int[8][7]; // Copy of the maze for an alternative pathfinding method

        // Initialize the maze with boundaries (set walls as '1')
        // Set the top and bottom rows as walls
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // Set the left and right columns as walls
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // Place internal obstacles in the maze
        map[3][1] = 1; // Wall block at position (3,1)
        map[3][2] = 1; // Wall block at position (3,2)

        System.out.println("Initial maze layout:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // Clone the maze into map2 for the second pathfinding method
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, map2[i], 0, map[i].length);
        }

        // Use the first pathfinding method with a "down -> right -> up -> left" strategy
        setWay(map, 1, 1);

        // Use the second pathfinding method with a "up -> right -> down -> left" strategy
        setWay2(map2, 1, 1);

        // Print the maze after pathfinding using the first method
        System.out.println("Maze after pathfinding using first strategy:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // Print the maze after pathfinding using the second method
        System.out.println("Maze after pathfinding using second strategy:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map2[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Attempts to find a path through the maze using a "down -> right -> up ->
     * left" movement strategy. The ball tries to reach position (6,5), and the path is
     * marked with '2' for valid paths and '3' for dead ends.
     *
     * @param map The 2D array representing the maze (walls, paths, etc.)
     * @param i   The current x-coordinate of the ball (row index)
     * @param j   The current y-coordinate of the ball (column index)
     * @return True if a path is found to (6,5), otherwise false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        }

        // If the current position is unvisited (0), explore it
        if (map[i][j] == 0) {
            // Assume the path is feasible, mark the current position as '2'
            map[i][j] = 2;

            // Move down
            if (setWay(map, i + 1, j)) {
                return true;
            }
            // Move right
            else if (setWay(map, i, j + 1)) {
                return true;
            }
            // Move up
            else if (setWay(map, i - 1, j)) {
                return true;
            }
            // Move left
            else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                // Mark the current position as a dead end (3) and backtrack
                map[i][j] = 3;
                return false;
            }
        } else {
            // If the position is not unvisited (either a wall, dead end, or already part of
            // the path), return false
            return false;
        }
    }

    /**
     * Attempts to find a path through the maze using an alternative movement
     * strategy "up -> right -> down -> left".
     * This method explores a different order of
     * movement compared to setWay().
     *
     * @param map The 2D array representing the maze (walls, paths, etc.)
     * @param i   The current x-coordinate of the ball (row index)
     * @param j   The current y-coordinate of the ball (column index)
     * @return True if a path is found to (6,5), otherwise false
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        // Check if the ball has reached the target at map[6][5]
        if (map[6][5] == 2) {
            return true; // Path found
        }

        // If the current position is unvisited (0), explore it
        if (map[i][j] == 0) {
            // Assume the path is feasible, mark the current position as '2'
            map[i][j] = 2;

            // Move up
            if (setWay2(map, i - 1, j)) {
                return true;
            }
            // Move right
            else if (setWay2(map, i, j + 1)) {
                return true;
            }
            // Move down
            else if (setWay2(map, i + 1, j)) {
                return true;
            }
            // Move left
            else if (setWay2(map, i, j - 1)) {
                return true;
            } else {
                // Mark the current position as a dead end (3) and backtrack
                map[i][j] = 3;
                return false;
            }
        } else {
            // If the position is not unvisited (either a wall, dead end, or already part of
            // the path), return false
            return false;
        }
    }
}
