package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The EdmondsBlossomAlgorithm class implements Edmonds' Blossom Algorithm
 * to find the maximum matching in a general graph. The algorithm efficiently
 * handles cases where the graph contains odd-length cycles by contracting
 * "blossoms" and finding augmenting paths.
 * <p>
 * The maximum matching problem seeks to find the largest set of vertex pairs
 * such that no vertex is part of more than one pair, which is especially useful
 * in graph theory problems like job assignments, scheduling, and network flows.
 * <p>
 * This implementation supports the following features:
 * - Handling general graphs (not restricted to bipartite graphs).
 * - Finding augmenting paths using BFS.
 * - Contracting blossoms to overcome odd-length cycles.
 * - Returning the list of matched vertex pairs.
 * <p>
 * Example:
 * For a triangle graph with edges {(0, 1), (1, 2), (2, 0)}, the algorithm
 * would find a maximum matching like {(0, 1)}, where vertex 2 is unmatched.
 * <p>
 * This class includes test cases in the main method to demonstrate how
 * the algorithm works on different graph structures, such as simple triangles,
 * squares, bipartite graphs, and graphs with more edges than vertices.
 */
public final class EdmondsBlossomAlgorithm {

    private EdmondsBlossomAlgorithm() {
        // Prevent instantiation of the utility class
    }

    private static final int UNMATCHED = -1;

    /**
     * Finds the maximum matching in a general graph (Edmonds Blossom Algorithm).
     *
     * @param edges A list of edges in the graph.
     * @param vertexCount The number of vertices in the graph.
     * @return A list of matched pairs of vertices.
     */
    public List<int[]> maximumMatching(List<int[]> edges, int vertexCount) {
        // Create adjacency list to represent the graph
        List<Integer>[] graph = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            graph[i] = new ArrayList<>();
        }

        // Populate the graph with the edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // Initial matching array and auxiliary data structures
        int[] match = new int[vertexCount];
        Arrays.fill(match, UNMATCHED); // All vertices are initially unmatched
        int[] parent = new int[vertexCount];
        int[] base = new int[vertexCount];
        boolean[] inBlossom = new boolean[vertexCount]; // Indicates if a vertex is part of a blossom
        boolean[] inQueue = new boolean[vertexCount]; // Tracks vertices in the BFS queue

        // Main logic for finding maximum matching
        for (int u = 0; u < vertexCount; u++) {
            if (match[u] == UNMATCHED) {
                // BFS initialization
                Arrays.fill(parent, UNMATCHED);
                for (int i = 0; i < vertexCount; i++) {
                    base[i] = i; // Each vertex is its own base initially
                }
                Arrays.fill(inBlossom, false);
                Arrays.fill(inQueue, false);

                Queue<Integer> queue = new LinkedList<>();
                queue.add(u);
                inQueue[u] = true;

                boolean augmentingPathFound = false;

                // BFS to find augmenting paths
                while (!queue.isEmpty() && !augmentingPathFound) {
                    int current = queue.poll(); // Use a different name for clarity
                    for (int y : graph[current]) {
                        // Skip if we are looking at the same edge as the current match
                        if (match[current] == y) {
                            continue;
                        }

                        if (base[current] == base[y]) {
                            continue; // Avoid self-loops
                        }

                        if (parent[y] == UNMATCHED) {
                            // Case 1: y is unmatched, we've found an augmenting path
                            if (match[y] == UNMATCHED) {
                                parent[y] = current;
                                augmentingPathFound = true;
                                updateMatching(match, parent, y); // Augment along this path
                                break;
                            }

                            // Case 2: y is matched, add y's match to the queue
                            int z = match[y];
                            parent[y] = current;
                            parent[z] = y;
                            if (!inQueue[z]) {
                                queue.add(z);
                                inQueue[z] = true;
                            }
                        } else {
                            // Case 3: Both x and y have a parent; check for a cycle/blossom
                            int baseU = findBase(base, parent, current, y);
                            if (baseU != UNMATCHED) {
                                contractBlossom(new BlossomData(queue, parent, base, inBlossom, match, inQueue, current, y, baseU));
                            }
                        }
                    }
                }
            }
        }

        // Create result list of matched pairs
        List<int[]> matchingResult = new ArrayList<>();
        for (int v = 0; v < vertexCount; v++) {
            if (match[v] != UNMATCHED && v < match[v]) {
                matchingResult.add(new int[] {v, match[v]});
            }
        }

