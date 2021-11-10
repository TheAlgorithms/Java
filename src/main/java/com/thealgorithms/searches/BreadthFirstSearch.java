package com.thealgorithms.searches;

import com.thealgorithms.searches.DepthFirstSearch.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 */
public class BreadthFirstSearch {

    public static Optional<Node> search(final Node node, final String name) {
        if (node.getName().equals(name)) {
            return Optional.of(node);
        }

        List<Node> queue = new ArrayList<>(node.getSubNodes());

        while (!queue.isEmpty()) {
            final Node current = queue.get(0);

            if (current.getName().equals(name)) {
                return Optional.of(current);
            }

            queue.addAll(current.getSubNodes());

            queue.remove(0);
        }

        return Optional.empty();
    }

    public static void assertThat(final Object actual, final Object expected) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError(String.format("expected=%s but was actual=%s", expected, actual));
        }
    }

    public static void main(final String[] args) {
        final Node rootNode = new Node("A", List.of(
                new Node("B", List.of(new Node("D"), new Node("F", List.of(
                        new Node("H"), new Node("I")
                )))),
                new Node("C", List.of(new Node("G"))),
                new Node("E")
        ));

        {
            final String expected = "I";

            final Node result = search(rootNode, expected)
                    .orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
        }

        {
            final String expected = "G";

            final Node result = search(rootNode, expected)
                    .orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
        }

        {
            final String expected = "E";

            final Node result = search(rootNode, expected)
                    .orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
        }
    }
}
