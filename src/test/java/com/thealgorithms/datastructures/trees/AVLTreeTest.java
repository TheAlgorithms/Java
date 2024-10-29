package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AVLTreeTest {
    private AVLTree avlTree;

    @BeforeEach
    public void setUp() {
        avlTree = new AVLTree();
    }

    @Test
    public void testInsert() {
        assertTrue(avlTree.insert(10));
        assertTrue(avlTree.insert(20));
        assertTrue(avlTree.insert(5));
        assertFalse(avlTree.insert(10)); // Duplicate
    }

    @Test
    public void testSearch() {
        avlTree.insert(15);
        avlTree.insert(25);
        assertTrue(avlTree.search(15));
        assertFalse(avlTree.search(30)); // Not in the tree
    }

    @Test
    public void testDeleteLeafNode() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.delete(30);
        assertFalse(avlTree.search(30));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        avlTree.insert(20);
        avlTree.insert(10);
        avlTree.insert(30);
        avlTree.delete(10);
        assertFalse(avlTree.search(10));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        avlTree.insert(20);
        avlTree.insert(10);
        avlTree.insert(30);
        avlTree.insert(25);
        avlTree.delete(20);
        assertFalse(avlTree.search(20));
        assertTrue(avlTree.search(30));
        assertTrue(avlTree.search(25));
    }

    @Test
    public void testReturnBalance() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);
        List<Integer> balances = avlTree.returnBalance();
        assertEquals(3, balances.size()); // There should be 3 nodes
        assertEquals(0, balances.get(0)); // Balance for node 5
        assertEquals(0, balances.get(1)); // Balance for node 10
        assertEquals(0, balances.get(2)); // Balance for node 20
    }

    @Test
    public void testInsertAndRebalance() {
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(10); // This should cause a right rotation
        assertTrue(avlTree.search(20));
        assertTrue(avlTree.search(10));
        assertTrue(avlTree.search(30));
    }

    @Test
    public void testComplexInsertionAndDeletion() {
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(10);
        avlTree.insert(25);
        avlTree.insert(5);
        avlTree.insert(15);

        avlTree.delete(20); // Test deletion
        assertFalse(avlTree.search(20));
        assertTrue(avlTree.search(30));
        assertTrue(avlTree.search(25));
    }
}
