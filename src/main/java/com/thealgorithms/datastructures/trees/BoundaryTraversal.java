package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * BoundaryTraversal
 * <p>
 * Start with the Root:
 * Add the root node to the boundary list.
 * Traverse the Left Boundary (Excluding Leaf Nodes):
 * Move down the left side of the tree, adding each non-leaf node to the boundary list.
 * If a node has a left child, go left; otherwise, go right.
 * Visit All Leaf Nodes:
 * Traverse the tree and add all leaf nodes to the boundary list, from left to right.
 * Traverse the Right Boundary (Excluding Leaf Nodes) in Reverse Order:
 * Move up the right side of the tree, adding each non-leaf node to a temporary list.
 * If a node has a right child, go right; otherwise, go left.
 * Reverse the temporary list and add it to the boundary list.
 * Combine and Output:
 * The final boundary list contains the root, left boundary, leaf nodes, and reversed right boundary in that order.
 */
public final class BoundaryTraversal {
    private BoundaryTraversal() {
    }

    // Main function for boundary traversal, returns a list of boundary nodes in order
    public static List<Integer> boundaryTraversal(BinaryTree.Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // Add root node if it's not a leaf node
        if (!isLeaf(root)) {
            result.add(root.data);
        }

        // Add left boundary
        addLeftBoundary(root, result);

        // Add leaf nodes
        addLeaves(root, result);

        // Add right boundary
        addRightBoundary(root, result);

        return result;
    }

    // Adds the left boundary, including nodes that have no left child but have a right child
    private static void addLeftBoundary(BinaryTree.Node node, List<Integer> result) {
        BinaryTree.Node cur = node.left;

        // If there is no left child but there is a right child, treat the right child as part of the left boundary
        if (cur == null && node.right != null) {
            cur = node.right;
        }

        while (cur != null) {
            if (!isLeaf(cur)) {
                result.add(cur.data); // Add non-leaf nodes to result
            }
            if (cur.left != null) {
                cur = cur.left; // Move to the left child
            } else if (cur.right != null) {
                cur = cur.right; // If left child is null, move to the right child
            } else {
                break; // Stop if there are no children
            }
        }
    }

    // Adds leaf nodes (nodes without children)
    private static void addLeaves(BinaryTree.Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (isLeaf(node)) {
            result.add(node.data); // Add leaf node
        } else {
            addLeaves(node.left, result); // Recur for left subtree
            addLeaves(node.right, result); // Recur for right subtree
        }
    }

    // Adds the right boundary, excluding leaf nodes
    private static void addRightBoundary(BinaryTree.Node node, List<Integer> result) {
        BinaryTree.Node cur = node.right;
        List<Integer> temp = new ArrayList<>();

        // If no right boundary is present and there is no left subtree, skip
        if (cur != null && node.left == null) {
            return;
        }
        while (cur != null) {
            if (!isLeaf(cur)) {
                temp.add(cur.data); // Store non-leaf nodes temporarily
            }
            if (cur.right != null) {
                cur = cur.right; // Move to the right child
            } else if (cur.left != null) {
                cur = cur.left; // If right child is null, move to the left child
            } else {
                break; // Stop if there are no children
            }
        }

        // Add the right boundary nodes in reverse order
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    // Checks if a node is a leaf node
    private static boolean isLeaf(BinaryTree.Node node) {
        return node.left == null && node.right == null;
    }

    // Iterative boundary traversal
    public static List<Integer> iterativeBoundaryTraversal(BinaryTree.Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // Add root node if it's not a leaf node
        if (!isLeaf(root)) {
            result.add(root.data);
        }

        // Handle the left boundary
        BinaryTree.Node cur = root.left;
        if (cur == null && root.right != null) {
            cur = root.right;
        }
        while (cur != null) {
            if (!isLeaf(cur)) {
                result.add(cur.data); // Add non-leaf nodes to result
            }
            cur = (cur.left != null) ? cur.left : cur.right; // Prioritize left child, move to right if left is null
        }

        // Add leaf nodes
        addLeaves(root, result);

        // Handle the right boundary using a stack (reverse order)
        cur = root.right;
        Deque<Integer> stack = new LinkedList<>();
        if (cur != null && root.left == null) {
            return result;
        }
        while (cur != null) {
            if (!isLeaf(cur)) {
                stack.push(cur.data); // Temporarily store right boundary nodes in a stack
            }
            cur = (cur.right != null) ? cur.right : cur.left; // Prioritize right child, move to left if right is null
        }

        // Add the right boundary nodes from the stack to maintain the correct order
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
