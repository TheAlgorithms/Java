package com.thealgorithms.datastructures.trees;

import java.util.*;

/**
 * Given a binary tree.
 * This code returns the zigzag level order traversal of its nodes' values.
 * Binary tree:
 *                               7
 *                   /                         \
 *                6                           3
 *         /                \             /             \
 *      2                    4         10                19
 * Zigzag traversal:
 * [[7], [3, 6], [2, 4, 10, 19]]
 * <p>
 * This solution implements the breadth-first search (BFS) algorithm using a queue.
 * 1. The algorithm starts with a root node. This node is added to a queue.
 * 2. While the queue is not empty:
 *  - each time we enter the while-loop we get queue size. Queue size refers to the number of nodes
 * at the current level.
 *  - we traverse all the level nodes in 2 ways: from left to right OR from right to left
 *    (this state is stored on `prevLevelFromLeftToRight` variable)
 *  - if the current node has children we add them to a queue
 *  - add level with nodes to a result.
 * <p>
 * Complexities:
 * O(N) - time, where N is the number of nodes in a binary tree
 * O(N) - space, where N is the number of nodes in a binary tree
 *
 * @author Albina Gimaletdinova on 11/01/2023
 */
public class ZigzagTraversal {
    public static List<List<Integer>> traverse(BinaryTree.Node root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();

        // create a queue
        Deque<BinaryTree.Node> q = new ArrayDeque<>();
        q.offer(root);
        // start with writing nodes from left to right
        boolean prevLevelFromLeftToRight = false;

        while (!q.isEmpty()) {
            int nodesOnLevel = q.size();
            List<Integer> level = new LinkedList<>();
            // traverse all the level nodes
            for (int i = 0; i < nodesOnLevel; i++) {
                BinaryTree.Node node = q.poll();
                if (prevLevelFromLeftToRight) {
                    level.add(0, node.data);
                } else {
                    level.add(node.data);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            // the next level node traversal will be from the other side
            prevLevelFromLeftToRight = !prevLevelFromLeftToRight;
            result.add(level);
        }
        return result;
    }
}
