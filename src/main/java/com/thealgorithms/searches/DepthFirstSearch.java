package com.thealgorithms.searches;

import com.thealgorithms.datastructures.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 * @wiki: https://en.wikipedia.org/wiki/Depth-first_search
 */
public class DepthFirstSearch<T> {

    private final List<T> visited = new ArrayList<>();

    public Optional<Node<T>> recursiveSearch(final Node<T> node, final Integer value) {
        if (node == null) {
            return Optional.empty();
        }
        visited.add(node.getValue());
        if (node.getValue().equals(value)) {
            return Optional.of(node);
        }

        return node.getChildren().stream().map(v -> recursiveSearch(v, value)).flatMap(Optional::stream).findAny();
    }

    public List<T> getVisited() {
        return visited;
    }
}
