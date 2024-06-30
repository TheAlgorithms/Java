package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
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
        return Stream.of(new Object[] {SplayTree.IN_ORDER, Arrays.asList(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90)}, new Object[] {SplayTree.PRE_ORDER, Arrays.asList(15, 5, 10, 80, 70, 45, 25, 20, 35, 30, 40, 55, 50, 65, 60, 75, 90, 85)},
            new Object[] {SplayTree.POST_ORDER, Arrays.asList(10, 5, 20, 30, 40, 35, 25, 50, 60, 65, 55, 45, 75, 70, 85, 90, 80, 15)});
    }

    private static Stream<Integer> valuesToTest() {
        return Stream.of(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90);
    }

    private static Stream<Integer> nonExistentValues() {
        return Stream.of(0, 100, 42, 58);
    }

    private SplayTree createComplexTree() {
        SplayTree tree = new SplayTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(20);
        tree.insert(80);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(45);
        tree.insert(55);
        tree.insert(65);
        tree.insert(75);
        tree.insert(85);
        tree.insert(5);
        tree.insert(90);
        tree.insert(15);

        return tree;
    }

    @Test
    public void testZigZagCaseWithNullChild() {
        SplayTree tree = new SplayTree();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        tree.insert(15);

        assertTrue(tree.search(15));

        assertEquals(Arrays.asList(10, 15, 20, 30), tree.traverse(SplayTree.IN_ORDER));
    }
}
