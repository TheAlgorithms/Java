package com.thealgorithms.datastructures.trees;

/*  The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *  The length of a path between two nodes is represented by the number of edges between them.
 *  Link for further explanation - https://www.javatpoint.com/diameter-of-binary-tree   
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class DiameterOfBT {

    //Below functions accepts root of a Binary Tree and returns the diameter of a Binary tree
    public int diameterOfBinaryTree(TreeNode root) {
        return diameter(root)[0]-1;
    }
    
    //Function to recursively call and get the height and diameter of Binary tree
    static int[] diameter(TreeNode root) {
        //[0] denotes diameter
        //[1] denotes height
        int res[]={0,0};
        
        //base case
        if(root==null)
            return res;
        
        //Recursively calling for left and right of Binary Tree
        int left[]=diameter(root.left);
        int right[]=diameter(root.right);
        
        //Calculating height
        res[1] = Math.max(left[1],right[1])+1;
        
        //Calculating diameter
        //diam1 = left diam = laft[0]
        //diam2 = right diam = right[0]
        //diam3 = passess through root = left height+ right height+ 1
        res[0] = Math.max(Math.max(left[0],right[0]), left[1]+right[1]+1);
        
        return res;
    }
}
