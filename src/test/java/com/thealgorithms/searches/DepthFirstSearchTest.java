package com.thealgorithms.searches;

import com.thealgorithms.datastructures.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearchTest {

    private Node<Integer> root;
    private DepthFirstSearch<Integer> dfs;

    @BeforeEach
    public void setUp() {
        // nodes declaration
        root = new Node<>(1);

        var nodeB = new Node<>(2);
        var nodeC = new Node<>(3);
        var nodeD = new Node<>(4);

        var nodeE = new Node<>(5);
        var nodeF = new Node<>(6);

        // tree initialization
        root.addChild(nodeB);
        root.addChild(nodeC);
        root.addChild(nodeD);

        nodeB.addChild(nodeE);
        nodeB.addChild(nodeF);

        // create an instance to monitor the visited nodes
        dfs = new DepthFirstSearch<>();
    }

    @Test
    public void testSearchRoot() {
        Integer expectedValue = 1;
        List<Integer> expectedPath = List.of(1);

        // check value
        Optional<Node<Integer>> value = dfs.recursiveSearch(root, 1);
        assertEquals(expectedValue, value.orElse(new Node<>(null)).getValue());

        // check path
        assertArrayEquals(expectedPath.toArray(), dfs.getVisited().toArray());
    }
}
