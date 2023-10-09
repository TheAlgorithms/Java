package com.thealgorithms.datastructures.trees;

import com.thealgorithms.datastructures.trees.BinaryTree.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * Approach: Naive Solution: Create root node from first value present in
 * preorder traversal. Look for the index of root node's value in inorder
 * traversal. That will tell total nodes present in left subtree and right
 * subtree. Based on that index create left and right subtree. Complexity: Time:
 * O(n^2) for each node there is iteration to find index in inorder array Space:
 * Stack size = O(height) = O(lg(n))
 * <p>
 * Optimized Solution: Instead of iterating over inorder array to find index of
 * root value, create a hashmap and find out the index of root value.
 * Complexity: Time: O(n) hashmap reduced iteration to find index in inorder
 * array Space: O(n) space taken by hashmap
 */
public class CreateBinaryTreeFromInorderPreorder {
    public static Node createTree(final Integer[] preorder, final Integer[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        return createTree(preorder, inorder, 0, 0, inorder.length);
    }

    public static Node createTreeOptimized(final Integer[] preorder, final Integer[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return createTreeOptimized(preorder, inorderMap, 0, 0, inorder.length);
    }

    private static Node createTree(final Integer[] preorder, final Integer[] inorder, final int preStart, final int inStart, final int size) {
        if (size == 0) {
            return null;
        }

        Node root = new Node(preorder[preStart]);
        int i = inStart;
        while (!preorder[preStart].equals(inorder[i])) {
            i++;
        }
        int leftNodesCount = i - inStart;
        int rightNodesCount = size - leftNodesCount - 1;
        root.left = createTree(preorder, inorder, preStart + 1, inStart, leftNodesCount);
        root.right = createTree(preorder, inorder, preStart + leftNodesCount + 1, i + 1, rightNodesCount);
        return root;
    }

    private static Node createTreeOptimized(final Integer[] preorder, final Map<Integer, Integer> inorderMap, final int preStart, final int inStart, final int size) {
        if (size == 0) {
            return null;
        }

        Node root = new Node(preorder[preStart]);
        int i = inorderMap.get(preorder[preStart]);
        int leftNodesCount = i - inStart;
        int rightNodesCount = size - leftNodesCount - 1;
        root.left = createTreeOptimized(preorder, inorderMap, preStart + 1, inStart, leftNodesCount);
        root.right = createTreeOptimized(preorder, inorderMap, preStart + leftNodesCount + 1, i + 1, rightNodesCount);
        return root;
    }
}
