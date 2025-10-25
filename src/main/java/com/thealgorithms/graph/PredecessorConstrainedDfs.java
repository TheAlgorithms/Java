package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * DFS that visits a successor only when all its predecessors are already visited,
 * emitting VISIT and SKIP events.
 * <p>
 *     This class includes a DFS variant that visits a successor only when all of its
 *     predecessors have already been visited
 * </p>
 * <p>Related reading:
 * <ul>
 *   <li><a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological sorting</a></li>
 *   <li><a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a></li>
 * </ul>
 * </p>
 */

public final class PredecessorConstrainedDfs {

    private PredecessorConstrainedDfs() {
        // utility class
    }

    /** An event emitted by the traversal: either a VISIT with an order, or a SKIP with a note. */
    public record TraversalEvent<T>(T node,
        Integer order, // non-null for visit, null for skip
        String note // non-null for skip, null for visit
    ) {
        public TraversalEvent {
            Objects.requireNonNull(node);
            // order and note can be null based on event type
        }

        /** A visit event with an increasing order (0,1,2,...) */
        public static <T> TraversalEvent<T> visit(T node, int order) {
            return new TraversalEvent<>(node, order, null);
        }

        /** A skip event with an explanatory note (e.g., not all parents visited yet). */
        public static <T> TraversalEvent<T> skip(T node, String note) {
            return new TraversalEvent<>(node, null, Objects.requireNonNull(note));
        }

        public boolean isVisit() {
            return order != null;
        }

        public boolean isSkip() {
            return order == null;
        }

        @Override
        public String toString() {
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
    public static <T> List<TraversalEvent<T>> dfsRecursiveOrder(Map<T, List<T>> successors, Map<T, List<T>> predecessors, T start) {

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

    private static <T> void dfs(T currentNode, Map<T, List<T>> successors, Map<T, List<T>> predecessors, Set<T> visited, int[] order, List<TraversalEvent<T>> result) {

        if (!visited.add(currentNode)) {
            return; // already visited
        }
        result.add(TraversalEvent.visit(currentNode, order[0]++)); // record visit and increment

        for (T childNode : successors.getOrDefault(currentNode, List.of())) {
            if (visited.contains(childNode)) {
                continue;
            }
            if (allParentsVisited(childNode, visited, predecessors)) {
                dfs(childNode, successors, predecessors, visited, order, result);
            } else {
                result.add(TraversalEvent.skip(childNode, "⛔ Skipping " + childNode + ": not all parents are visited yet."));
                // do not mark visited; it may be visited later from another parent
            }
        }
    }

    private static <T> boolean allParentsVisited(T node, Set<T> visited, Map<T, List<T>> predecessors) {
        for (T parent : predecessors.getOrDefault(node, List.of())) {
            if (!visited.contains(parent)) {
                return false;
            }
        }
        return true;
    }

    private static <T> boolean appearsAnywhere(Map<T, List<T>> successors, T node) {
        if (successors.containsKey(node)) {
            return true;
        }
        for (List<T> neighbours : successors.values()) {
            if (neighbours != null && neighbours.contains(node)) {
                return true;
            }
        }
        return false;
    }

    private static <T> Map<T, List<T>> derivePredecessors(Map<T, List<T>> successors) {
        Map<T, List<T>> predecessors = new HashMap<>();
        // ensure keys exist for all nodes appearing anywhere
        for (Map.Entry<T, List<T>> entry : successors.entrySet()) {
            predecessors.computeIfAbsent(entry.getKey(), key -> new ArrayList<>());
            for (T childNode : entry.getValue()) {
                predecessors.computeIfAbsent(childNode, key -> new ArrayList<>()).add(entry.getKey());
            }
        }
        return predecessors;
    }
}
