package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;

/**
 * This class implements a Leftist Heap, which is a type of priority queue
 * that follows similar operations to a binary min-heap but allows for
 * unbalanced structures based on the leftist property.
 *
 * <p>
 * A Leftist Heap maintains the leftist property, which ensures that the
 * left subtree is heavier than the right subtree based on the
 * null-path length (npl) values. This allows for efficient merging
 * of heaps and supports operations like insertion, extraction of
 * the minimum element, and in-order traversal.
 * </p>
 *
 * <p>
 * For more information on Leftist Heaps, visit:
 * <a href="https://iq.opengenus.org/leftist-heap/">OpenGenus</a>
 * </p>
 */
public class LeftistHeap {
    // Node class representing each element in the Leftist Heap
    private static final class Node {
        private final int element;
        private int npl;
        private Node left;
        private Node right;

        // Node constructor that initializes the element and sets child pointers to null
        private Node(int element) {
            this.element = element;
            left = null;
            right = null;
            npl = 0;
        }
    }

    private Node root;

    // Constructor initializing an empty Leftist Heap
    public LeftistHeap() {
        root = null;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty; false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Resets the heap to its initial state, effectively clearing all elements.
     */
    public void clear() {
        root = null; // Set root to null to clear the heap
    }

    /**
     * Merges the contents of another Leftist Heap into this one.
     *
     * @param h1 the LeftistHeap to be merged into this heap
     */
    public void merge(LeftistHeap h1) {
        // Merge the current heap with the provided heap and set the provided heap's root to null
        root = merge(root, h1.root);
        h1.root = null;
    }

    /**
     * Merges two nodes, maintaining the leftist property.
     *
     * @param a the first node
     * @param b the second node
     * @return the merged node maintaining the leftist property
     */
    public Node merge(Node a, Node b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        // Ensure that the leftist property is maintained
        if (a.element > b.element) {
            Node temp = a;
            a = b;
            b = temp;
        }

        // Merge the right child of node a with node b
        a.right = merge(a.right, b);

        // If left child is null, make right child the left child
        if (a.left == null) {
            a.left = a.right;
            a.right = null;
        } else {
            if (a.left.npl < a.right.npl) {
                Node temp = a.left;
                a.left = a.right;
                a.right = temp;
            }
            a.npl = a.right.npl + 1;
        }
        return a;
    }

    /**
     * Inserts a new element into the Leftist Heap.
     *
     * @param a the element to be inserted
     */
    public void insert(int a) {
        root = merge(new Node(a), root);
    }

    /**
     * Extracts and removes the minimum element from the heap.
     *
     * @return the minimum element in the heap, or -1 if the heap is empty
     */
    public int extractMin() {
        if (isEmpty()) {
            return -1;
        }

        int min = root.element;
        root = merge(root.left, root.right);
        return min;
    }

    /**
     * Returns a list of the elements in the heap in in-order traversal.
     *
     * @return an ArrayList containing the elements in in-order
     */
    public ArrayList<Integer> inOrder() {
        ArrayList<Integer> lst = new ArrayList<>();
        inOrderAux(root, lst);
        return new ArrayList<>(lst);
    }

    /**
     * Auxiliary function for in-order traversal
     *
     * @param n the current node
     * @param lst the list to store the elements in in-order
     */
    private void inOrderAux(Node n, ArrayList<Integer> lst) {
        if (n == null) {
            return;
        }
        inOrderAux(n.left, lst);
        lst.add(n.element);
        inOrderAux(n.right, lst);
    }
}
