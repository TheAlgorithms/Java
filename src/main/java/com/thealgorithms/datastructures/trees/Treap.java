package com.thealgorithms.datastructures.trees;

import java.util.Random;

/**
 * Treap -> Tree + Heap
 * Also called as cartesian tree
 *
 * @see
 * <a href = "https://cp-algorithms.com/data_structures/treap.html" />
 */

public class Treap {

    public static class TreapNode {
        /**
         * TreapNode class defines the individual nodes in the Treap
         *
         * value -> holds the value of the node.
         * Binary Search Tree is built based on value.
         *
         * priority -> holds the priority of the node.
         * Heaps are maintained based on priority.
         * It is randomly assigned
         *
         * size -> holds the size of the subtree with current node as root
         *
         * left -> holds the left subtree
         * right -> holds the right subtree
         */
        public int value;
        private int priority;
        private int size;
        public TreapNode left;
        public TreapNode right;

        public TreapNode(int valueParam, int priorityParam) {
            value = valueParam;
            priority = priorityParam;
            size = 1;
            left = null;
            right = null;
        }

        /**
         * updateSize -> updates the subtree size of the current node
         */
        private void updateSize() {
            size = 1;
            if (left != null) {
                size += left.size;
            }
            if (right != null) {
                size += right.size;
            }
        }
    }

    /**
     * root -> holds the root node in the Treap
     * random -> to generate random priority for the nodes in the Treap
     */
    private TreapNode root;
    private Random random = new Random();

    /**
     * Constructors
     *
     * Treap() -> create an empty Treap
     * Treap(int[] nodeValues) -> add the elements given in the array to the Treap
     */
    public Treap() {
        root = null;
    }

    /**
     * merges two Treaps left and right into a single Treap
     *
     * @param left left Treap
     * @param right right Treap
     * @return root of merged Treap
     */
    private TreapNode merge(TreapNode left, TreapNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            left.updateSize();
            return left;
        } else {
            right.left = merge(left, right.left);
            right.updateSize();
            return right;
        }
    }

    /**
     * split the Treap into two Treaps where left Treap has nodes <= key and right Treap has nodes > key
     *
     * @param node root node to be split
     * @param key key to compare the nodes
     * @return TreapNode array of size 2.
     * TreapNode[0] contains the root of left Treap after split
     * TreapNode[1] contains the root of right Treap after split
     */
    private TreapNode[] split(TreapNode node, int key) {
        if (node == null) {
            return new TreapNode[] {null, null};
        }

        TreapNode[] result;

        if (node.value <= key) {
            result = split(node.right, key);
            node.right = result[0];
            node.updateSize();
            result[0] = node;
        } else {
            result = split(node.left, key);
            node.left = result[1];
            node.updateSize();
            result[1] = node;
        }

        return result;
    }

    /**
     * insert a node into the Treap
     *
     * @param value value to be inserted into the Treap
     * @return root of the Treap where the value is inserted
     */
    public TreapNode insert(int value) {
        if (root == null) {
            root = new TreapNode(value, random.nextInt());
            return root;
        }

        TreapNode[] splitted = split(root, value);

        TreapNode node = new TreapNode(value, random.nextInt());

        TreapNode tempMerged = merge(splitted[0], node);
        tempMerged.updateSize();

        TreapNode merged = merge(tempMerged, splitted[1]);
        merged.updateSize();

        root = merged;

        return root;
    }

    /**
     * delete a value from root if present
     *
     * @param value value to be deleted from the Treap
     * @return root of the Treap where delete has been performed
     */
    public TreapNode delete(int value) {
        root = deleteNode(root, value);
        return root;
    }

    private TreapNode deleteNode(TreapNode root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.value) {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        } else {
            root = merge(root.left, root.right);
        }

        if (root != null) {
            root.updateSize();
        }
        return root;
    }

    /**
     * print inorder traversal of the Treap
     */
    public void inOrder() {
        System.out.print("{");
        printInorder(root);
        System.out.print("}");
    }

    private void printInorder(TreapNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.value + ",");
        printInorder(root.right);
    }

    /**
     * print preOrder traversal of the Treap
     */
    public void preOrder() {
        System.out.print("{");
        printPreOrder(root);
        System.out.print("}");
    }

    private void printPreOrder(TreapNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + ",");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    /**
     * print postOrder traversal of the Treap
     */
    public void postOrder() {
        System.out.print("{");
        printPostOrder(root);
        System.out.print("}");
    }

    private void printPostOrder(TreapNode root) {
        if (root == null) {
            return;
        }
        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.print(root.value + ",");
    }

    /**
     * Search a value in the Treap
     *
     * @param value value to be searched for
     * @return node containing the value
     * null if not found
     */
    public TreapNode search(int value) {
        return searchVal(root, value);
    }

    private TreapNode searchVal(TreapNode root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        } else if (root.value < value) {
            return searchVal(root.right, value);
        } else {
            return searchVal(root.left, value);
        }
    }

    /**
     * find the lowerBound of a value in the Treap
     *
     * @param value value for which lowerBound is to be found
     * @return node which is the lowerBound of the value passed
     */
    public TreapNode lowerBound(int value) {
        TreapNode lowerBoundNode = null;
        TreapNode current = root;

        while (current != null) {
            if (current.value >= value) {
                lowerBoundNode = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return lowerBoundNode;
    }

    /**
     * find the upperBound of a value in the Treap
     *
     * @param value value for which upperBound is to be found
     * @return node which is the upperBound of the value passed
     */
    public TreapNode upperBound(int value) {
        TreapNode upperBoundNode = null;
        TreapNode current = root;

        while (current != null) {
            if (current.value > value) {
                upperBoundNode = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return upperBoundNode;
    }

    /**
     * returns size of the Treap
     */
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    /**
     * returns if Treap is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * returns root node of the Treap
     */
    public TreapNode getRoot() {
        return root;
    }

    /**
     * returns left node of the TreapNode
     */
    public TreapNode getLeft(TreapNode node) {
        return node.left;
    }

    /**
     * returns the right node of the TreapNode
     */
    public TreapNode getRight(TreapNode node) {
        return node.right;
    }

    /**
     * prints the value, priority, size of the subtree of the TreapNode, left TreapNode and right TreapNode of the node
     */
    public String toString(TreapNode node) {
        return "{value : " + node.value + ", priority : " + node.priority + ", subTreeSize = " + node.size + ", left = " + node.left + ", right = " + node.right + "}";
    }
}
