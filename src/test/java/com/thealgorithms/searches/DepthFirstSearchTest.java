package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.Node;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepthFirstSearchTest {

    private Node<Integer> root;
    private DepthFirstSearch<Integer> dfs;

    //
    // Tree structure:
    //          1
    //        / | \
    //       2  3  4
    //      / \
    //     5   6

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
        Optional<Node<Integer>> value = dfs.recursiveSearch(root, expectedValue);
        assertEquals(expectedValue, value.orElseGet(() -> new Node<>(null)).getValue());

        // check path
        assertArrayEquals(expectedPath.toArray(), dfs.getVisited().toArray());
    }

    @Test
    public void testSearch4() {
        Integer expectedValue = 4;
        List<Integer> expectedPath = List.of(1, 2, 5, 6, 3, 4);

        // check value
        Optional<Node<Integer>> value = dfs.recursiveSearch(root, expectedValue);
        assertEquals(expectedValue, value.orElseGet(() -> new Node<>(null)).getValue());

        // check path
        assertArrayEquals(expectedPath.toArray(), dfs.getVisited().toArray());
    }

    @Test
    void testNullRoot() {
        var value = dfs.recursiveSearch(null, 4);
        assertTrue(value.isEmpty());
    }

    @Test
    void testSearchValueThatNotExists() {
        List<Integer> expectedPath = List.of(1, 2, 5, 6, 3, 4);
        var value = dfs.recursiveSearch(root, 10);

        // check that the value is empty because it's not exists in the tree
        assertTrue(value.isEmpty());

        // check path is the whole list
        assertArrayEquals(expectedPath.toArray(), dfs.getVisited().toArray());
    }
}
