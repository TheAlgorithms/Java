package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of Edmonds's algorithm (also known as the Chu–Liu/Edmonds algorithm)
 * for finding a Minimum Spanning Arborescence (MSA).
 *
 * <p>An MSA is a directed graph equivalent of a Minimum Spanning Tree. It is a tree rooted
 * at a specific vertex 'r' that reaches all other vertices, such that the sum of the
 * weights of its edges is minimized.
 *
 * <p>The algorithm works recursively:
 * <ol>
 * <li>For each vertex other than the root, select the incoming edge with the minimum weight.</li>
 * <li>If the selected edges form a spanning arborescence, it is the MSA.</li>
 * <li>If cycles are formed, contract each cycle into a new "supernode".</li>
 * <li>Modify the weights of edges entering the new supernode.</li>
 * <li>Recursively call the algorithm on the contracted graph.</li>
 * <li>The final cost is the sum of the initial edge selections and the result of the recursive call.</li>
 * </ol>
 *
 * <p>Time Complexity: O(E * V) where E is the number of edges and V is the number of vertices.
 *
 * <p>References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Edmonds%27_algorithm">Wikipedia: Edmonds's algorithm</a></li>
 * </ul>
 */
public final class Edmonds {

    private Edmonds() {
    }

    /**
     * Represents a directed weighted edge in the graph.
     */
    public static class Edge {
        final int from;
        final int to;
        final long weight;

        /**
         * Constructs a directed edge.
         *
         * @param from source vertex
         * @param to destination vertex
         * @param weight edge weight
         */
        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Computes the total weight of the Minimum Spanning Arborescence of a directed,
     * weighted graph from a given root.
     *
     * @param numVertices the number of vertices, labeled {@code 0..numVertices-1}
     * @param edges list of directed edges in the graph
     * @param root the root vertex
     * @return the total weight of the MSA. Returns -1 if not all vertices are reachable
     *         from the root or if a valid arborescence cannot be formed.
     * @throws IllegalArgumentException if {@code numVertices <= 0} or {@code root} is out of range.
     */
    public static long findMinimumSpanningArborescence(int numVertices, List<Edge> edges, int root) {
        if (root < 0 || root >= numVertices) {
            throw new IllegalArgumentException("Invalid number of vertices or root");
        }
        if (numVertices == 1) {
            return 0;
        }

        return findMSARecursive(numVertices, edges, root);
    }

    /**
     * Recursive helper method for finding MSA.
     */
    private static long findMSARecursive(int n, List<Edge> edges, int root) {
        long[] minWeightEdge = new long[n];
        int[] predecessor = new int[n];
        Arrays.fill(minWeightEdge, Long.MAX_VALUE);
        Arrays.fill(predecessor, -1);

        for (Edge edge : edges) {
            if (edge.to != root && edge.weight < minWeightEdge[edge.to]) {
                minWeightEdge[edge.to] = edge.weight;
                predecessor[edge.to] = edge.from;
            }
        }
        // Check if all non-root nodes are reachable
        for (int i = 0; i < n; i++) {
            if (i != root && minWeightEdge[i] == Long.MAX_VALUE) {
                return -1; // No spanning arborescence exists
            }
        }
        int[] cycleId = new int[n];
        Arrays.fill(cycleId, -1);
        boolean[] visited = new boolean[n];
        int cycleCount = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            List<Integer> path = new ArrayList<>();
            int curr = i;

            // Follow predecessor chain
            while (curr != -1 && !visited[curr]) {
                visited[curr] = true;
                path.add(curr);
                curr = predecessor[curr];
            }

            // If we hit a visited node, check if it forms a cycle
            if (curr != -1) {
                boolean inCycle = false;
                for (int node : path) {
                    if (node == curr) {
                        inCycle = true;
                    }
                    if (inCycle) {
                        cycleId[node] = cycleCount;
                    }
                }
                if (inCycle) {
                    cycleCount++;
                }
            }
        }
        if (cycleCount == 0) {
            long totalWeight = 0;
            for (int i = 0; i < n; i++) {
                if (i != root) {
                    totalWeight += minWeightEdge[i];
                }
            }
            return totalWeight;
        }
        long cycleWeightSum = 0;
        for (int i = 0; i < n; i++) {
            if (cycleId[i] >= 0) {
                cycleWeightSum += minWeightEdge[i];
            }
        }

        // Map old nodes to new nodes (cycles become supernodes)
        int[] newNodeMap = new int[n];
        int[] cycleToNewNode = new int[cycleCount];
        int newN = 0;

        // Assign new node IDs to cycles first
        for (int i = 0; i < cycleCount; i++) {
            cycleToNewNode[i] = newN++;
        }

        // Assign new node IDs to non-cycle nodes
        for (int i = 0; i < n; i++) {
            if (cycleId[i] == -1) {
                newNodeMap[i] = newN++;
            } else {
                newNodeMap[i] = cycleToNewNode[cycleId[i]];
            }
        }

        int newRoot = newNodeMap[root];

        // Build contracted graph
        List<Edge> newEdges = new ArrayList<>();
        for (Edge edge : edges) {
            int uCycleId = cycleId[edge.from];
            int vCycleId = cycleId[edge.to];

            // Skip edges internal to a cycle
            if (uCycleId >= 0 && uCycleId == vCycleId) {
                continue;
            }

            int newU = newNodeMap[edge.from];
            int newV = newNodeMap[edge.to];

            long newWeight = edge.weight;
            // Adjust weight for edges entering a cycle
            if (vCycleId >= 0) {
                newWeight -= minWeightEdge[edge.to];
            }

            if (newU != newV) {
                newEdges.add(new Edge(newU, newV, newWeight));
            }
        }
        return cycleWeightSum + findMSARecursive(newN, newEdges, newRoot);
    }
}
