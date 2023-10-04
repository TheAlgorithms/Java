package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 20/04/2023
 */
public class BSTFromSortedArrayTest {
    @Test
    public void testNullArray() {
        BinaryTree.Node actualBST = BSTFromSortedArray.createBST(null);
        Assertions.assertNull(actualBST);
    }

    @Test
    public void testEmptyArray() {
        BinaryTree.Node actualBST = BSTFromSortedArray.createBST(new int[] {});
        Assertions.assertNull(actualBST);
    }

    @Test
    public void testSingleElementArray() {
        BinaryTree.Node actualBST = BSTFromSortedArray.createBST(new int[] {Integer.MIN_VALUE});
        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(actualBST));
    }

    @Test
    public void testCreateBSTFromSmallArray() {
        BinaryTree.Node actualBST = BSTFromSortedArray.createBST(new int[] {1, 2, 3});
        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(actualBST));
    }

    @Test
    public void testCreateBSTFromLongerArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BinaryTree.Node actualBST = BSTFromSortedArray.createBST(array);
        Assertions.assertTrue(CheckBinaryTreeIsValidBST.isBST(actualBST));
    }

    @Test
    public void testShouldNotCreateBSTFromNonSortedArray() {
        int[] array = {10, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        BinaryTree.Node actualBST = BSTFromSortedArray.createBST(array);
        Assertions.assertFalse(CheckBinaryTreeIsValidBST.isBST(actualBST));
    }
}
