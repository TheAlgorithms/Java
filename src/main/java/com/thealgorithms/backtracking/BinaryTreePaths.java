package com.thealgorithms.backtracking;
import java.util.*;
public class BinaryTreePaths {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;
        dfs(root, "", list);
        return list;
    }

    private void dfs(TreeNode node, String path, List<String> list) {
        if (node == null) return;

        if (path.isEmpty()) path = "" + node.val;
        else path += "->" + node.val;

        if (node.left == null && node.right == null) {
            list.add(path);
            return;
        }

        dfs(node.left, path, list);
        dfs(node.right, path, list);
    }
    private static TreeNode buildTreeFromInput(Scanner sc) {
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        if (n == 0) return null;

        System.out.println("Enter node values in level order (use -1 for nulls):");
        int[] values = new int[n];
        for (int i = 0; i < n; i++) values[i] = sc.nextInt();

        if (values[0] == -1) return null;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (i < n && values[i] != -1) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;
            if (i < n && values[i] != -1) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = buildTreeFromInput(sc);

        BinaryTreePaths solver = new BinaryTreePaths();
        List<String> result = solver.binaryTreePaths(root);

        System.out.println("All root-to-leaf paths: " + result);
        sc.close();
    }
}
