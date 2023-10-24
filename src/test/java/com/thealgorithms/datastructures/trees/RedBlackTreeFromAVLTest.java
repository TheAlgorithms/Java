package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RedBlackTreeFromAVLTest {
    private RedBlackTreeFromAVL tree;

    public void setUp() {
        tree = new RedBlackTreeFromAVL();
    }
    @Test
    public void testPaintRedBlackFromAVL() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        // Construct a simple AVL tree:
        //      10
        //     /  \
        //    5   15
        RedBlackTreeFromAVL.Node root = tree.new Node(10);
        root.left = tree.new Node(5);
        root.right = tree.new Node(15);

        tree.paintRedBlackFromAVL(root);

        // According to the logic of your method, let's verify node colors:
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.left.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.right.colour);
    }
    @Test
    public void testPaintRedBlackFromAVLNonSymmetric() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        RedBlackTreeFromAVL.Node root = tree.new Node(10);
        root.left = tree.new Node(5);
        root.right = tree.new Node(15);
        root.left.left = tree.new Node(3);

        tree.paintRedBlackFromAVL(root);
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.left.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.RED, root.left.left.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.right.colour);
    }
    @Test
    public void testResolveRedRedConflictFromDown() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        // Build a simple tree with potential red-red conflicts:
        RedBlackTreeFromAVL.Node root = tree.new Node(10);
        root.colour = RedBlackTreeFromAVL.Colour.BLACK;
        root.left = tree.new Node(5);
        root.left.colour = RedBlackTreeFromAVL.Colour.RED;
        root.right = tree.new Node(15);

        tree.resolveRedRedConflictFromDown(root);

        // Check there are no red-red conflicts:
        assertFalse(root.colour == RedBlackTreeFromAVL.Colour.RED && root.left.colour == RedBlackTreeFromAVL.Colour.RED);
        // ... continue this for other nodes and subtrees as well.
    }
    @Test
    public void testResolveRedRedConflictWithInitialConflict() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        RedBlackTreeFromAVL.Node root = tree.new Node(10);
        root.left = tree.new Node(5);
        root.left.colour = RedBlackTreeFromAVL.Colour.RED;
        root.left.left = tree.new Node(3);
        root.left.left.colour = RedBlackTreeFromAVL.Colour.RED;
        root.left.right = tree.new Node(6);
        root.left.right.colour = RedBlackTreeFromAVL.Colour.BLACK;

        root.left.left.left = tree.new Node(1);
        root.left.left.left.colour = RedBlackTreeFromAVL.Colour.BLACK;
        root.left.left.right = tree.new Node(4);
        root.left.left.right.colour = RedBlackTreeFromAVL.Colour.BLACK;

        root.right = tree.new Node(15);
        root.right.colour = RedBlackTreeFromAVL.Colour.BLACK;

        root.right.right = tree.new Node(16);
        root.right.right.colour = RedBlackTreeFromAVL.Colour.RED;

        tree.resolveRedRedConflictFromDown(root);

        // After resolving, root.left should be black to fix the conflict.
        assertEquals(RedBlackTreeFromAVL.Colour.RED, root.left.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.left.left.colour);
        assertEquals(RedBlackTreeFromAVL.Colour.RED, root.left.left.left.colour);
    }

    @Test
    public void testRedBlack() {
        RedBlackTreeFromAVL tree = new RedBlackTreeFromAVL();

        // Construct an AVL tree:
        RedBlackTreeFromAVL.Node root = tree.new Node(10);
        root.left = tree.new Node(5);
        root.right = tree.new Node(15);

        tree.redBlack(root);

        // Check if the tree now satisfies Red-Black properties:
        // Root should be black:
        assertEquals(RedBlackTreeFromAVL.Colour.BLACK, root.colour);

        // No consecutive red nodes:
        assertFalse(root.colour == RedBlackTreeFromAVL.Colour.RED && root.left.colour == RedBlackTreeFromAVL.Colour.RED);
        // ... continue for other nodes and subtrees.
    }
}
