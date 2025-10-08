package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightViewOfBinaryTreeTest {

    @Test
    public void testRightViewOfBalancedTree() {
        RightViewOfBinaryTree.Node root = new RightViewOfBinaryTree.Node(1);
        root.left = new RightViewOfBinaryTree.Node(2);
        root.right = new RightViewOfBinaryTree.Node(3);
        root.left.left = new RightViewOfBinaryTree.Node(4);
        root.left.right = new RightViewOfBinaryTree.Node(5);
        root.right.right = new RightViewOfBinaryTree.Node(6);

        List<Integer> expected = List.of(1, 3, 6);
        assertEquals(expected, RightViewOfBinaryTree.rightViewDFS(root));
    }

    @Test
    public void testRightSkewedTree() {
        RightViewOfBinaryTree.Node root = new RightViewOfBinaryTree.Node(1);
        root.right = new RightViewOfBinaryTree.Node(2);
        root.right.right = new RightViewOfBinaryTree.Node(3);

        List<Integer> expected = List.of(1, 2, 3);
        assertEquals(expected, RightViewOfBinaryTree.rightViewDFS(root));
    }

    @Test
    public void testLeftSkewedTree() {
        RightViewOfBinaryTree.Node root = new RightViewOfBinaryTree.Node(1);
        root.left = new RightViewOfBinaryTree.Node(2);
        root.left.left = new RightViewOfBinaryTree.Node(3);

        List<Integer> expected = List.of(1, 2, 3);
        assertEquals(expected, RightViewOfBinaryTree.rightViewBFS(root));
    }
}
