package com.thealgorithms.datastructures.trees;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class will check if a BinaryTree is balanced. A balanced binary tree is
 * defined as a binary tree where the differenced in height between the left and
 * right subtree of each node differs by at most one.
 *
 * This can be done in both an iterative and recursive fashion. Below,
 * `isBalancedRecursive()` is implemented in a recursive fashion, and
 * `isBalancedIterative()` is implemented in an iterative fashion.
 *
 * @author [Ian Cowan](https://github.com/iccowan)
 */
public class CheckIfBinaryTreeBalanced {

    /**
     * This class implements the BinaryTree for these algorithms
     */
    class BinaryTree {

        /**
         * The root node of the binary tree
         */
        BTNode root = null;
    }

    /**
     * This class implements the nodes for the binary tree
     */
    class BTNode {

        /**
         * The value of the node
         */
        int value;

        /**
         * The left child of the node
         */
        BTNode left = null;

        /**
         * The right child of the node
         */
        BTNode right = null;

        /**
         * Constructor
         */
        BTNode(int value) {
            this.value = value;
        }
    }

    /**
     * Recursive is BT balanced implementation
     *
     * @param binaryTree The binary tree to check if balanced
     */
    public boolean isBalancedRecursive(BinaryTree binaryTree) {
        // Create an array of length 1 to keep track of our balance
        // Default to true. We use an array so we have an efficient mutable object
        boolean[] isBalanced = new boolean[1];
        isBalanced[0] = true;

        // Check for balance and return whether or not we are balanced
        isBalancedRecursive(binaryTree.root, 0, isBalanced);
        return isBalanced[0];
    }

    /**
     * Private helper method to keep track of the depth and balance during
     * recursion. We effectively perform a modified post-order traversal where
     * we are looking at the heights of both children of each node in the tree
     *
     * @param node The current node to explore
     * @param depth The current depth of the node
     * @param isBalanced The array of length 1 keeping track of our balance
     */
    private int isBalancedRecursive(BTNode node, int depth, boolean[] isBalanced) {
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
    public boolean isBalancedIterative(BinaryTree binaryTree) {
        // Default that we are balanced and our algo will prove it wrong
        boolean isBalanced = true;

        // Create a stack for our post order traversal
        Stack<BTNode> nodeStack = new Stack<BTNode>();

        // For post order traversal, we'll have to keep track of where we
        // visited last
        BTNode lastVisited = null;

        // Create a HashMap to keep track of the subtree heights for each node
        HashMap<BTNode, Integer> subtreeHeights = new HashMap<BTNode, Integer>();

        // We begin at the root of the tree
        BTNode node = binaryTree.root;

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
                    // max of the left and right subtree heighs plus 1
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

        // Return whether or not the tree is balanced
        return isBalanced;
    }

    /**
     * Generates the following unbalanced binary tree for testing 0 / \ / \ 0 0
     * / / \ / / \ 0 0 0 / \ / \ 0 0 / / 0
     */
    private BinaryTree buildUnbalancedTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new BTNode(0);

        BTNode root = tree.root;
        root.left = new BTNode(0);
        root.right = new BTNode(0);

        BTNode left = root.left;
        BTNode right = root.right;

        left.left = new BTNode(0);
        right.left = new BTNode(0);
        right.right = new BTNode(0);
        right.left.right = new BTNode(0);

        left = left.left;
        left.left = new BTNode(0);
        left.left.left = new BTNode(0);
        left.left.left.left = new BTNode(0);

        return tree;
    }

    /**
     * Generates the following balanced binary tree for testing 0 / \ / \ 0 0 /
     * \ / \ / 0 / \ 0 0 0 / / / / 0 0
     */
    private BinaryTree buildBalancedTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new BTNode(0);

        BTNode root = tree.root;
        root.left = new BTNode(0);
        root.right = new BTNode(0);

        BTNode left = root.left;
        BTNode right = root.right;

        left.left = new BTNode(0);
        left.right = new BTNode(0);
        right.left = new BTNode(0);
        right.right = new BTNode(0);

        right.right.left = new BTNode(0);

        left.left.left = new BTNode(0);

        return tree;
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        // We create a new object to check the binary trees for balance
        CheckIfBinaryTreeBalanced balanceCheck = new CheckIfBinaryTreeBalanced();

        // Build a balanced and unbalanced binary tree
        BinaryTree balancedTree = balanceCheck.buildBalancedTree();
        BinaryTree unbalancedTree = balanceCheck.buildUnbalancedTree();

        // Run basic tests on the algorithms to check for balance
        boolean isBalancedRB = balanceCheck.isBalancedRecursive(balancedTree); // true
        boolean isBalancedRU = balanceCheck.isBalancedRecursive(unbalancedTree); // false
        boolean isBalancedIB = balanceCheck.isBalancedIterative(balancedTree); // true
        boolean isBalancedIU = balanceCheck.isBalancedIterative(unbalancedTree); // false

        // Print the results
        System.out.println("isBalancedRB: " + isBalancedRB);
        System.out.println("isBalancedRU: " + isBalancedRU);
        System.out.println("isBalancedIB: " + isBalancedIB);
        System.out.println("isBalancedIU: " + isBalancedIU);
    }
}
