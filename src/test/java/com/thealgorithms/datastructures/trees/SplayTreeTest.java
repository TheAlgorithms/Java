package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SplayTreeTest {

    @ParameterizedTest
    @MethodSource("traversalStrategies")
    public void testTraversal(SplayTree.TreeTraversal traversal, List<Integer> expected) {
        SplayTree tree = createComplexTree();
        List<Integer> result = tree.traverse(traversal);
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

    @ParameterizedTest
    @MethodSource("valuesToTest")
    public void testSearchInEmptyTree(int value) {
        SplayTree tree = new SplayTree();
        assertFalse(tree.search(value));
    }

    private static Stream<Object[]> traversalStrategies() {
        return Stream.of(new Object[] {SplayTree.IN_ORDER, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18)}, new Object[] {SplayTree.PRE_ORDER, Arrays.asList(18, 17, 16, 15, 13, 11, 9, 8, 7, 3, 2, 1, 5, 4, 6, 10, 12)},
            new Object[] {SplayTree.POST_ORDER, Arrays.asList(1, 2, 4, 6, 5, 3, 7, 8, 10, 9, 12, 11, 13, 15, 16, 17, 18)});
    }

    private static Stream<Integer> valuesToTest() {
        return Stream.of(1, 5, 10, 17, 8, 13, 6, 17, 4, 11, 9, 2, 18, 3, 16, 7, 12);
    }

    private static Stream<Integer> nonExistentValues() {
        return Stream.of(0, 21, 20, 14);
    }

    private SplayTree createComplexTree() {
        SplayTree tree = new SplayTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(3);
        tree.insert(8);
        tree.insert(12);
        tree.insert(17);
        tree.insert(11);
        tree.insert(13);
        tree.insert(16);
        tree.insert(18);
        return tree;
    }
}
