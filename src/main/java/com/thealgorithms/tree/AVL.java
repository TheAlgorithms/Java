package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * AVL (Adelson-Velsky and Landis) Tree is a self-balancing Binary Search Tree.
 *  Operations supported:
 *   - insert, delete, search
 *   - inorder, preorder, postorder traversal
 *   - findMin(), findMax()
 *
 * Properties:
 *   - For every node: |height(left) - height(right)| <= 1
 *   - Maintains O(log n) time complexity for insert/delete/search
 */
public class AVL {

    /**
     * Inner class to represent a node in AVL Tree
     */
    private static class Node {
        int key;
        int height;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.height = 1; // New node starts as a leaf node with height = 1
        }
    }

    // Root node of the AVL Tree
    private Node root;

    // Constructor
    public AVL() {
        root = null;
    }

    /* ======================== PUBLIC METHODS ======================== */

    /** Insert a key into the AVL Tree */
    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    /** Delete a key from the AVL Tree */
    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    /** Search a key in the AVL Tree */
    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    /** Return the smallest key in the AVL Tree */
    public int findMin() {
        if (root == null) throw new NoSuchElementException("AVL Tree is empty");
        return findMinNode(root).key;
    }

    /** Return the largest key in the AVL Tree */
    public int findMax() {
        if (root == null) throw new NoSuchElementException("AVL Tree is empty");
        Node cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    /** Print nodes in Inorder (sorted order) */
    public void printInorder() {
        System.out.print("Inorder: ");
        printInorderRecursive(root);
        System.out.println();
    }

    /** Print nodes in Preorder (Root → Left → Right) */
    public void printPreorder() {
        System.out.print("Preorder: ");
        printPreorderRecursive(root);
        System.out.println();
    }

    /** Print nodes in Postorder (Left → Right → Root) */
    public void printPostorder() {
        System.out.print("Postorder: ");
        printPostorderRecursive(root);
        System.out.println();
    }

    /** Return Inorder list (useful for testing) */
    public List<Integer> inorderList() {
        List<Integer> res = new ArrayList<>();
        inorderToList(root, res);
        return res;
    }

    /** Return Preorder list (useful for testing) */
    public List<Integer> preorderList() {
        List<Integer> res = new ArrayList<>();
        preorderToList(root, res);
        return res;
    }

    /** Return Postorder list (useful for testing) */
    public List<Integer> postorderList() {
        List<Integer> res = new ArrayList<>();
        postorderToList(root, res);
        return res;
    }

    /**
     * Recursive insert:
     * 1. Insert key like a normal BST
     * 2. Update height of current node
     * 3. Balance the node if it became unbalanced
     */
    private Node insertRecursive(Node node, int key) {
        // Step 1: Perform standard BST insert
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key)
            node.left = insertRecursive(node.left, key);
        else if (key > node.key)
            node.right = insertRecursive(node.right, key);
        else
            return node; // Duplicates not allowed

        // Step 2: Update height of ancestor node
        updateHeight(node);

        // Step 3: Balance the node and return new root
        return balanceNode(node);
    }

    /**
     * Recursive delete:
     * 1. Perform normal BST delete
     * 2. Update height of current node
     * 3. Balance it if necessary
     */
    private Node deleteRecursive(Node node, int key) {
        if (node == null) return null;

        // Step 1: Perform BST delete
        if (key < node.key)
            node.left = deleteRecursive(node.left, key);
        else if (key > node.key)
            node.right = deleteRecursive(node.right, key);
        else {
            // Node found
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;

                // No child case
                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // Node with two children → get inorder successor
                Node successor = findMinNode(node.right);
                node.key = successor.key;
                node.right = deleteRecursive(node.right, successor.key);
            }
        }

        // If tree had only one node
        if (node == null) return null;

        // Step 2: Update height
        updateHeight(node);

        // Step 3: Rebalance node
        return balanceNode(node);
    }

    /** Recursive search like normal BST */
    private boolean searchRecursive(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return key < node.key ? searchRecursive(node.left, key) : searchRecursive(node.right, key);
    }

    /** Find node with minimum key */
    private Node findMinNode(Node node) {
        Node cur = node;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    /* ======================== ROTATIONS & BALANCING ======================== */

    /** Right rotation (used in LL or LR imbalance) */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x; // New root
    }

    /** Left rotation (used in RR or RL imbalance) */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y; // New root
    }

    /**
     * Balances a node by checking its balance factor:
     *
     *  - If > 1 → left heavy
     *  - If < -1 → right heavy
     *
     * Depending on the case, we do:
     *  - LL → Right Rotate
     *  - RR → Left Rotate
     *  - LR → Left Rotate child + Right Rotate
     *  - RL → Right Rotate child + Left Rotate
     */
    private Node balanceNode(Node node) {
        int balance = getBalance(node);

        // Case 1: Left Left (LL)
        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);

        // Case 2: Left Right (LR)
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Case 3: Right Right (RR)
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);

        // Case 4: Right Left (RL)
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Already balanced
    }

    /* ======================== HELPER FUNCTIONS ======================== */

    /** Returns height of a node */
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    /** Updates height of a node based on its children */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /** Calculates balance factor = height(left) - height(right) */
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /* ======================== TRAVERSALS ======================== */

    private void printInorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        printInorderRecursive(node.left);
        System.out.print(node.key + " ");
        printInorderRecursive(node.right);
    }

    private void printPreorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " ");
        printPreorderRecursive(node.left);
        printPreorderRecursive(node.right);
    }

    private void printPostorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        printPostorderRecursive(node.left);
        printPostorderRecursive(node.right);
        System.out.print(node.key + " ");
    }

    private void inorderToList(Node node, List<Integer> out) {
        if (node == null) {
            return;
        }
        inorderToList(node.left, out);
        out.add(node.key);
        inorderToList(node.right, out);
    }

    private void preorderToList(Node node, List<Integer> out) {
        if (node == null) {
            return;
        }
        out.add(node.key);
        preorderToList(node.left, out);
        preorderToList(node.right, out);
    }

    private void postorderToList(Node node, List<Integer> out) {
        if (node == null) {
            return;
        }
        postorderToList(node.left, out);
        postorderToList(node.right, out);
        out.add(node.key);
    }

    public static void main(String[] args) {
        AVL avl = new AVL();

        int[] values = {30, 20, 40, 10, 25, 35, 50, 5, 15, 27, 45, 60};

        // Insert all values one by one
        for (int v : values) avl.insert(v);

        // Display traversals
        avl.printInorder(); // should show sorted order
        avl.printPreorder();
        avl.printPostorder();

        // Display traversal lists
        System.out.println("Inorder List: " + avl.inorderList());
        System.out.println("Preorder List: " + avl.preorderList());
        System.out.println("Postorder List: " + avl.postorderList());

        // Search examples
        System.out.println("Search 27: " + avl.search(27)); // true
        System.out.println("Search 99: " + avl.search(99)); // false

        // Min and Max
        System.out.println("Min: " + avl.findMin());
        System.out.println("Max: " + avl.findMax());

        // Delete operations and show tree after each
        avl.delete(10);
        System.out.println("After deleting 10: " + avl.inorderList());

        avl.delete(30);
        System.out.println("After deleting 30: " + avl.inorderList());

        avl.delete(40);
        System.out.println("After deleting 40: " + avl.inorderList());
    }
}
