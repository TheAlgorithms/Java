package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AVLTest {

    private static class Node {
        int key;
        int height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // new node is initially added at leaf
            this.left = this.right = null;
        }
    }

    private Node root;

    public AVLTest() {
        root = null;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    public int findMin() {
        if (root == null) throw new NoSuchElementException("AVL is empty");
        return findMinNode(root).key;
    }

    public int findMax() {
        if (root == null) throw new NoSuchElementException("AVL is empty");
        Node cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    public void printInorder() {
        System.out.print("Inorder: ");
        printInorderRecursive(root);
        System.out.println();
    }

    public void printPreorder() {
        System.out.print("Preorder: ");
        printPreorderRecursive(root);
        System.out.println();
    }

    public void printPostorder() {
        System.out.print("Postorder: ");
        printPostorderRecursive(root);
        System.out.println();
    }

    public List<Integer> inorderList() {
        List<Integer> res = new ArrayList<>();
        inorderToList(root, res);
        return res;
    }

    public List<Integer> preorderList() {
        List<Integer> res = new ArrayList<>();
        preorderToList(root, res);
        return res;
    }

    public List<Integer> postorderList() {
        List<Integer> res = new ArrayList<>();
        postorderToList(root, res);
        return res;
    }

    /* Recursive helpers */

    private Node insertRecursive(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) {
            node.left = insertRecursive(node.left, key);
        } else if (key > node.key) {
            node.right = insertRecursive(node.right, key);
        } else {
            return node; // duplicates ignored
        }

        updateHeight(node);
        return balanceNode(node);
    }

    private Node deleteRecursive(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = deleteRecursive(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRecursive(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node successor = findMinNode(node.right);
                node.key = successor.key;
                node.right = deleteRecursive(node.right, successor.key);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balanceNode(node);
    }

    private boolean searchRecursive(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return key < node.key ? searchRecursive(node.left, key) : searchRecursive(node.right, key);
    }

    private Node findMinNode(Node node) {
        Node cur = node;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    /* Rotations & Balancing  */

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private Node balanceNode(Node node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node); // LL
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left); // LR: left then right
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node); // RR
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right); // RL: right then left
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /* ============ Traversal helpers ============ */

    private void printInorderRecursive(Node node) {
        if (node == null) return;
        printInorderRecursive(node.left);
        System.out.print(node.key + " ");
        printInorderRecursive(node.right);
    }

    private void printPreorderRecursive(Node node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        printPreorderRecursive(node.left);
        printPreorderRecursive(node.right);
    }

    private void printPostorderRecursive(Node node) {
        if (node == null) return;
        printPostorderRecursive(node.left);
        printPostorderRecursive(node.right);
        System.out.print(node.key + " ");
    }

    private void inorderToList(Node node, List<Integer> out) {
        if (node == null) return;
        inorderToList(node.left, out);
        out.add(node.key);
        inorderToList(node.right, out);
    }

    private void preorderToList(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.key);
        preorderToList(node.left, out);
        preorderToList(node.right, out);
    }

    private void postorderToList(Node node, List<Integer> out) {
        if (node == null) return;
        postorderToList(node.left, out);
        postorderToList(node.right, out);
        out.add(node.key);
    }
}
