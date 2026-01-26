package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds all possible simple paths from a given source vertex to a destination vertex
 * in a directed graph using backtracking.
 *
 * <p>This algorithm performs a Depth First Search (DFS) traversal while keeping track
 * of visited vertices to avoid cycles. Whenever the destination vertex is reached,
 * the current path is stored as one valid path.</p>
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li>Works on directed graphs</li>
 *   <li>Does not allow revisiting vertices in the same path</li>
 *   <li>Stores all possible paths from source to destination</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *   <li>Worst Case: O(V!) — when the graph is fully connected</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b></p>
 * <ul>
 *   <li>O(V) for recursion stack and visited array</li>
 *   <li>Additional space for storing all valid paths</li>
 * </ul>
 *
 * <p>This implementation is intended for educational purposes.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth First Search</a>
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class AllPathsFromSourceToTarget {

    // No. of vertices in graph
    private final int v;

    // To store the paths from source to destination
    static List<List<Integer>> nm = new ArrayList<>();
    // adjacency list
    private ArrayList<Integer>[] adjList;

    // Constructor
    public AllPathsFromSourceToTarget(int vertices) {

        // initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
    }

    // utility method to initialise adjacency list
    private void initAdjList() {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v) {
        // Add v to u's list.
        adjList[u].add(v);
    }

    public void storeAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);
        // Call recursive utility
        storeAllPathsUtil(s, d, isVisited, pathList);
    }

    // A recursive function to print all paths from 'u' to 'd'.
    // isVisited[] keeps track of vertices in current path.
    // localPathList<> stores actual vertices in the current path
    private void storeAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {

        if (u.equals(d)) {
            nm.add(new ArrayList<>(localPathList));
            return;
        }

        // Mark the current node
        isVisited[u] = true;

        // Recursion for all the vertices adjacent to current vertex

        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node in path[]
                localPathList.add(i);
                storeAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    // Driver program
    public static List<List<Integer>> allPathsFromSourceToTarget(int vertices, int[][] a, int source, int destination) {
        // Create a sample graph
        AllPathsFromSourceToTarget g = new AllPathsFromSourceToTarget(vertices);
        for (int[] i : a) {
            g.addEdge(i[0], i[1]);
            // edges are added
        }
        g.storeAllPaths(source, destination);
        // method call to store all possible paths
        return nm;
        // returns all possible paths from source to destination
    }
}
