package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BST {

    /**
     * Node class represents a node in the BST.
     * 0->0->0
     * 0=Nodes\
     * each node contains divided into 3 section
     * Key-> Actual value,
     * left-> store the address of left tree,
     * Right-> store the address of right tree
     */
    private static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    // Root of the BST
    // the first of Node tree
    // 0->
    private Node root;

    /** Create an empty BST. */
    public BST() {
        root = null;
    }

    /* ===========================
     * INSERT
     * =========================== */

    /**
     * Insert a value into the BST. Duplicate values are ignored (no-op).
     *
     * @param value value to insert
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    // Helper recursive method for insertion.
    private Node insertRecursive(Node node, int value) {
        // If we reached a null position, create and return a new node.
        if (node == null) {
            return new Node(value);
        }

        // If value is less, go left; if greater, go right; if equal, do nothing.
        if (value < node.key) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.key) {
            node.right = insertRecursive(node.right, value);
        } // else duplicate -> ignore

        return node; // return current (possibly updated) subtree root
    }

    /* ===========================
     * SEARCH
     * =========================== */

    /**
     * Search the BST for a value.
     *
     * @param value value to search
     * @return true if value exists in the BST, false otherwise
     */
    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node node, int value) {
        if (node == null) {
            return false; // reached leaf, not found
        }
        if (value == node.key) {
            return true; // found exact match
        } else if (value < node.key) {
            return searchRecursive(node.left, value); // search left subtree
        } else {
            return searchRecursive(node.right, value); // search right subtree
        }
    }

    /* ===========================
     * DELETE
     * =========================== */

    /**
     * Delete a value from the BST. If the value is not present, tree remains unchanged.
     *
     * @param value value to delete
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    // Helper for deletion, handles three cases:
    // 1) node is a leaf -> just remove
    // 2) node has one child -> replace node with child
    // 3) node has two children -> replace node with successor (smallest in right subtree)
    private Node deleteRecursive(Node node, int value) {
        if (node == null) {
            return null; // value not found
        }

        if (value < node.key) {
            // target is in left subtree
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.key) {
            // target is in right subtree
            node.right = deleteRecursive(node.right, value);
        } else {
            // node.key == value -> delete this node
            // Case 1 & 2: node has 0 or 1 child
            if (node.left == null) {
                // return right child (could be null) to replace node
                return node.right;
            } else if (node.right == null) {
                // return left child to replace node
                return node.left;
            }

            // Case 3: node has two children
            // Find the inorder successor (smallest in the right subtree)
            Node successor = findMinNode(node.right);
            // Copy successor's value to this node
            node.key = successor.key;
            // Delete the successor node from right subtree
            node.right = deleteRecursive(node.right, successor.key);
        }

        return node;
    }

    /**
     * Find the minimum key in the BST.
     *
     * @return the smallest integer key in the tree
     * @throws NoSuchElementException if the tree is empty
     */
    public int findMin() {
        if (root == null) {
            throw new NoSuchElementException("BST is empty");
        }
        return findMinNode(root).key;
    }

    // Helper to find node with minimum key in a subtree (leftmost node)
    private Node findMinNode(Node node) {
        Node current = node;
        // go as far left as possible
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Find the maximum key in the BST.
     *
     * the largest integer key in the tree
     * NoSuchElementException if the tree is empty
     */
    public int findMax() {
        if (root == null) {
            throw new NoSuchElementException("BST is empty");
        }
        Node current = root;
        // go as far right as possible
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }


    /**
     * Print inorder traversal (Left, Node, Right).
     * InOrder -> Left, key, Right
     */
    public void printInorder() {
        System.out.print("Inorder: ");
        printInorderRecursive(root);
        System.out.println();
    }

    private void printInorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        printInorderRecursive(node.left); // left
        System.out.print(node.key + " "); // node
        printInorderRecursive(node.right); // right
    }

    /**
     * Print preorder traversal (Node, Left, Right).
     * PreOrder -> key,Left, Right
     */
    public void printPreorder() {
        System.out.print("Preorder: ");
        printPreorderRecursive(root);
        System.out.println();
    }

    private void printPreorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " "); // node
        printPreorderRecursive(node.left); // left
        printPreorderRecursive(node.right); // right
    }

    /**
     * Print postorder traversal (Left, Right, Node).
     *PreOrder -> key,Left, Right
     */
    public void printPostorder() {
        System.out.print("Postorder: ");
        printPostorderRecursive(root);
        System.out.println();
    }

    private void printPostorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        printPostorderRecursive(node.left); // left
        printPostorderRecursive(node.right); // right
        System.out.print(node.key + " "); // node
    }

        public List<Integer> inorderList() {
        List<Integer> result = new ArrayList<>();
        inorderToList(root, result);
        return result;
    }

    private void inorderToList(Node node, List<Integer> out) {
        if (node == null) {
            return;
        }
        inorderToList(node.left, out);
        out.add(node.key);
        inorderToList(node.right, out);
    }


    public List<Integer> preorderList() {
        List<Integer> result = new ArrayList<>();
        preorderToList(root, result);
        return result;
    }

    private void preorderToList(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.key);
        preorderToList(node.left, out);
        preorderToList(node.right, out);
    }

    public List<Integer> postorderList() {
        List<Integer> result = new ArrayList<>();
        postorderToList(root, result);
        return result;
    }

    private void postorderToList(Node node, List<Integer> out) {
        if (node == null) return;
        postorderToList(node.left, out);
        postorderToList(node.right, out);
        out.add(node.key);
    }

    public static void main(String[] args) {
        BST bst = new BST();

        // Insert values
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            bst.insert(v);
        }

        bst.printInorder();
        bst.printPreorder();
        bst.printPostorder();

        System.out.println("Inorder List: " + bst.inorderList());
        System.out.println("Preorder List: " + bst.preorderList());
        System.out.println("Postorder List: " + bst.postorderList());


        System.out.println("Search 40: " + bst.search(40)); // true
        System.out.println("Search 99: " + bst.search(99)); // false


        System.out.println("Min: " + bst.findMin()); // 20
        System.out.println("Max: " + bst.findMax()); // 80


        bst.delete(20);
        System.out.println("After deleting 20 (leaf): " + bst.inorderList());

        bst.delete(30);
        System.out.println("After deleting 30 (one child): " + bst.inorderList());

        bst.delete(50);
        System.out.println("After deleting 50 (two children): " + bst.inorderList());
    }
}
