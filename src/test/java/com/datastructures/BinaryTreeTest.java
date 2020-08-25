package com.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    BinaryTreeTest() {
    }

    /**
     * Test of insert method, of class BinaryTree.
     */
    @Test
    void testInsertBinaryTree() {
        System.out.println("insert");
        BinaryTree<String> lowerData = new BinaryTree<>("1");
        BinaryTree<String> upperData = new BinaryTree<>("3");
        BinaryTree<String> instance = new BinaryTree<>("2");
        instance.insert(lowerData);
        instance.insert(upperData);
        String proof = instance.getLeft().toString()
                + instance.toString()
                + instance.getRight().toString();
        Assertions.assertEquals("123", proof);
    }

    /**
     * Test of search method, of class BinaryTree.
     */
    @Test
    void testSearch() {
        System.out.println("search");
        BinaryTree<Integer> instance = new BinaryTree<>(5);
        for (int i = 1; i < 10; i++) {
            instance.insert(i);
        }
        BinaryTree result = instance.search(1);
        Assertions.assertEquals(1, result.getData());
    }

    /**
     * Test of contains method, of class BinaryTree.
     */
    @Test
    void testContains() {
        System.out.println("contains");
        BinaryTree<Integer> instance = new BinaryTree<>(5);
        for (int i = 1; i < 10; i++) {
            instance.insert(i);
        }

        boolean result = instance.contains(2) && instance.contains(11);
        Assertions.assertFalse(result);
    }
}
