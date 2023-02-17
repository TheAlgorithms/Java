package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given tree is traversed in a 'pre-order' way: ROOT -> LEFT -> RIGHT.
 * Below are given the recursive and iterative implementations.
 *
 * Complexities:
 * Recursive: O(n) - time, O(n) - space, where 'n' is the number of nodes in a tree.
 *
 * Iterative: O(n) - time, O(h) - space, where 'n' is the number of nodes in a tree
 * and 'h' is the height of a binary tree.
 * In the worst case 'h' can be O(n) if tree is completely unbalanced, for instance:
 * 5
 *  \
 *   6
 *    \
 *     7
 *      \
 *       8
 *
 * @author Albina Gimaletdinova on 17/02/2023
 */
public class PreOrderTraversal {
    public static List<Integer> recursivePreOrder(BinaryTree.Node root) {
        List<Integer> result = new ArrayList<>();
        recursivePreOrder(root, result);
        return result;
    }

    public static List<Integer> iterativePreOrder(BinaryTree.Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<BinaryTree.Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTree.Node node = stack.pop();
            result.add(node.data);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return result;
    }

    private static void recursivePreOrder(BinaryTree.Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.data);
        recursivePreOrder(root.left, result);
        recursivePreOrder(root.right, result);
    }
}
