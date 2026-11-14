package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RightSideViewOfBinaryTreeTest {

    @Test
    public void testNullRoot() {
        assertEquals(Collections.emptyList(), RightSideViewOfBinaryTree.rightSideView(null));
    }

    @Test
    public void testSingleNode() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1});
        assertEquals(List.of(1), RightSideViewOfBinaryTree.rightSideView(root));
    }

    /*
         1
        / \
       2   3
        \   \
         5   4
    */
    @Test
    public void testRightSideView() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, null, 5, null, 4});
        assertEquals(List.of(1, 3, 4), RightSideViewOfBinaryTree.rightSideView(root));
    }

    /*
         1
        /
       2
      /
     3
    */
    @Test
    public void testLeftSkewedTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, null, 3});
        assertEquals(List.of(1, 2, 3), RightSideViewOfBinaryTree.rightSideView(root));
    }

    /*
         1
          \
           2
            \
             3
    */
    @Test
    public void testRightSkewedTree() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, null, 2, null, 3});
        assertEquals(List.of(1, 2, 3), RightSideViewOfBinaryTree.rightSideView(root));
    }
}
