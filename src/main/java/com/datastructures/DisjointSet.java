package com.datastructures;

import java.io.Serializable;
import java.util.*;

/**
 * An implementation of disjoint-set, represented by rooted trees.
 * <p>
 * Actually, the instance of {@link DisjointSet} is a disjoint-set forests.
 *
 * <p>
 * Disjoint-set operations:
 * <p>
 * 1. quickly unites two sets into a new set, requiring O(1) time.
 * <p>
 * 2. quickly query two elements whether contained in the same set, requiring about O(1) time.
 *
 */
public class DisjointSet<T> implements Serializable {
    private static final long serialVersionUID = 3134700471905625636L;
    private static final String elementKey = "element";

    private final Map<T, Node<T>> nodeMap = new HashMap<>();

    /**
     * Add an element to the disjoint-set forests as a set.
     */
    public void makeSet(T element) {
        checkNotNull(element, elementKey);
        nodeMap.putIfAbsent(element, new Node<>());
    }

    /**
     * Unites the set that contains left and the set that contains right
     * into a new set with the union-by-rank heuristic.
     * <p>
     * Rank is an upper bound on the height of node.
     */
    public void union(T left, T right) {
        checkNotNull(left, elementKey);
        checkNotNull(right, elementKey);

        Node<T> leftNode = nodeMap.get(left),
                rightNode = nodeMap.get(right);

        if (leftNode == null) {
            throw new NoSuchElementException(left.toString());
        }

        if (rightNode == null) {
            throw new NoSuchElementException(right.toString());
        }

        Node<T> leftSet = findSet(leftNode),
                rightSet = findSet(rightNode);

        if (leftSet == rightSet) {
            return;
        }

        if (leftSet.rank < rightSet.rank) {
            leftSet.parent = rightSet;
        } else {
            rightSet.parent = leftSet;
            if (leftSet.rank == rightSet.rank) {
                leftSet.rank++;
            }
        }
    }

    /**
     * Query two elements whether contained in the same set.
     */
    public boolean isConnected(T left, T right) {
        if (left == null || right == null) {
            return false;
        }

        Node<T> leftNode = nodeMap.get(left);
        if (leftNode == null) {
            return false;
        }

        Node<T> rightNode = nodeMap.get(right);
        if (rightNode == null) {
            return false;
        }

        if (leftNode == rightNode) {
            return true;
        }

        return findSet(leftNode) == findSet(rightNode);
    }

    public Collection<Set<T>> toSets() {
        Map<Node<T>, Set<T>> setMap = new HashMap<>();
        for (Map.Entry<T, Node<T>> entry : nodeMap.entrySet()) {
            setMap.computeIfAbsent(findSet(entry.getValue()), k -> new HashSet<>())
                  .add(entry.getKey());
        }
        return setMap.values();
    }

    public void show() {
        toSets().forEach(System.out::println);
    }

    /**
     * Find the set that contains the node, actual is the first node of the set.
     * <p>
     * Backtracking with path compression.
     */
    private Node<T> findSet(Node<T> node) {
        if (node != node.parent) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }

    private static void checkNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg + " must be not null");
        }
    }

    static class Node<T> {
        int rank;
        Node<T> parent;

        Node() {
            parent = this;
        }
    }

}
