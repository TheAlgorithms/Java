package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public
class BinaryTreeTest {

    // Test for adding elements and finding data within the tree
    @Test
    void test1() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        Assertions.assertNotNull(t.find(5),
                "Node with value 5 should exist in the tree.");
        Assertions.assertEquals(5, t.find(5).data);

        Assertions.assertNotNull(t.find(7),
                "Node with value 7 should exist in the tree.");
        Assertions.assertEquals(7, t.find(7).data);
    }

    @Test
    void test2() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        // Perform removals and check the new root
        t.remove(3);
        t.remove(5);
        t.remove(7);

        BinaryTree.Node root = t.getRoot();

        // Check the size of the tree to confirm it has remaining nodes
        Assertions.assertEquals(2, t.size(),
                "Tree should have 2 nodes left after removals.");

        // Check if new root is correct
        if (root != null) {
            Assertions.assertEquals(9, root.data);
        } else {
            Assertions.fail("Root is null after removals.");
        }
    }

    // Test for attempting to remove a nonexistent node
    @Test
    void test3() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        Assertions.assertTrue(t.remove(9), "Node with value 9 should be removed.");
        Assertions.assertFalse(t.remove(398745987),
                "Removing a nonexistent node should return false.");
    }

    // Test traversal methods (bfs, inOrder, preOrder, postOrder)
    @Test
    void test4() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        // Ensure root is not null before traversal
        Assertions.assertNotNull(t.getRoot(),
                "Root should not be null for traversal.");

        // Invoke traversal methods to increase test coverage
        System.out.println("BFS Traversal:");
        t.bfs(); // No need to pass the root, just call the method

        System.out.print("In-Order Traversal: ");
        t.inOrder(t.getRoot()); // In-Order

        System.out.print("Pre-Order Traversal: ");
        t.preOrder(t.getRoot()); // Pre-Order

        System.out.print("Post-Order Traversal: ");
        t.postOrder(t.getRoot()); // Post-Order

        System.out.println(); // For a new line after traversals

        // Additional assertions
        Assertions.assertTrue(t.remove(9), "Node with value 9 should be removed.");
        Assertions.assertFalse(t.remove(398745987),
                "Removing a nonexistent node should return false.");
    }
}
