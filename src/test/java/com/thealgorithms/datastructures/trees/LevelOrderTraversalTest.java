package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 08/02/2023
 */
public class LevelOrderTraversalTest {
    @Test
    public void testRootNull() {
        assertEquals(Collections.emptyList(), LevelOrderTraversal.traverse(null));
    }

    @Test
    public void testSingleNodeTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {50});
        assertEquals(List.of(List.of(50)), LevelOrderTraversal.traverse(root));
    }

    /*
         1
        / \
       2   3
      /\   /\
     4  5 6  7
    */
    @Test
    public void testLevelOrderTraversalCompleteTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)), LevelOrderTraversal.traverse(root));
    }

    /*
         1
        / \
       2   3
      /\   /\
     4  5 6  7
        /  \
       8    9
    */
    @Test
    public void testLevelOrderTraversalDifferentHeight() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7, null, null, 8, null, null, 9});
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7), List.of(8, 9)), LevelOrderTraversal.traverse(root));
    }
}
