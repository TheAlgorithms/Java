package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 23/04/2023
 */
public class BSTIterativeTest {
    @Test
    public void testBSTIsCorrectlyConstructedFromOneNode() {
        BSTIterative tree = new BSTIterative();
        tree.add(6);

        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(tree.getRoot()));
    }

    @Test
    public void testBSTIsCorrectlyCleanedAndEmpty() {
        BSTIterative tree = new BSTIterative();

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
        BSTIterative tree = new BSTIterative();

        tree.add(6);
        tree.remove(6);

        tree.add(12);
        tree.add(1);
        tree.add(2);

        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(tree.getRoot()));
    }

    @Test
    public void testBSTIsCorrectlyConstructedFromMultipleNodes() {
        BSTIterative tree = new BSTIterative();
        tree.add(7);
        tree.add(1);
        tree.add(5);
        tree.add(100);
        tree.add(50);

        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(tree.getRoot()));
    }
}
