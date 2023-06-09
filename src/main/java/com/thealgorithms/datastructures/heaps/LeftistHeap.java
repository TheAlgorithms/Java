package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;

/*
 * This is a leftist heap that follows the same operations as a
 * binary min heap, but may be unbalanced at times and follows a
 * leftist property, in which the left side is more heavy on the
 * right based on the null-path length (npl) values.
 *
 * Source: https://iq.opengenus.org/leftist-heap/
 *
 */

public class LeftistHeap {
    private class Node {
        private int element, npl;
        private Node left, right;

        // Node constructor setting the data element and left/right pointers to null
        private Node(int element) {
            this.element = element;
            left = right = null;
            npl = 0;
        }
    }

    private Node root;

    // Constructor
    public LeftistHeap() {
        root = null;
    }

    // Checks if heap is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Resets structure to initial state
    public void clear() {
        // We will put head is null
        root = null;
    }

    // Merge function that merges the contents of another leftist heap with the
    // current one
    public void merge(LeftistHeap h1) {
        // If the present function is rhs then we ignore the merge
        root = merge(root, h1.root);
        h1.root = null;
    }

    // Function merge with two Nodes a and b
    public Node merge(Node a, Node b) {
        if (a == null) return b;

        if (b == null) return a;

        // Violates leftist property, so must do a swap
        if (a.element > b.element) {
            Node temp = a;
            a = b;
            b = temp;
        }

        // Now we call the function merge to merge a and b
        a.right = merge(a.right, b);

        // Violates leftist property so must swap here
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

    // Function insert. Uses the merge function to add the data
    public void insert(int a) {
        root = merge(new Node(a), root);
    }

    // Returns and removes the minimum element in the heap
    public int extract_min() {
        // If is empty return -1
        if (isEmpty()) return -1;

        int min = root.element;
        root = merge(root.left, root.right);
        return min;
    }

    // Function returning a list of an in order traversal of the data structure
    public ArrayList<Integer> in_order() {
        ArrayList<Integer> lst = new ArrayList<>();
        in_order_aux(root, lst);
        return new ArrayList<>(lst);
    }

    // Auxiliary function for in_order
    private void in_order_aux(Node n, ArrayList<Integer> lst) {
        if (n == null) return;
        in_order_aux(n.left, lst);
        lst.add(n.element);
        in_order_aux(n.right, lst);
    }
}