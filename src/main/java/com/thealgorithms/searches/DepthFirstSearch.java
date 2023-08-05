package com.thealgorithms.searches;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 */
public class DepthFirstSearch {

    static class Node {

        private final String name;
        private final List<Node> subNodes;

        public Node(final String name) {
            this.name = name;
            this.subNodes = new ArrayList<>();
        }

        public Node(final String name, final List<Node> subNodes) {
            this.name = name;
            this.subNodes = subNodes;
        }

        public String getName() {
            return name;
        }

        public List<Node> getSubNodes() {
            return subNodes;
        }
    }

    public static Optional<Node> search(final Node node, final String name) {
        if (node.getName().equals(name)) {
            return Optional.of(node);
        }

        return node.getSubNodes().stream().map(value -> search(value, name)).flatMap(Optional::stream).findAny();
    }

    public static void assertThat(final Object actual, final Object expected) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError(String.format("expected=%s but was actual=%s", expected, actual));
        }
    }

    public static void main(final String[] args) {
        final Node rootNode = new Node("A", List.of(new Node("B", List.of(new Node("D"), new Node("F", List.of(new Node("H"), new Node("I"))))), new Node("C", List.of(new Node("G"))), new Node("E")));

        {
            final String expected = "I";

            final Node result = search(rootNode, expected).orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
        }

        {
            final String expected = "G";

            final Node result = search(rootNode, expected).orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
        }

        {
            final String expected = "E";

            final Node result = search(rootNode, expected).orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
        }
    }
}
