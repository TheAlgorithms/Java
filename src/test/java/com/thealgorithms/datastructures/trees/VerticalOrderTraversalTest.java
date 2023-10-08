package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 13/01/2023
 */
public class VerticalOrderTraversalTest {
    @Test
    public void testRootNull() {
        assertEquals(Collections.emptyList(), VerticalOrderTraversal.verticalTraversal(null));
    }

    @Test
    public void testSingleNodeTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {50});
        assertEquals(List.of(50), VerticalOrderTraversal.verticalTraversal(root));
    }

    /*
         1
        / \
       2   3
      /\   /\
     4  5 6  7
    */
    @Test
    public void testVerticalTraversalCompleteTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        assertEquals(List.of(4, 2, 1, 5, 6, 3, 7), VerticalOrderTraversal.verticalTraversal(root));
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
    public void testVerticalTraversalDifferentHeight() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7, null, null, 8, null, null, 9});
        assertEquals(List.of(4, 2, 8, 1, 5, 6, 3, 9, 7), VerticalOrderTraversal.verticalTraversal(root));
    }
}
