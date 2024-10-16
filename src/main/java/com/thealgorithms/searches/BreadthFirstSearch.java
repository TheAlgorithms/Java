package com.thealgorithms.searches;

import com.thealgorithms.datastructures.Node;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * Breadth-First Search implementation for tree/graph traversal.
 * @author caos321
 * @co-author manishraj27
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first search</a>
 */
public class BreadthFirstSearch<T> {

    private final List<T> visited = new ArrayList<>();
    private final Set<T> visitedSet = new HashSet<>();

    /**
     * Performs a breadth-first search to find a node with the given value.
     *
     * @param root The root node to start the search from
     * @param value The value to search for
     * @return Optional containing the found node, or empty if not found
     */
    public Optional<Node<T>> search(final Node<T> root, final T value) {
        if (value == null || root == null) {
            return Optional.empty();
        }

        visited.clear();
        visitedSet.clear();

        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        visitedSet.add(root.getValue());
        visited.add(root.getValue());

        while (!queue.isEmpty()) {
            final Node<T> current = queue.poll();

            if (value.equals(current.getValue())) {
                return Optional.of(current);
            }

            for (Node<T> child : current.getChildren()) {
                if (child != null && !visitedSet.contains(child.getValue())) {
                    queue.offer(child);
                    visitedSet.add(child.getValue());
                    visited.add(child.getValue());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the list of nodes in the order they were visited.
     *
     * @return A new list containing the visited nodes
     */
    public List<T> getVisited() {
        return new ArrayList<>(visited);
    }
}
