package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for BinarySearchTree
 * 
 * @author Raghu0703
 */
class BinarySearchTreeTest {
    
    private BinarySearchTree bst;
    
    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }
    
    @Test
    void testEmptyTree() {
        assertTrue(bst.isEmpty(), "New tree should be empty");
        assertEquals(0, bst.height(), "Empty tree height should be 0");
    }
    
    @Test
    void testInsertSingleElement() {
        bst.insert(10);
        assertFalse(bst.isEmpty(), "Tree should not be empty after insert");
        assertTrue(bst.search(10), "Should find inserted element");
        assertEquals(1, bst.height(), "Height should be 1 with single element");
    }
    
    @Test
    void testInsertMultipleElements() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        assertTrue(bst.search(50), "Should find 50");
        assertTrue(bst.search(30), "Should find 30");
        assertTrue(bst.search(70), "Should find 70");
        assertTrue(bst.search(20), "Should find 20");
        assertTrue(bst.search(40), "Should find 40");
        assertTrue(bst.search(60), "Should find 60");
        assertTrue(bst.search(80), "Should find 80");
    }
    
    @Test
    void testSearchNonExistentElement() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        
        assertFalse(bst.search(100), "Should not find non-existent element");
        assertFalse(bst.search(25), "Should not find non-existent element");
    }
    
    @Test
    void testSearchInEmptyTree() {
        assertFalse(bst.search(10), "Should not find element in empty tree");
    }
    
    @Test
    void testInorderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        String expected = "20 30 40 50 60 70 80";
        assertEquals(expected, bst.inorder(), "Inorder traversal should be sorted");
    }
    
    @Test
    void testPreorderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        
        String expected = "50 30 20 40 70";
        assertEquals(expected, bst.preorder(), "Preorder traversal should match");
    }
    
    @Test
    void testPostorderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        
        String expected = "20 40 30 70 50";
        assertEquals(expected, bst.postorder(), "Postorder traversal should match");
    }
    
    @Test
    void testDeleteLeafNode() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        
        bst.delete(20);
        assertFalse(bst.search(20), "Deleted leaf node should not be found");
        assertEquals("30 50 70", bst.inorder(), "Tree should be correct after deletion");
    }
    
    @Test
    void testDeleteNodeWithOneChild() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(60);
        
        bst.delete(70);
        assertFalse(bst.search(70), "Deleted node should not be found");
        assertTrue(bst.search(60), "Child of deleted node should still exist");
        assertEquals("30 50 60", bst.inorder(), "Tree should be correct after deletion");
    }
    
    @Test
    void testDeleteNodeWithTwoChildren() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        bst.delete(50);
        assertFalse(bst.search(50), "Deleted node should not be found");
        assertEquals("20 30 40 60 70 80", bst.inorder(), "Tree should be correct after deletion");
    }
    
    @Test
    void testDeleteRootNode() {
        bst.insert(50);
        bst.delete(50);
        assertTrue(bst.isEmpty(), "Tree should be empty after deleting only node");
    }
    
    @Test
    void testDeleteNonExistentElement() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        
        bst.delete(100);
        assertEquals("30 50 70", bst.inorder(), "Tree should remain unchanged");
    }
    
    @Test
    void testHeight() {
        bst.insert(50);
        assertEquals(1, bst.height(), "Height should be 1");
        
        bst.insert(30);
        bst.insert(70);
        assertEquals(2, bst.height(), "Height should be 2");
        
        bst.insert(20);
        assertEquals(3, bst.height(), "Height should be 3");
    }
    
    @Test
    void testInsertDuplicates() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(50); // Duplicate
        
        String result = bst.inorder();
        // BST should handle duplicates by either ignoring or placing right
        assertTrue(result.contains("30") && result.contains("50"), 
                   "Tree should contain all unique values");
    }
    
    @Test
    void testComplexOperations() {
        // Insert elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        // Verify structure
        assertEquals("20 30 40 50 60 70 80", bst.inorder());
        
        // Delete some elements
        bst.delete(20);
        bst.delete(70);
        
        // Verify after deletions
        assertEquals("30 40 50 60 80", bst.inorder());
        
        // Search for remaining elements
        assertTrue(bst.search(50));
        assertTrue(bst.search(30));
        assertFalse(bst.search(20));
        assertFalse(bst.search(70));
    }
    
    @Test
    void testAscendingOrder() {
        // Insert in ascending order
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(40);
        bst.insert(50);
        
        assertEquals("10 20 30 40 50", bst.inorder());
        assertTrue(bst.search(30));
    }
    
    @Test
    void testDescendingOrder() {
        // Insert in descending order
        bst.insert(50);
        bst.insert(40);
        bst.insert(30);
        bst.insert(20);
        bst.insert(10);
        
        assertEquals("10 20 30 40 50", bst.inorder());
        assertTrue(bst.search(30));
    }
}
