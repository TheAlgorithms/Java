package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class RatInAMazeTest {

    @Test
    void testMultiplePathsExist() {
        int[][] maze = {{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 0, 0}, {0, 1, 1, 1}};

        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.size() >= 1);
        for (String path : paths) {
            assertTrue(path.chars().allMatch(c -> "DLRU".indexOf(c) >= 0));
        }
    }

    @Test
    void testSinglePath() {
        int[][] maze = {{1, 0, 0}, {1, 1, 0}, {0, 1, 1}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertEquals(1, paths.size());
        assertEquals("DRDR", paths.get(0));
    }

    @Test
    void testNoPathExists() {
        int[][] maze = {{1, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.isEmpty());
    }

    @Test
    void testSourceBlocked() {
        int[][] maze = {{0, 1}, {1, 1}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.isEmpty());
    }

    @Test
    void testDestinationBlocked() {
        int[][] maze = {{1, 1}, {1, 0}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.isEmpty());
    }

    @Test
    void testSingleCellMazeOpen() {
        int[][] maze = {{1}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertEquals(1, paths.size());
        assertEquals("", paths.get(0));
    }

    @Test
    void testSingleCellMazeBlocked() {
        int[][] maze = {{0}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.isEmpty());
    }

    @Test
    void testNullMazeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RatInAMaze.findPaths(null));
    }

    @Test
    void testEmptyMazeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RatInAMaze.findPaths(new int[][] {}));
    }

    @Test
    void testNonSquareMazeThrowsException() {
        int[][] maze = {{1, 0, 1}, {1, 1, 1}};
        assertThrows(IllegalArgumentException.class, () -> RatInAMaze.findPaths(maze));
    }

    @Test
    void testAllCellsOpen() {
        int[][] maze = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.size() > 1);
    }

    @Test
    void testLargerMazeWithPath() {
        int[][] maze = {{1, 1, 1, 1}, {0, 1, 0, 1}, {0, 1, 0, 1}, {0, 1, 1, 1}};
        List<String> paths = RatInAMaze.findPaths(maze);
        assertTrue(paths.size() >= 1);
        for (String path : paths) {
            assertTrue(path.chars().allMatch(c -> "DLRU".indexOf(c) >= 0), "Path contains invalid characters: " + path);
        }
    }
}
