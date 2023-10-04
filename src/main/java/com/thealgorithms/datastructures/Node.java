package com.thealgorithms.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private final T value;
    private final List<Node<T>> children;

    public Node(final T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Node(final T value, final List<Node<T>> children) {
        this.value = value;
        this.children = children;
    }

    public T getValue() {
        return value;
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}
