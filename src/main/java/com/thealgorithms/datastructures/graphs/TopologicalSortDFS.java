package com.thealgorithms.datastructures.graphs;

import java.util.*;

/**
 * This class provides an implementation of Topological Sort using DFS
 * for the Course Schedule problem (LeetCode 207).
 *
 * @author Lochan Paudel
 */
public class TopologicalSortDFS {

    /**
     * This method performs topological sort using DFS to determine if all courses
     * can be completed.
     *
     * @param numCourses    Total number of courses
     * @param prerequisites A 2D array where prerequisites[i] = [a, b] indicates
     *                      that course b must be taken before course a
     * @return true if all courses can be finished, false otherwise
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Edge Case: No courses or no prerequisites
        if (numCourses == 0 || prerequisites == null) {
            return true;
        }

        // Initialize graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph (adjacency list)
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            graph.get(prerequisite).add(course);
        }

        // Array to track visiting and visited nodes
        int[] visited = new int[numCourses];

        // Perform DFS for all courses
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, visited)) {
                return false; // If a cycle is found, return false
            }
        }

        return true; // No cycle found, return true
    }

    /**
     * Helper method to perform DFS and detect cycles.
     *
     * @param course  The current course
     * @param graph   The adjacency list of the graph
     * @param visited Array to keep track of visit status (0 = unvisited, 1 =
     *                visiting, 2 = visited)
     * @return true if no cycle is detected, otherwise false
     */
    private boolean dfs(int course, List<List<Integer>> graph, int[] visited) {
        if (visited[course] == 1) {
            return false; // Cycle detected (back edge)
        }
        if (visited[course] == 2) {
            return true; // Already visited, no cycle here
        }

        visited[course] = 1; // Mark course as visiting

        for (int neighbor : graph.get(course)) {
            if (!dfs(neighbor, graph, visited)) {
                return false;
            }
        }

        visited[course] = 2; // Mark course as fully visited
        return true;
    }

    /**
     * Main method for testing the Topological Sort DFS solution.
     */
    public static void main(String[] args) {
        TopologicalSortDFS ts = new TopologicalSortDFS();

        // Test cases
        System.out.println(ts.canFinish(2, new int[][] { { 1, 0 } })); // true
        System.out.println(ts.canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } })); // false
    }
}
