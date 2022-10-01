package com.thealgorithms.datastructures.trees;
import java.util.*;
/*
Creating class Node for tree which contain data part,left and right of that tree
*/
class Node{
  int data; // data part
  Node left; // left part of data which contains address of left-nodes
  Node right; // right part of data which conatins address of right-nodes
  Node(int val){  // constructor which initialize the values of data
    data=val;
    left=null; // initialize left address as NULL
    right=null; // initialize right address as NULL
  }
}
public class Main{ // Main class
   public static int maxDepth(Node root) { // Method which is used to calculate the maximum height of the tree
        if(root==null) return 0; //if pointer reaches null node that means their is no more nodes after. So the base case returns zero
        int lh=maxDepth(root.left); // recursive call to maxDepth which will calculate the height of left subtree.
        int rh=maxDepth(root.right); //recursive call to maxDepth which will calculate the height of right subtree.
        return 1+Math.max(lh,rh);  // After calcluating both height of left subtree and right subtree now it's time to find maximum out of both and add one to it because
     // we are standing at root node . So we have to add that also
    }
  public static void main(String args[]){ //Main method
    Scanner sc=new Scanner(System.in);  //Scanner object
    Node root=new Node(1); // Creating root node of the tree
    root.left=new Node(2);  // Creting child node of the tree
    root.right=new Node(3); // Creting child node of the tree
    root.left.left=new Node(4); // Creting leaf node of the tree
    root.left.right=new Node(5); // Creting leaf node of the tree
    root.right.left=new Node(6); // Creting leaf node of the tree
    root.right.right=new Node(7); // Creting leaf node of the tree
  int maxi=maxDepth(root); // calling the maxDepth method and storing that value in maxi variable
    System.out.println(maxi); // printing the maxi
  }
}

/*
TIME COMPLEXITY: O(N) 
SPACE COMPLEXITY: O(N) // Stack space of recursion
*/
