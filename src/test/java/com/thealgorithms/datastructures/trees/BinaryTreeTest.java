package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BinaryTree class.
 */
public class BinaryTreeTest {

    @Test
    public void testInsertAndFind() {
        BinaryTree tree = new BinaryTree();
        tree.put(3);
        tree.put(5);
        tree.put(7);
        tree.put(9);
        tree.put(12);

        Assertions.assertNotNull(tree.find(5), "Node with value 5 should exist");
        Assertions.assertEquals(5, tree.find(5).data, "Value of the found node should be 5");
        Assertions.assertEquals(7, tree.find(7).data, "Value of the found node should be 7");
    }

    @Test
    public void testRemove() {
        BinaryTree tree = new BinaryTree();
        tree.put(3);
        tree.put(5);
        tree.put(7);
        tree.put(9);
        tree.put(12);
        tree.remove(3);
        tree.remove(5);
        tree.remove(7);

        Assertions.assertNotNull(tree.getRoot(), "Root should not be null after removals");
        if (tree.getRoot() != null) {
            Assertions.assertEquals(9, tree.getRoot().data, "Root value should be 9 after removals");
        } else {
            Assertions.fail("Root should not be null after removals, but it is.");
        }
    }

    @Test
    public void testRemoveReturnValue() {
        BinaryTree tree = new BinaryTree();
        tree.put(3);
        tree.put(5);
        tree.put(7);
        tree.put(9);
        tree.put(12);

        Assertions.assertTrue(tree.remove(9), "Removing existing node 9 should return true");
        Assertions.assertFalse(tree.remove(398745987), "Removing non-existing node should return false");
    }

    @Test
    public void testTraversalMethods() {
        BinaryTree tree = new BinaryTree();
        tree.put(3);
        tree.put(5);
        tree.put(7);
        tree.put(9);
        tree.put(12);

        // Testing traversal methods
        tree.bfs(tree.getRoot());
        tree.inOrder(tree.getRoot());
        tree.preOrder(tree.getRoot());
        tree.postOrder(tree.getRoot());

        Assertions.assertTrue(tree.remove(9), "Removing existing node 9 should return true");
        Assertions.assertFalse(tree.remove(398745987), "Removing non-existing node should return false");

        Assertions.assertNotNull(tree.getRoot(), "Root should not be null after operations");
    }
}
