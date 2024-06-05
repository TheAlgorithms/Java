package com.thealgorithms.searches;

import com.thealgorithms.datastructures.Node;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 * @wiki: https://en.wikipedia.org/wiki/Breadth-first_search
 */
public class BreadthFirstSearch<T> {

    private final List<T> visited = new ArrayList<>();

    public Optional<Node<T>> search(final Node<T> node, final T value) {
        if (node == null) {
            return Optional.empty();
        }
        if (node.getValue().equals(value)) {
            // add root node to visited
            visited.add(value);
            return Optional.of(node);
        }
        visited.add(node.getValue());

        Queue<Node<T>> queue = new ArrayDeque<>(node.getChildren());

        while (!queue.isEmpty()) {
            final Node<T> current = queue.poll();
            visited.add(current.getValue());

            if (current.getValue().equals(value)) {
                return Optional.of(current);
            }

            queue.addAll(current.getChildren());
        }

        return Optional.empty();
    }

    public List<T> getVisited() {
        return visited;
    }
}
