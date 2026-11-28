package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class InvertBinaryTreeTest {

    @Test
    public void testNullRoot() {
        assertNull(InvertBinaryTree.invertTree(null));
    }

    @Test
    public void testSingleNode() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1});
        InvertBinaryTree.invertTree(root);
        assertEquals(1, root.data);
        assertNull(root.left);
        assertNull(root.right);
    }

    /*
         4                4
        / \              / \
       2   7    ->      7   2
      / \ / \          / \ / \
     1  3 6  9        9  6 3  1
    */
    @Test
    public void testInvertTree() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {4, 2, 7, 1, 3, 6, 9});
        InvertBinaryTree.invertTree(root);
        assertEquals(4, root.data);
        assertEquals(7, root.left.data);
        assertEquals(2, root.right.data);
        assertEquals(9, root.left.left.data);
        assertEquals(6, root.left.right.data);
        assertEquals(3, root.right.left.data);
        assertEquals(1, root.right.right.data);
    }
}
