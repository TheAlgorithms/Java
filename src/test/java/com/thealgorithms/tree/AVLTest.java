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
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public AVLTest() {
        this.root = null;
    }

    public void insert(int key) {
        this.root = insertRecursive(this.root, key);
    }

    public void delete(int key) {
        this.root = deleteRecursive(this.root, key);
    }

    public boolean search(int key) {
        return searchRecursive(this.root, key);
    }

    public int findMin() {
        if (this.root == null) {
            throw new NoSuchElementException("AVL is empty");
        }
        return findMinNode(this.root).key;
    }

    public int findMax() {
        if (this.root == null) {
            throw new NoSuchElementException("AVL is empty");
        }
        Node cur = this.root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public void printInorder() {
        System.out.print("Inorder: ");
        printInorderRecursive(this.root);
        System.out.println();
    }

    public void printPreorder() {
        System.out.print("Preorder: ");
        printPreorderRecursive(this.root);
        System.out.println();
    }

    public void printPostorder() {
        System.out.print("Postorder: ");
        printPostorderRecursive(this.root);
        System.out.println();
    }

    public List<Integer> inorderList() {
        List<Integer> res = new ArrayList<>();
        inorderToList(this.root, res);
        return res;
    }

    public List<Integer> preorderList() {
        List<Integer> res = new ArrayList<>();
        preorderToList(this.root, res);
        return res;
    }

    public List<Integer> postorderList() {
        List<Integer> res = new ArrayList<>();
        postorderToList(this.root, res);
        return res;
    }

    /* Recursive helpers */

    private Node insertRecursive(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

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

        if (node == null) {
            return null;
        }

        updateHeight(node);
        return balanceNode(node);
    }

    private boolean searchRecursive(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key == node.key) {
            return true;
        }
        if (key < node.key) {
            return searchRecursive(node.left, key);
        } else {
            return searchRecursive(node.right, key);
        }
    }

    private Node findMinNode(Node node) {
        Node cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }
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
            node.left = leftRotate(node.left); // LR
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node); // RR
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right); // RL
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /* ============ Traversal helpers ============ */

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

        for (int v : values) {
            avl.insert(v);
        }

        avl.printInorder();
        avl.printPreorder();
        avl.printPostorder();

        System.out.println("Inorder List: " + avl.inorderList());
        System.out.println("Preorder List: " + avl.preorderList());
        System.out.println("Postorder List: " + avl.postorderList());

        System.out.println("Search 27: " + avl.search(27));
        System.out.println("Search 99: " + avl.search(99));

        System.out.println("Min: " + avl.findMin());
        System.out.println("Max: " + avl.findMax());

        avl.delete(10);
        System.out.println("After deleting 10: " + avl.inorderList());

        avl.delete(30);
        System.out.println("After deleting 30: " + avl.inorderList());

        avl.delete(40);
        System.out.println("After deleting 40: " + avl.inorderList());
    }
}
