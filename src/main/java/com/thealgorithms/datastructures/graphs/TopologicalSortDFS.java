package com.thealgorithms.datastructures.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topological Sort using Depth-First Search (DFS).
 *
 * <p>A topological ordering of a directed acyclic graph (DAG) is a linear ordering
 * of its vertices such that for every directed edge u → v, vertex u appears
 * before vertex v in the ordering.
 *
 * <p>This implementation uses DFS with a 3-state visited array:
 * <ul>
 *   <li>UNVISITED – node has not been visited yet</li>
 *   <li>IN_PROGRESS – node is on the current DFS path (used for cycle detection)</li>
 *   <li>DONE – node and all its descendants are fully processed</li>
 * </ul>
 *
 * <p>Time Complexity: O(V + E), where V = vertices, E = edges
 * Space Complexity: O(V + E) for the adjacency list and recursion stack
 *
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological Sorting (Wikipedia)</a>
 */
public final class TopologicalSortDFS {

    public TopologicalSortDFS() {
    }

    private enum VisitState { UNVISITED, IN_PROGRESS, DONE }

    private final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    /**
     * Adds a directed edge from vertex {@code u} to vertex {@code v}.
     * Both vertices are added to the graph if not already present.
     *
     * @param u the source vertex
     * @param v the destination vertex
     */
    public void addEdge(int u, int v) {
        adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjacencyList.computeIfAbsent(v, k -> new ArrayList<>());
    }

    /**
     * Adds an isolated vertex (no edges) to the graph.
     *
     * @param v the vertex to add
     */
    public void addVertex(int v) {
        adjacencyList.computeIfAbsent(v, k -> new ArrayList<>());
    }

    /**
     * Performs a topological sort of the graph using DFS.
     *
     * @return a {@link List} of vertices in topologically sorted order
     * @throws IllegalStateException if the graph contains a cycle (i.e., is not a DAG)
     */
    public List<Integer> topologicalSort() {
        Map<Integer, VisitState> visitState = new HashMap<>();
        for (int vertex : adjacencyList.keySet()) {
            visitState.put(vertex, VisitState.UNVISITED);
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (int vertex : adjacencyList.keySet()) {
            if (visitState.get(vertex) == VisitState.UNVISITED) {
                dfs(vertex, visitState, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * Recursive DFS helper that pushes fully processed nodes onto the stack.
     *
     * @param vertex     the current vertex being visited
     * @param visitState map tracking the visit state of each vertex
     * @param stack      stack accumulating vertices in reverse finish order
     * @throws IllegalStateException if a back edge (cycle) is detected
     */
    private void dfs(int vertex, Map<Integer, VisitState> visitState, Deque<Integer> stack) {
        visitState.put(vertex, VisitState.IN_PROGRESS);

        for (int neighbor : adjacencyList.get(vertex)) {
            VisitState state = visitState.get(neighbor);
            if (state == VisitState.IN_PROGRESS) {
                throw new IllegalStateException("Graph contains a cycle. Topological sort is not possible.");
            }
            if (state == VisitState.UNVISITED) {
                dfs(neighbor, visitState, stack);
            }
        }

        visitState.put(vertex, VisitState.DONE);
        stack.push(vertex);
    }
}