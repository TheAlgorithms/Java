package com.thealgorithms.datastructures.trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversalOfBt {

    
    /*
     * Link for Explanation of ZigZag Traversal: https://www.geeksforgeeks.org/zigzag-tree-traversal/
     */

    /**
     * Definition for a binary tree node.
     */
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

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root==null){
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        boolean flag=true;

        while (!q.isEmpty()) {
            int levelNum = q.size();
            List<Integer> subList = Arrays.asList(new Integer[levelNum]);
            for (int i = 0; i < levelNum; i++) {
                TreeNode currNode = q.remove();
                int index = flag?i : levelNum-i-1;

                subList.set(index,currNode.val);

                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            flag = !flag;
            ans.add(subList);
        }
        return ans;
    }
}
