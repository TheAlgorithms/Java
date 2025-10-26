package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree (BST) Implementation in Java.
 *
 * Supports: - insert(key): Insert a key into the BST - delete(key): Delete a
 * key from the BST - search(key): Search if a key exists in the BST -
 * findMin(): Find the minimum key - findMax(): Find the maximum key -
 * inorder(), preorder(), postorder(): Traversals (print) - inorderList(),
 * preorderList(), postorderList(): Traversals for testing (return
 * List<Integer>)
 *
 * Notes: - Duplicate keys are ignored
 */
public class BinarySearchTree {

    /**
     * Node class representing each node in BST
     */
    private static class Node {

        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    /**
     * Insert a key into the BST
     */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key); 
        }else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root; // duplicates ignored
    }

    /**
     * Search for a key in the BST
     */
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.key == key) {
            return true;
        }
        return key < root.key ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    /**
     * Delete a key from the BST
     */
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key); 
        }else if (key > root.key) {
            root.right = deleteRec(root.right, key); 
        }else {
            // Node found
            if (root.left == null && root.right == null) {
                return null; // no child

                        }if (root.left == null) {
                return root.right; // one child

                        }if (root.right == null) {
                return root.left; // one child
            }
            // two children: replace with inorder successor
            int minValue = findMinRec(root.right);
            root.key = minValue;
            root.right = deleteRec(root.right, minValue);
        }
        return root;
    }

    /**
     * Inorder traversal (print)
     */
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }

    /**
     * Preorder traversal (print)
     */
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    /**
     * Postorder traversal (print)
     */
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print(node.key + " ");
        }
    }

    /**
     * Inorder traversal returning a list (for testing)
     */
    public List<Integer> inorderList() {
        List<Integer> list = new ArrayList<>();
        inorderListRec(root, list);
        return list;
    }

    private void inorderListRec(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderListRec(node.left, list);
        list.add(node.key);
        inorderListRec(node.right, list);
    }

    /**
     * Preorder traversal returning a list (for testing)
     */
    public List<Integer> preorderList() {
        List<Integer> list = new ArrayList<>();
        preorderListRec(root, list);
        return list;
    }

    private void preorderListRec(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.key);
        preorderListRec(node.left, list);
        preorderListRec(node.right, list);
    }

    /**
     * Postorder traversal returning a list (for testing)
     */
    public List<Integer> postorderList() {
        List<Integer> list = new ArrayList<>();
        postorderListRec(root, list);
        return list;
    }

    private void postorderListRec(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postorderListRec(node.left, list);
        postorderListRec(node.right, list);
        list.add(node.key);
    }

    /**
     * Find minimum key
     */
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinRec(root);
    }

    private int findMinRec(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    /**
     * Find maximum key
     */
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxRec(root);
    }

    private int findMaxRec(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }
}
