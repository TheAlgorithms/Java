package com.thealgorithms.datastructures.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * Patricia (radix) trie for String keys and generic values.
 *
 * <p>A Patricia Trie is a memory-optimized trie where nodes with a single child
 * are merged, so edges are labeled with strings instead of single characters.</p>
 *
 * <p>Operations are O(L) where L is the key length.</p>
 *
 * <p>Contracts:
 * <ul>
 *   <li>Null keys are not allowed (IllegalArgumentException).</li>
 *   <li>The empty-string key ("") is a valid key.</li>
 *   <li>Null values are not allowed (IllegalArgumentException).</li>
 * </ul>
 * </p>
 */
public final class PatriciaTrie<V> {

    /** Node with compressed outgoing edges (label -> child). */
    private static final class Node<V> {
        private final Map<String, Node<V>> children = new HashMap<>();
        private boolean hasValue;
        private V value;
    }

    private final Node<V> root = new Node<>();
    private int size; // number of stored keys

    /** Creates an empty Patricia trie. */
    public PatriciaTrie() {
    }
    /**
     * Inserts or updates the value associated with {@code key}.
     *
     * @param key   non-null key (empty allowed)
     * @param value non-null value
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
     * @param key non-null key
     * @return stored value or null
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
     * @param key non-null key
     * @return true if the key is present
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
     * <p>Also compacts pass-through nodes (no value + single child) by concatenating
     * edge labels to keep the trie compressed.</p>
     *
     * @param key non-null key
     * @return true if the key existed and was removed
     */
    public boolean remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        if (key.isEmpty()) {
            if (root.hasValue) {
                root.hasValue = false;
                root.value = null;
                size--;
                return true;
            }
            return false;
        }
        return removeRecursive(root, key);
    }

    /**
     * Returns true if any key in the trie starts with {@code prefix}.
     *
     * @param prefix non-null prefix
     * @return true if a key starts with the given prefix
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

    /** Returns the number of stored keys. */
    public int size() {
        return size;
    }

    /** Returns true if no keys are stored. */
    public boolean isEmpty() {
        return size == 0;
    }

    // ----------------------------------------------------------------------
    // Internal helpers
    // ----------------------------------------------------------------------

    private void insert(Node<V> node, String key, V value) {
        if (key.isEmpty()) {
            if (!node.hasValue) {
                size++;
            }
            node.hasValue = true;
            node.value = value;
            return;
        }

        for (Map.Entry<String, Node<V>> e : node.children.entrySet()) {
            String edge = e.getKey();
            int cpl = commonPrefixLen(edge, key);

            if (cpl == 0) {
                continue; // No common prefix, try next edge.
            }

            // Case 1: edge == key
            if (cpl == edge.length() && cpl == key.length()) {
                Node<V> child = e.getValue();
                if (!child.hasValue) {
                    size++;
                }
                child.hasValue = true;
                child.value = value;
                return;
            }

            // Case 2: edge is prefix of key
            if (cpl == edge.length() && cpl < key.length()) {
                Node<V> child = e.getValue();
                String rest = key.substring(cpl);
                insert(child, rest, value);
                return;
            }

            // Case 3: partial overlap → split
            String prefix = edge.substring(0, cpl);
            String edgeSuffix = edge.substring(cpl);
            String keySuffix = key.substring(cpl);

            Node<V> mid = new Node<>();
            node.children.remove(edge);
            node.children.put(prefix, mid);

            // Reattach old child under the split node
            Node<V> oldChild = e.getValue();
            mid.children.put(edgeSuffix, oldChild);

            if (keySuffix.isEmpty()) {
                // New key ends at split
                if (!mid.hasValue) {
                    size++;
                }
                mid.hasValue = true;
                mid.value = value;
            } else {
                // New branch after split
                Node<V> leaf = new Node<>();
                leaf.hasValue = true;
                leaf.value = value;
                mid.children.put(keySuffix, leaf);
                size++; // important: new key added
            }
            return;
        }

        // Case 4: no shared prefix; create a new edge
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
            if (key.startsWith(edge)) {
                String rest = key.substring(edge.length());
                return findNode(e.getValue(), rest);
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
            if (prefix.startsWith(edge)) {
                String rest = prefix.substring(edge.length());
                return findPrefixNode(e.getValue(), rest);
            }
            if (edge.startsWith(prefix)) {
                return e.getValue();
            }
        }
        return null;
    }

    /** Recursive removal with cleanup (merging pass-through nodes). */
    private boolean removeRecursive(Node<V> parent, String key) {
        // Iterate on a snapshot to allow modification during the loop
        for (Map.Entry<String, Node<V>> entry : new HashMap<>(parent.children).entrySet()) {
            String edge = entry.getKey();
            Node<V> child = entry.getValue();

            if (!key.startsWith(edge)) {
                continue;
            }

            String rest = key.substring(edge.length());
            boolean removed;
            if (rest.isEmpty()) {
                if (!child.hasValue) {
                    return false; // key not present
                }
                child.hasValue = false;
                child.value = null;
                size--;
                removed = true;
            } else {
                removed = removeRecursive(child, rest);
            }

            if (!removed) {
                return false;
            }

            // Cleanup/compaction
            if (!child.hasValue) {
                int degree = child.children.size();
                if (degree == 0) {
                    parent.children.remove(edge);
                } else if (degree == 1) {
                    Map.Entry<String, Node<V>> gc = child.children.entrySet().iterator().next();
                    String grandEdge = gc.getKey();
                    Node<V> grandchild = gc.getValue();
                    parent.children.remove(edge);
                    parent.children.put(edge + grandEdge, grandchild);
                }
            }
            return true; // processed on this path
        }
        return false;
    }

    /** Returns the length of the common prefix of two strings. */
    private static int commonPrefixLen(String a, String b) {
        int n = Math.min(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return n;
    }
}
