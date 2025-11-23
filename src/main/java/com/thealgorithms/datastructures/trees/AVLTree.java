package com.thealgorithms.datastructures.trees;
/**
 * AVL Tree (Adelson-Velsky and Landis Tree) implementation.
 * A self-balancing Binary Search Tree where the difference between heights
 * of left and right subtrees cannot be more than one for all nodes.
 *
 * @author Raghu0703
 */
public final class AVLTree {
    static class Node {
        int data;
        int height;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public AVLTree() {
        this.root = null;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        x.right = y;
        y.left = t2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        y.left = x;
        x.right = t2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.data) {
            node.left = insertRec(node.left, value);
        } else if (value > node.data) {
            node.right = insertRec(node.right, value);
        } else {
            return node;
        }
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1 && value < node.left.data) {
            return rightRotate(node);
        }
        if (balance < -1 && value > node.right.data) {
            return leftRotate(node);
        }
        if (balance > 1 && value > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && value < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.data) {
            return true;
        }
        return value < node.data ? searchRec(node.left, value) : searchRec(node.right, value);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getHeight() {
        return height(root);
    }

    public boolean isBalanced() {
        return isBalancedRec(root);
    }

    private boolean isBalancedRec(Node node) {
        if (node == null) {
            return true;
        }
        int balance = getBalance(node);
        return Math.abs(balance) <= 1 && isBalancedRec(node.left) && isBalancedRec(node.right);
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRec(root, sb);
        return sb.toString().trim();
    }

    private void inorderRec(Node node, StringBuilder sb) {
        if (node != null) {
            inorderRec(node.left, sb);
            sb.append(node.data).append(" ");
            inorderRec(node.right, sb);
        }
    }
}
