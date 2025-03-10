package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.Objects;

/**
 * This class implements a hash table using Cuckoo Hashing.
 * Cuckoo hashing is a type of open-addressing hash table that resolves collisions
 * by relocating existing keys. It utilizes two hash functions to minimize collisions
 * and automatically resizes the table when the load factor exceeds 0.7.
 *
 * For more information on cuckoo hashing, refer to
 * <a href="https://en.wikipedia.org/wiki/Cuckoo_hashing">this Wikipedia page</a>.
 */
public class HashMapCuckooHashing {

    private int tableSize; // Size of the hash table
    private Integer[] buckets; // Array representing the hash table
    private final Integer emptySlot; // Placeholder for deleted slots
    private int size; // Number of elements in the hash table
    private int thresh; // Threshold for detecting infinite loops during insertion

    /**
     * Constructs a HashMapCuckooHashing object with the specified initial table size.
     *
     * @param tableSize the initial size of the hash map
     */
    public HashMapCuckooHashing(int tableSize) {
        this.buckets = new Integer[tableSize];
        this.tableSize = tableSize;
        this.emptySlot = Integer.MIN_VALUE;
        this.size = 0;
        this.thresh = (int) (Math.log(tableSize) / Math.log(2)) + 2;
    }

    /**
     * Computes the first hash index for a given key using the modulo operation.
     *
     * @param key the key for which the hash index is computed
     * @return an integer index corresponding to the key
     */
    public int hashFunction1(int key) {
        int hash = key % tableSize;
        if (hash < 0) {
            hash += tableSize;
        }
        return hash;
    }

    /**
     * Computes the second hash index for a given key using integer division.
     *
     * @param key the key for which the hash index is computed
     * @return an integer index corresponding to the key
     */
    public int hashFunction2(int key) {
        int hash = key / tableSize;
        hash %= tableSize;
        if (hash < 0) {
            hash += tableSize;
        }
        return hash;
    }

    /**
     * Inserts a key into the hash table using cuckoo hashing.
     * If the target bucket is occupied, it relocates the existing key and attempts to insert
     * it into its alternate location. If the insertion process exceeds the threshold,
     * the table is resized.
     *
     * @param key the key to be inserted into the hash table
     * @throws IllegalArgumentException if the key already exists in the table
     */
    public void insertKey2HashTable(int key) {
        Integer wrappedInt = key;
        Integer temp;
        int hash;
        int loopCounter = 0;

        if (isFull()) {
            System.out.println("Hash table is full, lengthening & rehashing table");
            reHashTableIncreasesTableSize();
        }

        if (checkTableContainsKey(key)) {
            throw new IllegalArgumentException("Key already exists; duplicates are not allowed.");
        }

        while (loopCounter <= thresh) {
            loopCounter++;
            hash = hashFunction1(key);

            if ((buckets[hash] == null) || Objects.equals(buckets[hash], emptySlot)) {
                buckets[hash] = wrappedInt;
                size++;
                checkLoadFactor();
                return;
            }

            temp = buckets[hash];
            buckets[hash] = wrappedInt;
            wrappedInt = temp;
            hash = hashFunction2(temp);
            if (Objects.equals(buckets[hash], emptySlot)) {
                buckets[hash] = wrappedInt;
                size++;
                checkLoadFactor();
                return;
            } else if (buckets[hash] == null) {
                buckets[hash] = wrappedInt;
                size++;
                checkLoadFactor();
                return;
            }

            temp = buckets[hash];
            buckets[hash] = wrappedInt;
            wrappedInt = temp;
        }
        System.out.println("Infinite loop occurred, lengthening & rehashing table");
        reHashTableIncreasesTableSize();
        insertKey2HashTable(key);
    }

