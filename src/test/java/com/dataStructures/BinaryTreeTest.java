package com.dataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BinaryTreeTest {

    /**
     * Test of insert method, of class BinaryTree.
     */
    @Test
    public void testInsertBinaryTree() {
        System.out.println("insert");
        BinaryTree<String> lowerData = new BinaryTree<>("1");
        BinaryTree<String> upperData = new BinaryTree<>("3");
        BinaryTree<String> instance = new BinaryTree<>("2");
        instance.insert(lowerData);
        instance.insert(upperData);
        String proof = instance.getLeft().toString()
                + instance.toString()
                + instance.getRight().toString();
        assertEquals("123", proof);
    }

    /**
     * Test of search method, of class BinaryTree.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        BinaryTree<Integer> instance = new BinaryTree<>(5);
        for (int i = 1; i < 10; i++) {
            instance.insert(i);
        }
        BinaryTree result = instance.search(1);
        assertEquals(1, result.getData());
    }

    /**
     * Test of contains method, of class BinaryTree.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        BinaryTree<Integer> instance = new BinaryTree<>(5);
        for (int i = 1; i < 10; i++) {
            instance.insert(i);
        }

        boolean result = instance.contains(2) && instance.contains(11);
        assertFalse(result);
    }
}
