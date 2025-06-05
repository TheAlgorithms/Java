package com.thealgorithms.datastructures.hashmap.hashing;

/**
 * A generic HashMap implementation that uses separate chaining with linked lists
 * to handle collisions. The class supports basic operations such as insert, delete,
 * and search, as well as displaying the contents of the hash map.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
@SuppressWarnings("rawtypes")
public class HashMap<K, V> {
    private final int hashSize;
    private final LinkedList<K, V>[] buckets;

    /**
     * Constructs a HashMap with the specified hash size.
     *
     * @param hashSize the number of buckets in the hash map
     */
    @SuppressWarnings("unchecked")
    public HashMap(int hashSize) {
        this.hashSize = hashSize;
        // Safe to suppress warning because we are creating an array of generic type
        this.buckets = new LinkedList[hashSize];
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Computes the hash code for the specified key.
     * Null keys are hashed to bucket 0.
     *
     * @param key the key for which the hash code is to be computed
     * @return the hash code corresponding to the key
     */
    private int computeHash(K key) {
        if (key == null) {
            return 0; // Use a special bucket (e.g., bucket 0) for null keys
        }
        int hash = key.hashCode() % hashSize;
        return hash < 0 ? hash + hashSize : hash;
    }

    /**
     * Inserts the specified key-value pair into the hash map.
     * If the key already exists, the value is updated.
     *
     * @param key   the key to be inserted
     * @param value the value to be associated with the key
     */
    public void insert(K key, V value) {
        int hash = computeHash(key);
        buckets[hash].insert(key, value);
    }

    /**
     * Deletes the key-value pair associated with the specified key from the hash map.
     *
     * @param key the key whose key-value pair is to be deleted
     */
    public void delete(K key) {
        int hash = computeHash(key);
        buckets[hash].delete(key);
    }

    /**
     * Searches for the value associated with the specified key in the hash map.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the key does not exist
     */
    public V search(K key) {
        int hash = computeHash(key);
        Node<K, V> node = buckets[hash].findKey(key);
        return node != null ? node.getValue() : null;
    }

    /**
     * Displays the contents of the hash map, showing each bucket and its key-value pairs.
     */
    public void display() {
        for (int i = 0; i < hashSize; i++) {
            System.out.printf("Bucket %d: %s%n", i, buckets[i].display());
        }
    }

    /**
     * Clears the contents of the hash map by reinitializing each bucket.
     */
    public void clear() {
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Gets the number of key-value pairs in the hash map.
     *
     * @return the number of key-value pairs in the hash map
     */
    public int size() {
        int size = 0;
        for (int i = 0; i < hashSize; i++) {
            size += buckets[i].isEmpty() ? 0 : 1;
        }
        return size;
    }

    /**
     * A nested static class that represents a linked list used for separate chaining in the hash map.
     *
     * @param <K> the type of keys maintained by this linked list
     * @param <V> the type of mapped values
     */
    public static class LinkedList<K, V> {
        private Node<K, V> head;

        /**
         * Inserts the specified key-value pair into the linked list.
         * If the linked list is empty, the pair becomes the head.
         * Otherwise, the pair is added to the end of the list.
         *
         * @param key   the key to be inserted
         * @param value the value to be associated with the key
         */
        public void insert(K key, V value) {
            Node<K, V> existingNode = findKey(key);
            if (existingNode != null) {
                existingNode.setValue(value); // Update the value, even if it's null
            } else {
                if (isEmpty()) {
                    head = new Node<>(key, value);
                } else {
                    Node<K, V> temp = findEnd(head);
                    temp.setNext(new Node<>(key, value));
                }
            }
        }

        /**
         * Finds the last node in the linked list.
         *
         * @param node the starting node
         * @return the last node in the linked list
         */
        private Node<K, V> findEnd(Node<K, V> node) {
            while (node.getNext() != null) {
                node = node.getNext();
            }
            return node;
        }

        /**
         * Finds the node associated with the specified key in the linked list.
         *
         * @param key the key to search for
         * @return the node associated with the specified key, or null if not found
         */
        public Node<K, V> findKey(K key) {
            Node<K, V> temp = head;
            while (temp != null) {
                if ((key == null && temp.getKey() == null) || (temp.getKey() != null && temp.getKey().equals(key))) {
                    return temp;
                }
                temp = temp.getNext();
            }
            return null;
        }

        /**
         * Deletes the node associated with the specified key from the linked list.
         * Handles the case where the key could be null.
         *
         * @param key the key whose associated node is to be deleted
         */
        public void delete(K key) {
            if (isEmpty()) {
                return;
            }

            // Handle the case where the head node has the key to delete
            if ((key == null && head.getKey() == null) || (head.getKey() != null && head.getKey().equals(key))) {
                head = head.getNext();
                return;
            }

            // Traverse the list to find and delete the node
            Node<K, V> current = head;
            while (current.getNext() != null) {
                if ((key == null && current.getNext().getKey() == null) || (current.getNext().getKey() != null && current.getNext().getKey().equals(key))) {
                    current.setNext(current.getNext().getNext());
                    return;
                }
                current = current.getNext();
            }
        }

        /**
         * Displays the contents of the linked list as a string.
         *
         * @return a string representation of the linked list
         */
        public String display() {
            return display(head);
        }

        /**
         * Constructs a string representation of the linked list non-recursively.
         *
         * @param node the starting node
         * @return a string representation of the linked list starting from the given node
         */
        private String display(Node<K, V> node) {
            StringBuilder sb = new StringBuilder();
            while (node != null) {
                sb.append(node.getKey()).append("=").append(node.getValue());
                node = node.getNext();
                if (node != null) {
                    sb.append(" -> ");
                }
            }
            return sb.toString().isEmpty() ? "null" : sb.toString();
        }

        /**
         * Checks if the linked list is empty.
         *
         * @return true if the linked list is empty, false otherwise
         */
        public boolean isEmpty() {
            return head == null;
        }
    }

    /**
     * A nested static class representing a node in the linked list.
     *
     * @param <K> the type of key maintained by this node
     * @param <V> the type of value maintained by this node
     */
    public static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        /**
         * Constructs a Node with the specified key and value.
         *
         * @param key   the key associated with this node
         * @param value the value associated with this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Gets the key associated with this node.
         *
         * @return the key associated with this node
         */
        public K getKey() {
            return key;
        }

        /**
         * Gets the value associated with this node.
         *
         * @return the value associated with this node
         */
        public V getValue() {
            return value;
        }

        public void setValue(V value) { // This method allows updating the value
            this.value = value;
        }

        /**
         * Gets the next node in the linked list.
         *
         * @return the next node in the linked list
         */
        public Node<K, V> getNext() {
            return next;
        }

        /**
         * Sets the next node in the linked list.
         *
         * @param next the next node to be linked
         */
        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
}
