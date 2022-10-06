package com.thealgorithms.datastructures.lists;
//import java.util.*;

/* 
Problem statement:
    Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */

//------------------------------------------------------------------------------
                            /* Approach - 1 */

//MORRIS Traversal Algorithm. 
// Time Complexity => O(N)
// Space Complexity =>  O(1)
 //Prodecesser meaning-->From the root node first go to its left node and then from that left node  go to its right untill node.right!=null and that node is called the Prodecesser of a particular node.
 class MorrisTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
}
    public void flatten(TreeNode root) {
     
     if(root==null) return;
     
     while(root!=null) {
       if(root.left!=null){
         TreeNode left=root.left;
         TreeNode curr=left;  
         while(curr.right!=null) //finding Prodecesser 
         {curr=curr.right;}
         curr.right=root.right; 
         root.left=null;
         root.right=left;
       }
      root=root.right; //because now the whole left_subtree shifted to right of root node.      
     }
   }
}

//------------------------------------------------------------------------------
                            /* Approach - 2 */

//Recursion Solution.  
// Time Complexity => O(N)
// Space Complexity => O(N)
class Recursive {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
}
    public void flatten(TreeNode root) {
        if(root == null) return;
        
        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        
        root.left = null;
        
        flatten(tempLeft);
        flatten(tempRight);
        
        root.right = tempLeft;
        TreeNode current = root;
        while(current.right != null) current = current.right;
        current.right = tempRight;
        
    }
}

//------------------------------------------------------------------------------
// website Link for understanding MORRIS Algorithm: https://www.educative.io/answers/what-is-morris-traversal
// Porblem statement link : https://leetcode.com/problems/flatten-binary-tree-to-linked-list/