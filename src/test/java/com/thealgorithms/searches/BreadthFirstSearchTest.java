package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import com.thealgorithms.datastructures.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BreadthFirstSearchTest {
    private Node<String> root;
    private BreadthFirstSearch<String> bfs;

    @BeforeEach
    public void setUp() {
        // nodes declaration
        root = new Node<>("A");

        var nodeB = new Node<>("B");
        var nodeC = new Node<>("C");
        var nodeD = new Node<>("D");

        var nodeE = new Node<>("E");
        var nodeF = new Node<>("F");

        // tree initialization
        root.addChild(nodeB);
        root.addChild(nodeC);
        root.addChild(nodeD);

        nodeB.addChild(nodeE);
        nodeB.addChild(nodeF);

        // create an instance to monitor the visited nodes
        bfs = new BreadthFirstSearch<>();
    }

    @Test
    public void testSearchRoot() {
        String expectedValue = "A";
        List<String> expectedPath = List.of("A");

        // check value
        Optional<Node<String>> value = bfs.search(root, "A");
        assertEquals(expectedValue, value.orElse(new Node<>("")).getValue());

        // check path
        assertArrayEquals(expectedPath.toArray(), bfs.getVisited().toArray());
    }

    @Test
    public void testSearchE() {
        String expectedValue = "E";
        List<String> expectedPath = List.of("A", "B", "C", "D", "E");

        // check value
        Optional<Node<String>> value = Optional.of(bfs.search(root, "E").orElse(new Node<>(null)));
        assertEquals(expectedValue, value.get().getValue());

        // check path
        assertArrayEquals(expectedPath.toArray(), bfs.getVisited().toArray());
    }

    @Test
    void searchNull() {
        List<String> expectedPath = List.of("A", "B", "C", "D", "E", "F");
        Optional<Node<String>> node = bfs.search(root, null);

        // check value
        assertTrue(node.isEmpty());

        // check path
        assertArrayEquals(expectedPath.toArray(), bfs.getVisited().toArray());
    }
}