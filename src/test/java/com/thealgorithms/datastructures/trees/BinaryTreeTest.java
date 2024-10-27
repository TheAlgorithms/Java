package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BinaryTreeTest {

    // Test for adding elements and finding data within the tree
    @Test
    void test1() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        Assertions.assertNotNull(t.find(5), "Node with value 5 should exist in the tree.");
        Assertions.assertEquals(5, t.find(5).data);

        Assertions.assertNotNull(t.find(7), "Node with value 7 should exist in the tree.");
        Assertions.assertEquals(7, t.find(7).data);
    }

    // Test for removing data and checking the new root
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

        Assertions.assertNotNull(t.getRoot(), "Root should not be null after removals.");
        Assertions.assertEquals(9, t.getRoot().data); // Direct access after ensuring non-null
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
        Assertions.assertNotNull(t.getRoot(), "Root should not be null for traversal.");

        // Invoke traversal methods to increase test coverage
        t.bfs(t.getRoot());
        t.inOrder(t.getRoot());
        t.preOrder(t.getRoot());
        t.postOrder(t.getRoot());

        // Additional assertions
        Assertions.assertTrue(t.remove(9), "Node with value 9 should be removed.");
        Assertions.assertFalse(t.remove(398745987),
                "Removing a nonexistent node should return false.");
    }
}
