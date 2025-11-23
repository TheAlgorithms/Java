package com.thealgorithms.datastructures.trees;

/**
 * Binary Search Tree implementation with insert, search, delete, and traversal operations.
 *
 * @author Raghu0703
 */
public final class BinarySearchTree {

    /**
     * Node class representing each element in the BST
     */
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    /**
     * Constructor to initialize empty BST
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Insert a value into the BST
     *
     * @param data the value to insert
     */
    public void insert(int data) {
        root = insertRec(root, data);
    }

    /**
     * Recursive helper method to insert a value
     *
     * @param root current node
     * @param data value to insert
     * @return the modified node
     */
    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    /**
     * Search for a value in the BST
     *
     * @param data the value to search for
     * @return true if found, false otherwise
     */
    public boolean search(int data) {
        return searchRec(root, data);
    }

    /**
     * Recursive helper method to search for a value
     *
     * @param root current node
     * @param data value to search for
     * @return true if found, false otherwise
     */
    private boolean searchRec(Node root, int data) {
        if (root == null) {
            return false;
        }

        if (root.data == data) {
            return true;
        }

        if (data < root.data) {
            return searchRec(root.left, data);
        }

        return searchRec(root.right, data);
    }

    /**
     * Delete a value from the BST
     *
     * @param data the value to delete
     */
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    /**
     * Recursive helper method to delete a value
     *
     * @param root current node
     * @param data value to delete
     * @return the modified node
     */
    private Node deleteRec(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get inorder successor
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    /**
     * Find the minimum value in a subtree
     *
     * @param root the root of the subtree
     * @return the minimum value
     */
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    /**
     * Inorder traversal (Left-Root-Right)
     *
     * @return string representation of inorder traversal
     */
    public String inorder() {
        StringBuilder result = new StringBuilder();
        inorderRec(root, result);
        return result.toString().trim();
    }

    /**
     * Recursive helper for inorder traversal
     */
    private void inorderRec(Node root, StringBuilder result) {
        if (root != null) {
            inorderRec(root.left, result);
            result.append(root.data).append(" ");
            inorderRec(root.right, result);
        }
    }

    /**
     * Preorder traversal (Root-Left-Right)
     *
     * @return string representation of preorder traversal
     */
    public String preorder() {
        StringBuilder result = new StringBuilder();
        preorderRec(root, result);
        return result.toString().trim();
    }

    /**
     * Recursive helper for preorder traversal
     */
    private void preorderRec(Node root, StringBuilder result) {
        if (root != null) {
            result.append(root.data).append(" ");
            preorderRec(root.left, result);
            preorderRec(root.right, result);
        }
    }

    /**
     * Postorder traversal (Left-Right-Root)
     *
     * @return string representation of postorder traversal
     */
    public String postorder() {
        StringBuilder result = new StringBuilder();
        postorderRec(root, result);
        return result.toString().trim();
    }

    /**
     * Recursive helper for postorder traversal
     */
    private void postorderRec(Node root, StringBuilder result) {
        if (root != null) {
            postorderRec(root.left, result);
            postorderRec(root.right, result);
            result.append(root.data).append(" ");
        }
    }

    /**
     * Get the height of the tree
     *
     * @return the height of the tree
     */
    public int height() {
        return heightRec(root);
    }

    /**
     * Recursive helper to calculate height
     */
    private int heightRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    /**
     * Check if the tree is empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
}
