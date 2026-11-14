package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, returns the values of the nodes visible
 * from the right side (the last node of each level in a level-order traversal).
 *
 * <p>Example:
 * <pre>
 *      1
 *     / \
 *    2   3
 *     \   \
 *      5   4
 * Right side view: [1, 3, 4]
 * </pre>
 *
 * <p>Time complexity: O(n), Space complexity: O(n)
 *
 * @see <a href="https://leetcode.com/problems/binary-tree-right-side-view/">LeetCode #199</a>
 */
public final class RightSideViewOfBinaryTree {
    private RightSideViewOfBinaryTree() {
    }

    public static List<Integer> rightSideView(BinaryTree.Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<BinaryTree.Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                BinaryTree.Node node = queue.poll();
                if (i == levelSize - 1) {
                    result.add(node.data);
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
}
