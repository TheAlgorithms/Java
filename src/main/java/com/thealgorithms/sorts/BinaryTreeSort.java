package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Tree Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Tree_sort">Binary Tree Sort Algorithm</a>
 */
public class BinaryTreeSort implements SortAlgorithm {

    private static class TreeNode<T extends Comparable<T>> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        TreeNode<T> root = null;
        for (T value : array) {
            root = insert(root, value);
        }

        List<T> result = new ArrayList<>();
        inorder(root, result);

        for (int i = 0; i < array.length; i++) {
            array[i] = result.get(i);
        }

        return array;
    }

    private <T extends Comparable<T>> TreeNode<T> insert(TreeNode<T> root, T data) {
        if (root == null) {
            return new TreeNode<>(data);
        }

        if (SortUtils.less(data, root.data)) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        return root;
    }

    private <T extends Comparable<T>> void inorder(TreeNode<T> root, List<T> result) {
        if (root != null) {
            inorder(root.left, result);
            result.add(root.data);
            inorder(root.right, result);
        }
    }
}
