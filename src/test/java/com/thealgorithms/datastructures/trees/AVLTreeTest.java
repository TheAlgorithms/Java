package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for AVLTree
 *
 * @author Raghu0703
 */
class AVLTreeTest {

    private AVLTree avl;

    @BeforeEach
    void setUp() {
        avl = new AVLTree();
    }

    @Test
    void testEmptyTree() {
        assertTrue(avl.isEmpty(), "New tree should be empty");
        assertEquals(0, avl.getHeight(), "Empty tree height should be 0");
        assertTrue(avl.isBalanced(), "Empty tree should be balanced");
    }

    @Test
    void testInsertSingleElement() {
        avl.insert(10);
        assertFalse(avl.isEmpty(), "Tree should not be empty after insert");
        assertTrue(avl.search(10), "Should find inserted element");
        assertEquals(1, avl.getHeight(), "Height should be 1 with single element");
        assertTrue(avl.isBalanced(), "Tree should be balanced");
    }

    @Test
    void testInsertMultipleElements() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);

        assertTrue(avl.search(50), "Should find 50");
        assertTrue(avl.search(30), "Should find 30");
        assertTrue(avl.search(70), "Should find 70");
        assertTrue(avl.search(20), "Should find 20");
        assertTrue(avl.search(40), "Should find 40");
        assertTrue(avl.search(60), "Should find 60");
        assertTrue(avl.search(80), "Should find 80");
        assertTrue(avl.isBalanced(), "Tree should remain balanced");
    }

    @Test
    void testLeftLeftRotation() {
        // Insert in descending order to trigger LL rotation
        avl.insert(30);
        avl.insert(20);
        avl.insert(10);

        assertEquals("10 20 30", avl.inorder(), "Tree should be balanced after LL rotation");
        assertTrue(avl.isBalanced(), "Tree should be balanced");
        assertEquals(2, avl.getHeight(), "Height should be 2 after balancing");
    }

    @Test
    void testRightRightRotation() {
        // Insert in ascending order to trigger RR rotation
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);

        assertEquals("10 20 30", avl.inorder(), "Tree should be balanced after RR rotation");
        assertTrue(avl.isBalanced(), "Tree should be balanced");
        assertEquals(2, avl.getHeight(), "Height should be 2 after balancing");
    }

    @Test
    void testLeftRightRotation() {
        // Insert to trigger LR rotation
        avl.insert(30);
        avl.insert(10);
        avl.insert(20);

        assertEquals("10 20 30", avl.inorder(), "Tree should be balanced after LR rotation");
        assertTrue(avl.isBalanced(), "Tree should be balanced");
        assertEquals(2, avl.getHeight(), "Height should be 2 after balancing");
    }

    @Test
    void testRightLeftRotation() {
        // Insert to trigger RL rotation
        avl.insert(10);
        avl.insert(30);
        avl.insert(20);

        assertEquals("10 20 30", avl.inorder(), "Tree should be balanced after RL rotation");
        assertTrue(avl.isBalanced(), "Tree should be balanced");
        assertEquals(2, avl.getHeight(), "Height should be 2 after balancing");
    }

    @Test
    void testSearchNonExistentElement() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);

        assertFalse(avl.search(100), "Should not find non-existent element");
        assertFalse(avl.search(25), "Should not find non-existent element");
    }

    @Test
    void testSearchInEmptyTree() {
        assertFalse(avl.search(10), "Should not find element in empty tree");
    }

    @Test
    void testInorderTraversal() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);

        String expected = "20 30 40 50 60 70 80";
        assertEquals(expected, avl.inorder(), "Inorder traversal should be sorted");
    }

    @Test
    void testPreorderTraversal() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);

        String result = avl.preorder();
        assertTrue(result.contains("50") && result.contains("30") && result.contains("70"), "Preorder traversal should contain all elements");
    }

    @Test
    void testDeleteLeafNode() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);

        avl.delete(20);
        assertFalse(avl.search(20), "Deleted leaf node should not be found");
        assertTrue(avl.isBalanced(), "Tree should remain balanced after deletion");
        assertEquals("30 50 70", avl.inorder(), "Tree should be correct after deletion");
    }

    @Test
    void testDeleteNodeWithOneChild() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(60);

        avl.delete(70);
        assertFalse(avl.search(70), "Deleted node should not be found");
        assertTrue(avl.search(60), "Child of deleted node should still exist");
        assertTrue(avl.isBalanced(), "Tree should remain balanced after deletion");
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);

        avl.delete(50);
        assertFalse(avl.search(50), "Deleted node should not be found");
        assertTrue(avl.isBalanced(), "Tree should remain balanced after deletion");
        String result = avl.inorder();
        assertEquals("20 30 40 60 70 80", result, "Tree should be correct after deletion");
    }

    @Test
    void testDeleteRootNode() {
        avl.insert(50);
        avl.delete(50);
        assertTrue(avl.isEmpty(), "Tree should be empty after deleting only node");
    }

    @Test
    void testDeleteNonExistentElement() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);

        avl.delete(100);
        assertEquals("30 50 70", avl.inorder(), "Tree should remain unchanged");
        assertTrue(avl.isBalanced(), "Tree should remain balanced");
    }

    @Test
    void testDeleteWithRebalancing() {
        // Create a tree that requires rebalancing after deletion
        avl.insert(50);
        avl.insert(25);
        avl.insert(75);
        avl.insert(10);
        avl.insert(30);
        avl.insert(60);
        avl.insert(80);
        avl.insert(5);
        avl.insert(15);

        // Delete node that will trigger rebalancing
        avl.delete(80);
        avl.delete(75);

        assertTrue(avl.isBalanced(), "Tree should be balanced after deletions");
        assertFalse(avl.search(80), "Deleted element should not be found");
        assertFalse(avl.search(75), "Deleted element should not be found");
    }

    @Test
    void testHeight() {
        avl.insert(50);
        assertEquals(1, avl.getHeight(), "Height should be 1");

        avl.insert(30);
        avl.insert(70);
        assertEquals(2, avl.getHeight(), "Height should be 2");

        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);
        // AVL tree should keep height minimal
        assertTrue(avl.getHeight() <= 4, "Height should be logarithmic");
        assertTrue(avl.isBalanced(), "Tree should be balanced");
    }

    @Test
    void testInsertDuplicates() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(50); // Duplicate

        String result = avl.inorder();
        assertTrue(result.contains("30") && result.contains("50"), "Tree should contain all unique values");
        assertTrue(avl.isBalanced(), "Tree should remain balanced");
    }

    @Test
    void testComplexOperations() {
        // Insert elements
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);

        // Verify structure
        assertEquals("20 30 40 50 60 70 80", avl.inorder());
        assertTrue(avl.isBalanced(), "Tree should be balanced");

        // Delete some elements
        avl.delete(20);
        avl.delete(70);

        // Verify after deletions
        assertEquals("30 40 50 60 80", avl.inorder());
        assertTrue(avl.isBalanced(), "Tree should remain balanced after deletions");

        // Search for remaining elements
        assertTrue(avl.search(50));
        assertTrue(avl.search(30));
        assertFalse(avl.search(20));
        assertFalse(avl.search(70));
    }

    @Test
    void testAscendingOrder() {
        // Insert in ascending order - AVL should handle this efficiently
        for (int i = 1; i <= 10; i++) {
            avl.insert(i);
        }

        assertEquals("1 2 3 4 5 6 7 8 9 10", avl.inorder());
        assertTrue(avl.isBalanced(), "Tree should remain balanced with sequential inserts");
        // Height should be logarithmic, not linear
        assertTrue(avl.getHeight() <= 5, "Height should be logarithmic for 10 elements");
    }

    @Test
    void testDescendingOrder() {
        // Insert in descending order - AVL should handle this efficiently
        for (int i = 10; i >= 1; i--) {
            avl.insert(i);
        }

        assertEquals("1 2 3 4 5 6 7 8 9 10", avl.inorder());
        assertTrue(avl.isBalanced(), "Tree should remain balanced with reverse sequential inserts");
        assertTrue(avl.getHeight() <= 5, "Height should be logarithmic for 10 elements");
    }

    @Test
    void testLargeNumberOfElements() {
        // Insert many elements to test balancing efficiency
        for (int i = 1; i <= 100; i++) {
            avl.insert(i);
            assertTrue(avl.isBalanced(), "Tree should remain balanced at every insertion");
        }

        // AVL tree with 100 elements should have height around log2(100) ≈ 7
        assertTrue(avl.getHeight() <= 10, "Height should be logarithmic for 100 elements");

        // Verify all elements are searchable
        for (int i = 1; i <= 100; i++) {
            assertTrue(avl.search(i), "Should find element " + i);
        }
    }

    @Test
    void testBalanceAfterMultipleDeletions() {
        // Insert elements
        for (int i = 1; i <= 15; i++) {
            avl.insert(i);
        }

        // Delete half of them
        for (int i = 1; i <= 15; i += 2) {
            avl.delete(i);
        }

        assertTrue(avl.isBalanced(), "Tree should remain balanced after multiple deletions");

        // Verify remaining elements
        for (int i = 2; i <= 14; i += 2) {
            assertTrue(avl.search(i), "Should find remaining element " + i);
        }
    }

    @Test
    void testEmptyTreeAfterDeletions() {
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);

        avl.delete(10);
        avl.delete(20);
        avl.delete(30);

        assertTrue(avl.isEmpty(), "Tree should be empty after all deletions");
        assertEquals(0, avl.getHeight(), "Height should be 0 for empty tree");
    }
}
