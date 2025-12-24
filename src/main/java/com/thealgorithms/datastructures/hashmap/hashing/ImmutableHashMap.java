package com.thealgorithms.datastructures.hashmap.hashing;

/**
 * Immutable HashMap implementation using separate chaining.
 *
 * <p>This HashMap does not allow modification of existing instances.
 * Any update operation returns a new ImmutableHashMap.
 *
 * @param <K> key type
 * @param <V> value type
 */
public final class ImmutableHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private final Node<K, V>[] table;
    private final int size;

    /**
     * Private constructor to enforce immutability.
     */
    private ImmutableHashMap(Node<K, V>[] table, int size) {
        this.table = table;
        this.size = size;
    }

    /**
     * Creates an empty ImmutableHashMap.
     *
     * @param <K> key type
     * @param <V> value type
     * @return empty ImmutableHashMap
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <K, V> ImmutableHashMap<K, V> empty() {
        Node<K, V>[] table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        return new ImmutableHashMap<>(table, 0);
    }

    /**
     * Returns a new ImmutableHashMap with the given key-value pair added.
     *
     * @param key key to add
     * @param value value to associate
     * @return new ImmutableHashMap instance
     */
    public ImmutableHashMap<K, V> put(K key, V value) {
        Node<K, V>[] newTable = table.clone();
        int index = hash(key);

        newTable[index] = new Node<>(key, value, newTable[index]);
        return new ImmutableHashMap<>(newTable, size + 1);
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key key to search
     * @return value if found, otherwise null
     */
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Checks whether the given key exists in the map.
     *
     * @param key key to check
     * @return true if key exists, false otherwise
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of key-value pairs.
     *
     * @return size of the map
     */
    public int size() {
        return size;
    }

    /**
     * Computes hash index for a given key.
     */
    private int hash(K key) {
        return key == null ? 0 : (key.hashCode() & Integer.MAX_VALUE) % table.length;
    }

    /**
     * Node class for separate chaining.
     */
    private static final class Node<K, V> {

        private final K key;
        private final V value;
        private final Node<K, V> next;

        private Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
