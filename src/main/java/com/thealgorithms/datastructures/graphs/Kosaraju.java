package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class implements the Kosaraju Algorithm to find all the Strongly Connected Components (SCCs)
 * of a directed graph. Kosaraju's algorithm runs in linear time and leverages the concept that
 * the SCCs of a directed graph remain the same in its transpose (reverse) graph.
 *
 * <p>
 * A strongly connected component (SCC) of a directed graph is a subgraph where every vertex
 * is reachable from every other vertex in the subgraph. The Kosaraju algorithm is particularly
 * efficient for finding SCCs because it performs two Depth First Search (DFS) passes on the
 * graph and its transpose.
 * </p>
 *
 * <p><strong>Algorithm:</strong></p>
 * <ol>
 *     <li>Perform DFS on the original graph and push nodes to a stack in the order of their finishing time.</li>
 *     <li>Generate the transpose (reversed edges) of the original graph.</li>
 *     <li>Perform DFS on the transpose graph, using the stack from the first DFS. Each DFS run on the transpose graph gives a SCC.</li>
 * </ol>
 *
 * <p><strong>Example Graph:</strong></p>
 * <pre>
 * 0 <--- 2 -------> 3 -------- > 4 ---- > 7
 * |     ^                      | ^       ^
 * |    /                       |  \     /
 * |   /                        |   \   /
 * v  /                         v    \ /
 * 1                            5 --> 6
 * </pre>
 *
 * <p><strong>SCCs in the example:</strong></p>
 * <ul>
 *     <li>{0, 1, 2}</li>
 *     <li>{3}</li>
 *     <li>{4, 5, 6}</li>
 *     <li>{7}</li>
 * </ul>
 *
 * <p>The order of nodes in an SCC does not matter because every node in an SCC is reachable from every other node within the same SCC.</p>
 *
 * <p><strong>Graph Transpose Example:</strong></p>
 * <pre>
 * 0 ---> 2 <------- 3 <------- 4 <------ 7
 * ^     /                      ^ \       /
 * |    /                       |  \     /
 * |   /                        |   \   /
 * |  v                         |    v v
 * 1                            5 <--- 6
 * </pre>
 *
 * The SCCs of this transpose graph are the same as the original graph.
 */
public class Kosaraju {

    // Stack to sort edges by the lowest finish time (used in the first DFS)
    private final Stack<Integer> stack = new Stack<>();

    // Store each strongly connected component
    private List<Integer> scc = new ArrayList<>();

    // List of all SCCs
    private final List<List<Integer>> sccsList = new ArrayList<>();

    /**
     * Main function to perform Kosaraju's Algorithm.
     * Steps:
     * 1. Sort nodes by the lowest finishing time
     * 2. Create the transpose (reverse edges) of the original graph
     * 3. Find SCCs by performing DFS on the transpose graph
     * 4. Return the list of SCCs
     *
     * @param v the number of vertices in the graph
     * @param list the adjacency list representing the directed graph
     * @return a list of SCCs where each SCC is a list of vertices
     */
    public List<List<Integer>> kosaraju(int v, List<List<Integer>> list) {
        sortEdgesByLowestFinishTime(v, list);
        List<List<Integer>> transposeGraph = createTransposeMatrix(v, list);
        findStronglyConnectedComponents(v, transposeGraph);
        return sccsList;
    }

    /**
     * Performs DFS on the original graph to sort nodes by their finishing times.
     * @param v the number of vertices in the graph
     * @param list the adjacency list representing the original graph
     */
    private void sortEdgesByLowestFinishTime(int v, List<List<Integer>> list) {
        int[] vis = new int[v];
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, list);
            }
        }
    }

    /**
     * Creates the transpose (reverse) of the original graph.
     * @param v the number of vertices in the graph
     * @param list the adjacency list representing the original graph
     * @return the adjacency list representing the transposed graph
     */
    private List<List<Integer>> createTransposeMatrix(int v, List<List<Integer>> list) {
        List<List<Integer>> transposeGraph = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            transposeGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < v; i++) {
            for (Integer neigh : list.get(i)) {
                transposeGraph.get(neigh).add(i);
            }
        }
        return transposeGraph;
    }

    /**
     * Finds the strongly connected components (SCCs) by performing DFS on the transposed graph.
     * @param v the number of vertices in the graph
     * @param transposeGraph the adjacency list representing the transposed graph
     */
    public void findStronglyConnectedComponents(int v, List<List<Integer>> transposeGraph) {
        int[] vis = new int[v];
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (vis[node] == 0) {
                dfs2(node, vis, transposeGraph);
                sccsList.add(scc);
                scc = new ArrayList<>();
            }
        }
    }

    /**
     * Performs DFS on the original graph and pushes nodes onto the stack in order of their finish time.
     * @param node the current node being visited
     * @param vis array to keep track of visited nodes
     * @param list the adjacency list of the graph
     */
    private void dfs(int node, int[] vis, List<List<Integer>> list) {
        vis[node] = 1;
        for (Integer neighbour : list.get(node)) {
            if (vis[neighbour] == 0) {
                dfs(neighbour, vis, list);
            }
        }
        stack.push(node);
    }

    /**
     * Performs DFS on the transposed graph to find the strongly connected components.
     * @param node the current node being visited
     * @param vis array to keep track of visited nodes
     * @param list the adjacency list of the transposed graph
     */
    private void dfs2(int node, int[] vis, List<List<Integer>> list) {
        vis[node] = 1;
        for (Integer neighbour : list.get(node)) {
            if (vis[neighbour] == 0) {
                dfs2(neighbour, vis, list);
            }
        }
        scc.add(node);
    }
}
