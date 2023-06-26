package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test check both implemented ways, iterative and recursive algorithms.
 *
 * @author Albina Gimaletdinova on 26/06/2023
 */
public class CheckIfBinaryTreeBalancedTest {
    @Test
    public void testRootNull() {
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedRecursive(null));
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedIterative(null));
    }

    @Test
    public void testOneNode() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {Integer.MIN_VALUE});
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedRecursive(root));
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedIterative(root));
    }

    /*
     9    <-- Math.abs(height(left) - height(right)) == 0
    / \
   7   13
  /\   / \
 3  8 10 20
*/
    @Test
    public void testBinaryTreeIsBalancedEqualSubtreeHeights() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, 13, 3, 8, 10, 20});
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedRecursive(root));
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedIterative(root));
    }

    /*
     9    <-- Math.abs(height(left) - height(right)) == 1
    / \
   7   13
  /\
 3  8
*/
    @Test
    public void testBinaryTreeIsBalancedWithDifferentHeights() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, 13, 3, 8});
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedRecursive(root));
        assertTrue(CheckIfBinaryTreeBalanced.isBalancedIterative(root));
    }

    /*
         9  <-- only left tree exists, Math.abs(height(left) - height(right)) > 1
        /
       7
      /\
     3  8
    */
    @Test
    public void testBinaryTreeNotBalanced() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, null, 3, 8});
        assertFalse(CheckIfBinaryTreeBalanced.isBalancedRecursive(root));
        assertFalse(CheckIfBinaryTreeBalanced.isBalancedIterative(root));
    }

    /*
         9    <-- Math.abs(height(left) - height(right)) > 1
        / \
       7   13
      /\
     3  8
    /
   11
*/
    @Test
    public void testBinaryTreeNotBalancedBecauseLeftTreeNotBalanced() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {9, 7, 13, 3, 8, null, null, 11});
        assertFalse(CheckIfBinaryTreeBalanced.isBalancedRecursive(root));
        assertFalse(CheckIfBinaryTreeBalanced.isBalancedIterative(root));
    }
}
