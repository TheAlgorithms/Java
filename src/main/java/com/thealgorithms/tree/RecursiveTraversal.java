package com.thealgorithms.tree;


import java.util.ArrayList;
import java.util.List;
/*
* This is an algorithm for recursive traversal of a binary tree.
* */
public class RecursiveTraversal {

    public static void main(String[] args) {
        /*
         * createTree
         * root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
         * explain
         * Binary Tree Structure:
         *        6
         *       / \
         *      2   8
         *     / \ / \
         *    0  4 7  9
         *      / \
         *     3   5
         *
         * */
        // createTreeNode
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        // Binary Tree Structure
        node6.left = node2;
        node6.right = node8;
        node2.left = node0;
        node2.right = node4;
        node4.left = node3;
        node4.right = node5;
        node8.left = node7;
        node8.right = node9;

        List<Integer>preorder = preorderFunction(node6);
        List<Integer>inorder = inorderFunction(node6);
        List<Integer>postorder = postorderFunction(node6);
        System.out.println(preorder.toString());
        System.out.println(inorder.toString());
        System.out.println(postorder.toString());



    }
    /*
    * Pre-order Traversal
    * */
    public static List<Integer> preorderFunction(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }
    public static void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
    /*
    * in-order Traversal
    * */
    public static List<Integer> inorderFunction(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }
    public static void  inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
    /*
    * pos-order Traversal
    * */
    public static List<Integer> postorderFunction(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    public  static void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
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
