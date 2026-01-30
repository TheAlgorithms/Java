package com.thealgorithms.datastructures.disjointsetunion;

/**
 * Disjoint Set Union (DSU) with Union by Size.
 * This data structure tracks a set of elements partitioned into disjoint (non-overlapping) subsets.
 * It supports two primary operations efficiently:
 *
 * <ul>
 *     <li>Find: Determine which subset a particular element belongs to.</li>
 *     <li>Union: Merge two subsets into a single subset using union by size.</li>
 * </ul>
 *
 * Union by size always attaches the smaller tree under the root of the larger tree.
 * This helps keep the tree shallow, improving the efficiency of find operations.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">Disjoint Set Union (Wikipedia)</a>
 */
public class DisjointSetUnionBySize<T> {
    /**
     * Node class for DSU by size.
     * Each node keeps track of its parent and the size of the set it represents.
     */
    public static class Node<T> {
        public T value;
        public Node<T> parent;
        public int size; // size of the set

        public Node(T value) {
            this.value = value;
            this.parent = this;
            this.size = 1; // initially, the set size is 1
        }
    }

    /**
     * Creates a new disjoint set containing the single specified element.
     * @param value the element to be placed in a new singleton set
     * @return a node representing the new set
     */
    public Node<T> makeSet(final T value) {
        return new Node<>(value);
    }

    /**
     * Finds and returns the representative (root) of the set containing the given node.
     * This method applies path compression to flatten the tree structure for future efficiency.
     * @param node the node whose set representative is to be found
     * @return the representative (root) node of the set
     */
    public Node<T> findSet(Node<T> node) {
        if (node != node.parent) {
            node.parent = findSet(node.parent); // path compression
        }
        return node.parent;
    }

    /**
     * Merges the sets containing the two given nodes using union by size.
     * The root of the smaller set is attached to the root of the larger set.
     * @param x a node in the first set
     * @param y a node in the second set
     */
    public void unionSets(Node<T> x, Node<T> y) {
        Node<T> rootX = findSet(x);
        Node<T> rootY = findSet(y);

        if (rootX == rootY) {
            return; // They are already in the same set
        }
        // Union by size: attach smaller tree under the larger one
        if (rootX.size < rootY.size) {
            rootX.parent = rootY;
            rootY.size += rootX.size; // update size
        } else {
            rootY.parent = rootX;
            rootX.size += rootY.size; // update size
        }
    }
}
// This implementation uses union by size instead of union by rank.
// The size field tracks the number of elements in each set.
// When two sets are merged, the smaller set is always attached to the larger set's root.
// This helps keep the tree shallow and improves the efficiency of find operations.
