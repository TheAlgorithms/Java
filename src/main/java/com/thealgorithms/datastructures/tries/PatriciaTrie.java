package com.thealgorithms.datastructures.tries;

// FIX: Placed each import on its own line for proper formatting.
import java.util.HashMap;
import java.util.Map;

/**
 * Patricia (radix) trie for String keys and generic values.
 *
 * <p>A Patricia Trie is a memory-optimized trie where each node with only one
 * child is merged with its child. This results in edges being labeled with
 * strings instead of single characters.</p>
 *
 * <p>Operations are O(L) where L is the key length.</p>
 *
 * <p>Contracts:
 * <ul>
 * <li>Null keys are not allowed (IllegalArgumentException).</li>
 * <li>The empty-string key ("") is a valid key.</li>
 * <li>Null values are not allowed (IllegalArgumentException).</li>
 * </ul>
 * </p>
 */
// FIX: Added a newline after the Javadoc for proper formatting.
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
        // The empty key is a special case as it's stored on the root node.
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
                continue; // No common prefix, check next child.
            }

            // Case 1: The entire edge and key match perfectly.
            // e.g., edge="apple", key="apple". Update the value at the child node.
            if (cpl == edge.length() && cpl == key.length()) {
                Node<V> child = e.getValue();
                if (!child.hasValue) {
                    size++;
                }
                child.hasValue = true;
                child.value = value;
                return;
            }

            // Case 2: The edge is a prefix of the key.
            // e.g., edge="apple", key="applepie". Recurse on the child node.
            if (cpl == edge.length() && cpl < key.length()) {
                Node<V> child = e.getValue();
                String rest = key.substring(cpl);
                insert(child, rest, value);
                return;
            }

            // Case 3: The key and edge partially match, requiring a split.
            // e.g., edge="romane", key="romanus" -> split at "roman".
            String prefix = edge.substring(0, cpl);
            String edgeSuffix = edge.substring(cpl);
            String keySuffix = key.substring(cpl);

            Node<V> mid = new Node<>();
            node.children.remove(edge);
            node.children.put(prefix, mid);

            // The old child is re-attached to the new middle node.
            Node<V> oldChild = e.getValue();
            mid.children.put(edgeSuffix, oldChild);

            if (keySuffix.isEmpty()) {
                // The new key ends at the split point, e.g., inserting "roman"
                if (!mid.hasValue) {
                    size++;
                }
                mid.hasValue = true;
                mid.value = value;
            } else {
                // The new key branches off after the split point, e.g., "romanus"
                Node<V> leaf = new Node<>();
                leaf.hasValue = true;
                leaf.value = value;
                mid.children.put(keySuffix, leaf);
                // FIX: This was the main bug. A new key was added, but size was not incremented.
                size++;
            }
            return;
        }

        // Case 4: No existing edge shares a prefix. Create a new one.
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

    /**
     * Recursive removal with cleanup (merging pass-through nodes).
     */
    private boolean removeRecursive(Node<V> parent, String key) {
        // Iterate on a snapshot to allow modification during the loop.
        for (Map.Entry<String, Node<V>> entry : new HashMap<>(parent.children).entrySet()) {
            String edge = entry.getKey();
            Node<V> child = entry.getValue();

            if (!key.startsWith(edge)) {
                continue;
            }

            String rest = key.substring(edge.length());
            boolean removed;
            if (rest.isEmpty()) {
                // This is the node to delete.
                if (!child.hasValue) {
                    return false; // Key doesn't actually exist.
                }
                child.hasValue = false;
                child.value = null;
                size--;
                removed = true;
            } else {
                // Continue search down the tree.
                removed = removeRecursive(child, rest);
            }

            if (!removed) {
                return false;
            }

            // Post-recursion cleanup: merge nodes if necessary.
            if (!child.hasValue) {
                int childDegree = child.children.size();
                if (childDegree == 0) {
                    // If child is now a valueless leaf, remove it.
                    parent.children.remove(edge);
                } else if (childDegree == 1) {
                    // If child is a pass-through node, merge it with its own child.
                    Map.Entry<String, Node<V>> grandchildEntry = child.children.entrySet().iterator().next();
                    String grandEdge = grandchildEntry.getKey();
                    Node<V> grandchild = grandchildEntry.getValue();

                    parent.children.remove(edge);
                    parent.children.put(edge + grandEdge, grandchild);
                }
            }
            return true; // Key was found and processed in this path.
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
