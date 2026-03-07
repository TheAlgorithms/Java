package com.thealgorithms.datastructures.trees;

/**
 * Finds the kth smallest element in a Binary Search Tree.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public final class KthSmallestElementInBST {

    private KthSmallestElementInBST() {
    }

    public static int kthSmallest(BinaryTree.Node root, int k) {
        Counter counter = new Counter();
        BinaryTree.Node result = inorder(root, k, counter);
        return result != null ? result.data : -1;
    }

    private static BinaryTree.Node inorder(BinaryTree.Node node, int k, Counter counter) {
        if (node == null) {
            return null;
        }

        BinaryTree.Node left = inorder(node.left, k, counter);
        if (left != null) {
            return left;
        }

        counter.count++;
        if (counter.count == k) {
            return node;
        }

        return inorder(node.right, k, counter);
    }

    private static class Counter {
        int count = 0;
    }
}
