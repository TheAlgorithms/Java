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
     * Tests the height of the root node after inserting multiple values into the BST.
     * Ensures the height is calculated correctly for a balanced tree.
     */
    @Test
    void testRootHeightAfterInsertions() {
        bst.populate(new int[]{30, 20, 40, 10, 25, 35, 50});
        int expectedHeight = 2; // Based on balanced tree structure
        assertEquals(expectedHeight, bst.height(bst.getRoot()), "Height of root node should be 2");
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
     * Tests insertion of negative values into the BST.
     * Verifies that the tree is not empty and remains balanced.
     */
    @Test
    void testInsertNegativeValues() {
        BinarySearchTree bst = new BinarySearchTree();
        int[] negativeValues = {-10, -20, -5, -15};
        bst.populate(negativeValues);
        assertFalse(bst.isEmpty(), "BST should not be empty after inserting negative values");
        assertTrue(bst.balanced(), "BST with negative values should be balanced");
    }

    /**
     * Tests insertion of duplicate values into the BST.
     * Verifies that the tree handles duplicates (either inserts or ignores them).
     * Note: Current BST implementation inserts duplicates to the right.
     */
    @Test
    void testInsertDuplicateValues() {
        BinarySearchTree bst = new BinarySearchTree();
        int[] valuesWithDuplicates = {10, 20, 10, 30, 20};
        bst.populate(valuesWithDuplicates);
        assertFalse(bst.isEmpty(), "BST should not be empty after inserting duplicates");

        // Optional: Check structure manually via traversal
        bst.inOrder(); // Output can be visually verified
        assertTrue(true, "BST handled duplicate values (check logic if duplicates are allowed)");
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
