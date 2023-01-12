package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Albina Gimaletdinova on 11/01/2023
 */
public class ZigzagTraversalTest {
    @Test
    public void testRootNull() {
        assertEquals(Collections.emptyList(), ZigzagTraversal.traverse(null));
    }

    @Test
    public void testSingleNodeTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[]{50});
        assertEquals(List.of(List.of(50)), ZigzagTraversal.traverse(root));
    }

    @Test
    public void testZigzagTraversal() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[]{7, 6, 14, 2, 80, 100});
        assertEquals(List.of(List.of(7), List.of(14, 6), List.of(2, 80, 100)), ZigzagTraversal.traverse(root));
    }
}
