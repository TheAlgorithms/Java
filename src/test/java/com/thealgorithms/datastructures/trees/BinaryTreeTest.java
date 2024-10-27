package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BinaryTree class.
 */
public class BinaryTreeTest {

    @Test
    public void testInsertAndFind() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        assertNotNull(t.find(5), "Node with value 5 should exist");
        assertEquals(5, t.find(5).data, "Value of the found node should be 5");
        assertEquals(7, t.find(7).data, "Value of the found node should be 7");
    }

    @Test
    public void testRemove() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);
        t.remove(3);
        t.remove(5);
        t.remove(7);

        assertNotNull(t.getRoot(), "Root should not be null after removals");

        // Check if root is not null before accessing its data
        if (t.getRoot() != null) {
            assertEquals(9, t.getRoot().data, "Root value should be 9 after removals");
        } else {
            fail("Root should not be null after removals, but it is.");
        }
    }

    @Test
    public void testRemoveReturnValue() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        assertTrue(t.remove(9), "Removing existing node 9 should return true");
        assertFalse(t.remove(398745987), "Removing non-existing node should return false");
    }

    @Test
    public void testTraversalMethods() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        // Testing traversal methods
        t.bfs(t.getRoot());
        t.inOrder(t.getRoot());
        t.preOrder(t.getRoot());
        t.postOrder(t.getRoot());

        // Ensure removal functionality is still working
        assertTrue(t.remove(9), "Removing existing node 9 should return true");
        assertFalse(t.remove(398745987), "Removing non-existing node should return false");

        // Check that the root is not null
        assertNotNull(t.getRoot(), "Root should not be null after operations");
    }
}
