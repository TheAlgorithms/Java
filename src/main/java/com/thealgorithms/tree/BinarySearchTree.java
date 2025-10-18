package com.thealgorithms.tree;

import java.util.Scanner;

/**
 * A simple implementation of a Binary Search Tree (BST) in Java.
 * Supports insertion of integer values and tree traversals: inorder, preorder, and postorder.
 */
public class BinarySearchTree {

    // Node class
    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            this.data = d;
        }
    }

    // Insert value into BST recursively
    public static Node insertRecursive(Node root, int d) {
        if (root == null) {
            return new Node(d);
        }
        if (d < root.data) {
            root.left = insertRecursive(root.left, d);
        } else {
            root.right = insertRecursive(root.right, d);
        }
        return root;
    }

    // Inorder traversal
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Preorder traversal
    public static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Postorder traversal
    public static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        int n = sc.nextInt();

        int[] values = new int[n];
        System.out.println("Enter " + n + " values:");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }
        sc.close();

        Node root = null;
        for (int value: values) {
            root = insertRecursive(root, value);
        }

        // Traversal outputs
        System.out.println("Inorder Traversal:");
        inorder(root);

        System.out.println("\nPostorder Traversal:");
        postorder(root);

        System.out.println("\nPreorder Traversal:");
        preorder(root);

        //   Test Cases
        System.out.println("\n\nRunning basic test case...");

        int[] testValues = {
            10,
            5,
            15,
            3,
            7,
            12,
            18
        };
        Node testRoot = null;
        for (int val: testValues) {
            testRoot = insertRecursive(testRoot, val);
        }

        System.out.print("Expected Inorder:   3 5 7 10 12 15 18\nActual:            ");
        inorder(testRoot);

        System.out.print("\nExpected Preorder:  10 5 3 7 15 12 18\nActual:            ");
        preorder(testRoot);

        System.out.print("\nExpected Postorder: 3 7 5 12 18 15 10\nActual:            ");
        postorder(testRoot);

        System.out.println("\nBasic test case complete.");
    }
}