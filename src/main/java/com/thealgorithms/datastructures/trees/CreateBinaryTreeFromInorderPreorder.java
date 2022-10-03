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
 *
 * Optimized Solution: Instead of iterating over inorder array to find index of
 * root value, create a hashmap and find out the index of root value.
 * Complexity: Time: O(n) hashmap reduced iteration to find index in inorder
 * array Space: O(n) space taken by hashmap
 *
 */
public class CreateBinaryTreeFromInorderPreorder {

    public static void main(String[] args) {
        test(new Integer[] {}, new Integer[] {}); // empty tree
        test(new Integer[] { 1 }, new Integer[] { 1 }); // single node tree
        test(new Integer[] { 1, 2, 3, 4 }, new Integer[] { 1, 2, 3, 4 }); // right skewed tree
        test(new Integer[] { 1, 2, 3, 4 }, new Integer[] { 4, 3, 2, 1 }); // left skewed tree
        test(
            new Integer[] { 3, 9, 20, 15, 7 },
            new Integer[] { 9, 3, 15, 20, 7 }
        ); // normal tree
    }

    private static void test(
        final Integer[] preorder,
        final Integer[] inorder
    ) {
        System.out.println(
            "\n===================================================="
        );
        System.out.println("Naive Solution...");
        BinaryTree root = new BinaryTree(
            createTree(preorder, inorder, 0, 0, inorder.length)
        );
        System.out.println("Preorder Traversal: ");
        root.preOrder(root.getRoot());
        System.out.println("\nInorder Traversal: ");
        root.inOrder(root.getRoot());
        System.out.println("\nPostOrder Traversal: ");
        root.postOrder(root.getRoot());

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        BinaryTree optimizedRoot = new BinaryTree(
            createTreeOptimized(preorder, inorder, 0, 0, inorder.length, map)
        );
        System.out.println("\n\nOptimized solution...");
        System.out.println("Preorder Traversal: ");
        optimizedRoot.preOrder(root.getRoot());
        System.out.println("\nInorder Traversal: ");
        optimizedRoot.inOrder(root.getRoot());
        System.out.println("\nPostOrder Traversal: ");
        optimizedRoot.postOrder(root.getRoot());
    }

    private static Node createTree(
        final Integer[] preorder,
        final Integer[] inorder,
        final int preStart,
        final int inStart,
        final int size
    ) {
        if (size == 0) {
            return null;
        }

        Node root = new Node(preorder[preStart]);
        int i = inStart;
        while (preorder[preStart] != inorder[i]) {
            i++;
        }
        int leftNodesCount = i - inStart;
        int rightNodesCount = size - leftNodesCount - 1;
        root.left =
            createTree(
                preorder,
                inorder,
                preStart + 1,
                inStart,
                leftNodesCount
            );
        root.right =
            createTree(
                preorder,
                inorder,
                preStart + leftNodesCount + 1,
                i + 1,
                rightNodesCount
            );
        return root;
    }

    private static Node createTreeOptimized(
        final Integer[] preorder,
        final Integer[] inorder,
        final int preStart,
        final int inStart,
        final int size,
        final Map<Integer, Integer> inorderMap
    ) {
        if (size == 0) {
            return null;
        }

        Node root = new Node(preorder[preStart]);
        int i = inorderMap.get(preorder[preStart]);
        int leftNodesCount = i - inStart;
        int rightNodesCount = size - leftNodesCount - 1;
        root.left =
            createTreeOptimized(
                preorder,
                inorder,
                preStart + 1,
                inStart,
                leftNodesCount,
                inorderMap
            );
        root.right =
            createTreeOptimized(
                preorder,
                inorder,
                preStart + leftNodesCount + 1,
                i + 1,
                rightNodesCount,
                inorderMap
            );
        return root;
    }
}
