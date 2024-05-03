package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SplayTreeTest {
    @ParameterizedTest
    @MethodSource("traversalOrders")
    public void testTraversal(String traversalOrder) {
        SplayTree tree = createComplexTree();

        // Perform traversal based on the provided order and verify the result
        List<Integer> expected = getExpectedTraversalResult(traversalOrder);
        List<Integer> result = performTraversal(tree, traversalOrder);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testSearch(int value) {
        SplayTree tree = createComplexTree();

        // Search for the value in the tree
        assertTrue(tree.search(value));
    }

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testDelete(int value) {
        SplayTree tree = createComplexTree();

        // Delete the value from the tree
        assertTrue(tree.search(value)); // Ensure value is present before deletion
        tree.delete(value);
        assertFalse(tree.search(value)); // Ensure value is not present after deletion
    }

    // Method to provide different traversal orders as parameters
    private static Stream<String> traversalOrders() {
        return Stream.of("inOrder", "preOrder", "postOrder");
    }

    // Method to provide values for search and delete tests as parameters
    private static Stream<Integer> valuesToTest() {
        return Stream.of(1, 5, 10); // Values present in the complex tree
    }

    // Method to perform traversal based on the provided order
    private List<Integer> performTraversal(SplayTree tree, String traversalOrder) {
        switch (traversalOrder) {
        case "inOrder":
            return tree.inOrder();
        case "preOrder":
            return tree.preOrder();
        case "postOrder":
            return tree.postOrder();
        default:
            throw new IllegalArgumentException("Invalid traversal order: " + traversalOrder);
        }
    }

    // Method to get the expected traversal result based on the provided order
    private List<Integer> getExpectedTraversalResult(String traversalOrder) {
        List<Integer> expected = new LinkedList<>();
        switch (traversalOrder) {
        case "inOrder":
            expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
            break;
        case "preOrder":
            expected.addAll(Arrays.asList(10, 9, 8, 7, 3, 1, 2, 5, 4, 6));
            break;
        case "postOrder":
            expected.addAll(Arrays.asList(2, 1, 4, 6, 5, 3, 7, 8, 9, 10));
            break;
        default:
            throw new IllegalArgumentException("Invalid traversal order: " + traversalOrder);
        }
        return expected;
    }

    // Method to create a complex SplayTree instance for testing
    private SplayTree createComplexTree() {
        SplayTree tree = new SplayTree();
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(3);
        tree.insert(8);
        tree.insert(10);
        return tree;
    }
}
