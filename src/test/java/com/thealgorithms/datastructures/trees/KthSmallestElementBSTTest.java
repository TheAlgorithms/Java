package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class KthSmallestElementInBSTTest {

    private BinaryTree.Node createSampleTree() {
        /*
                 5
               /   \
              3     7
             / \   / \
            2   4 6   8
         */
        BinaryTree.Node root = new BinaryTree.Node(5);
        root.left = new BinaryTree.Node(3);
        root.right = new BinaryTree.Node(7);
        root.left.left = new BinaryTree.Node(2);
        root.left.right = new BinaryTree.Node(4);
        root.right.left = new BinaryTree.Node(6);
        root.right.right = new BinaryTree.Node(8);

        return root;
    }

    @Test
    void testSmallestElement() {
        BinaryTree.Node root = createSampleTree();
        assertEquals(2, KthSmallestElementInBST.kthSmallest(root, 1));
    }

    @Test
    void testRootElement() {
        BinaryTree.Node root = createSampleTree();
        assertEquals(5, KthSmallestElementInBST.kthSmallest(root, 4));
    }

    @Test
    void testRightSubtreeElement() {
        BinaryTree.Node root = createSampleTree();
        assertEquals(8, KthSmallestElementInBST.kthSmallest(root, 7));
    }

    @Test
    void testSingleNodeTree() {
        BinaryTree.Node root = new BinaryTree.Node(10);
        assertEquals(10, KthSmallestElementInBST.kthSmallest(root, 1));
    }

    @Test
    void testInvalidK() {
        BinaryTree.Node root = createSampleTree();
        assertEquals(-1, KthSmallestElementInBST.kthSmallest(root, 10));
    }
}
