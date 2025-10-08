package com.thealgorithms.datastructures.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * Patricia (radix) trie for String keys and generic values.
 *
 * <p>Compressed edges: each child edge stores a non-empty String label.
 * Operations are O(L) where L is the key length.</p>
 *
 * <p>Contracts:
 * <ul>
 *   <li>Null keys are not allowed (IllegalArgumentException).</li>
 *   <li>Empty-string key ("") is allowed as a valid key.</li>
 *   <li>Null values are not allowed (IllegalArgumentException).</li>
 * </ul>
 * </p>
 */
public final class PatriciaTrie<V> {

    /** Node with compressed outgoing edges (label -> child). */
    private static final class Node<V> {
        Map<String, Node<V>> children = new HashMap<>();
        boolean hasValue;
        V value;
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
            if (!root.hasValue) {
                return false;
            }
            root.hasValue = false;
            root.value = null;
            size--;
            return true;
        }
        return removeRecursive(root, key);
    }

    /**
     * Returns true if any key starts with {@code prefix}.
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

    /** True if no keys are stored. */
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
                continue;
            }

            // edge matches the entire key
            if (cpl == edge.length() && cpl == key.length()) {
                Node<V> child = e.getValue();
                if (!child.hasValue) {
                    size++;
                }
                child.hasValue = true;
                child.value = value;
                return;
            }

            // edge is full prefix of key -> go down
            if (cpl == edge.length() && cpl < key.length()) {
                Node<V> child = e.getValue();
                String rest = key.substring(cpl);
                insert(child, rest, value);
                return;
            }

            // split edge (partial overlap or key is prefix of edge)
            String prefix = edge.substring(0, cpl);
            String edgeSuffix = edge.substring(cpl);
            String keySuffix = key.substring(cpl);

            Node<V> mid = new Node<>();
            node.children.remove(edge);
            node.children.put(prefix, mid);

            Node<V> oldChild = e.getValue();
            if (!edgeSuffix.isEmpty()) {
                mid.children.put(edgeSuffix, oldChild);
            } else {
                // Should not occur since cpl < edge.length() for this branch
                mid.children.put("", oldChild);
            }

            if (keySuffix.isEmpty()) {
                if (!mid.hasValue) {
                    size++;
                }
                mid.hasValue = true;
                mid.value = value;
            } else {
                Node<V> leaf = new Node<>();
                leaf.hasValue = true;
                leaf.value = value;
                mid.children.put(keySuffix, leaf);
            }
            return;
        }

        // No common prefix with any child: create a new edge
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
                String rest = key.substring(cpl);
                return findNode(e.getValue(), rest);
            } else {
                // partial match but edge not fully consumed => absent
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
                return e.getValue();
            }
            if (cpl == edge.length()) {
                String rest = prefix.substring(cpl);
                return findPrefixNode(e.getValue(), rest);
            }
            // neither fully consumed -> no such prefix
            return null;
        }
        return null;
    }

    /**
     * Recursive removal + cleanup (merge).
     *
     * <p>On the way back up, if a child has no value and:
     * <ul>
     *   <li>deg == 0: remove it from parent,</li>
     *   <li>deg == 1: merge it with its single child (concatenate edge labels).</li>
     * </ul>
     * </p>
     */
    private boolean removeRecursive(Node<V> parent, String key) {
        // iterate on a snapshot of keys to allow modifications during loop
        String[] keys = parent.children.keySet().toArray(new String[0]);
        for (String edge : keys) {
            int cpl = commonPrefixLen(edge, key);
            if (cpl == 0) {
                continue;
            }

            Node<V> child = parent.children.get(edge);

            // partial overlap with edge => key doesn't exist in this branch
            if (cpl < edge.length()) {
                return false;
            }

            String rest = key.substring(cpl);
            boolean removed;
            if (rest.isEmpty()) {
                if (!child.hasValue) {
                    return false;
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

            // post-recursion cleanup of child
            if (!child.hasValue) {
                int deg = child.children.size();
                if (deg == 0) {
                    // fully remove empty child (fixes leaf removal case)
                    parent.children.remove(edge);
                } else if (deg == 1) {
                    // merge pass-through child with its only grandchild
                    Map.Entry<String, Node<V>> only =
                        child.children.entrySet().iterator().next();
                    String grandEdge = only.getKey();
                    Node<V> grand = only.getValue();

                    parent.children.remove(edge);
                    parent.children.put(edge + grandEdge, grand);
                }
            }
            return true; // processed the matching path
        }
        return false;
    }

    /** Length of common prefix of a and b. */
    private static int commonPrefixLen(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return i;
    }
}
