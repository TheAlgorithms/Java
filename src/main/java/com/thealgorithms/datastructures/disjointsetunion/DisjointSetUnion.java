package com.thealgorithms.datastructures.disjointsetunion;

/**
 * Disjoint Set Union or DSU is useful for solving problems related to connected components,
 * cycle detection in graphs, and maintaining relationships in disjoint sets of data.
 * It is commonly employed in graph algorithms and problems.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">Disjoint Set Union</a>
 */
public class DisjointSetUnion<T> {

    /**
     * Creates a new node of DSU with parent initialised as same node
     */
    public Node<T> makeSet(final T x) {
        return new Node<T>(x);
    }

    /**
     * Finds and returns the representative (root) element of the set to which a given element belongs.
     * This operation uses path compression to optimize future findSet operations.
     */
    public Node<T> findSet(Node<T> node) {
        while (node != node.parent) {
            node = node.parent;
        }
        return node;
    }

    /**
     * Unions two sets by merging their representative elements. The merge is performed based on the rank of each set
     * to ensure efficient merging and path compression to optimize future findSet operations.
     */
    public void unionSets(final Node<T> x, final Node<T> y) {
        Node<T> nx = findSet(x);
        Node<T> ny = findSet(y);

        if (nx == ny) {
            return; // Both elements already belong to the same set.
        }
        // Merging happens based on rank of node, this is done to avoid long chaining of nodes and reduce time
        // to find root of the component. Idea is to attach small components in big, instead of other way around.
        if (nx.rank > ny.rank) {
            ny.parent = nx;
        } else if (ny.rank > nx.rank) {
            nx.parent = ny;
        } else {
            // Both sets have the same rank; choose one as the parent and increment the rank.
            ny.parent = nx;
            nx.rank++;
        }
    }
}
