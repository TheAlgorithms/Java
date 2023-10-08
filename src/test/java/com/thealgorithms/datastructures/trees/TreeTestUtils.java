package com.thealgorithms.datastructures.trees;

import com.thealgorithms.datastructures.trees.BinaryTree.Node;
import java.util.LinkedList;
import java.util.Queue;

public class TreeTestUtils {

    /**
     * Creates a binary tree with given values
     *
     * @param values: Level order representation of tree
     * @return Root of a binary tree
     */
    public static Node createTree(final Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            throw new IllegalArgumentException("Values array should not be empty or null.");
        }
        final Node root = new Node(values[0]);
        final Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int end = 1;
        while (end < values.length) {
            final Node node = queue.remove();
            if (values[end] == null) {
                node.left = null;
            } else {
                node.left = new Node(values[end]);
                queue.add(node.left);
            }
            end++;
            if (end < values.length) {
                if (values[end] == null) {
                    node.right = null;
                } else {
                    node.right = new Node(values[end]);
                    queue.add(node.right);
                }
            }
            end++;
        }
        return root;
    }
}
