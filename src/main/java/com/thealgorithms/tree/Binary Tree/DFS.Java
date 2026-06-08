package com.thealgorithms.tree.BinaryTree;

public class DFS {

    // Inorder
    void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    // Preorder
    void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // Postorder
    void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        BinaryTreeDFS tree = new BinaryTreeDFS();

        Node root = new Node(4);
        root.left       = new Node(2);
        root.right      = new Node(6);
        root.left.left  = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right= new Node(7);

        System.out.print("Inorder:   "); tree.inorder(root);   // 1 2 3 4 5 6 7
        System.out.print("\nPreorder:  "); tree.preorder(root);  // 4 2 1 3 6 5 7
        System.out.print("\nPostorder: "); tree.postorder(root); // 1 3 2 5 7 6 4
    }
}

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
    }
}