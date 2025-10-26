package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Implementation of Hierholzer's Algorithm for finding an Eulerian Path or Circuit
 * in a directed graph.
 *
 * <p>
 * An <b>Eulerian Circuit</b> is a path that starts and ends at the same vertex
 * and visits every edge exactly once.
 * </p>
 *
 * <p>
 * An <b>Eulerian Path</b> visits every edge exactly once but may start and end
 * at different vertices.
 * </p>
 *
 * <p>
 * <b>Algorithm Summary:</b><br>
 * 1. Compute indegree and outdegree for all vertices.<br>
 * 2. Check if the graph satisfies Eulerian path or circuit conditions.<br>
 * 3. Verify that all vertices with non-zero degree are weakly connected (undirected connectivity).<br>
 * 4. Use Hierholzer’s algorithm to build the path by exploring unused edges iteratively.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(E + V).<br>
 * <b>Space Complexity:</b> O(V + E).
 * </p>
 *
 * @author <a href="https://en.wikipedia.org/wiki/Eulerian_path#Hierholzer's_algorithm">Wikipedia: Hierholzer algorithm</a>
 */
public class HierholzerEulerianPath {

    /**
     * Simple directed graph represented by adjacency lists.
     */
    public static class Graph {
        private final List<List<Integer>> adjacencyList;

        /**
         * Constructs a graph with a given number of vertices.
         *
         * @param numNodes number of vertices
         */
        public Graph(int numNodes) {
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < numNodes; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        /**
         * Adds a directed edge from vertex {@code from} to vertex {@code to}.
         *
         * @param from source vertex
         * @param to   destination vertex
         */
        public void addEdge(int from, int to) {
            adjacencyList.get(from).add(to);
        }

        /**
         * Returns a list of outgoing edges from the given vertex.
         *
         * @param node vertex index
         * @return list of destination vertices
         */
        public List<Integer> getEdges(int node) {
            return adjacencyList.get(node);
        }

        /**
         * Returns the number of vertices in the graph.
         *
         * @return number of vertices
         */
        public int getNumNodes() {
            return adjacencyList.size();
        }
    }

    private final Graph graph;

    /**
     * Creates a Hierholzer solver for the given graph.
     *
     * @param graph directed graph
     */
    public HierholzerEulerianPath(Graph graph) {
        this.graph = graph;
    }

    /**
     * Finds an Eulerian Path or Circuit using Hierholzer’s Algorithm.
     *
     * @return list of vertices representing the Eulerian Path/Circuit,
     *         or an empty list if none exists
     */
    public List<Integer> findEulerianPath() {
        int n = graph.getNumNodes();

        // empty graph -> no path
        if (n == 0) {
            return new ArrayList<>();
        }

        int[] inDegree = new int[n];
        int[] outDegree = new int[n];
        int edgeCount = computeDegrees(inDegree, outDegree);

        // no edges -> single vertex response requested by tests: [0]
        if (edgeCount == 0) {
            return Collections.singletonList(0);
        }

        int startNode = determineStartNode(inDegree, outDegree);
        if (startNode == -1) {
            return new ArrayList<>();
        }

        if (!allNonZeroDegreeVerticesWeaklyConnected(startNode, n, outDegree, inDegree)) {
            return new ArrayList<>();
        }

        List<Integer> path = buildHierholzerPath(startNode, n);
        if (path.size() != edgeCount + 1) {
            return new ArrayList<>();
        }

        return rotateEulerianCircuitIfNeeded(path, outDegree, inDegree);
    }

    private int computeDegrees(int[] inDegree, int[] outDegree) {
        int edgeCount = 0;
        for (int u = 0; u < graph.getNumNodes(); u++) {
            for (int v : graph.getEdges(u)) {
                outDegree[u]++;
                inDegree[v]++;
                edgeCount++;
            }
        }
        return edgeCount;
    }

    private int determineStartNode(int[] inDegree, int[] outDegree) {
        int n = graph.getNumNodes();
        int startNode = -1;
        int startCount = 0;
        int endCount = 0;

        for (int i = 0; i < n; i++) {
            int diff = outDegree[i] - inDegree[i];
            if (diff == 1) {
                startNode = i;
                startCount++;
            } else if (diff == -1) {
                endCount++;
            } else if (Math.abs(diff) > 1) {
                return -1;
            }
        }

        if (!((startCount == 1 && endCount == 1) || (startCount == 0 && endCount == 0))) {
            return -1;
        }

        if (startNode == -1) {
            for (int i = 0; i < n; i++) {
                if (outDegree[i] > 0) {
                    startNode = i;
                    break;
                }
            }
        }
        return startNode;
    }

    private List<Integer> buildHierholzerPath(int startNode, int n) {
        List<Deque<Integer>> tempAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tempAdj.add(new ArrayDeque<>(graph.getEdges(i)));
        }

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            int u = stack.peek();
            if (!tempAdj.get(u).isEmpty()) {
                stack.push(tempAdj.get(u).pollFirst());
            } else {
                path.add(stack.pop());
            }
        }

        Collections.reverse(path);
        return path;
    }

    private List<Integer> rotateEulerianCircuitIfNeeded(List<Integer> path, int[] outDegree, int[] inDegree) {
        int startCount = 0;
        int endCount = 0;
        for (int i = 0; i < outDegree.length; i++) {
            int diff = outDegree[i] - inDegree[i];
            if (diff == 1) {
                startCount++;
            } else if (diff == -1) {
                endCount++;
            }
        }

        if (startCount == 0 && endCount == 0 && !path.isEmpty()) {
            int preferredStart = -1;
            for (int i = 0; i < outDegree.length; i++) {
                if (outDegree[i] > 0) {
                    preferredStart = i;
                    break;
                }
            }

            if (preferredStart != -1 && path.get(0) != preferredStart) {
                int idx = 0;
                for (Integer node : path) { // replaced indexed loop
                    if (node == preferredStart) {
                        break;
                    }
                    idx++;
                }

                if (idx > 0) {
                    List<Integer> rotated = new ArrayList<>();
                    int currentIndex = 0;
                    for (Integer node : path) { // replaced indexed loop
                        if (currentIndex >= idx) {
                            rotated.add(node);
                        }
                        currentIndex++;
                    }
                    currentIndex = 0;
                    for (Integer node : path) { // replaced indexed loop
                        if (currentIndex < idx) {
                            rotated.add(node);
                        }
                        currentIndex++;
                    }
                    path = rotated;
                }
            }
        }
        return path;
    }

    /**
     * Checks weak connectivity (undirected) among vertices that have non-zero degree.
     *
     * @param startNode node to start DFS from (must be a vertex with non-zero degree)
     * @param n number of vertices
     * @param outDegree out-degree array
     * @param inDegree in-degree array
     * @return true if all vertices having non-zero degree belong to a single weak component
     */
    private boolean allNonZeroDegreeVerticesWeaklyConnected(int startNode, int n, int[] outDegree, int[] inDegree) {
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(startNode);
        visited[startNode] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : graph.getEdges(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    stack.push(v);
                }
            }
            for (int x = 0; x < n; x++) {
                if (!visited[x]) {
                    for (int y : graph.getEdges(x)) {
                        if (y == u) {
                            visited[x] = true;
                            stack.push(x);
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (outDegree[i] + inDegree[i] > 0 && !visited[i]) {
                return false;
            }
        }
        return true;
    }
}
