package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Binary Search Tree (Recursive) Generic Type Implementation</h1>
 *
 * <p>
 * A recursive implementation of generic type BST.
 *
 * Reference: https://en.wikipedia.org/wiki/Binary_search_tree
 * </p>
 *
 * @author [Madhur Panwar](https://github.com/mdrpanwar)
 */
public class BSTRecursiveGeneric<T extends Comparable<T>> {

    /**
     * only data member is root of BST
     */
    private Node<T> root;

    /**
     * Constructor use to initialize node as null
     */
    public BSTRecursiveGeneric() {
        root = null;
    }

    /**
     * main function for testing
     */
    public static void main(String[] args) {
        System.out.println("Testing for integer data...");
        // Integer
        BSTRecursiveGeneric<Integer> integerTree = new BSTRecursiveGeneric<Integer>();

        integerTree.add(5);
        integerTree.add(10);
        integerTree.add(9);
        assert !integerTree.find(4) : "4 is not yet present in BST";
        assert integerTree.find(10) : "10 should be present in BST";
        integerTree.remove(9);
        assert !integerTree.find(9) : "9 was just deleted from BST";
        integerTree.remove(1);
        assert !integerTree.find(
            1
        ) : "Since 1 was not present so find deleting would do no change";
        integerTree.add(20);
        integerTree.add(70);
        assert integerTree.find(70) : "70 was inserted but not found";
        /*
     Will print in following order
     5 10 20 70
         */
        integerTree.inorder();
        System.out.println();
        System.out.println("Testing for string data...");
        // String
        BSTRecursiveGeneric<String> stringTree = new BSTRecursiveGeneric<String>();

        stringTree.add("banana");
        stringTree.add("pineapple");
        stringTree.add("date");
        assert !stringTree.find("girl") : "girl is not yet present in BST";
        assert stringTree.find("pineapple") : "10 should be present in BST";
        stringTree.remove("date");
        assert !stringTree.find("date") : "date was just deleted from BST";
        stringTree.remove("boy");
        assert !stringTree.find(
            "boy"
        ) : "Since boy was not present so deleting would do no change";
        stringTree.add("india");
        stringTree.add("hills");
        assert stringTree.find("hills") : "hills was inserted but not found";
        /*
     Will print in following order
     banana hills india pineapple
         */
        stringTree.inorder();
    }

    /**
     * Recursive method to delete a data if present in BST.
     *
     * @param node the node under which to (recursively) search for data
     * @param data the value to be deleted
     * @return Node the updated value of root parameter after delete operation
     */
    private Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            System.out.println("No such data present in BST.");
        } else if (node.data.compareTo(data) > 0) {
            node.left = delete(node.left, data);
        } else if (node.data.compareTo(data) < 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.right == null && node.left == null) { // If it is leaf node
                node = null;
            } else if (node.left == null) { // If only right node is present
                Node<T> temp = node.right;
                node.right = null;
                node = temp;
            } else if (node.right == null) { // Only left node is present
                Node<T> temp = node.left;
                node.left = null;
                node = temp;
            } else { // both child are present
                Node<T> temp = node.right;
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
    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            node = new Node<>(data);
        } else if (node.data.compareTo(data) > 0) {
            node.left = insert(node.left, data);
        } else if (node.data.compareTo(data) < 0) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    /**
     * Recursively print Preorder traversal of the BST
     *
     * @param node the root node
     */
    private void preOrder(Node<T> node) {
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
     * Recursively print Postorder traversal of BST.
     *
     * @param node the root node
     */
    private void postOrder(Node<T> node) {
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
    private void inOrder(Node<T> node) {
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
     * Recursively traverse the tree using inorder traversal and keep adding
     * elements to argument list.
     *
     * @param node the root node
     * @param sortedList the list to add the srted elements into
     */
    private void inOrderSort(Node<T> node, List<T> sortedList) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inOrderSort(node.left, sortedList);
        }
        sortedList.add(node.data);
        if (node.right != null) {
            inOrderSort(node.right, sortedList);
        }
    }

    /**
     * Serach recursively if the given value is present in BST or not.
     *
     * @param node the node under which to check
     * @param data the value to be checked
     * @return boolean if data is present or not
     */
    private boolean search(Node<T> node, T data) {
        if (node == null) {
            return false;
        } else if (node.data.compareTo(data) == 0) {
            return true;
        } else if (node.data.compareTo(data) > 0) {
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
    public void add(T data) {
        this.root = insert(this.root, data);
    }

    /**
     * If data is present in BST delete it else do nothing.
     *
     * @param data the value to be removed
     */
    public void remove(T data) {
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
     * return a sorted list by traversing the tree elements using inorder
     * traversal
     */
    public List<T> inorderSort() {
        List<T> sortedList = new ArrayList<>();
        inOrderSort(this.root, sortedList);
        return sortedList;
    }

    /**
     * To call postorder traversal on tree
     */
    public void postorder() {
        System.out.println("Postorder traversal of this tree is:");
        postOrder(this.root);
        System.out.println(); // for next line
    }

    /**
     * To call preorder traversal on tree.
     */
    public void preorder() {
        System.out.println("Preorder traversal of this tree is:");
        preOrder(this.root);
        System.out.println(); // for next line
    }

    /**
     * To check if given value is present in tree or not.
     *
     * @param data the data to be found for
     */
    public boolean find(T data) {
        if (search(this.root, data)) {
            System.out.println(data + " is present in given BST.");
            return true;
        }
        System.out.println(data + " not found.");
        return false;
    }

    /**
     * The generic Node class used for building binary search tree
     */
    private static class Node<T> {

        T data;
        Node<T> left;
        Node<T> right;

        /**
         * Constructor with data as parameter
         */
        Node(T d) {
            data = d;
            left = null;
            right = null;
        }
    }
}
