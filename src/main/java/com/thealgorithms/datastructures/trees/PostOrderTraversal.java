package com.thealgorithms.datastructures.trees;

import java.util.*;

/**
 * Given tree is traversed in a 'post-order' way: LEFT -> RIGHT -> ROOT.
 * Below are given the recursive and iterative implementations.
 * <p>
 * Complexities:
 * Recursive: O(n) - time, O(n) - space, where 'n' is the number of nodes in a tree.
 * <p>
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
 * @author Albina Gimaletdinova on 21/02/2023
 */
public class PostOrderTraversal {
    public static List<Integer> recursivePostOrder(BinaryTree.Node root) {
        List<Integer> result = new ArrayList<>();
        recursivePostOrder(root, result);
        return result;
    }

    public static List<Integer> iterativePostOrder(BinaryTree.Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Deque<BinaryTree.Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTree.Node node = stack.pop();
            result.addFirst(node.data);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return result;
    }

    private static void recursivePostOrder(BinaryTree.Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        recursivePostOrder(root.left, result);
        recursivePostOrder(root.right, result);
        result.add(root.data);
    }
}
