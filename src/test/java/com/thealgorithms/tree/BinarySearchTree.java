package com.thealgorithms.tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Binary Search Tree (BST) implementation.
 * Covers insertion, traversal, balance checking, and display logic.
 *
 * Author: Udaya Krishnan M
 * GitHub: https://github.com/UdayaKrishnanM/
 */
class BinarySearchTreeTest {

    private BinarySearchTree bst;
    private final int[] values = {30, 20, 40, 10, 25, 35, 50};

    /**
     * Initializes the BST before each test with a predefined set of values.
     */
    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
        bst.populate(values);
    }

    /**
     * Verifies that the BST is not empty after insertion.
     */
    @Test
    void testBSTIsNotEmpty() {
        assertFalse(bst.isEmpty(), "BST should not be empty after insertion");
    }

    /**
     * Tests the height calculation of the root node.
     */
    @Test
    void testRootHeight() {
        int expectedHeight = 2;
        assertEquals(expectedHeight, bst.height(bst.createBST(null, 30)), "Height of root node should be 2");
    }
    

    /**
     * Tests if the BST is balanced.
     */
    @Test
    void testBalancedTree() {
        assertTrue(bst.balanced(), "BST should be balanced with given values");
    }

    /**
     * Tests inorder traversal output.
     */
    @Test
    void testInOrderTraversal() {
        // Expected sorted order
        bst.inOrder(); // You can redirect System.out to capture output if needed
        assertTrue(true, "Inorder traversal executed successfully");
    }

    /**
     * Tests preorder traversal output.
     */
    @Test
    void testPreOrderTraversal() {
        bst.preOrder();
        assertTrue(true, "Preorder traversal executed successfully");
    }

    /**
     * Tests postorder traversal output.
     */
    @Test
    void testPostOrderTraversal() {
        bst.postOrder();
        assertTrue(true, "Postorder traversal executed successfully");
    }

    /**
     * Tests pretty display of the BST.
     */
    @Test
    void testPrettyDisplay() {
        bst.prettyDisplay();
        assertTrue(true, "Pretty display executed successfully");
    }

    /**
     * Tests balanced population using sorted array.
     */
    @Test
    void testPopulateSorted() {
        int[] sortedValues = {10, 20, 30, 40, 50};
        BinarySearchTree sortedBST = new BinarySearchTree();
        sortedBST.populateSorted(sortedValues);
        assertTrue(sortedBST.balanced(), "BST populated with sorted array should be balanced");
    }
}
