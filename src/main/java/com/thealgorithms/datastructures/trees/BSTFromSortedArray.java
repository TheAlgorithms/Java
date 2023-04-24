package com.thealgorithms.datastructures.trees;

import com.thealgorithms.datastructures.trees.BinaryTree.Node;

/**
 * Given a sorted array. Create a balanced binary search tree from it.
 *
 * Steps: 1. Find the middle element of array. This will act as root 2. Use the
 * left half recursively to create left subtree 3. Use the right half
 * recursively to create right subtree
 */
public class BSTFromSortedArray {
    public static Node createBST(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return createBST(array, 0, array.length - 1);
    }

    private static Node createBST(int[] array, int startIdx, int endIdx) {
        // No element left.
        if (startIdx > endIdx) {
            return null;
        }
        int mid = startIdx + (endIdx - startIdx) / 2;

        // middle element will be the root
        Node root = new Node(array[mid]);
        root.left = createBST(array, startIdx, mid - 1);
        root.right = createBST(array, mid + 1, endIdx);
        return root;
    }
}
