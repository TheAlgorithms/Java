package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Right View of a Binary Tree is the set of nodes visible when the tree
 * is viewed from the right side. For each level, the rightmost node is part
 * of the right view.
 *
 * <p>This implementation provides both DFS and BFS approaches.</p>
 */
public class RightViewOfBinaryTree {

    /**
     * Node class structure. If the repository already has a standard Node class,
     * reuse it instead of redefining this one.
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    /**
     * Returns the right view of the binary tree using DFS.
     *
     * @param root the root of the binary tree
     * @return list of node values visible from the right
     */
    public static List<Integer> rightViewDFS(Node root) {
        List<Integer> result = new ArrayList<>();
        dfsHelper(root, 0, result);
        return result;
    }

    private static void dfsHelper(Node node, int level, List<Integer> result) {
        if (node == null) return;
        if (level == result.size()) {
            result.add(node.data);
        }
        dfsHelper(node.right, level + 1, result);
        dfsHelper(node.left, level + 1, result);
    }

    /**
     * Returns the right view using a level-order (BFS) approach.
     *
     * @param root the root node
     * @return list of right view nodes
     */
    public static List<Integer> rightViewBFS(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (i == size - 1) {
                    result.add(current.data);
                }
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        System.out.println("Right View (DFS): " + rightViewDFS(root));
        System.out.println("Right View (BFS): " + rightViewBFS(root));
    }
}
