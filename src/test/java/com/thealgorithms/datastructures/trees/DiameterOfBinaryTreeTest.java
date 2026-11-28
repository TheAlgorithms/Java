package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiameterOfBinaryTreeTest {

    @Test
    public void testNullRoot() {
        assertEquals(0, DiameterOfBinaryTree.diameterOfBinaryTree(null));
    }

    @Test
    public void testSingleNode() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1});
        assertEquals(0, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    /*
          1
         / \
        2   3
       / \
      4   5
    Diameter: 3
    */
    @Test
    public void testDiameterThroughRoot() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5});
        assertEquals(3, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    /*
        1
       /
      2
     /
    3
    Diameter: 2
    */
    @Test
    public void testLinearTree() {
        BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, null, 3});
        assertEquals(2, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }
}
