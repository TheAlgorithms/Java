package com.thealgorithms.tree;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for BinarySearchTree. Tests: - insert and search - delete (leaf,
 * one child, two children) - findMin and findMax - inorder, preorder, postorder
 * traversals
 */
class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
    }

    @Test
    void testSearch() {
        assertTrue(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
        assertFalse(bst.search(7));
    }

    @Test
    void testFindMinMax() {
        assertEquals(5, bst.findMin());
        assertEquals(15, bst.findMax());
    }

    @Test
    void testDeleteLeafNode() {
        bst.delete(5);
        assertFalse(bst.search(5));
        assertTrue(bst.search(10));
        assertTrue(bst.search(15));
    }

    @Test
    void testDeleteNodeWithOneChild() {
        bst.insert(12);
        bst.delete(15);
        assertFalse(bst.search(15));
        assertTrue(bst.search(12));
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        bst.insert(12);
        bst.insert(20);
        bst.delete(10);
        assertFalse(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(12));
        assertTrue(bst.search(15));
    }

    @Test
    void testInorderTraversal() {
        List<Integer> expected = Arrays.asList(5, 10, 15);
        assertEquals(expected, bst.inorderList());
    }

    @Test
    void testPreorderTraversal() {
        List<Integer> expected = Arrays.asList(10, 5, 15);
        assertEquals(expected, bst.preorderList());
    }

    @Test
    void testPostorderTraversal() {
        List<Integer> expected = Arrays.asList(5, 15, 10);
        assertEquals(expected, bst.postorderList());
    }
}
