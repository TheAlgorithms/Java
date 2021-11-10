package com.thealgorithms.datastructures.disjointsets;

public class Node<T> {

    public int rank;
    public Node<T> parent;
    public T data;

    public Node(T data) {
        this.data = data;
        parent = this;
    }
}
