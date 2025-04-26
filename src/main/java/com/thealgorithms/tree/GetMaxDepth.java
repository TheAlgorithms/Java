package com.thealgorithms.tree;


import java.util.LinkedList;
import java.util.Queue;

/*
 * This is an algorithm for obtaining the maximum depth of a binary tree,
 * using a level-order traversal approach.
 * */
public class GetMaxDepth {

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
        Integer res = getDepth(node6);
        System.out.println(res);
    }

    public static int getDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res++;
        }
        return res;
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
