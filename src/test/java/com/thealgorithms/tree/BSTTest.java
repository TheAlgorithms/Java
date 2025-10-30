package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BSTTest {

    private static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }    }

    private Node root;

    public BSTTest() {
        root = null;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.key) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.key) {
            node.right = insertRecursive(node.right, value);
        } else {
            // else duplicate -> ignore
        }

        return node;
    }

    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.key) {
            return true;
        } else if (value < node.key) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.key) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.key) {
            node.right = deleteRecursive(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node successor = findMinNode(node.right);
                node.key = successor.key;
                node.right = deleteRecursive(node.right, successor.key);
            }
        }

        return node;
    }

    public int findMin() {
        if (root == null) {
            throw new NoSuchElementException("BST is empty");
        }
        return findMinNode(root).key;
    }

    private Node findMinNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public int findMax() {
        if (root == null) {
            throw new NoSuchElementException("BST is empty");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public void printInorder() {
        System.out.print("Inorder: ");
        printInorderRecursive(root);
        System.out.println();
    }

    private void printInorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        printInorderRecursive(node.left);
        System.out.print(node.key + " ");
        printInorderRecursive(node.right);
    }

    public void printPreorder() {
        System.out.print("Preorder: ");
        printPreorderRecursive(root);
        System.out.println();
    }

    private void printPreorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " ");
        printPreorderRecursive(node.left);
        printPreorderRecursive(node.right);
    }

    public void printPostorder() {
        System.out.print("Postorder: ");
        printPostorderRecursive(root);
        System.out.println();
    }

    private void printPostorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        printPostorderRecursive(node.left);
        printPostorderRecursive(node.right);
        System.out.print(node.key + " ");
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
        if (node == null) {
            return;
        }
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
        if (node == null) {
            return;
        }
        postorderToList(node.left, out);
        postorderToList(node.right, out);
        out.add(node.key);
    }

    public static void main(String[] args) {
        BSTTest bst = new BSTTest();

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

        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Search 99: " + bst.search(99));

        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());

        bst.delete(20);
        System.out.println("After deleting 20 (leaf): " + bst.inorderList());

        bst.delete(30);
        System.out.println("After deleting 30 (one child): " + bst.inorderList());

        bst.delete(50);
        System.out.println("After deleting 50 (two children): " + bst.inorderList());
    }
}
