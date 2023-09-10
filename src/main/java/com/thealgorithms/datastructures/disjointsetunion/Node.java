package com.thealgorithms.datastructures.disjointsetunion;

public class Node<T> {

    /**
     * The rank of the node, used for optimizing union operations.
     */
    public int rank;

    /**
     * Reference to the parent node in the set.
     * Initially, a node is its own parent (represents a singleton set).
     */
    public Node<T> parent;

    /**
     * The data element associated with the node.
     */
    public T data;

    public Node(final T data) {
        this.data = data;
        parent = this; // Initially, a node is its own parent.
    }
}
