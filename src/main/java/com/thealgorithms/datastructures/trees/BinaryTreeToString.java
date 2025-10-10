package com.thealgorithms.datastructures.trees;

/**
 * Converts a Binary Tree to its string representation using preorder traversal.
 * Rules:
 * - Each node is wrapped in parentheses (val)
 * - Include "()" when a right child exists without a left child
 * - Skip empty parentheses otherwise
 *
 * Example:
 *    Input:  1
 *            / \
 *           2   3
 *            \
 *             4
 *    Output: "1(2()(4))(3)"
 *
 * Matches the logic from LeetCode 606, using a DFS traversal.
 */
public class BinaryTreeToString {

    private StringBuilder sb;

    public String tree2str(BinaryTree.Node root) {
        if (root == null) {
            return "";
        }

        sb = new StringBuilder();
        dfs(root);
        // remove the first and last parentheses
        return sb.substring(1, sb.length() - 1);
    }

    private void dfs(BinaryTree.Node node) {
        if (node == null) {
            return;
        }

        sb.append("(").append(node.data);

        if (node.left != null) {
            dfs(node.left);
        }

        if (node.right != null && node.left == null) {
            sb.append("()");
            dfs(node.right);
        } else if (node.right != null) {
            dfs(node.right);
        }

        sb.append(")");
    }
}
