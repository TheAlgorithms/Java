package com.thealgorithms.datastructures.trees;

/**
 * Finds the maximum depth (height) of a binary tree — the number of nodes
 * along the longest path from the root down to the farthest leaf node.
 *
 * <p>Example:
 * <pre>
 *      3
 *     / \
 *    9  20
 *       / \
 *      15   7
 * Maximum depth: 3
 * </pre>
 *
 * <p>Time complexity: O(n), Space complexity: O(h) where h is tree height.
 *
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">LeetCode #104</a>
 */
public final class MaximumDepthOfBinaryTree {
    private MaximumDepthOfBinaryTree() {
    }

    public static int maxDepth(BinaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
