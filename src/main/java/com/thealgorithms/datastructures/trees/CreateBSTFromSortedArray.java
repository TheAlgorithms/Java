package com.thealgorithms.datastructures.trees;

import com.thealgorithms.datastructures.trees.BinaryTree.Node;

/**
 * Given a sorted array. Create a balanced binary search tree from it.
 *
 * Steps: 1. Find the middle element of array. This will act as root 2. Use the
 * left half recursively to create left subtree 3. Use the right half
 * recursively to create right subtree
 */
public class CreateBSTFromSortedArray {

    public static void main(String[] args) {
        test(new int[] {});
        test(new int[] { 1, 2, 3 });
        test(new int[] { 1, 2, 3, 4, 5 });
        test(new int[] { 1, 2, 3, 4, 5, 6, 7 });
    }

    private static void test(int[] array) {
        BinaryTree root = new BinaryTree(createBst(array, 0, array.length - 1));
        System.out.println("\n\nPreorder Traversal: ");
        root.preOrder(root.getRoot());
        System.out.println("\nInorder Traversal: ");
        root.inOrder(root.getRoot());
        System.out.println("\nPostOrder Traversal: ");
        root.postOrder(root.getRoot());
    }

    private static Node createBst(int[] array, int start, int end) {
        // No element left.
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;

        // middle element will be the root
        Node root = new Node(array[mid]);
        root.left = createBst(array, start, mid - 1);
        root.right = createBst(array, mid + 1, end);
        return root;
    }
}
