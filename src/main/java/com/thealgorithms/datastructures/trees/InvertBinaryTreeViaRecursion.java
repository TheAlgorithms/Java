package com.thealgorithms.datastructures.trees;



class InvertBinaryTreeviaRecursion {

    //Question Link: https://leetcode.com/problems/invert-binary-tree/


    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            if (root.left == null && root.right == null) {
                return new TreeNode(root.val, null, null);
            }

            //Recursion calls
            TreeNode left = invertTree(root.right);
            TreeNode right = invertTree(root.left);

            TreeNode node = new TreeNode(root.val, left, right);
            return node;

        }
    }
}
