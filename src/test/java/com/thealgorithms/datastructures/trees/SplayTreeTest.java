package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SplayTreeTest {
    @ParameterizedTest
    @MethodSource("traversalOrders")
    public void testTraversal(SplayTree.TraverseOrder traverseOrder) {
        SplayTree tree = createComplexTree();
        List<Integer> expected = getExpectedTraversalResult(traverseOrder);
        List<Integer> result = tree.traverse(traverseOrder);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testSearch(int value) {
        SplayTree tree = createComplexTree();
        assertTrue(tree.search(value));
    }

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testDelete(int value) {
        SplayTree tree = createComplexTree();

        assertTrue(tree.search(value));
        tree.delete(value);
        assertFalse(tree.search(value));
    }

    @ParameterizedTest
    @MethodSource("nonExistentValues")
    public void testSearchNonExistent(int value) {
        SplayTree tree = createComplexTree();
        assertFalse(tree.search(value));
    }

    @ParameterizedTest
    @MethodSource("nonExistentValues")
    public void testDeleteNonExistent(int value) {
        SplayTree tree = createComplexTree();

        tree.delete(value);
        assertFalse(tree.search(value));
    }

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testDeleteThrowsExceptionForEmptyTree(int value) {
        SplayTree tree = new SplayTree();
        assertThrows(IllegalArgumentException.class, () -> tree.delete(value));
    }

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testInsertThrowsExceptionForDuplicateKeys(int value) {
        SplayTree tree = createComplexTree();
        assertThrows(IllegalArgumentException.class, () -> tree.insert(value));
    }

    private static Stream<SplayTree.TraverseOrder> traversalOrders() {
        return Stream.of(SplayTree.TraverseOrder.IN_ORDER, SplayTree.TraverseOrder.PRE_ORDER, SplayTree.TraverseOrder.POST_ORDER);
    }

    private static Stream<Integer> valuesToTest() {
        return Stream.of(1, 5, 10);
    }

    private static Stream<Integer> nonExistentValues() {
        return Stream.of(0, 11, 15);
    }

    private List<Integer> getExpectedTraversalResult(SplayTree.TraverseOrder traverseOrder) {
        List<Integer> expected = new LinkedList<>();
        switch (traverseOrder) {
        case IN_ORDER:
            expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
            break;
        case PRE_ORDER:
            expected.addAll(Arrays.asList(10, 9, 8, 7, 3, 1, 2, 5, 4, 6));
            break;
        case POST_ORDER:
            expected.addAll(Arrays.asList(2, 1, 4, 6, 5, 3, 7, 8, 9, 10));
            break;
        default:
            throw new IllegalArgumentException("Invalid traversal order: " + traverseOrder);
        }
        return expected;
    }

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
