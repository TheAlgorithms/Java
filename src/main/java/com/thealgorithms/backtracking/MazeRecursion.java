package com.thealgorithms.backtracking;

public class MazeRecursion {

    public static void mazeRecursion() {
        // First create a 2 dimensions array to mimic a maze map
        int[][] map = new int[8][7];
        int[][] map2 = new int[8][7];

        // We use 1 to indicate wall
        // Set the ceiling and floor to 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // Then we set the left and right wall to 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // Now we have created a maze with its wall initialized

        // Here we set the obstacle
        map[3][1] = 1;
        map[3][2] = 1;

        // Print the current map
        System.out.println("The condition of the map： ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // clone another map for setWay2 method
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map2[i][j] = map[i][j];
            }
        }

        // By using recursive backtracking to let your ball(target) find its way in the
        // maze
        // The first parameter is the map
        // Second parameter is x coordinate of your target
        // Thrid parameter is the y coordinate of your target
        setWay(map, 1, 1);
        setWay2(map2, 1, 1);

        // Print out the new map1, with the ball footprint
        System.out.println(
            "After the ball goes through the map1，show the current map1 condition"
        );
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // Print out the new map2, with the ball footprint
        System.out.println(
            "After the ball goes through the map2，show the current map2 condition"
        );
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map2[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Using recursive path finding to help the ball find its way in the maze
     * Description：
     * 1. map (means the maze)
     * 2. i, j (means the initial coordinate of the ball in the maze)
     * 3. if the ball can reach the end of maze, that is position of map[6][5],
     * means the we have found a path for the ball
     * 4. Additional Information： 0 in the map[i][j] means the ball has not gone
     * through this position, 1 means the wall, 2 means the path is feasible, 3
     * means the ball has gone through the path but this path is dead end
     * 5. We will need strategy for the ball to pass through the maze for example:
     * Down -> Right -> Up -> Left, if the path doesn't work, then backtrack
     * 
     * @author OngLipWei
     * @version Jun 23, 2021 11:36:14 AM
     * @param map The maze
     * @param i   x coordinate of your ball(target)
     * @param j   y coordinate of your ball(target)
     * @return If we did find a path for the ball，return true，else false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // means the ball find its path, ending condition
            return true;
        }
        if (map[i][j] == 0) { // if the ball haven't gone through this point
            // then the ball follows the move strategy : down -> right -> up -> left
            map[i][j] = 2; // we assume that this path is feasible first, set the current point to 2 first。
            if (setWay(map, i + 1, j)) { // go down
                return true;
            } else if (setWay(map, i, j + 1)) { // go right
                return true;
            } else if (setWay(map, i - 1, j)) { // go up
                return true;
            } else if (setWay(map, i, j - 1)) { // go left
                return true;
            } else {
                // means that the current point is the dead end, the ball cannot proceed, set
                // the current point to 3 and return false, the backtraking will start, it will
                // go to the previous step and check for feasible path again
                map[i][j] = 3;
                return false;
            }
        } else { // if the map[i][j] != 0 , it will probably be 1,2,3, return false because the
            // ball cannot hit the wall, cannot go to the path that has gone though before,
            // and cannot head to deadend.
            return false;
        }
    }

    // Here is another move strategy for the ball: up->right->down->left
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // means the ball find its path, ending condition
            return true;
        }
        if (map[i][j] == 0) { // if the ball haven't gone through this point
            // then the ball follows the move strategy : up->right->down->left
            map[i][j] = 2; // we assume that this path is feasible first, set the current point to 2 first。
            if (setWay2(map, i - 1, j)) { // go up
                return true;
            } else if (setWay2(map, i, j + 1)) { // go right
                return true;
            } else if (setWay2(map, i + 1, j)) { // go down
                return true;
            } else if (setWay2(map, i, j - 1)) { // go left
                return true;
            } else {
                // means that the current point is the dead end, the ball cannot proceed, set
                // the current point to 3 and return false, the backtraking will start, it will
                // go to the previous step and check for feasible path again
                map[i][j] = 3;
                return false;
            }
        } else { // if the map[i][j] != 0 , it will probably be 1,2,3, return false because the
            // ball cannot hit the wall, cannot go to the path that has gone though before,
            // and cannot head to deadend.
            return false;
        }
    }
}
