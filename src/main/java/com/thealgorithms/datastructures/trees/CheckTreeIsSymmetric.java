package com.thealgorithms.datastructures.trees;

import com.thealgorithms.datastructures.trees.BinaryTree.Node;

/**
 * Check if a binary tree is symmetric or not.
 * A binary tree is a symmetric tree if the left and right subtree of root are mirror image.
 * Below is a symmetric tree
 *                               1
 *                   /                         \
 *                2                           2
 *         /                \             /             \
 *      3                    4         4                3
 *
 * Below is not symmetric because values is different in last level
 *                               1
 *                   /                         \
 *                 2                           2
 *         /                \             /             \
 *      3                    5         4                3
 * <p>
 * Approach:
 * Recursively check for left and right subtree of root
 * 1. left subtrees root's values should be equal right subtree's root value
 * 2. recursively check with left subtrees' left child VS right subtree's right child AND
 * left subtree's right child VS right subtree left child
 * Complexity
 * 1. Time: O(n)
 * 2. Space: O(lg(n)) for height of tree
 *
 * @author kumanoit on 10/10/22 IST 12:52 AM
 */
public final class CheckTreeIsSymmetric {
    private CheckTreeIsSymmetric() {
    }

    public static boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(Node leftSubtreeRoot, Node rightSubtreeRoot) {
        if (leftSubtreeRoot == null && rightSubtreeRoot == null) {
            return true;
        }

        if (isInvalidSubtree(leftSubtreeRoot, rightSubtreeRoot)) {
            return false;
        }

        return isSymmetric(leftSubtreeRoot.right, rightSubtreeRoot.left) && isSymmetric(leftSubtreeRoot.left, rightSubtreeRoot.right);
    }

    private static boolean isInvalidSubtree(Node leftSubtreeRoot, Node rightSubtreeRoot) {
        return leftSubtreeRoot == null || rightSubtreeRoot == null || leftSubtreeRoot.data != rightSubtreeRoot.data;
    }
}
