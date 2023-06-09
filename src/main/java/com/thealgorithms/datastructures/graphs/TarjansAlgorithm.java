package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Java program that implements Tarjan's Algorithm.
 * @author Shivanagouda S A (https://github.com/shivu2002a)
 *
 */

/**
 * Tarjan's algorithm is a linear time algorithm to find the strongly connected components of a
   directed graph, which, from here onwards will be referred as SCC.

 * A graph is said to be strongly connected if every vertex is reachable from every other vertex.
   The SCCs of a directed graph form a partition into subgraphs that are themselves strongly
 connected. Single node is always a SCC.

 * Example:
    0 --------> 1 -------> 3 --------> 4
    ^          /
    |         /
    |        /
    |       /
    |      /
    |     /
    |    /
    |   /
    |  /
    | /
    |V
    2

    For the above graph, the SCC list goes as follows:
    1, 2, 0
    3
    4

    We can also see that order of the nodes in an SCC doesn't matter since they are in cycle.

 {@summary}
    Tarjan's Algorithm:
    * DFS search produces a DFS tree
    * Strongly Connected Components form subtrees of the DFS tree.
    * If we can find the head of these subtrees, we can get all the nodes in that subtree (including
 the head) and that will be one SCC.
    * There is no back edge from one SCC to another (here can be cross edges, but they will not be
 used).

    * Kosaraju Algorithm aims at doing the same but uses two DFS traversalse whereas Tarjan’s
 algorithm does the same in a single DFS, which leads to much lower constant factors in the latter.

 */
public class TarjansAlgorithm {

    // Timer for tracking lowtime and insertion time
    private int Time;

    private List<List<Integer>> SCClist = new ArrayList<List<Integer>>();

    public List<List<Integer>> stronglyConnectedComponents(int V, List<List<Integer>> graph) {

        // Initially all vertices as unvisited, insertion and low time are undefined

        // insertionTime:Time when a node is visited 1st time while DFS traversal

        // lowTime: indicates the earliest visited vertex (the vertex with minimum insertion time)
        // that can be reached from a subtree rooted with a particular node.
        int[] lowTime = new int[V];
        int[] insertionTime = new int[V];
        for (int i = 0; i < V; i++) {
            insertionTime[i] = -1;
            lowTime[i] = -1;
        }

        // To check if element is present in stack
        boolean[] isInStack = new boolean[V];

        // Store nodes during DFS
        Stack<Integer> st = new Stack<Integer>();

        for (int i = 0; i < V; i++) {
            if (insertionTime[i] == -1) stronglyConnCompsUtil(i, lowTime, insertionTime, isInStack, st, graph);
        }

        return SCClist;
    }

    private void stronglyConnCompsUtil(int u, int[] lowTime, int[] insertionTime, boolean[] isInStack, Stack<Integer> st, List<List<Integer>> graph) {

        // Initialize insertion time and lowTime value of current node
        insertionTime[u] = Time;
        lowTime[u] = Time;
        Time += 1;

        // Push current node into stack
        isInStack[u] = true;
        st.push(u);

        // Go through all vertices adjacent to this
        for (Integer vertex : graph.get(u)) {
            // If the adjacent node is unvisited, do DFS
            if (insertionTime[vertex] == -1) {
                stronglyConnCompsUtil(vertex, lowTime, insertionTime, isInStack, st, graph);
                // update lowTime for the current node comparing lowtime of adj node
                lowTime[u] = Math.min(lowTime[u], lowTime[vertex]);
            } else if (isInStack[vertex]) {
                // If adj node is in stack, update low
                lowTime[u] = Math.min(lowTime[u], insertionTime[vertex]);
            }
        }
        // If lowtime and insertion time are same, current node is the head of an SCC
        //  head node found, get all the nodes in this SCC
        if (lowTime[u] == insertionTime[u]) {
            int w = -1;
            var scc = new ArrayList<Integer>();

            // Stack has all the nodes of the current SCC
            while (w != u) {
                w = st.pop();
                scc.add(w);
                isInStack[w] = false;
            }
            SCClist.add(scc);
        }
    }
}
