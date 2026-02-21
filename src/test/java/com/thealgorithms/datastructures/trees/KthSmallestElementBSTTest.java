package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthSmallestElementBSTTest {

    @Test
    void simpleTest() {
        BinaryTree.Node root = new BinaryTree.Node(5);
        root.left = new BinaryTree.Node(3);
        root.right = new BinaryTree.Node(7);

        assertEquals(3, KthSmallestElementInBST.kthSmallest(root, 1));
    }
}
