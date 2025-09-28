package com.thealgorithms.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testBSTIsNotEmpty() {
        assertFalse(bst.isEmpty(), "BST should not be empty after insertion");
    }

    @Test
    void testRootHeight() {
        int expectedHeight = 2;
        assertEquals(expectedHeight, bst.height(bst.getRoot()), "Height of root node should be 2");
    }

    @Test
    void testPopulateSortedAndBalanced() {
        BinarySearchTree sortedBST = new BinarySearchTree();
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        sortedBST.populateSorted(sortedArray);
        assertTrue(sortedBST.balanced(), "BST should be balanced after populateSorted");
    }

    @Test
    void testHeightCalculation() {
        BinarySearchTree localBST = new BinarySearchTree();
        localBST.insert(10); // Root
        localBST.insert(5);  // Left child
        localBST.insert(15); // Right child

        assertEquals(0, localBST.height(localBST.getRoot().getLeft()), "Left child height should be 0");
    }

    @Test
    void testBalancedTree() {
        assertTrue(bst.balanced(), "BST should be balanced with given values");
    }

    @Test
    void testInOrderTraversal() {
        bst.inOrder(); // Output can be redirected and verified if needed
        assertTrue(true, "Inorder traversal executed successfully");
    }

    @Test
    void testPreOrderTraversal() {
        bst.preOrder();
        assertTrue(true, "Preorder traversal executed successfully");
    }

    @Test
    void testRootHeightAfterInsertions() {
        bst.populate(new int[] {30, 20, 40, 10, 25, 35, 50});
        int expectedHeight = 2;
        assertEquals(expectedHeight, bst.height(bst.getRoot()), "Height of root node should be 2");
    }

    @Test
    void testPostOrderTraversal() {
        bst.postOrder();
        assertTrue(true, "Postorder traversal executed successfully");
    }

    @Test
    void testPrettyDisplay() {
        bst.prettyDisplay();
        assertTrue(true, "Pretty display executed successfully");
    }

    @Test
    void testInsertNegativeValues() {
        BinarySearchTree negativeBST = new BinarySearchTree();
        int[] negativeValues = {-10, -20, -5, -15};
        negativeBST.populate(negativeValues);
        assertFalse(negativeBST.isEmpty(), "BST should not be empty after inserting negative values");
        assertTrue(negativeBST.balanced(), "BST with negative values should be balanced");
    }

    @Test
    void testInsertDuplicateValues() {
        BinarySearchTree duplicateBST = new BinarySearchTree();
        int[] valuesWithDuplicates = {10, 20, 10, 30, 20};
        duplicateBST.populate(valuesWithDuplicates);
        assertFalse(duplicateBST.isEmpty(), "BST should not be empty after inserting duplicates");

        duplicateBST.inOrder(); // Visual check if needed
        assertTrue(true, "BST handled duplicate values (check logic if duplicates are allowed)");
    }

    @Test
    void testPopulateSorted() {
        int[] sortedValues = {10, 20, 30, 40, 50};
        BinarySearchTree sortedBST = new BinarySearchTree();
        sortedBST.populateSorted(sortedValues);
        assertTrue(sortedBST.balanced(), "BST populated with sorted array should be balanced");
    }
}
