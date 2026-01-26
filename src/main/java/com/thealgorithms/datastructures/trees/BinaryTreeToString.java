package com.thealgorithms.datastructures.trees;

/**
 * Leetcode 606: Construct String from Binary Tree:
 * https://leetcode.com/problems/construct-string-from-binary-tree/
 *
 * Utility class to convert a {@link BinaryTree} into its string representation.
 * <p>
 * The conversion follows a preorder traversal pattern (root → left → right)
 * and uses parentheses to denote the tree structure.
 * Empty parentheses "()" are used to explicitly represent missing left children
 * when a right child exists, ensuring the structure is unambiguous.
 * </p>
 *
 * <h2>Rules:</h2>
 * <ul>
 * <li>Each node is represented as {@code (value)}.</li>
 * <li>If a node has only a right child, include {@code ()} before the right
 * child
 * to indicate the missing left child.</li>
 * <li>If a node has no children, it appears as just {@code (value)}.</li>
 * <li>The outermost parentheses are removed from the final string.</li>
 * </ul>
 *
 * <h3>Example:</h3>
 *
 * <pre>
 *     Input tree:
 *           1
 *          / \
 *         2   3
 *          \
 *           4
 *
 *     Output string:
 *     "1(2()(4))(3)"
 * </pre>
 *
 * <p>
 * This implementation matches the logic from LeetCode problem 606:
 * <i>Construct String from Binary Tree</i>.
 * </p>
 *
 * @author Muhammad Junaid
 * @see BinaryTree
 */
public class BinaryTreeToString {

    /** String builder used to accumulate the string representation. */
    private StringBuilder sb;

    /**
     * Converts a binary tree (given its root node) to its string representation.
     *
     * @param root the root node of the binary tree
     * @return the string representation of the binary tree, or an empty string if
     *         the tree is null
     */
    public String tree2str(BinaryTree.Node root) {
        if (root == null) {
            return "";
        }

        sb = new StringBuilder();
        dfs(root);

        // Remove the leading and trailing parentheses added by the root call
        return sb.substring(1, sb.length() - 1);
    }

    /**
     * Performs a recursive depth-first traversal to build the string.
     * Each recursive call appends the node value and its children (if any)
     * enclosed in parentheses.
     *
     * @param node the current node being processed
     */
    private void dfs(BinaryTree.Node node) {
        if (node == null) {
            return;
        }

        sb.append("(").append(node.data);

        // Recursively build left and right subtrees
        if (node.left != null) {
            dfs(node.left);
        }

        // Handle the special case: right child exists but left child is null
        if (node.right != null && node.left == null) {
            sb.append("()");
            dfs(node.right);
        } else if (node.right != null) {
            dfs(node.right);
        }

        sb.append(")");
    }
}
