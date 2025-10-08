package com.thealgorithms.tree;
import java.util.*;
/*
 * A simple implementation of a Binary Search Tree (BST) in Java.
 * Supports insertion of integer values and tree traversals: inorder, preorder, and postorder.
 */
public class BinarySearchTree {

  //node class
  static class node {
    int data;
    node left;
    node right;

    node(int d) {
      this.data = d;
    }
  }

  //insert value into BST
  public static node insert_recurssive(node root, int d) {
    if (root == null) {
      root = new node(d);
      return root;
    }
    if (root.data > d) {
      //left subtree
      root.left = insert_recurssive(root.left, d);
    } else {
      //right subtree
      root.right = insert_recurssive(root.right, d);
    }
    return root;
  }

  //inorder traversal
  public static void inorder(node root) {
    if (root == null) {
      return;
    }
    inorder(root.left);
    System.out.print(root.data + " ");
    inorder(root.right);
  }

  //postorder traversal
  public static void postorder(node root) {

    if (root == null) {
      return;
    }
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.data + " ");
  }

  //preorder traversal
  public static void preorder(node root) {

    if (root == null) {
      return;
    }
    System.out.print(root.data + " ");
    preorder(root.left);
    preorder(root.right);
  }

  //main method
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of integer elements");
    int n = sc.nextInt();

    int values[] = new int[n];
    System.out.println("Enter " + n + " values:-");
    for (int i = 0; i < n; i++)
      values[i] = sc.nextInt();
    sc.close();
    node root = null;
    //insert values into BST
    for (int i = 0; i < values.length; i++) {
      root = insert_recurssive(root, values[i]);
    }

    //traversal of BST
    System.out.println("Inorder Traversal is:-");
    inorder(root);
    System.out.println("\nPostorder Traversal is:-");
    postorder(root);
    System.out.println("\nPreorder Traversal is:-");
    preorder(root);
  }
}