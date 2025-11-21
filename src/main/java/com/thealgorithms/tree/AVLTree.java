package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Generic AVL Tree (self-balancing BST). Duplicates are handled via a count in each node.
 *
 * @param <T> key type (must be Comparable)
 */
public class AVLTree<T extends Comparable<T>> {

    static class Node<T> {
        T key;
        Node<T> left, right;
        int height;
        int count;

        Node(T key) {
            this.key = key;
            this.height = 1;
            this.count = 1;
        }
    }

    private Node<T> root;
    private int size; // distinct nodes

    public AVLTree() {}

    // Public API
    public void insert(T key) {
        Objects.requireNonNull(key);
        root = insert(root, key);
    }

    public boolean contains(T key) {
        Objects.requireNonNull(key);
        Node<T> cur = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) return true;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    public boolean delete(T key) {
        Objects.requireNonNull(key);
        if (!contains(key)) return false;
        root = delete(root, key);
        return true;
    }

    public T findMin() {
        if (root == null) return null;
        Node<T> cur = root;
        while (cur.left != null) cur = cur.left;
        return cur.key;
    }

    public T findMax() {
        if (root == null) return null;
        Node<T> cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    public List<T> inorder() {
        List<T> out = new ArrayList<>();
        inorder(root, out);
        return out;
    }

    public int sizeDistinct() {
        return size;
    }

    public int countOccurrences(T key) {
        Node<T> cur = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) return cur.count;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return 0;
    }

    // === Private helpers ===
    private Node<T> insert(Node<T> node, T key) {
        if (node == null) {
            size++;
            return new Node<>(key);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key);
        } else if (cmp > 0) {
            node.right = insert(node.right, key);
        } else {
            node.count++;
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private Node<T> delete(Node<T> node, T key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.count > 1) {
                node.count--;
                return node;
            }
            // remove node
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // two children: replace with successor
            Node<T> succ = minNode(node.right);
            node.key = succ.key;
            node.count = succ.count;
            node.right = deleteNodeMin(node.right);
        }
        updateHeight(node);
        return balance(node);
    }

    private Node<T> minNode(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node<T> deleteNodeMin(Node<T> node) {
        if (node.left == null) return node.right;
        node.left = deleteNodeMin(node.left);
        updateHeight(node);
        return balance(node);
    }

    private void inorder(Node<T> node, List<T> out) {
        if (node == null) return;
        inorder(node.left, out);
        for (int i = 0; i < node.count; i++) out.add(node.key);
        inorder(node.right, out);
    }

    // --- AVL utilities ---
    private int height(Node<T> n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(Node<T> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int balanceFactor(Node<T> n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private Node<T> balance(Node<T> n) {
        int bf = balanceFactor(n);
        if (bf > 1) {
            if (balanceFactor(n.left) < 0) {
                // Left-Right
                n.left = rotateLeft(n.left);
            }
            // Left-Left
            return rotateRight(n);
        } else if (bf < -1) {
            if (balanceFactor(n.right) > 0) {
                // Right-Left
                n.right = rotateRight(n.right);
            }
            // Right-Right
            return rotateLeft(n);
        }
        return n;
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }
}
