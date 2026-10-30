package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author onglipwei
 * @create 2022-08-03 5:17 AM
 */
public class MazeRecursionTest {

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

        @Test
     void testSmallMazeWithCustomStartAndTarget() {
        int[][] map = {{0, 0}, {0, 0}};
        int[][] result = MazeRecursion.solveMazeUsingFirstStrategy(map, 0, 0, 1, 1);
        assertArrayEquals(new int[][] {{2, 0}, {2, 2}}, result); // down -> right
     }

     @Test
     void testSecondStrategyKeepsItsTraversalOrder() {
       int[][] map = {{0, 0}, {0, 0}};
       int[][] result = MazeRecursion.solveMazeUsingSecondStrategy(map, 0, 0, 1, 1);
        assertArrayEquals(new int[][] {{2, 2}, {0, 2}}, result); // right -> down
      }

     @Test
     void testDefaultTargetOutsideSmallMazeThrowsIllegalArgument() {
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingFirstStrategy(new int[][] {{0, 0}, {0, 0}}));
     }

     @Test
     void testNullAndEmptyMaze() {
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingFirstStrategy(null));
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingSecondStrategy(new int[][] {}));
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingFirstStrategy(new int[][] {{}}));
     }

     @Test
     void testJaggedMazeThrows() {
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingFirstStrategy(new int[][] {{0, 0}, {0}}, 0, 0, 0, 1)); 
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingSecondStrategy(new int[][] {{0}, {0, 0}}, 0, 0, 1, 1)); 
     }

     @Test
     void testStartOrTargetOutOfBoundsThrows() {
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingFirstStrategy(new int[][] {{0, 0}, {0, 0}}, -1, 0, 1, 1));
     assertThrows(IllegalArgumentException.class, () -> MazeRecursion.solveMazeUsingSecondStrategy(new int[][] {{0, 0}, {0, 0}}, 0, 0, 2, 2));
     }

     @Test
     void testUnsolvableMazeReturnsNull() {
     int[][] map = {{0, 1}, {1, 0}};
     assertNull(MazeRecursion.solveMazeUsingFirstStrategy(map, 0, 0, 1, 1)); 
     }
    }
} 

