package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsAVLTreeTest {
    private RedBlackTreeFromAVL tree;

    public void setUp() {
        tree = new RedBlackTreeFromAVL();
    }
    @Test
    public void testIsSingleTreeNodeAVL() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();
        RedBlackTreeFromAVL.Node node = tree.new Node(10);
        assertTrue(tree.isAVL(node)); // A single node tree should always be an AVL tree.
    }

    @Test
    public void testIsSkewedRightTreeAVL() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        RedBlackTreeFromAVL.Node node10 = tree.new Node(10);
        RedBlackTreeFromAVL.Node node20 = tree.new Node(20);
        RedBlackTreeFromAVL.Node node30 = tree.new Node(30);

        node10.right = node20;
        node20.right = node30;

        assertFalse(tree.isAVL(node10)); // A right-skewed tree with height greater than 2 is not AVL.
    }

    @Test
    public void testIsSkewedLeftTreeAVL() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        RedBlackTreeFromAVL.Node node10 = tree.new Node(10);
        RedBlackTreeFromAVL.Node node5 = tree.new Node(5);
        RedBlackTreeFromAVL.Node node2 = tree.new Node(2);

        node10.left = node5;
        node5.left = node2;

        assertFalse(tree.isAVL(node10)); // A left-skewed tree with height greater than 2 is not AVL.
    }

    @Test
    public void testIsBalancedTreeAVL() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        RedBlackTreeFromAVL.Node node10 = tree.new Node(10);
        RedBlackTreeFromAVL.Node node5 = tree.new Node(5);
        RedBlackTreeFromAVL.Node node20 = tree.new Node(20);
        RedBlackTreeFromAVL.Node node3 = tree.new Node(3);
        RedBlackTreeFromAVL.Node node7 = tree.new Node(7);
        RedBlackTreeFromAVL.Node node15 = tree.new Node(15);
        RedBlackTreeFromAVL.Node node25 = tree.new Node(25);

        node10.left = node5;
        node10.right = node20;
        node5.left = node3;
        node5.right = node7;
        node20.left = node15;
        node20.right = node25;

        assertTrue(tree.isAVL(node10)); // This tree is balanced and should be AVL.
    }

    @Test
    public void testTreeWithInvalidBSTProperty() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        RedBlackTreeFromAVL.Node node10 = tree.new Node(10);
        RedBlackTreeFromAVL.Node node5 = tree.new Node(5);
        RedBlackTreeFromAVL.Node node20 = tree.new Node(20);
        RedBlackTreeFromAVL.Node node7 = tree.new Node(7);
        RedBlackTreeFromAVL.Node node15 = tree.new Node(15);

        node10.left = node5;
        node10.right = node20;
        node5.right = node7;
        node7.right = node15; // This makes the tree a non-BST.

        assertFalse(tree.isAVL(node10)); // This tree doesn't have BST property and thus can't be AVL.
    }
}
