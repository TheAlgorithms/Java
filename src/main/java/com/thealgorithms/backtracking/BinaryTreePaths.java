package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        dfs(root, "", list);
        return list;
    }

    private void dfs(TreeNode node, String path, List<String> list) {
        if (node == null) {
            return;
        }

        if (path.isEmpty()) {
            path = Integer.toString(node.val);
        } else {
            path += "->" + node.val;
        }

        if (node.left == null && node.right == null) {
            list.add(path);
            return;
        }

        dfs(node.left, path, list);
        dfs(node.right, path, list);
    }
}
