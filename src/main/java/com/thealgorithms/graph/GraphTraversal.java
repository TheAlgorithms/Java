package com.thealgorithms.graph;

import java.util.*;

/**
 * Graph traversals and utilities.
 *
 * <p>This class includes a DFS variant that visits a successor only when
 * <b>all</b> of its predecessors have already been visited. For each step,
 * it emits an event either for a visit (with an increasing order) or for a skip
 * explaining that not all parents were visited yet.</p>
 *
 * <p>Input is an adjacency list of successors. If a predecessor map is not
 * provided, it is derived once from the successors map.</p>
 */
public final class GraphTraversal {

    private GraphTraversal() {
        // utility class
    }

    /** An event emitted by the traversal: either a VISIT with an order, or a SKIP with a note. */
    public static final class TraversalEvent<T> {
        private final T node;
        private final Integer order; // non-null for visit, null for skip
        private final String note;   // non-null for skip, null for visit

        private TraversalEvent(T node, Integer order, String note) {
            this.node = node;
            this.order = order;
            this.note = note;
        }

        /** A visit event with an increasing order (0,1,2,...) */
        public static <T> TraversalEvent<T> visit(T node, int order) {
            return new TraversalEvent<>(Objects.requireNonNull(node), order, null);
        }

        /** A skip event with an explanatory note (e.g., not all parents visited yet). */
        public static <T> TraversalEvent<T> skip(T node, String note) {
            return new TraversalEvent<>(Objects.requireNonNull(node), null, Objects.requireNonNull(note));
        }

        public boolean isVisit() { return order != null; }
        public boolean isSkip() { return order == null; }
        public T node() { return node; }
        public Integer order() { return order; }
        public String note() { return note; }

        @Override public String toString() {
            return isVisit() ? "VISIT(" + node + ", order=" + order + ")" : "SKIP(" + node + ", " + note + ")";
        }
    }

    /**
     * DFS (recursive) that records the order of first visit starting at {@code start},
     * but only recurses to a child when <b>all</b> its predecessors have been visited.
     * If a child is encountered early (some parent unvisited), a SKIP event is recorded.
     *
     * <p>Equivalent idea to the Python pseudo in the user's description (with successors and predecessors),
     * but implemented in Java and returning a sequence of {@link TraversalEvent}s.</p>
     *
     * @param successors adjacency list: for each node, its outgoing neighbors
     * @param start start node
     * @return immutable list of traversal events (VISITs with monotonically increasing order and SKIPs with messages)
     * @throws IllegalArgumentException if {@code successors} is null
     */
    public static <T> List<TraversalEvent<T>> dfsRecursiveOrder(Map<T, List<T>> successors, T start) {
        if (successors == null) {
            throw new IllegalArgumentException("successors must not be null");
        }
        // derive predecessors once
        Map<T, List<T>> predecessors = derivePredecessors(successors);
        return dfsRecursiveOrder(successors, predecessors, start);
    }

    /**
     * Same as {@link #dfsRecursiveOrder(Map, Object)} but with an explicit predecessors map.
     */
    public static <T> List<TraversalEvent<T>> dfsRecursiveOrder(
            Map<T, List<T>> successors, Map<T, List<T>> predecessors, T start) {

        if (successors == null || predecessors == null) {
            throw new IllegalArgumentException("successors and predecessors must not be null");
        }
        if (start == null) {
            return List.of();
        }
        if (!successors.containsKey(start) && !appearsAnywhere(successors, start)) {
            return List.of(); // start not present in graph
        }

        List<TraversalEvent<T>> events = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        int[] order = {0};
        dfs(start, successors, predecessors, visited, order, events);
        return Collections.unmodifiableList(events);
    }

    private static <T> void dfs(
            T u,
            Map<T, List<T>> succ,
            Map<T, List<T>> pred,
            Set<T> visited,
            int[] order,
            List<TraversalEvent<T>> out) {

        if (!visited.add(u)) {
            return; // already visited
        }
        out.add(TraversalEvent.visit(u, order[0]++)); // record visit and increment

        for (T v : succ.getOrDefault(u, List.of())) {
            if (visited.contains(v)) {
                continue;
            }
            if (allParentsVisited(v, visited, pred)) {
                dfs(v, succ, pred, visited, order, out);
            } else {
                out.add(TraversalEvent.skip(v, "⛔ Skipping " + v + ": not all parents are visited yet."));
                // do not mark visited; it may be visited later from another parent
            }
        }
    }

    private static <T> boolean allParentsVisited(T node, Set<T> visited, Map<T, List<T>> pred) {
        for (T p : pred.getOrDefault(node, List.of())) {
            if (!visited.contains(p)) {
                return false;
            }
        }
        return true;
    }

    private static <T> boolean appearsAnywhere(Map<T, List<T>> succ, T node) {
        if (succ.containsKey(node)) return true;
        for (List<T> nbrs : succ.values()) {
            if (nbrs != null && nbrs.contains(node)) return true;
        }
        return false;
    }

    private static <T> Map<T, List<T>> derivePredecessors(Map<T, List<T>> succ) {
        Map<T, List<T>> pred = new HashMap<>();
        // ensure keys exist for all nodes appearing anywhere
        for (Map.Entry<T, List<T>> e : succ.entrySet()) {
            pred.computeIfAbsent(e.getKey(), k -> new ArrayList<>());
            for (T v : e.getValue()) {
                pred.computeIfAbsent(v, k -> new ArrayList<>()).add(e.getKey());
            }
        }
        return pred;
    }
}
