package com.thealgorithms.datastructures.trees;

public class LevelOrderTraversal {

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

    public LevelOrderTraversal(Node root) {
        this.root = root;
    }

    /* function to print level order traversal of tree*/
    void printLevelOrder() {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            printGivenLevel(root, i);
        }
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

    /* Print nodes at the given level */
    void printGivenLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }
}
