package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The EdmondsBlossomAlgorithm class implements Edmonds' Blossom Algorithm
 * to find the maximum matching in a general graph. The algorithm efficiently
 * handles cases where the graph contains odd-length cycles by contracting
 * "blossoms" and finding augmenting paths.
 *<p>
 * <a href="https://stanford.edu/~rezab/classes/cme323/S16/projects_reports/shoemaker_vare.pdf">Documentation of Algorithm (Stanford University)</a>
 * <p></p>
 * <a href="https://en.wikipedia.org/wiki/Blossom_algorithm">Wikipedia Documentation</a>
 */
public final class EdmondsBlossomAlgorithm {

    private EdmondsBlossomAlgorithm() {
    }

    private static final int UNMATCHED = -1; // Constant to represent unmatched vertices

    /**
     * Finds the maximum matching in a general graph (Edmonds Blossom Algorithm).
     *
     * @param edges A list of edges in the graph.
     * @param vertexCount The number of vertices in the graph.
     * @return A list of matched pairs of vertices.
     */
    public static List<int[]> maximumMatching(Iterable<int[]> edges, int vertexCount) {
        List<List<Integer>> graph = new ArrayList<>(vertexCount);

        // Initialize each vertex's adjacency list.
        for (int i = 0; i < vertexCount; i++) {
            graph.add(new ArrayList<>());
        }

        // Populate the graph with the edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
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
                    for (int y : graph.get(current)) {
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
                                contractBlossom(new BlossomData(new BlossomAuxData(queue, parent, base, inBlossom, match, inQueue), current, y, baseU));
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
        int currentU = u;
        while (true) {
            currentU = base[currentU]; // Move assignment out of the condition
            visited[currentU] = true;
            if (parent[currentU] == UNMATCHED) {
                break;
            }
            currentU = parent[currentU]; // Move assignment out of the condition
        }

        // Find the common ancestor of v
        int currentV = v;
        while (true) {
            currentV = base[currentV]; // Move assignment out of the condition
            if (visited[currentV]) {
                return currentV;
            }
            currentV = parent[currentV]; // Move assignment out of the condition
        }
    }

    /**
     * Contracts a blossom and updates the base array.
     *
     * @param blossomData The data containing the parameters related to the blossom contraction.
     */
    private static void contractBlossom(BlossomData blossomData) {
        for (int x = blossomData.u; blossomData.auxData.base[x] != blossomData.lca; x = blossomData.auxData.parent[blossomData.auxData.match[x]]) {
            int baseX = blossomData.auxData.base[x];
            int matchBaseX = blossomData.auxData.base[blossomData.auxData.match[x]];

            // Split the inner assignment into two separate assignments
            blossomData.auxData.inBlossom[baseX] = true;
            blossomData.auxData.inBlossom[matchBaseX] = true;
        }

        for (int x = blossomData.v; blossomData.auxData.base[x] != blossomData.lca; x = blossomData.auxData.parent[blossomData.auxData.match[x]]) {
            int baseX = blossomData.auxData.base[x];
            int matchBaseX = blossomData.auxData.base[blossomData.auxData.match[x]];

            // Split the inner assignment into two separate assignments
            blossomData.auxData.inBlossom[baseX] = true;
            blossomData.auxData.inBlossom[matchBaseX] = true;
        }

        // Update the base for all marked vertices
        for (int i = 0; i < blossomData.auxData.base.length; i++) {
            if (blossomData.auxData.inBlossom[blossomData.auxData.base[i]]) {
                blossomData.auxData.base[i] = blossomData.lca; // Contract to the lowest common ancestor
                if (!blossomData.auxData.inQueue[i]) {
                    blossomData.auxData.queue.add(i); // Add to queue if not already present
                    blossomData.auxData.inQueue[i] = true;
                }
            }
        }
    }

    /**
     * Auxiliary data class to encapsulate common parameters for the blossom operations.
     */
    static class BlossomAuxData {
        Queue<Integer> queue; // Queue for BFS traversal
        int[] parent; // Parent array to store the paths
        int[] base; // Base array to track the base of each vertex
        boolean[] inBlossom; // Flags to indicate if a vertex is in a blossom
        int[] match; // Array to store matches for each vertex
        boolean[] inQueue; // Flags to track vertices in the BFS queue

        BlossomAuxData(Queue<Integer> queue, int[] parent, int[] base, boolean[] inBlossom, int[] match, boolean[] inQueue) {
            this.queue = queue;
            this.parent = parent;
            this.base = base;
            this.inBlossom = inBlossom;
            this.match = match;
            this.inQueue = inQueue;
        }
    }

    /**
     * BlossomData class with reduced parameters.
     */
    static class BlossomData {
        BlossomAuxData auxData; // Use the auxiliary data class
        int u; // One vertex in the edge
        int v; // Another vertex in the edge
        int lca; // Lowest Common Ancestor

        BlossomData(BlossomAuxData auxData, int u, int v, int lca) {
            this.auxData = auxData;
            this.u = u;
            this.v = v;
            this.lca = lca;
        }
    }
}
