package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of <b>Topological Sort</b> using <b>Depth-First Search
 * (DFS)</b>.
 *
 * <p>
 * This algorithm returns a valid topological ordering of a directed acyclic
 * graph (DAG).
 * If a cycle is detected, meaning the graph cannot be topologically sorted,
 * it returns an empty array.
 *
 * <p>
 * <b>Use Case:</b> Determining the order of course completion based on
 * prerequisite dependencies
 * (commonly known as the “Course Schedule II” problem on LeetCode).
 * Problem link: <a href=
 * "https://leetcode.com/problems/course-schedule-ii/description/">LeetCode —
 * Course Schedule II</a>
 *
 * <p>
 * <b>Algorithm Overview:</b>
 * <ul>
 * <li>Each course (node) is visited using DFS.</li>
 * <li>During traversal, nodes currently in the recursion stack are tracked to
 * detect cycles.</li>
 * <li>When a node finishes processing, it is added to the output list.</li>
 * <li>The output list is then reversed to form a valid topological order.</li>
 * </ul>
 *
 * <p>
 * <b>Time Complexity:</b> O(V + E) — where V is the number of courses
 * (vertices),
 * and E is the number of prerequisite relations (edges).
 * <br>
 * <b>Space Complexity:</b> O(V + E) — for adjacency list, recursion stack, and
 * auxiliary sets.
 *
 * <p>
 * <b>Example:</b>
 *
 * <pre>
 * int numCourses = 4;
 * int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
 * TopologicalSortDFS topo = new TopologicalSortDFS();
 * int[] order = topo.findOrder(numCourses, prerequisites);
 * // Possible output: [0, 2, 1, 3]
 * </pre>
 *
 * @author Muhammad Junaid
 */
public class TopologicalSortDFS {

    /**
     * Finds a valid topological order of courses given prerequisite constraints.
     *
     * @param numCourses    the total number of courses labeled from 0 to numCourses
     *                      - 1
     * @param prerequisites an array of prerequisite pairs where each pair [a, b]
     *                      indicates that course {@code a} depends on course
     *                      {@code b}
     * @return an integer array representing one possible order to complete all
     *         courses;
     *         returns an empty array if it is impossible (i.e., a cycle exists)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> prereq = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            prereq.put(i, new ArrayList<>());
        }
        for (int[] pair : prerequisites) {
            int crs = pair[0];
            int pre = pair[1];
            prereq.get(crs).add(pre);
        }

        List<Integer> output = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();

        for (int c = 0; c < numCourses; c++) {
            if (!dfs(c, prereq, visited, cycle, output)) {
                return new int[0]; // Cycle detected — impossible order
            }
        }

        return output.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Performs a depth-first search to visit all prerequisites of a course.
     *
     * @param crs     the current course being visited
     * @param prereq  adjacency list mapping courses to their prerequisites
     * @param visited set of courses that have been completely processed
     * @param cycle   set of courses currently in the recursion stack (used for
     *                cycle detection)
     * @param output  list that accumulates the topological order in reverse
     * @return {@code true} if the current course and its prerequisites can be
     *         processed without cycles;
     *         {@code false} if a cycle is detected
     */
    private boolean dfs(int crs, Map<Integer, List<Integer>> prereq, Set<Integer> visited, Set<Integer> cycle, List<Integer> output) {

        if (cycle.contains(crs)) {
            return false; // Cycle detected
        }
        if (visited.contains(crs)) {
            return true; // Already processed
        }

        cycle.add(crs);
        for (int pre : prereq.get(crs)) {
            if (!dfs(pre, prereq, visited, cycle, output)) {
                return false;
            }
        }

        cycle.remove(crs);
        visited.add(crs);
        output.add(crs);
        return true;
    }
}
