package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Generic Binary Search Tree that allows duplicate keys via a counter in each node.
 *
 * @param <T> key type (must be Comparable)
 */
public class BinarySearchTree<T extends Comparable<T>> {

    static class Node<T> {
        T key;
        Node<T> left, right;
        int count; // number of duplicates (>=1)

        Node(T key) {
            this.key = key;
            this.count = 1;
        }
    }

    private Node<T> root;
    private int size; // number of distinct nodes (not counting duplicates)

    public BinarySearchTree() {}

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
        int initialCount = countOccurrences(key);
        if (initialCount == 0) return false;
        root = delete(root, key);
        return countOccurrences(key) < initialCount;
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

    public List<T> preorder() {
        List<T> out = new ArrayList<>();
        preorder(root, out);
        return out;
    }

    public List<T> postorder() {
        List<T> out = new ArrayList<>();
        postorder(root, out);
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
        }
        return node;
    }

    private Node<T> delete(Node<T> node, T key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            // found
            if (node.count > 1) {
                node.count--;
                return node;
            }
            // remove node
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // two children: find successor
            Node<T> successor = minNode(node.right);
            node.key = successor.key;
            node.count = successor.count;
            // delete successor node (all its counts)
            node.right = deleteNodeMin(node.right);
        }
        return node;
    }

    private Node<T> minNode(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node<T> deleteNodeMin(Node<T> node) {
        if (node.left == null) return node.right;
        node.left = deleteNodeMin(node.left);
        return node;
    }

    private void inorder(Node<T> node, List<T> out) {
        if (node == null) return;
        inorder(node.left, out);
        for (int i = 0; i < node.count; i++) out.add(node.key);
        inorder(node.right, out);
    }

    private void preorder(Node<T> node, List<T> out) {
        if (node == null) return;
        for (int i = 0; i < node.count; i++) out.add(node.key);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    private void postorder(Node<T> node, List<T> out) {
        if (node == null) return;
        postorder(node.left, out);
        postorder(node.right, out);
        for (int i = 0; i < node.count; i++) out.add(node.key);
    }
}
