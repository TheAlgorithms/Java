package com.thealgorithms.datastructures.trees;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a Splay Tree data structure.
 *
 * <p>
 * A splay tree is a self-adjusting binary search tree with the additional property
 * that recently accessed elements are quick to access again. It performs basic
 * operations such as insertion, deletion, and searching in O(log n) amortized time,
 * where n is the number of elements in the tree.
 * </p>
 *
 * <p>
 * The key feature of splay trees is the splay operation, which moves a node closer
 * to the root of the tree when it is accessed. This operation helps to maintain
 * good balance and improves the overall performance of the tree. After performing
 * a splay operation, the accessed node becomes the new root of the tree.
 * </p>
 *
 * <p>
 * Splay trees have applications in various areas, including caching, network routing,
 * and dynamic optimality analysis.
 * </p>
 */
public class SplayTree {

    private static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    private Node root;

    /**
     * Constructs an empty SplayTree.
     */
    public SplayTree() {
        root = null;
    }

    /**
     * Zig operation.
     *
     * <p>
     * The zig operation is used to perform a single rotation on a node to move it closer to
     * the root of the tree. It is typically applied when the node is a left child of its parent
     * and needs to be rotated to the right.
     * </p>
     *
     * @param x The node to perform the zig operation on.
     * @return The new root node after the operation.
     */
    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    /**
     * Zag operation.
     *
     * <p>
     * The zag operation is used to perform a single rotation on a node to move it closer to
     * the root of the tree. It is typically applied when the node is a right child of its parent
     * and needs to be rotated to the left.
     * </p>
     *
     * @param x The node to perform the zag operation on.
     * @return The new root node after the operation.
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    /**
     * Splay operation.
     *
     * <p>
     * The splay operation is the core operation of a splay tree. It moves a specified node
     * closer to the root of the tree by performing a series of rotations. The goal of the splay
     * operation is to improve the access time for frequently accessed nodes by bringing them
     * closer to the root.
     * </p>
     *
     * <p>
     * The splay operation consists of three main cases:
     * <ul>
     * <li>Zig-Zig case: Perform two consecutive rotations.</li>
     * <li>Zig-Zag case: Perform two consecutive rotations in opposite directions.</li>
     * <li>Zag-Zag case: Perform two consecutive rotations.</li>
     * </ul>
     * </p>
     *
     * <p>
     * After performing the splay operation, the accessed node becomes the new root of the tree.
     * </p>
     *
     * @param root The root of the subtree to splay.
     * @param key  The key to splay around.
     * @return The new root of the splayed subtree.
     */
    private Node splay(Node root, int key) {
        if (root == null || root.key == key) return root;

        if (root.key > key) {
            if (root.left == null) return root;
            // Zig-Zig case
            if (root.left.key > key) {
                // Recursive call to splay on grandchild
                root.left.left = splay(root.left.left, key);
                // Perform zig operation on parent
                root = rotateRight(root);
            } // Zig-Zag case
            else if (root.left.key < key) {
                // Recursive call to splay on grandchild
                root.left.right = splay(root.left.right, key);
                // Perform zag operation on parent
                if (root.left.right != null) root.left = rotateLeft(root.left);
            }

            return (root.left == null) ? root : rotateRight(root);
        } else {
            if (root.right == null) return root;
            // Zag-Zag case
            if (root.right.key > key) {
                // Recursive call to splay on grandchild
                root.right.left = splay(root.right.left, key);
                // Perform zig operation on parent
                if (root.right.left != null) root.right = rotateRight(root.right);
            } // Zag-Zig case
            else if (root.right.key < key) {
                // Recursive call to splay on grandchild
                root.right.right = splay(root.right.right, key);
                // Perform zag operation on parent
                root = rotateLeft(root);
            }

            return (root.right == null) ? root : rotateLeft(root);
        }
    }

    /**
     * Insert a key into the SplayTree.
     *
     * @param key The key to insert.
     */
    public void insert(int key) {
        root = insertRec(root, key);
        root = splay(root, key);
    }

    /**
     * Recursive function to insert a key into a subtree.
     *
     * @param root The root of the subtree to insert the key into.
     * @param key  The key to insert.
     * @return The root of the modified subtree after insertion.
     * @throws IllegalArgumentException If the key to be inserted already exists in the subtree.
     */
    private Node insertRec(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        } else {
            throw new IllegalArgumentException("Duplicate key: " + key);
        }

        return root;
    }

    /**
     * Search for a key in the SplayTree.
     *
     * @param key The key to search for.
     * @return True if the key is found, otherwise false.
     */
    public boolean search(int key) {
        root = splay(root, key);
        return root != null && root.key == key;
    }

    /**
     * Deletes a key from the SplayTree.
     *
     * @param key The key to delete.
     * @throws IllegalArgumentException If the tree is empty.
     */
    public void delete(int key) {
        if (root == null) {
            throw new IllegalArgumentException("Cannot delete from an empty tree");
        }

        // Splay the tree with the key to be deleted
        root = splay(root, key);

        // If the key is not found at the root, return without deleting
        if (root.key != key) {
            return;
        }

        // Handle deletion
        if (root.left == null) {
            root = root.right;
        } else {
            Node temp = root;
            // Splay to bring the largest key in left subtree to root
            root = splay(root.left, key);
            root.right = temp.right;
        }
    }

    /**
     * Perform a traversal of the SplayTree.
     *
     * @param traverseOrder The order of traversal (IN_ORDER, PRE_ORDER, or POST_ORDER).
     * @return A list containing the keys in the specified traversal order.
     */
    public List<Integer> traverse(TraverseOrder traverseOrder) {
        List<Integer> result = new LinkedList<>();
        switch (traverseOrder) {
        case IN_ORDER:
            inOrderRec(root, result);
            break;
        case PRE_ORDER:
            preOrderRec(root, result);
            break;
        case POST_ORDER:
            postOrderRec(root, result);
            break;
        }
        return result;
    }

    /**
     * Recursive function for in-order traversal.
     *
     * @param root   The root of the subtree to traverse.
     * @param result The list to store the traversal result.
     */
    private void inOrderRec(Node root, List<Integer> result) {
        if (root != null) {
            inOrderRec(root.left, result);
            result.add(root.key);
            inOrderRec(root.right, result);
        }
    }

    /**
     * Recursive function for pre-order traversal.
     *
     * @param root   The root of the subtree to traverse.
     * @param result The list to store the traversal result.
     */
    private void preOrderRec(Node root, List<Integer> result) {
        if (root != null) {
            result.add(root.key);
            preOrderRec(root.left, result);
            preOrderRec(root.right, result);
        }
    }

    /**
     * Recursive function for post-order traversal.
     *
     * @param root   The root of the subtree to traverse.
     * @param result The list to store the traversal result.
     */
    private void postOrderRec(Node root, List<Integer> result) {
        if (root != null) {
            postOrderRec(root.left, result);
            postOrderRec(root.right, result);
            result.add(root.key);
        }
    }

    /**
     * Enum to specify the order of traversal.
     */
    public enum TraverseOrder { IN_ORDER, PRE_ORDER, POST_ORDER }
}
