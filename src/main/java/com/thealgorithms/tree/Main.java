package com.thealgorithms.tree;

public class Main {
    public static void main(String[] args) {
        // --- Test BST ---
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("BST Inorder: " + bst.inorder());
        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());
        System.out.println("Contains 40? " + bst.contains(40));

        bst.delete(30);
        System.out.println("After deleting 30: " + bst.inorder());

        // --- Test AVL Tree ---
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);

        System.out.println("\nAVL Inorder: " + avl.inorder());
        System.out.println("Min: " + avl.findMin());
        System.out.println("Max: " + avl.findMax());
        System.out.println("Contains 25? " + avl.contains(25));

        avl.delete(30);
        System.out.println("After deleting 30: " + avl.inorder());
    }
}
