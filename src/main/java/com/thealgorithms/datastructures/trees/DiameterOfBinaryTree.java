package com.thealgorithms.datastructures.trees;

/**
 * Computes the diameter of a binary tree — the length of the longest path
 * between any two nodes (the path may or may not pass through the root).
 *
 * <p>Example:
 * <pre>
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 * Diameter: 3 (path: 4 -> 2 -> 1 -> 3 or 5 -> 2 -> 1 -> 3)
 * </pre>
 *
 * <p>Time complexity: O(n), Space complexity: O(h) where h is tree height.
 *
 * @see <a href="https://leetcode.com/problems/diameter-of-binary-tree/">LeetCode #543</a>
 */
public final class DiameterOfBinaryTree {
    private DiameterOfBinaryTree() {
    }

    public static int diameterOfBinaryTree(BinaryTree.Node root) {
        int[] maxDiameter = {0};
        depth(root, maxDiameter);
        return maxDiameter[0];
    }

    private static int depth(BinaryTree.Node node, int[] maxDiameter) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.left, maxDiameter);
        int rightDepth = depth(node.right, maxDiameter);
        maxDiameter[0] = Math.max(maxDiameter[0], leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
