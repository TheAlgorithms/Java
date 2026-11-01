package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
/**
 * @author onglipwei
 * @create 2022-08-03 5:17 AM
 */
public class MazeRecursionTest {


    @Test
    public void testNullMaze() {
        int[][] result = MazeRecursion.solveMazeUsingFirstStrategy(null);

        assertNull(result);
    }

    @Test
    public void testSmallMazeDoesNotThrowException() {
        int[][] map = {
                {0, 0},
                {0, 0}
        };

        int[][] result = MazeRecursion.solveMazeUsingFirstStrategy(map);

        assertArrayEquals(map, result);
    }

    @Test
    public void testCustomStartAndDestination() {
        int[][] map = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        int[][] result = MazeRecursion.solveMazeUsingFirstStrategy(
                map, 1, 1, 2, 3
        );

        assertNotNull(result);
        assertEquals(2, result[1][1]);
        assertEquals(2, result[2][3]);
    }

    @Test
    public void testInvalidCoordinates() {
        int[][] map = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        int[][] result = MazeRecursion.solveMazeUsingFirstStrategy(
                map, 10, 10, 1, 1
        );

        assertNull(result);
    }

    @Test
    public void testEmptyMaze() {
        int[][] map = new int[0][0];

        int[][] result = MazeRecursion.solveMazeUsingFirstStrategy(map);

        assertNull(result);
    }

    @Test
    public void testSolveMazeUsingFirstAndSecondStrategy() {
        int[][] map = new int[8][7];
        int[][] map2 = new int[8][7];

        // We use 1 to indicate walls
        // Set the ceiling and floor to 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // Set the left and right wall to 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // Set obstacles
        map[3][1] = 1;
        map[3][2] = 1;

        // Clone the original map for the second pathfinding strategy
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, map2[i], 0, map[i].length);
        }

        // Solve the maze using the first strategy
        int[][] solvedMap1 = MazeRecursion.solveMazeUsingFirstStrategy(map);
        // Solve the maze using the second strategy
        int[][] solvedMap2 = MazeRecursion.solveMazeUsingSecondStrategy(map2);
        int[][] expectedMap1 = new int[][] {
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

        // Assert the results
        assertArrayEquals(expectedMap1, solvedMap1);
        assertArrayEquals(expectedMap2, solvedMap2);
    }

}
