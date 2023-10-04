package com.thealgorithms.datastructures.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given 2 binary trees.
 * This code checks whether they are the same (structurally identical and have the same values) or
 * not. <p> Example:
 * 1. Binary trees:
 *      1                 1
 *     / \               / \
 *    2   3             2   3
 *   /\   /\           /\   /\
 *  4  5 6  7         4  5 6  7
 * These trees are the same, so the code returns 'true'.
 * <p>
 * 2. Binary trees:
 *      1   1
 *     /     \
 *    2       2
 * These trees are NOT the same (the structure differs), so the code returns 'false'.
 * <p>
 * This solution implements the breadth-first search (BFS) algorithm.
 * For each tree we create a queue and iterate the trees using these queues.
 * On each step we check the nodes for equality, and if the nodes are not the same, return false.
 * Otherwise, add children nodes to the queues and continue traversing the trees.
 * <p>
 * Complexities:
 * O(N) - time, where N is the number of nodes in a binary tree,
 * O(N) - space, where N is the number of nodes in a binary tree.
 *
 * @author Albina Gimaletdinova on 13/01/2023
 */
public class SameTreesCheck {
    public static boolean check(BinaryTree.Node p, BinaryTree.Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        Deque<BinaryTree.Node> q1 = new ArrayDeque<>();
        Deque<BinaryTree.Node> q2 = new ArrayDeque<>();
        q1.add(p);
        q2.add(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            BinaryTree.Node first = q1.poll();
            BinaryTree.Node second = q2.poll();
            // check that some node can be null
            // if the check is true: both nodes are null or both nodes are not null
            if (!equalNodes(first, second)) return false;

            if (first != null) {
                if (!equalNodes(first.left, second.left)) return false;
                if (first.left != null) {
                    q1.add(first.left);
                    q2.add(second.left);
                }

                if (!equalNodes(first.right, second.right)) return false;
                if (first.right != null) {
                    q1.add(first.right);
                    q2.add(second.right);
                }
            }
        }
        return true;
    }

    private static boolean equalNodes(BinaryTree.Node p, BinaryTree.Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.data == q.data;
    }
}
