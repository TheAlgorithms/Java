package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Java program that implements Tarjan's Algorithm to find Strongly Connected Components (SCCs) in a directed graph.
 *
 * <p>
 * Tarjan's algorithm is a linear time algorithm (O(V + E)) that identifies the SCCs of a directed graph.
 * An SCC is a maximal subgraph where every vertex is reachable from every other vertex within the subgraph.
 *
 * <h3>Algorithm Overview:</h3>
 * <ul>
 * <li>DFS Search: A depth-first search (DFS) is performed on the graph to generate a DFS tree.</li>
 * <li>Identification of SCCs: SCCs correspond to subtrees within this DFS tree.</li>
 * <li>Low-Link Values: For each node, a low-link value is maintained, which indicates the earliest visited
 * vertex (the one with the minimum insertion time) that can be reached from that subtree.</li>
 * <li>Stack Usage: Nodes are stored in a stack during DFS. When an SCC is identified, nodes are popped from
 * the stack until the head of the SCC is reached.</li>
 * </ul>
 *
 * <p>
 * Example of a directed graph:
 * <pre>
 *  0 --------> 1 -------> 3 --------> 4
 *  ^          /
 *  |         /
 *  |        /
 *  |       /
 *  |      /
 *  |     /
 *  |    /
 *  |   /
 *  |  /
 *  | /
 *  V
 *  2
 * </pre>
 *
 * <p>
 * For the above graph, the SCC list is as follows:
 * <ul>
 * <li>1, 2, 0</li>
 * <li>3</li>
 * <li>4</li>
 * </ul>
 * The order of nodes in an SCC does not matter as they form cycles.
 *
 * <h3>Comparison with Kosaraju's Algorithm:</h3>
 * <p>
 * Kosaraju's algorithm also identifies SCCs but does so using two DFS traversals.
 * In contrast, Tarjan's algorithm achieves this in a single DFS traversal, leading to improved performance
 * in terms of constant factors.
 * </p>
 */
public class TarjansAlgorithm {

    // Timer for tracking low time and insertion time
    private int time;

    // List to store all strongly connected components
    private final List<List<Integer>> sccList = new ArrayList<>();

    /**
     * Finds and returns the strongly connected components (SCCs) of the directed graph.
     *
     * @param v the number of vertices in the graph
     * @param graph the adjacency list representation of the graph
     * @return a list of lists, where each inner list represents a strongly connected component
     */
    public List<List<Integer>> stronglyConnectedComponents(int v, List<List<Integer>> graph) {
        // Initialize arrays for insertion time and low-link values
        int[] lowTime = new int[v];
        int[] insertionTime = new int[v];
        for (int i = 0; i < v; i++) {
            insertionTime[i] = -1;
            lowTime[i] = -1;
        }

        // Track if vertices are in the stack
        boolean[] isInStack = new boolean[v];

        // Stack to hold nodes during DFS
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < v; i++) {
            if (insertionTime[i] == -1) {
                stronglyConnCompsUtil(i, lowTime, insertionTime, isInStack, st, graph);
            }
        }

        return sccList;
    }

    /**
     * A utility function to perform DFS and find SCCs.
     *
     * @param u the current vertex being visited
     * @param lowTime array to keep track of the low-link values
     * @param insertionTime array to keep track of the insertion times
     * @param isInStack boolean array indicating if a vertex is in the stack
     * @param st the stack used for DFS
     * @param graph the adjacency list representation of the graph
     */
    private void stronglyConnCompsUtil(int u, int[] lowTime, int[] insertionTime, boolean[] isInStack, Stack<Integer> st, List<List<Integer>> graph) {
        // Set insertion time and low-link value
        insertionTime[u] = time;
        lowTime[u] = time;
        time++;

        // Push current node onto the stack
        isInStack[u] = true;
        st.push(u);

        // Explore adjacent vertices
        for (Integer vertex : graph.get(u)) {
            if (insertionTime[vertex] == -1) {
                stronglyConnCompsUtil(vertex, lowTime, insertionTime, isInStack, st, graph);
                // Update low-link value
                lowTime[u] = Math.min(lowTime[u], lowTime[vertex]);
            } else if (isInStack[vertex]) {
                // Vertex is in the stack; update low-link value
                lowTime[u] = Math.min(lowTime[u], insertionTime[vertex]);
            }
        }

        // Check if the current vertex is the root of an SCC
        if (lowTime[u] == insertionTime[u]) {
            int w = -1;
            List<Integer> scc = new ArrayList<>();

            // Pop vertices from the stack until the root is found
            while (w != u) {
                w = st.pop();
                scc.add(w);
                isInStack[w] = false;
            }
            sccList.add(scc);
        }
    }
}
