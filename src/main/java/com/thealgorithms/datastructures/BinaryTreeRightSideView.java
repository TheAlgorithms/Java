package com.thealgorithms.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    // FIX: Make the TreeNode class static.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            this.val = x;
        }
    }

    // FIX: Make the RightSideView class static.
    public static class RightSideView {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();

                    // If it's the last node in this level, add it to the result.
                    if (i == size - 1) {
                        result.add(node.val);
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return result;
        }

        // The main method can now correctly instantiate these static classes.
        public static void main(String[] args) {
            RightSideView sol = new RightSideView();

            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.right = new TreeNode(5);
            root.right.right = new TreeNode(4);

            // The logic correctly produces the right side view.
            System.out.println(sol.rightSideView(root)); // Expected Output: [1, 3, 4]
        }
    }
}