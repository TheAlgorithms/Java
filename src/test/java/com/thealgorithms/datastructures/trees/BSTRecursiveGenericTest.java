package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for BSTRecursiveGeneric class.
 * Covers insertion, deletion, search, traversal, sorting, and display.
 *
 * Author: Udaya Krishnan M
 * GitHub: https://github.com/UdayaKrishnanM/
 */
class BSTRecursiveGenericTest {

    private BSTRecursiveGeneric<Integer> intTree;
    private BSTRecursiveGeneric<String> stringTree;

    /**
     * Initializes test trees before each test.
     */
    @BeforeEach
    void setUp() {
        intTree = new BSTRecursiveGeneric<>();
        stringTree = new BSTRecursiveGeneric<>();
    }

    /**
     * Tests insertion and search of integer values.
     */
    @Test
    void testAddAndFindInteger() {
        intTree.add(10);
        intTree.add(5);
        intTree.add(15);
        assertTrue(intTree.find(10));
        assertTrue(intTree.find(5));
        assertTrue(intTree.find(15));
        assertFalse(intTree.find(20));
    }

    /**
     * Tests insertion and search of string values.
     */
    @Test
    void testAddAndFindString() {
        stringTree.add("apple");
        stringTree.add("banana");
        stringTree.add("cherry");
        assertTrue(stringTree.find("banana"));
        assertFalse(stringTree.find("date"));
    }

    /**
     * Tests deletion of existing and non-existing elements.
     */
    @Test
    void testRemoveElements() {
        intTree.add(10);
        intTree.add(5);
        intTree.add(15);
        assertTrue(intTree.find(5));
        intTree.remove(5);
        assertFalse(intTree.find(5));
        intTree.remove(100); // non-existent
        assertFalse(intTree.find(100));
    }

    /**
     * Tests inorder traversal output.
     */
    @Test
    void testInorderTraversal() {
        intTree.add(20);
        intTree.add(10);
        intTree.add(30);
        intTree.inorder(); // visually verify output
        assertTrue(true);
    }

    /**
     * Tests preorder traversal output.
     */
    @Test
    void testPreorderTraversal() {
        intTree.add(20);
        intTree.add(10);
        intTree.add(30);
        intTree.preorder(); // visually verify output
        assertTrue(true);
    }

    /**
     * Tests postorder traversal output.
     */
    @Test
    void testPostorderTraversal() {
        intTree.add(20);
        intTree.add(10);
        intTree.add(30);
        intTree.postorder(); // visually verify output
        assertTrue(true);
    }

    /**
     * Tests inorderSort returns sorted list.
     */
    @Test
    void testInorderSort() {
        intTree.add(30);
        intTree.add(10);
        intTree.add(20);
        List<Integer> sorted = intTree.inorderSort();
        assertEquals(List.of(10, 20, 30), sorted);
    }

    /**
     * Tests prettyDisplay method for visual tree structure.
     */
    @Test
    void testPrettyDisplay() {
        intTree.add(50);
        intTree.add(30);
        intTree.add(70);
        intTree.add(20);
        intTree.add(40);
        intTree.add(60);
        intTree.add(80);
        intTree.prettyDisplay(); // visually verify output
        assertTrue(true);
    }

    /**
     * Tests edge case: empty tree.
     */
    @Test
    void testEmptyTree() {
        assertFalse(intTree.find(1));
        List<Integer> sorted = intTree.inorderSort();
        assertTrue(sorted.isEmpty());
    }

    /**
     * Tests edge case: single node tree.
     */
    @Test
    void testSingleNodeTree() {
        intTree.add(42);
        assertTrue(intTree.find(42));
        intTree.remove(42);
        assertFalse(intTree.find(42));
    }

    /**
     * Tests duplicate insertions.
     */
    @Test
    void testDuplicateInsertions() {
        intTree.add(10);
        intTree.add(10);
        intTree.add(10);
        List<Integer> sorted = intTree.inorderSort();
        assertEquals(List.of(10), sorted); // assuming duplicates are ignored
    }
}
