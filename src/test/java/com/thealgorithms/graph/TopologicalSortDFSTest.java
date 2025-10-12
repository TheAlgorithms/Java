package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopologicalSortDFSTest {
    private TopologicalSortDFS topologicalSortDFS;

    @BeforeEach
    public void setUp() {
        topologicalSortDFS = new TopologicalSortDFS();
    }

    @Test
    public void testSimpleCase() {
        // Example: Two courses where 1 depends on 0
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        int[] expected = {0, 1};

        int[] result = topologicalSortDFS.findOrder(numCourses, prerequisites);

        assertArrayEquals(expected, result, "Expected order is [0, 1].");
    }

    @Test
    public void testMultipleDependencies() {
        // Example: 4 courses with dependencies
        // 1 -> 0, 2 -> 0, 3 -> 1, 3 -> 2
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result = topologicalSortDFS.findOrder(numCourses, prerequisites);

        // Valid answers could be [0,1,2,3] or [0,2,1,3]
        int[] expected = {0, 1, 2, 3};
        assertArrayEquals(expected, result, "Valid topological order expected, e.g., [0,1,2,3] or [0,2,1,3].");
    }

    @Test
    public void testNoDependencies() {
        // Example: 3 courses with no dependencies
        int numCourses = 3;
        int[][] prerequisites = {};
        int[] expected = {0, 1, 2};

        int[] result = topologicalSortDFS.findOrder(numCourses, prerequisites);

        assertArrayEquals(expected, result, "Any order is valid when there are no dependencies.");
    }

    @Test
    public void testCycleGraph() {
        // Example: A cycle exists (0 -> 1 -> 0)
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}, {1, 0}};
        int[] expected = {};

        int[] result = topologicalSortDFS.findOrder(numCourses, prerequisites);

        assertArrayEquals(expected, result, "Cycle detected, no valid course order.");
    }

    @Test
    public void testComplexGraph() {
        // Complex example: 6 courses
        // Dependencies: 5->2, 5->0, 4->0, 4->1, 2->3, 3->1
        int numCourses = 6;
        int[][] prerequisites = {{2, 5}, {0, 5}, {0, 4}, {1, 4}, {3, 2}, {1, 3}};
        int[] result = topologicalSortDFS.findOrder(numCourses, prerequisites);
        // Valid order: [5, 4, 2, 3, 1, 0]
        int[] expected = {5, 4, 0, 2, 3, 1};
        assertArrayEquals(expected, result, "Valid topological order expected such as [5, 4, 0, 2, 3, 1].");
    }
}
