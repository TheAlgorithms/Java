package com.thealgorithms.datastructures.trees;
/**
 * Binary Search Tree implementation
 *
 * @author Raghu0703
 */
public final class BinarySearchTree {
    static class Node {
        int key;
        int value;
        Node left;
        Node right;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int key, int value) {
        root = insertRec(root, key, value);
    }

    private Node insertRec(Node root, int key, int value) {
        if (root == null) {
            return new Node(key, value);
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key, value);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key, value);
        } else {
            root.value = value;
        }
        return root;
    }

    public Integer search(int key) {
        return searchRec(root, key);
    }

    private Integer searchRec(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key == root.key) {
            return root.value;
        }
        return key < root.key ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    private int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRec(root, sb);
        return sb.toString().trim();
    }

    private void inorderRec(Node root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left, sb);
            sb.append(root.key).append(" ");
            inorderRec(root.right, sb);
        }
    }
}
