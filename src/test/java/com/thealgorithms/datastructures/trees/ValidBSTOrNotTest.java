package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Albina Gimaletdinova on 17/02/2023
 */
public class ValidBSTOrNotTest {
    @Test
    public void testRootNull() {
        assertTrue(ValidBSTOrNot.isBST(null));
    }

    @Test
    public void testOneNode() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[]{Integer.MIN_VALUE});
        assertTrue(ValidBSTOrNot.isBST(root));
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
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[]{9, 7, 13, 3, 8, 10, 20});
        assertTrue(ValidBSTOrNot.isBST(root));
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
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[]{9, 7, 13, 3, 8, 10, 13});
        assertFalse(ValidBSTOrNot.isBST(root));
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
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[]{9, 7, 13, 3, 8, 10, 12});
        assertFalse(ValidBSTOrNot.isBST(root));
    }
}
