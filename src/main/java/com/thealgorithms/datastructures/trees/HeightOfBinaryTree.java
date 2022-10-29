package com.thealgorithms.datastructures.trees;

public class HeightOfBinaryTree {

    class Node {

        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // Root of the Binary Tree
    Node root;

    public HeightOfBinaryTree(Node root) {
        this.root = root;
    }

    /* Compute the "height" of a tree -- the number of
  nodes along the longest path from the root node
  down to the farthest leaf node.*/
    int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            /**
             * Return the height of larger subtree
             */
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
}