        return matchingResult;
    }

    /**
     * Updates the matching along the augmenting path found.
     *
     * @param match The matching array.
     * @param parent The parent array used during the BFS.
     * @param u The starting node of the augmenting path.
     */
    private static void updateMatching(int[] match, int[] parent, int u) {
        while (u != UNMATCHED) {
            int v = parent[u];
            int next = match[v];
            match[v] = u;
            match[u] = v;
            u = next;
        }
    }

    /**
     * Finds the base of a node in the blossom.
     *
     * @param base The base array.
     * @param parent The parent array.
     * @param u One end of the edge.
     * @param v The other end of the edge.
     * @return The base of the node or UNMATCHED.
     */
    private static int findBase(int[] base, int[] parent, int u, int v) {
        boolean[] visited = new boolean[base.length];

        // Mark ancestors of u
        int currentU = u; // Use a temporary variable
        while (true) {
            currentU = base[currentU]; // Assign to the temporary variable
            visited[currentU] = true; // Mark as visited
            if (parent[currentU] == UNMATCHED) {
                break;
            }
            currentU = parent[currentU]; // Reassign to the temporary variable
        }

        // Find the common ancestor of v
        int currentV = v; // Use a temporary variable
        while (true) {
            currentV = base[currentV]; // Assign to the temporary variable
            if (visited[currentV]) {
                return currentV; // The first visited node is the lowest common ancestor
            }
            currentV = parent[currentV]; // Reassign to the temporary variable
        }
    }

    /**
     * Contracts a blossom and updates the base array.
     *
     * @param blossomData The data containing the parameters related to the blossom contraction.
     */
    private static void contractBlossom(BlossomData blossomData) {
        for (int x = blossomData.u; blossomData.base[x] != blossomData.lca; x = blossomData.parent[blossomData.match[x]]) {
            blossomData.inBlossom[blossomData.base[x]] = blossomData.inBlossom[blossomData.base[blossomData.match[x]]] = true; // Mark blossom vertices
        }
        for (int x = blossomData.v; blossomData.base[x] != blossomData.lca; x = blossomData.parent[blossomData.match[x]]) {
            blossomData.inBlossom[blossomData.base[x]] = blossomData.inBlossom[blossomData.base[blossomData.match[x]]] = true; // Mark blossom vertices
        }

        // Update the base for all marked vertices
        for (int i = 0; i < blossomData.base.length; i++) {
            if (blossomData.inBlossom[blossomData.base[i]]) {
                blossomData.base[i] = blossomData.lca; // Contract to the lowest common ancestor
                if (!blossomData.inQueue[i]) {
                    blossomData.queue.add(i); // Add to queue if not already present
                    blossomData.inQueue[i] = true;
                }
            }
        }
    }

    /**
     * Helper method to print the matching results in a readable format.
     *
     * @param testCase The name of the test case being executed.
     * @param matching The list of matched pairs of vertices.
     */
    private static void printMatchingResult(String testCase, List<int[]> matching) {
        System.out.print(testCase + ": ");
        for (int[] match : matching) {
            System.out.print(Arrays.toString(match) + " ");
        }
        System.out.println();
    }

    /**
     * Runs test cases to demonstrate the functionality of the algorithm.
     */
    public static void runTests() {
        // Test Case 1: Simple triangle
        List<int[]> edges1 = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 0});
        int vertexCount1 = 3;
        printMatchingResult("Test Case 1", new EdmondsBlossomAlgorithm().maximumMatching(edges1, vertexCount1));

        // Test Case 2: Square shape
        List<int[]> edges2 = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 3}, new int[] {3, 0});
        int vertexCount2 = 4;
        printMatchingResult("Test Case 2", new EdmondsBlossomAlgorithm().maximumMatching(edges2, vertexCount2));

        // Test Case 3: Bipartite graph
        List<int[]> edges3 = Arrays.asList(new int[] {0, 2}, new int[] {0, 3}, new int[] {1, 2}, new int[] {1, 3});
        int vertexCount3 = 4;
        printMatchingResult("Test Case 3", new EdmondsBlossomAlgorithm().maximumMatching(edges3, vertexCount3));

        // Test Case 4: More edges than vertices
        List<int[]> edges4 = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {0, 2}, new int[] {1, 3}, new int[] {2, 3});
        int vertexCount4 = 4;
        printMatchingResult("Test Case 4", new EdmondsBlossomAlgorithm().maximumMatching(edges4, vertexCount4));
    }

    static class BlossomData {
        Queue<Integer> queue;
        int[] parent;
        int[] base;
        boolean[] inBlossom;
        int[] match;
        boolean[] inQueue;
        int u;
        int v;
        int lca;

        public BlossomData(Queue<Integer> queue, int[] parent, int[] base,
                           boolean[] inBlossom, int[] match, boolean[] inQueue,
                           int u, int v, int lca) {
            this.queue = queue;
            this.parent = parent;
            this.base = base;
            this.inBlossom = inBlossom;
            this.match = match;
            this.inQueue = inQueue;
            this.u = u;
            this.v = v;
            this.lca = lca;
        }
    }

}
