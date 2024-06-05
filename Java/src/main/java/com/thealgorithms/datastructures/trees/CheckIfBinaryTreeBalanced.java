package com.thealgorithms.datastructures.trees;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class will check if a BinaryTree is balanced. A balanced binary tree is
 * defined as a binary tree where the difference in height between the left and
 * right subtree of each node differs by at most one.
 * <p>
 * This can be done in both an iterative and recursive fashion. Below,
 * `isBalancedRecursive()` is implemented in a recursive fashion, and
 * `isBalancedIterative()` is implemented in an iterative fashion.
 *
 * @author [Ian Cowan](<a href="https://github.com/iccowan">Git-Ian Cowan</a>)
 */
public final class CheckIfBinaryTreeBalanced {
    private CheckIfBinaryTreeBalanced() {
    }
    /**
     * Recursive is BT balanced implementation
     *
     * @param root The binary tree to check if balanced
     */
    public static boolean isBalancedRecursive(BinaryTree.Node root) {
        if (root == null) {
            return true;
        }
        // Create an array of length 1 to keep track of our balance
        // Default to true. We use an array, so we have an efficient mutable object
        boolean[] isBalanced = new boolean[1];
        isBalanced[0] = true;

        // Check for balance and return whether we are balanced
        isBalancedRecursive(root, 0, isBalanced);
        return isBalanced[0];
    }

    /**
     * Private helper method to keep track of the depth and balance during
     * recursion. We effectively perform a modified post-order traversal where
     * we are looking at the heights of both children of each node in the tree
     *
     * @param node       The current node to explore
     * @param depth      The current depth of the node
     * @param isBalanced The array of length 1 keeping track of our balance
     */
    private static int isBalancedRecursive(BinaryTree.Node node, int depth, boolean[] isBalanced) {
        // If the node is null, we should not explore it and the height is 0
        // If the tree is already not balanced, might as well stop because we
        // can't make it balanced now!
        if (node == null || !isBalanced[0]) {
            return 0;
        }

        // Visit the left and right children, incrementing their depths by 1
        int leftHeight = isBalancedRecursive(node.left, depth + 1, isBalanced);
        int rightHeight = isBalancedRecursive(node.right, depth + 1, isBalanced);

        // If the height of either of the left or right subtrees differ by more
        // than 1, we cannot be balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced[0] = false;
        }

        // The height of our tree is the maximum of the heights of the left
        // and right subtrees plus one
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Iterative is BT balanced implementation
     */
    public static boolean isBalancedIterative(BinaryTree.Node root) {
        if (root == null) {
            return true;
        }

        // Default that we are balanced and our algo will prove it wrong
        boolean isBalanced = true;

        // Create a stack for our post order traversal
        Stack<BinaryTree.Node> nodeStack = new Stack<>();

        // For post order traversal, we'll have to keep track of where we
        // visited last
        BinaryTree.Node lastVisited = null;

        // Create a HashMap to keep track of the subtree heights for each node
        HashMap<BinaryTree.Node, Integer> subtreeHeights = new HashMap<>();

        // We begin at the root of the tree
        BinaryTree.Node node = root;

        // We loop while:
        // - the node stack is empty and the node we explore is null
        // AND
        // - the tree is still balanced
        while (!(nodeStack.isEmpty() && node == null) && isBalanced) {
            // If the node is not null, we push it to the stack and continue
            // to the left
            if (node != null) {
                nodeStack.push(node);
                node = node.left;
                // Once we hit a node that is null, we are as deep as we can go
                // to the left
            } else {
                // Find the last node we put on the stack
                node = nodeStack.peek();

                // If the right child of the node has either been visited or
                // is null, we visit this node
                if (node.right == null || node.right == lastVisited) {
                    // We assume the left and right heights are 0
                    int leftHeight = 0;
                    int rightHeight = 0;

                    // If the right and left children are not null, we must
                    // have already explored them and have a height
                    // for them so let's get that
                    if (node.left != null) {
                        leftHeight = subtreeHeights.get(node.left);
                    }

                    if (node.right != null) {
                        rightHeight = subtreeHeights.get(node.right);
                    }

                    // If the difference in the height of the right subtree
                    // and left subtree differs by more than 1, we cannot be
                    // balanced
                    if (Math.abs(rightHeight - leftHeight) > 1) {
                        isBalanced = false;
                    }

                    // The height of the subtree containing this node is the
                    // max of the left and right subtree heights plus 1
                    subtreeHeights.put(node, Math.max(rightHeight, leftHeight) + 1);

                    // We've now visited this node, so we pop it from the stack
                    nodeStack.pop();
                    lastVisited = node;

                    // Current visiting node is now null
                    node = null;
                    // If the right child node of this node has not been visited
                    // and is not null, we need to get that child node on the stack
                } else {
                    node = node.right;
                }
            }
        }

        // Return whether the tree is balanced
        return isBalanced;
    }
}
