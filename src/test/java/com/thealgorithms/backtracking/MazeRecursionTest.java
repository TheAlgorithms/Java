package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author onglipwei
 * @create 2022-08-03 5:17 AM
 */
public class MazeRecursionTest {

    @Test
    public void testMaze() {
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

        // clone another map for setWay2 method
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map2[i][j] = map[i][j];
            }
        }

        MazeRecursion.setWay(map, 1, 1);
        MazeRecursion.setWay2(map2, 1, 1);

        int[][] expectedMap = new int[][] {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 1},
            {1, 2, 2, 2, 0, 0, 1},
            {1, 1, 1, 2, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 1},
            {1, 0, 0, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1},
        };

        int[][] expectedMap2 = new int[][] {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 1},
            {1, 0, 0, 0, 0, 2, 1},
            {1, 1, 1, 0, 0, 2, 1},
            {1, 0, 0, 0, 0, 2, 1},
            {1, 0, 0, 0, 0, 2, 1},
            {1, 0, 0, 0, 0, 2, 1},
            {1, 1, 1, 1, 1, 1, 1},
        };

        assertArrayEquals(map, expectedMap);
        assertArrayEquals(map2, expectedMap2);
    }
}
