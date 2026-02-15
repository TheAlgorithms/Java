package com.thealgorithms.datastructures.trees;

public final class InvertBinaryTree {

    private InvertBinaryTree() {
    }

    public static BinaryTree.Node invertTree(BinaryTree.Node root) {
        if (root == null) {
            return null;
        }

        BinaryTree.Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
