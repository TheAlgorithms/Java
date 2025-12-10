package com.thealgorithms.datastructures.disjointsetunion;

/**
 * Disjoint Set Union (DSU), also known as Union-Find, is a data structure that tracks a set of elements
 * partitioned into disjoint (non-overlapping) subsets. It supports two primary operations efficiently:
 *
 * <ul>
 *     <li>Find: Determine which subset a particular element belongs to.</li>
 *     <li>Union: Merge two subsets into a single subset.</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">Disjoint Set Union (Wikipedia)</a>
 */
public class DisjointSetUnion<T> {

    /**
     * Creates a new disjoint set containing the single specified element.
     *
     * @param value the element to be placed in a new singleton set
     * @return a node representing the new set
     */
    public Node<T> makeSet(final T value) {
        return new Node<>(value);
    }

    /**
     * Finds and returns the representative (root) of the set containing the given node.
     * This method applies path compression to flatten the tree structure for future efficiency.
     *
     * @param node the node whose set representative is to be found
     * @return the representative (root) node of the set
     */
    public Node<T> findSet(Node<T> node) {
        if (node != node.parent) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }

    /**
     * Merges the sets containing the two given nodes. Union by rank is used to attach the smaller tree under the larger one.
     * If both sets have the same rank, one becomes the parent and its rank is incremented.
     *
     * @param x a node in the first set
     * @param y a node in the second set
     */
    public void unionSets(Node<T> x, Node<T> y) {
        Node<T> rootX = findSet(x);
        Node<T> rootY = findSet(y);

        if (rootX == rootY) {
            return; // They are already in the same set
        }
        // Merging happens based on rank of node, this is done to avoid long chaining of nodes and reduce time
        // to find root of the component. Idea is to attach small components in big, instead of other way around.
        if (rootX.rank > rootY.rank) {
            rootY.parent = rootX;
        } else if (rootY.rank > rootX.rank) {
            rootX.parent = rootY;
        } else {
            rootY.parent = rootX;
            rootX.rank++;
        }
    }
}
