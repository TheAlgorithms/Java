package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Java program that implements Kosaraju Algorithm.
 * @author Shivanagouda S A (https://github.com/shivu2002a)
 *
 */

/**
 * Kosaraju algorithm is a linear time algorithm to find the strongly connected components of a
   directed graph, which, from here onwards will be referred by SCC. It leverages the fact that the
 transpose graph (same graph with all the edges reversed) has exactly the same SCCs as the original
 graph.

 * A graph is said to be strongly connected if every vertex is reachable from every other vertex.
   The SCCs of a directed graph form a partition into subgraphs that are themselves strongly
 connected. Single node is always a SCC.

 * Example:

    0 <--- 2 -------> 3 -------- > 4 ---- > 7
    |     ^                      | ^       ^
    |    /                       |  \     /
    |   /                        |   \   /
    v  /                         v    \ /
    1                            5 --> 6

    For the above graph, the SCC list goes as follows:
    0, 1, 2
    3
    4, 5, 6
    7

    We can also see that order of the nodes in an SCC doesn't matter since they are in cycle.

 {@summary}
 * Kosaraju Algorithm:
    1. Perform DFS traversal of the graph. Push node to stack before returning. This gives edges
 sorted by lowest finish time.
    2. Find the transpose graph by reversing the edges.
    3. Pop nodes one by one from the stack and again to DFS on the modified graph.

    The transpose graph of the above graph:
     0 ---> 2 <------- 3 <------- 4 <------ 7
    ^     /                      ^ \       /
    |    /                       |  \     /
    |   /                        |   \   /
    |  v                         |    v v
    1                            5 <--- 6

    We can observe that this graph has the same SCC as that of original graph.

 */

public class Kosaraju {

    // Sort edges according to lowest finish time
    Stack<Integer> stack = new Stack<Integer>();

    // Store each component
    private List<Integer> scc = new ArrayList<>();

    // All the strongly connected components
    private List<List<Integer>> sccsList = new ArrayList<>();

    /**
     *
     * @param v Node count
     * @param list Adjacency list of graph
     * @return List of SCCs
     */
    public List<List<Integer>> kosaraju(int v, List<List<Integer>> list) {

        sortEdgesByLowestFinishTime(v, list);

        List<List<Integer>> transposeGraph = createTransposeMatrix(v, list);

        findStronglyConnectedComponents(v, transposeGraph);

        return sccsList;
    }

    private void sortEdgesByLowestFinishTime(int v, List<List<Integer>> list) {
        int[] vis = new int[v];
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, list);
            }
        }
    }

    private List<List<Integer>> createTransposeMatrix(int v, List<List<Integer>> list) {
        var transposeGraph = new ArrayList<List<Integer>>(v);
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
     *
     * @param v Node count
     * @param transposeGraph Transpose of the given adjacency list
     */
    public void findStronglyConnectedComponents(int v, List<List<Integer>> transposeGraph) {
        int[] vis = new int[v];
        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (vis[node] == 0) {
                dfs2(node, vis, transposeGraph);
                sccsList.add(scc);
                scc = new ArrayList<>();
            }
        }
    }

    // Dfs to store the nodes in order of lowest finish time
    private void dfs(int node, int[] vis, List<List<Integer>> list) {
        vis[node] = 1;
        for (Integer neighbour : list.get(node)) {
            if (vis[neighbour] == 0) dfs(neighbour, vis, list);
        }
        stack.push(node);
    }

    // Dfs to find all the nodes of each strongly connected component
    private void dfs2(int node, int[] vis, List<List<Integer>> list) {
        vis[node] = 1;
        for (Integer neighbour : list.get(node)) {
            if (vis[neighbour] == 0) dfs2(neighbour, vis, list);
        }
        scc.add(node);
    }
}
