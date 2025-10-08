package com.thealgorithms.datastructures.tries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Patricia (radix) trie for String keys and generic values.
 *
 * <p>Edges are compressed: each child edge stores a non-empty String label.
 * Operations run in O(L) where L is the key length, with small constant factors
 * from edge-label comparisons.</p>
 *
 * <p>Notes:
 * <ul>
 *   <li>Null keys are not allowed (IllegalArgumentException).</li>
 *   <li>Empty-string key ("") is allowed as a valid key.</li>
 *   <li>Null values are not allowed (IllegalArgumentException).</li>
 * </ul>
 * </p>
 */
public final class PatriciaTrie<V> {

    /** A trie node with compressed outgoing edges (label -> child). */
    private static final class Node<V> {
        Map<String, Node<V>> children = new HashMap<>();
        boolean hasValue;
        V value;
    }

    private final Node<V> root = new Node<>();
    private int size; // number of stored keys

    /** Creates an empty Patricia trie. */
    public PatriciaTrie() {}

    /**
     * Inserts or updates the value associated with {@code key}.
     *
     * @param key   the key (non-null; empty string allowed)
     * @param value the value (non-null)
     * @throws IllegalArgumentException if key or value is null
     */
    public void put(String key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        insert(root, key, value);
    }

    /**
     * Returns the value associated with {@code key}, or {@code null} if absent.
     *
     * @param key the key (non-null)
     * @return the stored value or {@code null} if key not present
     * @throws IllegalArgumentException if key is null
     */
    public V get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        Node<V> n = findNode(root, key);
        return (n != null && n.hasValue) ? n.value : null;
    }

    /**
     * Returns true if the trie contains {@code key}.
     *
     * @param key the key (non-null)
     * @return true if key is present
     * @throws IllegalArgumentException if key is null
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        Node<V> n = findNode(root, key);
        return n != null && n.hasValue;
    }

    /**
     * Removes {@code key} if present.
     *
     * @param key the key (non-null)
     * @return true if the key existed and was removed
     * @throws IllegalArgumentException if key is null
     */
    public boolean remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        return delete(root, key);
    }

    /**
     * Returns true if there exists any key with the given {@code prefix}.
     *
     * @param prefix non-null prefix (empty prefix matches if trie non-empty)
     * @return true if any key starts with {@code prefix}
     * @throws IllegalArgumentException if prefix is null
     */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix must not be null");
        }
        if (prefix.isEmpty()) {
            return size > 0;
        }
        Node<V> n = findPrefixNode(root, prefix);
        return n != null;
    }

    /** Number of stored keys. */
    public int size() {
        return size;
    }

    /** Returns true if no keys are stored. */
    public boolean isEmpty() {
        return size == 0;
    }

    // ---------------- internal helpers ----------------

    private void insert(Node<V> node, String key, V value) {
        // Special case: empty remaining key => store at node
        if (key.isEmpty()) {
            if (!node.hasValue) {
                size++;
            }
            node.hasValue = true;
            node.value = value;
            return;
        }

        // Find a child edge with a non-zero common prefix with 'key'
        for (Map.Entry<String, Node<V>> e : node.children.entrySet()) {
            String edge = e.getKey();
            int cpl = commonPrefixLen(edge, key);
            if (cpl == 0) {
                continue;
            }

            // Case A: Edge fully matches the remaining key (edge == key)
            if (cpl == edge.length() && cpl == key.length()) {
                Node<V> child = e.getValue();
                if (!child.hasValue) {
                    size++;
                }
                child.hasValue = true;
                child.value = value;
                return;
            }

            // Case B: Key is longer (edge is full prefix of key) => descend
            if (cpl == edge.length() && cpl < key.length()) {
                Node<V> child = e.getValue();
                String rest = key.substring(cpl);
                insert(child, rest, value);
                // After recursion, maybe compact child (not required here)
                return;
            }

            // Case C: Edge longer (key is full prefix of edge) OR partial split
            // Need to split the existing edge.
            // Split into 'prefix' (common), and two suffix edges.
            String prefix = edge.substring(0, cpl);
            String edgeSuffix = edge.substring(cpl); // might be non-empty
            String keySuffix = key.substring(cpl);   // might be empty or non-empty

            // Create an intermediate node for 'prefix'
            Node<V> mid = new Node<>();
            node.children.remove(edge);
            node.children.put(prefix, mid);

            // Old child moves under 'edgeSuffix'
            Node<V> oldChild = e.getValue();
            if (!edgeSuffix.isEmpty()) {
                mid.children.put(edgeSuffix, oldChild);
            } else {
                // edgeSuffix empty means 'edge' == 'prefix'; just link child
                // (handled by not adding anything)
                mid.children.put("", oldChild); // should not happen since cpl < edge.length()
            }

            // If keySuffix empty => store value at mid
            if (keySuffix.isEmpty()) {
                if (!mid.hasValue) {
                    size++;
                }
                mid.hasValue = true;
                mid.value = value;
            } else {
                // Add a new leaf under keySuffix
                Node<V> leaf = new Node<>();
                leaf.hasValue = true;
                leaf.value = value;
                mid.children.put(keySuffix, leaf);
            }
            return;
        }

        // No common prefix with any child => add new edge directly
        Node<V> leaf = new Node<>();
        leaf.hasValue = true;
        leaf.value = value;
        node.children.put(key, leaf);
        size++;
    }

    private Node<V> findNode(Node<V> node, String key) {
        if (key.isEmpty()) {
            return node;
        }
        for (Map.Entry<String, Node<V>> e : node.children.entrySet()) {
            String edge = e.getKey();
            int cpl = commonPrefixLen(edge, key);
            if (cpl == 0) {
                continue;
            }
            if (cpl == edge.length()) {
                // Edge fully matches a prefix of key
                String rest = key.substring(cpl);
                return findNode(e.getValue(), rest);
            } else {
                // Partial match but edge not fully consumed => key absent
                return null;
            }
        }
        return null;
    }

    private Node<V> findPrefixNode(Node<V> node, String prefix) {
        if (prefix.isEmpty()) {
            return node;
        }
        for (Map.Entry<String, Node<V>> e : node.children.entrySet()) {
            String edge = e.getKey();
            int cpl = commonPrefixLen(edge, prefix);
            if (cpl == 0) {
                continue;
            }
            if (cpl == prefix.length()) {
                // consumed the whole prefix: prefix exists in this subtree
                return e.getValue();
            }
            if (cpl == edge.length()) {
                // consume edge, continue with remaining prefix
                String rest = prefix.substring(cpl);
                return findPrefixNode(e.getValue(), rest);
            }
            // partial split where neither fully consumed => no such prefix path
            return null;
        }
        return null;
    }

    private boolean delete(Node<V> node, String key) {
        if (key.isEmpty()) {
            if (!node.hasValue) {
                return false;
            }
            node.hasValue = false;
            node.value = null;
            size--;
            // After removing value at this node, maybe merge if only one child
            // (merging handled by caller via cleanup step)
            return true;
        }

        // Find matching child by common prefix
        for (Map.Entry<String, Node<V>> e : node.children.entrySet()) {
            String edge = e.getKey();
            int cpl = commonPrefixLen(edge, key);
            if (cpl == 0) {
                continue;
            }
            if (cpl < edge.length()) {
                // Partial overlap (edge not fully matched) -> key not present
                return false;
            }
            // Edge fully matched; go deeper
            String rest = key.substring(cpl);
            Node<V> child = e.getValue();
            boolean removed = delete(child, rest);
            if (!removed) {
                return false;
            }
            // Cleanup/merge after successful deletion
            mergeIfNeeded(node, edge, child);
            return true;
        }
        return false;
    }

    /**
     * If the child at {@code parent.children[edge]} can be merged up (no value and
     * a single child), compress the two edges into one. Also, if the child has no
     * value and no children, remove it.
     */
    private void mergeIfNeeded(Node<V> parent, String edge, Node<V> child) {
        if (child.hasValue) {
            // Can't merge if child holds a value
            return;
        }
        int deg = child.children.size();
        if (deg == 0) {
            // Remove empty child
            parent.children.remove(edge);
            return;
        }
        if (deg == 1) {
            // Merge child's only edge into parent edge: edge + subEdge
            Map.Entry<String, Node<V>> only = child.children.entrySet().iterator().next();
            String subEdge = only.getKey();
            Node<V> grand = only.getValue();

            parent.children.remove(edge);
            parent.children.put(edge + subEdge, grand);
        }
    }

    /** Returns length of common prefix of a and b (0..min(a.length,b.length)). */
    private static int commonPrefixLen(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return i;
    }

    @Override
    public int hashCode() {
        // not used by algorithms; keep minimal but deterministic with size
        return Objects.hash(size);
    }

    @Override
    public boolean equals(Object obj) {
        // Structural equality is not required; keep reference equality
        return this == obj;
    }
}
