package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Threaded binary tree implementation that supports insertion and
 * in-order traversal without recursion or stack by using threads.
 *
 * <p>In this implementation, a node's null left/right pointers are used
 * to point to the in-order predecessor/successor respectively. Two flags
 * indicate whether left/right pointers are real children or threads.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Threaded_binary_tree">Wikipedia:
 * Threaded binary tree</a>
 */
public final class ThreadedBinaryTree {

    private Node root;

    private static final class Node {
        int value;
        Node left;
        Node right;
        boolean leftIsThread;
        boolean rightIsThread;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.leftIsThread = false;
            this.rightIsThread = false;
        }
    }

    public ThreadedBinaryTree() {
        this.root = null;
    }

    /**
     * Inserts a value into the threaded binary tree. Duplicate values are inserted
     * to the right subtree (consistent deterministic rule).
     *
     * @param value the integer value to insert
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (value < current.value) {
                if (!current.leftIsThread && current.left != null) {
                    current = current.left;
                } else {
                    break;
                }
            } else { // value >= current.value
                if (!current.rightIsThread && current.right != null) {
                    current = current.right;
                } else {
                    break;
                }
            }
        }

        if (value < parent.value) {
            // attach newNode as left child
            newNode.left = parent.left;
            newNode.leftIsThread = parent.leftIsThread;
            newNode.right = parent;
            newNode.rightIsThread = true;

            parent.left = newNode;
            parent.leftIsThread = false;
        } else {
            // attach newNode as right child
            newNode.right = parent.right;
            newNode.rightIsThread = parent.rightIsThread;
            newNode.left = parent;
            newNode.leftIsThread = true;

            parent.right = newNode;
            parent.rightIsThread = false;
        }
    }

    /**
     * Returns the in-order traversal of the tree as a list of integers.
     * Traversal is done without recursion or an explicit stack by following threads.
     *
     * @return list containing the in-order sequence of node values
     */
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        Node current = root;
        if (current == null) {
            return result;
        }

        // Move to the leftmost node
        while (current.left != null && !current.leftIsThread) {
            current = current.left;
        }

        while (current != null) {
            result.add(current.value);

            // If right pointer is a thread, follow it
            if (current.rightIsThread) {
                current = current.right;
            } else {
                // Move to leftmost node in right subtree
                current = current.right;
                while (current != null && !current.leftIsThread && current.left != null) {
                    current = current.left;
                }
            }
        }

        return result;
    }

    /**
     * Helper: checks whether the tree is empty.
     *
     * @return true if tree has no nodes
     */
    public boolean isEmpty() {
        return root == null;
    }
}
