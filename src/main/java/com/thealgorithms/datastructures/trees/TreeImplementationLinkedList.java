package com.thealgorithms.datastructures.trees;

import java.util.Scanner;

public class TreeImplementationLinkedList {

    static Scanner sc=null;
    public static void main(String[] args) {

        sc=new Scanner(System.in);

        Node root= createTree();
        inOrder(root);
    }
    static Node createTree()
    {
        Node root = null;
        System.out.println("Enter Data:- ");
        int data= sc.nextInt();

        if (data==-1) return null;

        root=new Node(data);

        System.out.println("Enter Left for " + data );
        root.left=createTree();

        System.out.println("Enter Right for " + data );
        root.right=createTree();

        return root;
    }
    static void inOrder(Node root) {
        if(root == null) return;

        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    static void preOrder(Node root) {
        if(root == null) return;
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node root) {
        if(root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }
}
class Node{
    Node left,right;
    int data;

    public Node(int data)
    {
        this.data=data;
    }
}