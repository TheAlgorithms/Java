package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 06/05/2023
 */
public class BSTRecursiveTest {
    @Test
    public void testBSTIsCorrectlyConstructedFromOneNode() {
        BSTRecursive tree = new BSTRecursive();
        tree.add(6);

        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(tree.getRoot()));
    }

    @Test
    public void testBSTIsCorrectlyCleanedAndEmpty() {
        BSTRecursive tree = new BSTRecursive();

        tree.add(6);
        tree.remove(6);

        tree.add(12);
        tree.add(1);
        tree.add(2);

        tree.remove(1);
        tree.remove(2);
        tree.remove(12);

        Assertions.assertNull(tree.getRoot());
    }

    @Test
    public void testBSTIsCorrectlyCleanedAndNonEmpty() {
        BSTRecursive tree = new BSTRecursive();

        tree.add(6);
        tree.remove(6);

        tree.add(12);
        tree.add(1);
        tree.add(2);

        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(tree.getRoot()));
    }

    @Test
    public void testBSTIsCorrectlyConstructedFromMultipleNodes() {
        BSTRecursive tree = new BSTRecursive();
        tree.add(7);
        tree.add(1);
        tree.add(5);
        tree.add(100);
        tree.add(50);

        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(tree.getRoot()));
    }
}
