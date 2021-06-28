package DataStructures.Graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * In computer science, a disjoint-set data structure, also called a union–find data structure or merge–find set,
 * is a data structure that stores a collection of disjoint (non-overlapping) sets.
 *
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
public class UnionFind {
    private static class Node {
        long id;

        Node parent;

        long size;

        Node(long id, Node parent, long size) {
            this.id = id;
            this.parent = parent;
            this.size = size;
        }
    }

    private Map<Long, Node> map = new HashMap<>();

    /**
     * Finds the parent of a given node.
     */
    public long find(long p) {
        return map.get(root(p).id).id;
    }

    /**
     * Merges the sets belonging to node p & q
     */
    public void union(long p, long q) {
        Node pRoot = root(p);
        Node qRoot = root(q);

        if (pRoot == qRoot)
            return;

        // limit the height of the tree
        if (pRoot.size > qRoot.size) {
            qRoot.parent = pRoot;
            pRoot.size += qRoot.size;
        } else {
            pRoot.parent = qRoot;
            qRoot.size += pRoot.size;
        }
    }

    private Node root(long p) {
        Node node = map.get(p);

        if (node == null) {
            node = new Node(p, null, 1L);
            map.put(p, node);
            return node;
        }

        while (node.parent != null) {
            Node grandParent = node.parent.parent;

            // path compression
            if (grandParent != null) {
                node.parent = grandParent;
            }

            node = node.parent;
        }

        return node;
    }
}
