package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Implements a vertical order traversal in a tree from top to bottom
 * and left to right.
 */
public final class VerticalOrderTraversal {

    private VerticalOrderTraversal() {
    }

    /**
     * Modern Java Record to encapsulate a node and its vertical column index.
     * This replaces the bulky inner class and parallel queues.
     */
    private record NodeColumnPair(BinaryTree.Node node, int column) {}

    /**
     * Prints the tree in Vertical Order.
     * Note: Visibility changed to package-private to match BinaryTree.Node.
     * * @param root The root node of the binary tree.
     * @return An ArrayList containing the vertical traversal order.
     */
    static ArrayList<Integer> verticalTraversal(BinaryTree.Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<NodeColumnPair> queue = new LinkedList<>();
        Map<Integer, List<Integer>> columnMap = new HashMap<>();

        int minColumn = 0;
        int maxColumn = 0;

        queue.offer(new NodeColumnPair(root, 0));

        while (!queue.isEmpty()) {
            NodeColumnPair current = queue.poll();

            // Accessing record components uses method syntax '()'
            BinaryTree.Node currentNode = current.node();
            int currentColumn = current.column();

            // Automatically create a new list if the column doesn't exist yet
            columnMap.computeIfAbsent(currentColumn, k -> new ArrayList<>())
                    .add(currentNode.data);

            // Track bounds to avoid needing to sort map keys later
            minColumn = Math.min(minColumn, currentColumn);
            maxColumn = Math.max(maxColumn, currentColumn);

            if (currentNode.left != null) {
                queue.offer(new NodeColumnPair(currentNode.left, currentColumn - 1));
            }

            if (currentNode.right != null) {
                queue.offer(new NodeColumnPair(currentNode.right, currentColumn + 1));
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = minColumn; i <= maxColumn; i++) {
            result.addAll(columnMap.get(i));
        }

        return result;
    }
}