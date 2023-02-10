package com.thealgorithms.searches;

import com.thealgorithms.searches.DepthFirstSearch.Node;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 */
public class BreadthFirstSearch {
    public static Optional<Node> search(final Node node, final String name) {
        if (node.getName().equals(name)) {
            return Optional.of(node);
        }

        Queue<Node> queue = new ArrayDeque<>(node.getSubNodes());

        while (!queue.isEmpty()) {
            final Node current = queue.poll();

            if (current.getName().equals(name)) {
                return Optional.of(current);
            }

            queue.addAll(current.getSubNodes());
        }

        return Optional.empty();
    }
}
