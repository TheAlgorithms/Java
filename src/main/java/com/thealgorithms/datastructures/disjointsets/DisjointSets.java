package com.thealgorithms.datastructures.disjointsets;

public class DisjointSets<T> {

    public Node<T> MakeSet(T x) {
        return new Node<T>(x);
    }

    public Node<T> FindSet(Node<T> node) {
        if (node != node.parent) {
            node.parent = FindSet(node.parent);
        }

        return node.parent;
    }

    public void UnionSet(Node<T> x, Node<T> y) {
        Node<T> nx = FindSet(x);
        Node<T> ny = FindSet(y);

        if (nx == ny) {
            return;
        }
        if (nx.rank > ny.rank) {
            ny.parent = nx;
        } else if (ny.rank > nx.rank) {
            nx.parent = ny;
        } else {
            nx.parent = ny;
            ny.rank++;
        }
    }
}
