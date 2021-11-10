package com.thealgorithms.datastructures.trees;

/**
 *
 *
 * <h1>Binary Search Tree (Recursive)</h1>
 *
 * An implementation of BST recursively. In recursive implementation the checks
 * are down the tree First root is checked if not found then its childs are
 * checked Binary Search Tree is a binary tree which satisfies three properties:
 * left child is less than root node, right child is grater than root node, both
 * left and right childs must themselves be a BST.
 *
 * <p>
 * I have made public functions as methods and to actually implement recursive
 * approach I have used private methods
 *
 * @author [Lakhan Nad](https://github.com/Lakhan-Nad)
 */
public class BSTRecursive {

    /**
     * only data member is root of BST
     */
    private Node root;

    /**
     * Constructor use to initialize node as null
     */
    BSTRecursive() {
        root = null;
    }

    /**
     * main function for tests
     */
    public static void main(String[] args) {
        BSTRecursive tree = new BSTRecursive();
        tree.add(5);
        tree.add(10);
        tree.add(9);
        assert !tree.find(4) : "4 is not yet present in BST";
        assert tree.find(10) : "10 should be present in BST";
        tree.remove(9);
        assert !tree.find(9) : "9 was just deleted from BST";
        tree.remove(1);
        assert !tree.find(1) : "Since 1 was not present so find deleting would do no change";
        tree.add(20);
        tree.add(70);
        assert tree.find(70) : "70 was inserted but not found";
        /*
     Will print in following order
     5 10 20 70
         */
        tree.inorder();
    }

    /**
     * Recursive method to delete a data if present in BST.
     *
     * @param node the current node to search for data
     * @param data the value to be deleted
     * @return Node the updated value of root parameter after delete operation
     */
    private Node delete(Node node, int data) {
        if (node == null) {
            System.out.println("No such data present in BST.");
        } else if (node.data > data) {
            node.left = delete(node.left, data);
        } else if (node.data < data) {
            node.right = delete(node.right, data);
        } else {
            if (node.right == null && node.left == null) { // If it is leaf node
                node = null;
            } else if (node.left == null) { // If only right node is present
                Node temp = node.right;
                node.right = null;
                node = temp;
            } else if (node.right == null) { // Only left node is present
                Node temp = node.left;
                node.left = null;
                node = temp;
            } else { // both child are present
                Node temp = node.right;
                // Find leftmost child of right subtree
                while (temp.left != null) {
                    temp = temp.left;
                }
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }
        return node;
    }

    /**
     * Recursive insertion of value in BST.
     *
     * @param node to check if the data can be inserted in current node or its
     * subtree
     * @param data the value to be inserted
     * @return the modified value of the root parameter after insertion
     */
    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else if (node.data > data) {
            node.left = insert(node.left, data);
        } else if (node.data < data) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    /**
     * Recursively print Preorder traversal of the BST
     *
     * @param node the root node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    /**
     * Recursively print Postorder travesal of BST.
     *
     * @param node the root node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        System.out.print(node.data + " ");
    }

    /**
     * Recursively print Inorder traversal of BST.
     *
     * @param node the root node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inOrder(node.left);
        }
        System.out.print(node.data + " ");
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    /**
     * Serach recursively if the given value is present in BST or not.
     *
     * @param node the current node to check
     * @param data the value to be checked
     * @return boolean if data is present or not
     */
    private boolean search(Node node, int data) {
        if (node == null) {
            return false;
        } else if (node.data == data) {
            return true;
        } else if (node.data > data) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }

    /**
     * add in BST. if the value is not already present it is inserted or else no
     * change takes place.
     *
     * @param data the value to be inserted
     */
    public void add(int data) {
        this.root = insert(this.root, data);
    }

    /**
     * If data is present in BST delete it else do nothing.
     *
     * @param data the value to be removed
     */
    public void remove(int data) {
        this.root = delete(this.root, data);
    }

    /**
     * To call inorder traversal on tree
     */
    public void inorder() {
        System.out.println("Inorder traversal of this tree is:");
        inOrder(this.root);
        System.out.println(); // for next line
    }

    /**
     * To call postorder traversal on tree
     */
    public void postorder() {
        System.out.println("Postorder traversal of this tree is:");
        postOrder(this.root);
        System.out.println(); // for next li
    }

    /**
     * To call preorder traversal on tree.
     */
    public void preorder() {
        System.out.println("Preorder traversal of this tree is:");
        preOrder(this.root);
        System.out.println(); // for next li
    }

    /**
     * To check if given value is present in tree or not.
     *
     * @param data the data to be found for
     */
    public boolean find(int data) {
        if (search(this.root, data)) {
            System.out.println(data + " is present in given BST.");
            return true;
        }
        System.out.println(data + " not found.");
        return false;
    }

    /**
     * The Node class used for building binary search tree
     */
    private static class Node {

        int data;
        Node left;
        Node right;

        /**
         * Constructor with data as parameter
         */
        Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }
}
