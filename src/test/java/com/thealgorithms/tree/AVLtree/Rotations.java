package com.thealgorithms.tree.AVLtree;



public class Rotations extends AVLInsert {

    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public static void main(String[] args) {
        Rotations r = new Rotations();
        Node root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);

        System.out.println("Performing right rotation on 30...");
        root = r.rightRotate(root);
        System.out.println("New root after rotation: " + root.key);
    }
}

