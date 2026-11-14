package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumDepthOfBinaryTreeTest {

    @Test
    public void testNullRoot() {
        assertEquals(0, MaximumDepthOfBinaryTree.maxDepth(null));
    }

    @Test
    public void testSingleNode() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1});
        assertEquals(1, MaximumDepthOfBinaryTree.maxDepth(root));
    }

    /*
         3
        / \
       9  20
          / \
         15   7
    Max depth: 3
    */
    @Test
    public void testMaxDepth() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {3, 9, 20, null, null, 15, 7});
        assertEquals(3, MaximumDepthOfBinaryTree.maxDepth(root));
    }

    /*
        1
       /
      2
    Max depth: 2
    */
    @Test
    public void testLeftOnlyTree() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2});
        assertEquals(2, MaximumDepthOfBinaryTree.maxDepth(root));
    }
}
