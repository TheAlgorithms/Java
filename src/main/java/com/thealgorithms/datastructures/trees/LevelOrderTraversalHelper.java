package com.thealgorithms.datastructures.trees;

public class LevelOrderTraversalHelper {
    /* function to print level order traversal of tree*/
    public static void printLevelOrder(BinaryTree.Node root) {
        if (root == null) {
            System.out.println("Root node must not be null! Exiting.");
            return;
        }

        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            printGivenLevel(root, i);
        }
    }

    /* Compute the "height" of a tree -- the number of
  nodes along the longest path from the root node
  down to the farthest leaf node.*/
    private static int height(BinaryTree.Node root) {
        if (root == null) {
            return 0;
        } else {
            //return the height of larger subtree
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    /* Print nodes at the given level */
    public static void printGivenLevel(BinaryTree.Node root, int level) {
        if (root == null) {
            System.out.println("Root node must not be null! Exiting.");
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
