package com.thealgorithms.tree.AVLtree;

public class Rotations extends AVLInsert {

    public static void main(String[] args) {
        Rotations tree = new Rotations();
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 10);

        System.out.println("Performing right rotation...");
        tree.root = tree.rightRotate(tree.root);

        System.out.println("New root after rotation: " + tree.root.key);
    }
}
