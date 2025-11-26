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
    private int currentSize; // Correctly track the total number of key-value pairs
    private final LinkedList<K, V>[] buckets;

    /**
     * Constructs a HashMap with the specified hash size.
     *
     * @param hashSize the number of buckets in the hash map
     */
    @SuppressWarnings("unchecked")
    public HashMap(int hashSize) {
        this.hashSize = hashSize;
        this.currentSize = 0;
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
            return 0; // Use bucket 0 for null keys
        }
        int hash = key.hashCode() % hashSize;
        // Ensure the hash index is non-negative
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
        boolean keyExisted = buckets[hash].insert(key, value);
        
        // Only increment size if a new key was inserted (not an update)
        if (!keyExisted) {
            currentSize++;
        }
    }

    /**
     * Deletes the key-value pair associated with the specified key from the hash map.
     *
     * @param key the key whose key-value pair is to be deleted
     */
    public void delete(K key) {
        int hash = computeHash(key);
        boolean deletedSuccessfully = buckets[hash].delete(key);

        // Only decrement size if deletion was successful
        if (deletedSuccessfully) {
            currentSize--;
        }
    }

    /**
     * Searches for the value associated with the specified key in the hash map.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the key does not exist
     */
    public V search(K key) {
        int hash = computeHash(key);
        // Changed LinkedList to return the value directly for better encapsulation
        return buckets[hash].findValue(key);
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
     * Clears the contents of the hash map by reinitializing each bucket and resetting the size.
     */
    public void clear() {
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        currentSize = 0; // Reset size
    }

    /**
     * Gets the number of key-value pairs in the hash map.
     * Corrected to return the stored 'currentSize' in O(1) time.
     *
     * @return the number of key-value pairs in the hash map
     */
    public int size() {
        return currentSize;
    }

    // --- NESTED LINKED LIST CLASS (Collision Handler) ---

    /**
     * A nested static class that represents a linked list used for separate chaining in the hash map.
     */
    public static class LinkedList<K, V> {
        private Node<K, V> head;

        /**
         * Inserts the specified key-value pair into the linked list.
         * If the key already exists, the value is updated.
         *
         * @param key   the key to be inserted
         * @param value the value to be associated with the key
         * @return true if an existing key was updated, false if a new node was created.
         */
        public boolean insert(K key, V value) {
            Node<K, V> existingNode = findNode(key);
            if (existingNode != null) {
                existingNode.setValue(value);
                return true; // Key existed, value updated
            } else {
                Node<K, V> newNode = new Node<>(key, value);
                if (isEmpty()) {
                    head = newNode;
                } else {
                    Node<K, V> temp = head; // Start search from head
                    while (temp.getNext() != null) {
                        temp = temp.getNext();
                    }
                    temp.setNext(newNode);
                }
                return false; // New key added
            }
        }

        /**
         * Finds the node associated with the specified key.
         *
         * @param key the key to search for
         * @return the node associated with the specified key, or null if not found
         */
        private Node<K, V> findNode(K key) {
            Node<K, V> temp = head;
            while (temp != null) {
                // Robust key comparison, handles null keys correctly
                if ((key == null && temp.getKey() == null) || (temp.getKey() != null && temp.getKey().equals(key))) {
                    return temp;
                }
                temp = temp.getNext();
            }
            return null;
        }

        /**
         * Finds the value associated with the specified key.
         *
         * @param key the key to search for
         * @return the value associated with the specified key, or null if not found
         */
        public V findValue(K key) {
            Node<K, V> node = findNode(key);
            return node != null ? node.getValue() : null;
        }


        /**
         * Deletes the node associated with the specified key from the linked list.
         *
         * @param key the key whose associated node is to be deleted
         * @return true if the node was successfully deleted, false otherwise.
         */
        public boolean delete(K key) {
            if (isEmpty()) {
                return false;
            }

            // Handle head deletion
            if ((key == null && head.getKey() == null) || (head.getKey() != null && head.getKey().equals(key))) {
                head = head.getNext();
                return true;
            }

            // Traverse to find node to delete
            Node<K, V> current = head;
            while (current.getNext() != null) {
                Node<K, V> nextNode = current.getNext();
                if ((key == null && nextNode.getKey() == null) || (nextNode.getKey() != null && nextNode.getKey().equals(key))) {
                    current.setNext(nextNode.getNext());
                    return true;
                }
                current = current.getNext();
            }
            return false; // Key not found
        }

        /**
         * Displays the contents of the linked list as a string.
         *
         * @return a string representation of the linked list
         */
        public String display() {
            StringBuilder sb = new StringBuilder();
            Node<K, V> node = head;
            while (node != null) {
                sb.append(node.getKey()).append("=").append(node.getValue());
                node = node.getNext();
                if (node != null) {
                    sb.append(" -> ");
                }
            }
            return sb.length() > 0 ? sb.toString() : "empty";
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

    // --- NESTED NODE CLASS (Key-Value Pair) ---

    /**
     * A nested static class representing a node (Entry) in the linked list.
     */
    public static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
}
