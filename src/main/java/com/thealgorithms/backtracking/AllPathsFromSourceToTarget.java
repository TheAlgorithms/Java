package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Program description - To find all possible paths from source to destination
 * <a href="https://en.wikipedia.org/wiki/Shortest_path_problem">Wikipedia</a>
 *
 * @author <a href="https://github.com/siddhant2002">Siddhant Swarup Mallick</a>
 */
public final class AllPathsFromSourceToTarget {

    private final int vertexCount;
    private final List<List<Integer>> allPaths;
    private final List<Integer>[] adjList;

    private AllPathsFromSourceToTarget(int vertices) {
        this.vertexCount = vertices;
        this.allPaths = new ArrayList<>();
        this.adjList = new List[vertices];
        initializeAdjacencyList();
    }

    /**
     * Initializes the adjacency list to represent a graph with the specified number of vertices.
     */
    private void initializeAdjacencyList() {
        for (int i = 0; i < vertexCount; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    /**
     * Adds a directed edge from source vertex to destination vertex in the graph.
     * @param source The starting vertex of the edge.
     * @param destination The ending vertex of the edge.
     */
    private void addEdge(int source, int destination) {
        adjList[source].add(destination);
    }

    /**
     * Finds all possible paths from a given source vertex to a destination vertex in the graph.
     * This method uses a backtracking approach to explore all possible paths.
     * @param source The starting vertex of the path search.
     * @param destination The ending vertex of the path search.
     * @return A list containing all the found paths represented as lists of vertex indices.
     */
    private List<List<Integer>> findAllPathsStart(int source, int destination) {
        boolean[] visited = new boolean[vertexCount];
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(source);
        findAllPathsUtil(source, destination, visited, currentPath);
        return allPaths;
    }

    /**
     * A recursive helper function that implements the backtracking logic to find all paths.
     * @param source The current vertex being explored in the path search.
     * @param destination The ending vertex of the path search.
     * @param visited A boolean array to keep track of visited vertices.
     * @param currentPath A list representing the current path being constructed.
     */
    private void findAllPathsUtil(int source, int destination, boolean[] visited, List<Integer> currentPath) {
        if (source == destination) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        visited[source] = true;

        for (int neighbor : adjList[source]) {
            if (!visited[neighbor]) {
                currentPath.add(neighbor);
                findAllPathsUtil(neighbor, destination, visited, currentPath);
                currentPath.removeLast();
            }
        }

        visited[source] = false;
    }

    /**
     * Finds all possible paths from a given source vertex to a destination vertex in the graph.
     * This method uses a backtracking approach to explore all possible paths.
     * The found paths are stored in the `allPaths` member variable.
     *
     * @param source The starting vertex of the path search.
     * @param destination The ending vertex of the path search.
     * @return A list containing all the found paths represented as lists of vertex indices.
     */
    public static List<List<Integer>> findAllPaths(int vertices, int[][] a, int source, int destination) {
        AllPathsFromSourceToTarget g = new AllPathsFromSourceToTarget(vertices);
        for (int[] i : a) {
            g.addEdge(i[0], i[1]);
        }
        return g.findAllPathsStart(source, destination);
    }
}
