package com.thealgorithms.datastructures.trees;

/**
 * Inverts a binary tree by swapping the left and right children of every node.
 *
 * <p>Example:
 * <pre>
 *      4                4
 *     / \              / \
 *    2   7    ->      7   2
 *   / \ / \          / \ / \
 *  1  3 6  9        9  6 3  1
 * </pre>
 *
 * <p>Time complexity: O(n), Space complexity: O(h) where h is tree height.
 *
 * @see <a href="https://leetcode.com/problems/invert-binary-tree/">LeetCode #226</a>
 */
public final class InvertBinaryTree {
    private InvertBinaryTree() {
    }

    public static BinaryTree.Node invertTree(BinaryTree.Node root) {
        if (root == null) {
            return null;
        }
        BinaryTree.Node temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}
