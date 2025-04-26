package com.thealgorithms.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * This is an algorithm for level-order traversal of a binary tree.
 * */
public class LevelOrderTraversal {

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
        List<List<Integer>> res = levelOrder(node6);
        for (List item : res) {
            for (Object number : item) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        bfs(root, res);
        return res;
    }

    public static void bfs(TreeNode root, List<List<Integer>> res) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<Integer>();
            int length = queue.size();

            for (int i = 0; i < length; i++) {
                TreeNode tmpNode = queue.poll();
                temp.add(tmpNode.val);

                if (tmpNode.left != null) queue.offer(tmpNode.left);
                if (tmpNode.right != null) queue.offer(tmpNode.right);
            }
            res.add(temp);
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
