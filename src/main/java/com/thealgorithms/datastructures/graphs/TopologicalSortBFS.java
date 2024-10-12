package com.thealgorithms.datastructures.graphs;

import java.util.*;

/**
 * This class provides an implementation of Topological Sort using BFS (Kahn's
 * Algorithm)
 * for the Course Schedule problem (LeetCode 207).
 *
 * @author Lochan Paudel
 */
public class TopologicalSortBFS {

    /**
     * This method performs topological sort using BFS to determine if all courses
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

        // Initialize graph and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        // Build the graph (adjacency list) and fill in-degree array
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }

        // Initialize queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Add courses with no prerequisites (in-degree 0) to the queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Number of courses processed
        int processedCourses = 0;

        // Process courses in BFS order
        while (!queue.isEmpty()) {
            int course = queue.poll();
            processedCourses++;

            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If we've processed all courses, return true, otherwise return false (cycle
        // detected)
        return processedCourses == numCourses;
    }

    /**
     * Main method for testing the Topological Sort BFS solution.
     */
    public static void main(String[] args) {
        TopologicalSortBFS ts = new TopologicalSortBFS();

        // Test cases
        System.out.println(ts.canFinish(2, new int[][] { { 1, 0 } })); // true
        System.out.println(ts.canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } })); // false
    }
}
