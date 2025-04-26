package com.thealgorithms.tree;

import java.util.ArrayList;

/*
 * This is an algorithm for finding the lowest common ancestor (LCA) of a binary tree,
 * presented in a recursive form.
 * */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        /*
         * createTreeNode
         *root = [3,5,1,6,2,0,8,null,null,7,4]
         *explain
         * Binary Tree Structure:
         *        3
         *       / \
         *      5   1
         *     / \ / \
         *    6  2 0  8
         *      / \
         *     7   4.
         * */
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        // createTree
        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node6.left = node7;
        node6.right = node4;
        node2.left = node0;
        node2.right = node8;
        TreeNode treeNode = recursiveFunction(node3, node5, node1);
        System.out.println(treeNode.val);
    }

    public static TreeNode recursiveFunction(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = recursiveFunction(root.left, p, q);
        TreeNode right = recursiveFunction(root.right, p, q);

        if (left == null && right == null) {
            return null;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            return root;
        }
    }

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
