package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Adaptive A* (A-star) pathfinding algorithm with semantic cost weighting.
 *
 * This implementation extends the classical A* algorithm by introducing
 * a semantic risk cost layer, as proposed in:
 *
 *
 *   Hong Yun, "An Adaptive Path Planning Method for Indoor and Outdoor
 *   Integrated Navigation," 2025 IEEE International Conference on Machine
 *   Learning and Intelligent Systems Engineering (MLISE 2025).
 *
 *
 * Cost Function
 *   f(n) = g(n) + h(n) + lambda * R_sem(n)
 *
 *
 *
 *   g(n) — actual cost from the start node to node n</li>
 *   h(n) — heuristic estimate from node n to the goal</li>
 *   lambda — global semantic weight multiplier</li>
 *   R_sem(n) — per-node semantic risk value
 *       (e.g., 2.0 for construction zones, 0.5 for sidewalks, 0.0 for normal)
 *
 *
 * The semantic cost enables the algorithm to prefer safer or more convenient
 * routes in indoor/outdoor navigation scenarios, such as avoiding construction
 * areas, preferring well-lit paths at night, or prioritizing barrier-free routes.
 *
 * When all semantic risk values are zero and lambda is zero, the algorithm
 * behaves identically to classical A*.
 *
 * Time Complexity: O((V + E) log V) where V is the number of vertices
 * and E is the number of edges. In the worst case, this reduces to O(E) when
 * the heuristic provides perfect guidance.
 *
 * @see <a href="https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/datastructures/graphs/AStar.java">
 *      Classical AStar (without semantic cost)</a>
 */
public final class AdaptiveAStar {

    private AdaptiveAStar() {
    }

    /**
     * Directed or undirected edge in the graph.
     */
    public static class Edge {
        private final int from;
        private final int to;
        private final int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    /**
     * Graph represented as an adjacency list.
     */
    public static class Graph {
        private final ArrayList<ArrayList<Edge>> adjacencyList;

        public Graph(int nodeCount) {
            this.adjacencyList = new ArrayList<>(nodeCount);
            for (int i = 0; i < nodeCount; i++) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }

        /**
         * Adds a bidirectional (undirected) edge.
         */
        public void addBidirectionalEdge(int from, int to, int weight) {
            adjacencyList.get(from).add(new Edge(from, to, weight));
            adjacencyList.get(to).add(new Edge(to, from, weight));
        }

        /**
         * Adds a directed edge.
         */
        public void addDirectedEdge(int from, int to, int weight) {
            adjacencyList.get(from).add(new Edge(from, to, weight));
        }

        public int nodeCount() {
            return adjacencyList.size();
        }

        public ArrayList<Edge> getNeighbors(int node) {
            return adjacencyList.get(node);
        }
    }

    /**
     * Holds the result of a pathfinding operation.
     */
    public static class PathResult {
        private final int totalCost;
        private final List<Integer> path;
        private final boolean found;

        public PathResult(int totalCost, List<Integer> path, boolean found) {
            this.totalCost = totalCost;
            this.path = path;
            this.found = found;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public List<Integer> getPath() {
            return path;
        }

        public boolean isFound() {
            return found;
        }
    }

    /**
     * Internal node wrapper used in the priority queue.
     */
    private static class NodeState {
        final int node;
        final int gCost;       // actual cost from start
        final int fCost;       // f(n) = g(n) + h(n) + lambda * R_sem(n)

        NodeState(int node, int gCost, int fCost) {
            this.node = node;
            this.gCost = gCost;
            this.fCost = fCost;
        }
    }

    /**
     * Runs the Adaptive A* algorithm.
     *
     * @param start        the starting node index
     * @param goal         the target node index
     * @param graph        the graph (adjacency list)
     * @param heuristic    heuristic values h[n] for each node (e.g., Euclidean distance to goal)
     * @param semanticRisk per-node semantic risk values (e.g., 0.0 = normal, 2.0 = construction zone)
     * @param lambda       global semantic weight multiplier
     * @return a {@link PathResult} containing the total cost and path if found
     */
    public static PathResult findPath(int start, int goal, Graph graph,
                                       int[] heuristic, double[] semanticRisk,
                                       double lambda) {
        int nodeCount = graph.nodeCount();
        if (start < 0 || start >= nodeCount || goal < 0 || goal >= nodeCount) {
            return new PathResult(-1, null, false);
        }

        // gCost[i] = actual cost from start to node i
        int[] gCost = new int[nodeCount];
        Arrays.fill(gCost, Integer.MAX_VALUE);
        gCost[start] = 0;

        // parent[i] = predecessor of node i on the best path
        int[] parent = new int[nodeCount];
        Arrays.fill(parent, -1);

        // closed[i] = true if node i has been fully explored
        boolean[] closed = new boolean[nodeCount];

        // Priority queue orders by fCost = gCost + heuristic + semantic penalty
        PriorityQueue<NodeState> openSet = new PriorityQueue<>(
            Comparator.comparingInt(ns -> ns.fCost));

        int initialFCost = computeFCost(0, heuristic[start],
            semanticRisk[start], lambda);
        openSet.add(new NodeState(start, 0, initialFCost));

        while (!openSet.isEmpty()) {
            NodeState current = openSet.poll();

            // If the current node is the goal, reconstruct and return the path
            if (current.node == goal) {
                List<Integer> path = reconstructPath(parent, goal);
                return new PathResult(current.gCost, path, true);
            }

            if (closed[current.node]) {
                continue;
            }
            closed[current.node] = true;

            // Expand neighbors
            for (Edge edge : graph.getNeighbors(current.node)) {
                int neighbor = edge.getTo();

                if (closed[neighbor]) {
                    continue;
                }

                int tentativeGCost = current.gCost + edge.getWeight();

                if (tentativeGCost < gCost[neighbor]) {
                    gCost[neighbor] = tentativeGCost;
                    parent[neighbor] = current.node;

                    int fCost = computeFCost(tentativeGCost, heuristic[neighbor],
                        semanticRisk[neighbor], lambda);
                    openSet.add(new NodeState(neighbor, tentativeGCost, fCost));
                }
            }
        }

        return new PathResult(-1, null, false);
    }

    /**
     * Computes the adaptive cost function:
     * <pre>f(n) = g(n) + h(n) + lambda * R_sem(n)</pre>
     *
     * @param gCost       actual cost from start to current node
     * @param heuristic   heuristic estimate to goal
     * @param semanticRisk per-node semantic risk
     * @param lambda      semantic weight multiplier
     * @return the total f-cost
     */
    private static int computeFCost(int gCost, int heuristic,
                                     double semanticRisk, double lambda) {
        int semanticPenalty = (int) Math.round(lambda * semanticRisk);
        return gCost + heuristic + semanticPenalty;
    }

    /**
     * Reconstructs the path from start to goal using the parent array.
     */
    private static List<Integer> reconstructPath(int[] parent, int goal) {
        List<Integer> path = new ArrayList<>();
        int current = goal;
        while (current != -1) {
            path.add(0, current);
            current = parent[current];
        }
        return path;
    }
}
