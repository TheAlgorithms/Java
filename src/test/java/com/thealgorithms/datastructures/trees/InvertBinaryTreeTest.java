package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class InvertBinaryTreeTest {

    @Test
    void testInvertTreeNormalCase() {
        BinaryTree.Node root = new BinaryTree.Node(4);
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(7);
        root.left.left = new BinaryTree.Node(1);
        root.left.right = new BinaryTree.Node(3);

        BinaryTree.Node inverted = InvertBinaryTree.invertTree(root);

        assertEquals(7, inverted.left.data);
        assertEquals(2, inverted.right.data);
        assertEquals(3, inverted.right.left.data);
        assertEquals(1, inverted.right.right.data);
    }

    @Test
    void testInvertTreeSingleNode() {
        BinaryTree.Node root = new BinaryTree.Node(1);

        BinaryTree.Node inverted = InvertBinaryTree.invertTree(root);

        assertEquals(1, inverted.data);
        assertNull(inverted.left);
        assertNull(inverted.right);
    }

    @Test
    void testInvertTreeNull() {
        BinaryTree.Node inverted = InvertBinaryTree.invertTree(null);

        assertNull(inverted);
    }
}
