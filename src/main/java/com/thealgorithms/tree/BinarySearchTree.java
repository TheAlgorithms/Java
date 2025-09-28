package com.thealgorithms.tree;


import java.util.Arrays;

/**
 * Binary Search Tree (BST) implementation in Java.
 * Supports insertion, traversal (preorder, inorder, postorder),
 * balance checking, and pretty display of the tree structure.
 *
 * Author: Udaya Krishnan M
 * GitHub: https://github.com/UdayaKrishnanM/
 */
public class BinarySearchTree {

    /**
     * Node class representing each element in the BST.
     */
    class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        
        public Node getLeft() {
            return left;
        }
        
        public Node getRight() {
            return right;
        }
        
        public int getHeight() {
            return height;
        }
        
 

    }

    private Node root;

    public BinarySearchTree() {
        // Empty constructor
    }

    /**
     * Returns the height of a node.
     */
    public int height(Node node) {
        return node == null ? -1 : node.height;
    }
    
    /**
     * Returns the root node of the BST.
     * Used for testing and internal inspection.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Checks if the BST is empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserts a value into the BST.
     */
    public void insert(int value) {
        root = createBST(root, value);
    }

    /**
     * Recursively creates the BST based on the value.
     */
    public Node createBST(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = createBST(node.left, value);
        } else if (value > node.value) {
            node.right = createBST(node.right, value);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    /**
     * Populates the BST with an array of values.
     */
    public void populate(int[] nums) {
        for (int num : nums) {
            insert(num);
        }
    }

    /**
     * Checks if the BST is balanced.
     */
    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) return true;

        int balanceFactor = Math.abs(height(node.left) - height(node.right));
        System.out.println("Node value: " + node.value + " | Balance Factor: " + balanceFactor);

        return balanceFactor <= 1 && balanced(node.left) && balanced(node.right);
    }

    /**
     * Displays the tree in a structured format.
     */
    public void prettyDisplay() {
        prettyDisplay(root, 0);
    }

    private void prettyDisplay(Node node, int level) {
        if (node == null) return;

        prettyDisplay(node.right, level + 1);

        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|----> " + node.value);
        } else{
            System.out.println(node.value);
        }
        prettyDisplay(node.left, level + 1);
    }

    /**
     * Populates the BST in a balanced way using sorted array.
     */
    public void populateSorted(int[] nums) {
        Arrays.sort(nums);
        populateSorted(nums, 0, nums.length);
    }

    private void populateSorted(int[] nums, int start, int end) {
        if (start >= end) return;

        int mid = start + (end - start) / 2;
        insert(nums[mid]);
        populateSorted(nums, start, mid);
        populateSorted(nums, mid + 1, end);
    }

    /**
     * Preorder traversal: Root -> Left -> Right
     */
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Inorder traversal: Left -> Root -> Right
     */
    public void inOrder(){
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " (height: " + node.height + ") | ");
        inOrder(node.right);
    }

    /**
     * Postorder traversal: Left -> Right -> Root
     */
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    /**
     * Displays the tree with node relationships.
     */
    public void display() {
        display(root, "Root Node: ");
    }

    private void display(Node node, String details) {
        if (node == null) return;
        System.out.println(details + node.value);
        display(node.left, "Left child of " + node.value + ": ");
        display(node.right, "Right child of " + node.value + ": ");
    }
}
