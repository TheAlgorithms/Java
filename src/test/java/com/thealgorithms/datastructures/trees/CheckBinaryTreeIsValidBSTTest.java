package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 17/02/2023
 */
public class CheckBinaryTreeIsValidBSTTest {
    @Test
    public void testRootNull() {
        assertTrue(CheckBinaryTreeIsValidBST.isBST(null));
    }

    @Test
    public void testOneNode() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {Integer.MIN_VALUE});
        assertTrue(CheckBinaryTreeIsValidBST.isBST(root));
    }

    /*
         9
        / \
       7   13
      /\   / \
     3  8 10 20
    */
    @Test
    public void testBinaryTreeIsBST() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, 13, 3, 8, 10, 20});
        assertTrue(CheckBinaryTreeIsValidBST.isBST(root));
    }

    /*
         9
        / \
       7   13
      /\   / \
     3  8 10 13 <--- duplicated node
    */
    @Test
    public void testBinaryTreeWithDuplicatedNodesIsNotBST() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, 13, 3, 8, 10, 13});
        assertFalse(CheckBinaryTreeIsValidBST.isBST(root));
    }

    /*
         9
        / \
       7   13
      /\   / \
     3  8 10 12 <---- violates BST rule, needs to be more than 13 (parent node)
    */
    @Test
    public void testBinaryTreeIsNotBST() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, 13, 3, 8, 10, 12});
        assertFalse(CheckBinaryTreeIsValidBST.isBST(root));
    }
}