    /**
     * Rehashes the current table to a new size (double the current size) and reinserts existing keys.
     */
    public void reHashTableIncreasesTableSize() {
        HashMapCuckooHashing newT = new HashMapCuckooHashing(tableSize * 2);
        for (int i = 0; i < tableSize; i++) {
            if (buckets[i] != null && !Objects.equals(buckets[i], emptySlot)) {
                newT.insertKey2HashTable(this.buckets[i]);
            }
        }
        this.tableSize *= 2;
        this.buckets = newT.buckets;
        this.thresh = (int) (Math.log(tableSize) / Math.log(2)) + 2;
    }

    /**
     * Deletes a key from the hash table, marking its position as available.
     *
     * @param key the key to be deleted from the hash table
     * @throws IllegalArgumentException if the table is empty or if the key is not found
     */
    public void deleteKeyFromHashTable(int key) {
        Integer wrappedInt = key;
        int hash = hashFunction1(key);
        if (isEmpty()) {
            throw new IllegalArgumentException("Table is empty, cannot delete.");
        }

        if (Objects.equals(buckets[hash], wrappedInt)) {
            buckets[hash] = emptySlot;
            size--;
            return;
        }

        hash = hashFunction2(key);
        if (Objects.equals(buckets[hash], wrappedInt)) {
            buckets[hash] = emptySlot;
            size--;
            return;
        }
        throw new IllegalArgumentException("Key " + key + " not found in the table.");
    }

    /**
     * Displays the hash table contents, bucket by bucket.
     */
    public void displayHashtable() {
        for (int i = 0; i < tableSize; i++) {
            if ((buckets[i] == null) || Objects.equals(buckets[i], emptySlot)) {
                System.out.println("Bucket " + i + ": Empty");
            } else {
                System.out.println("Bucket " + i + ": " + buckets[i].toString());
            }
        }
        System.out.println();
    }

    /**
     * Finds the index of a given key in the hash table.
     *
     * @param key the key to be found
     * @return the index where the key is located
     * @throws IllegalArgumentException if the table is empty or the key is not found
     */
    public int findKeyInTable(int key) {
        Integer wrappedInt = key;
        int hash = hashFunction1(key);

        if (isEmpty()) {
            throw new IllegalArgumentException("Table is empty; cannot find keys.");
        }

        if (Objects.equals(buckets[hash], wrappedInt)) {
            return hash;
        }

        hash = hashFunction2(key);
        if (!Objects.equals(buckets[hash], wrappedInt)) {
            throw new IllegalArgumentException("Key " + key + " not found in the table.");
        } else {
            return hash;
        }
    }

    /**
     * Checks if the given key is present in the hash table.
     *
     * @param key the key to be checked
     * @return true if the key exists, false otherwise
     */
    public boolean checkTableContainsKey(int key) {
        return ((buckets[hashFunction1(key)] != null && buckets[hashFunction1(key)].equals(key)) || (buckets[hashFunction2(key)] != null && buckets[hashFunction2(key)].equals(key)));
    }

    /**
     * Checks the load factor of the hash table. If the load factor exceeds 0.7,
     * the table is resized to prevent further collisions.
     *
     * @return the current load factor of the hash table
     */
    public double checkLoadFactor() {
        double factor = (double) size / tableSize;
        if (factor > .7) {
            System.out.printf("Load factor is %.2f, rehashing table.%n", factor);
            reHashTableIncreasesTableSize();
        }
        return factor;
    }

    /**
     * Checks if the hash map is full.
     *
     * @return true if the hash map is full, false otherwise
     */
    public boolean isFull() {
        for (int i = 0; i < tableSize; i++) {
            if (buckets[i] == null || Objects.equals(buckets[i], emptySlot)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the hash map is empty.
     *
     * @return true if the hash map is empty, false otherwise
     */
    public boolean isEmpty() {
        for (int i = 0; i < tableSize; i++) {
            if (buckets[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the current number of keys in the hash table.
     *
     * @return the number of keys present in the hash table
     */
    public int getNumberOfKeysInTable() {
        return size;
    }
}
