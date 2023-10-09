package com.thealgorithms.datastructures.hashmap.hashing;

/**
 * A simple hash map implementation using separate chaining.
 */
public class HashMap {

    private final int tableSize;
    private final LinkedList[] buckets;

    /**
     * Constructs a new HashMap with the specified table size.
     *
     * @param tableSize The size of the hash map.
     */
    public HashMap(int tableSize) {
        buckets = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            buckets[i] = new LinkedList();
        }
        this.tableSize = tableSize;
    }

    /**
     * Hashes an integer key to determine the bucket index.
     *
     * @param key The integer key to be hashed.
     * @return The index of the bucket where the key belongs.
     */
    public int hashing(int key) {
        int hash = key % tableSize;
        if (hash < 0) {
            hash += tableSize;
        }
        return hash;
    }

    /**
     * Inserts an integer key into the hash map.
     *
     * @param key The integer key to be inserted.
     */
    public void insert(int key) {
        int hash = hashing(key);
        buckets[hash].insert(key);
    }

    /**
     * Deletes an integer key from the hash map.
     *
     * @param key The integer key to be deleted.
     */
    public void delete(int key) {
        int hash = hashing(key);
        buckets[hash].delete(key);
    }

    /**
     * Displays the contents of the hash map.
     */
    public void displayHashtable() {
        for (int i = 0; i < tableSize; i++) {
            System.out.printf("Bucket %d: %s%n", i, buckets[i].display());
        }
    }

    /**
     * Represents a linked list used for chaining in the hash map.
     */
    public static class LinkedList {

        private Node first;

        /**
         * Constructs an empty linked list.
         */
        public LinkedList() {
            first = null;
        }

        /**
         * Inserts an integer key into the linked list.
         *
         * @param key The integer key to be inserted.
         */
        public void insert(int key) {
            if (isEmpty()) {
                first = new Node(key);
            } else {
                Node temp = findEnd(first);
                temp.setNext(new Node(key));
            }
        }

        private Node findEnd(Node node) {
            while (node.getNext() != null) {
                node = node.getNext();
            }
            return node;
        }

        /**
         * Finds a specific integer key in the linked list.
         *
         * @param key The integer key to be found.
         * @return The Node containing the key, or null if not found.
         */
        public Node findKey(int key) {
            if (!isEmpty()) {
                Node temp = first;
                while (temp != null) {
                    if (temp.getKey() == key) {
                        return temp;
                    }
                    temp = temp.getNext();
                }
            }
            return null;
        }

        /**
         * Deletes a specific integer key from the linked list.
         *
         * @param key The integer key to be deleted.
         */
        public void delete(int key) {
            if (!isEmpty()) {
                if (first.getKey() == key) {
                    Node next = first.getNext();
                    first.setNext(null);
                    first = next;
                } else {
                    delete(first, key);
                }
            }
        }

        private void delete(Node currentNode, int key) {
            if (currentNode.getNext() == null) {
                return; // Key not found
            }
            if (currentNode.getNext().getKey() == key) {
                if (currentNode.getNext().getNext() == null) {
                    currentNode.setNext(null);
                } else {
                    currentNode.setNext(currentNode.getNext().getNext());
                }
            } else {
                delete(currentNode.getNext(), key);
            }
        }

        /**
         * Displays the contents of the linked list as a string.
         *
         * @return A string representation of the linked list.
         */
        public String display() {
            return display(first);
        }

        private String display(Node node) {
            if (node == null) {
                return "null";
            } else {
                return node.getKey() + "->" + display(node.getNext());
            }
        }

        /**
         * Checks if the linked list is empty.
         *
         * @return true if the linked list is empty, false otherwise.
         */
        public boolean isEmpty() {
            return first == null;
        }
    }

    /**
     * Represents a node in the linked list.
     */
    public static class Node {

        private Node next;
        private final int key;

        /**
         * Constructs a new Node with an integer key.
         *
         * @param key The integer key for the Node.
         */
        public Node(int key) {
            next = null;
            this.key = key;
        }

        /**
         * Gets the next Node in the linked list.
         *
         * @return The next Node in the linked list.
         */
        public Node getNext() {
            return next;
        }

        /**
         * Gets the integer key of the Node.
         *
         * @return The integer key of the Node.
         */
        public int getKey() {
            return key;
        }

        /**
         * Sets the next Node in the linked list.
         *
         * @param next The Node to set as the next Node.
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }
}
